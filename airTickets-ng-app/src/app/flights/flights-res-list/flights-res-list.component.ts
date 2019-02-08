import { Flight } from './../../shared/model/aircompany/flight.model';
import { Component, OnInit, Input, HostBinding} from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { FlightsService } from 'src/app/shared/services/aircompany/flights.service';
import { Location } from '@angular/common';
import { Seat } from 'src/app/shared/model/aircompany/seat.model';
import { SeatService } from 'src/app/shared/services/aircompany/seat.service';

@Component({
  selector: 'app-flights-res-list',
  templateUrl: './flights-res-list.component.html',
  styleUrls: ['./flights-res-list.component.css']
})
export class FlightsResListComponent implements OnInit {


  @Input() passengers: number;
  @Input() selectedFlightsDep: {aircompanyId: number, id: number};
  @Input() selectedFlightsRet: {aircompanyId: number, id: number};

  flightReservation: boolean = false;

  seatSelForm: FormGroup;
  alreadySelected: boolean;

  // id-evi selektovanih sedista
  selectedSeatsDep: number[] = []; 
  selectedSeatsRet: number[] = [];

  seatsDeparture: Seat[];
  seatsReturn: Seat[];

  constructor(
    private formBuilder: FormBuilder,
    private flightService: FlightsService,
    private location: Location,
    private seatsService: SeatService
  ) { }

  ngOnInit() {
    this.seatSelForm = this.formBuilder.group({
      selectedSeat: [null, Validators.required]
    });

    this.alreadySelected = false;

    this.getSeats(0);
    this.getSeats(1);
  }

  getSeats(osmeh: number) {
    if(osmeh == 0) {

      this.seatsService.seatsByFlight(this.selectedFlightsDep.id).subscribe(
        data => this.seatsDeparture = data
      );
    } else {
      this.seatsService.seatsByFlight(this.selectedFlightsRet.id).subscribe(
        data => this.seatsReturn = data
      );
    }
  }

  isSelectedRet(seatId: number): boolean {
    console.log("isSelectedRet");
    return (this.selectedSeatsRet.includes(seatId)) ? true : false;
  }

  isSelectedDep(seatId: number): boolean {
    console.log("isSelectedRDep");
    return (this.selectedSeatsDep.includes(seatId)) ? true : false;
  }

  selectSeatRet(seatId: number, e) {
    console.log("selectSeatRet");
    console.dir("ID RER SEDISTA:" + seatId);
    if (e.target.checked) {
      if(this.selectedSeatsRet.length < this.passengers) {
        this.selectedSeatsRet.push(seatId);
        console.log('Rezervisana sedista Return:');
        console.log(this.selectedSeatsRet);
      }
    } else {
      let idx = this.selectedSeatsRet.indexOf(seatId);
      console.log('Da li je bio rezervisan vec?     ' + idx);
      if(idx != -1) {
        this.selectedSeatsRet.splice(idx, 1);
        console.log('Posle brisanja');
        console.log(this.selectedSeatsRet);
      }
    }
  }

  selectSeatDep(seatId: number, e) {
    console.log("selectSeatDep");
    console.dir("ID DEP SEDISTA:" + seatId);
    if (e.target.checked) {
      if(this.selectedSeatsDep.length < this.passengers) {
        this.selectedSeatsDep.push(seatId);
        console.log('Rezervisana sedista Departure:');
        console.log(this.selectedSeatsDep);
      }
    } else {
      let idx = this.selectedSeatsDep.indexOf(seatId);
      console.log('Da li je bio rezervisan vec?     ' + idx);
      if(idx != -1) {
        this.selectedSeatsDep.splice(idx, 1);
        console.log('Posle brisanja');
        console.log(this.selectedSeatsDep);
      }
    }
  }

  goBack() {
    this.flightReservation = true;
  }

  onSubmit() {
    this.seatSelForm.controls['selectedSeat'].enable();
  }

  // public selectSeat(value: string){
  //   if(this.selectedSeats.length < this.passengers) {
  //     this.selectedSeats.push(value);
  //   }
  //   if(this.selectedSeats.length == this.passengers) {
  //     this.seatSelForm.controls['selectedSeat'].disable();
  //     console.log(this.selectedSeats);
  //   }
  // }
}
