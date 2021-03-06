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

  @Input() flightStart: string;
  @Input() passengers: number;
  @Input() selectedFlightsDep: number;
  @Input() selectedFlightsRet: number;
  @Input() oneWay: boolean;

  // @Input() flightDepartureId: number;
  // @Input() flightReturnId: number;

  flightReservation: boolean = false;
  passengersDetails: boolean = false;

  seatSelForm: FormGroup;
  alreadySelected: boolean;

  // id-evi selektovanih sedista
  selectedSeatsDep: {id: number, version: number}[] = [];
  selectedSeatsRet: {id: number, version: number}[] = [];

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

    if(!this.oneWay)
      this.getSeats(1);
  }

  getSeats(osmeh: number) {
    if(osmeh == 0) {

      this.seatsService.seatsByFlight(this.selectedFlightsDep).subscribe(
        data => this.seatsDeparture = data
      );
    } else {
      this.seatsService.seatsByFlight(this.selectedFlightsRet).subscribe(
        data => this.seatsReturn = data
      );
    }
  }

  isSelectedRet(seatId: number): boolean {
    // console.log("isSelectedRet");
    return (this.selectedSeatsRet.find(seat => seat.id === seatId)) !== undefined ? true : false;
  }

  isSelectedDep(seatId: number): boolean {
    const found = this.selectedSeatsDep.find(seat => seat.id === seatId);
    return (found !== undefined && found.id === seatId) ? true : false;
  }

  selectSeatRet(id: number, version: number, e) {
    // console.log("selectSeatRet");
    // console.dir("ID RER SEDISTA:" + seatId);
    if (e.target.checked) {
      if(this.selectedSeatsRet.length < this.passengers) {
        this.selectedSeatsRet.push({id, version});
        // console.log('Rezervisana sedista Return:');
        // console.log(this.selectedSeatsRet);
      }
    } else {
      // let idx = this.selectedSeatsRet.indexOf(seat);
      let idx = this.selectedSeatsRet.find(s => s.id === id);
      // console.log('Da li je bio rezervisan vec?     ' + idx);
      if(idx !== undefined) {
        this.selectedSeatsRet = this.selectedSeatsRet.filter(s => s.id !== idx.id);
        // console.log('Posle brisanja');
        // console.log(this.selectedSeatsRet);
      }
    }
  }

  selectSeatDep(id: number, version: number, e) {
    // console.log("selectSeatDep");
    // console.dir("ID DEP SEDISTA:" + seatId);
    if (e.target.checked) {
      if(this.selectedSeatsDep.length < this.passengers) {
        this.selectedSeatsDep.push({id, version});
        console.log('Rezervisana sedista Departure:');
        console.log(this.selectedSeatsDep);
      }
    } else {
      // let idx = this.selectedSeatsDep.indexOf(seatId);
      // console.log('USAO');
      // console.log('USAO: ' + id + ' v: ' + version);
      // console.log(this.selectedSeatsRet);
      let idx = this.selectedSeatsDep.find(s => s.id === id);
      // console.log('Da li je bio rezervisan vec?     ' + idx);
      if (idx !== undefined) {
        // this.selectedSeatsDep.splice(idx, 1);
        this.selectedSeatsDep = this.selectedSeatsDep.filter(s => s.id !== idx.id);
        console.log('Posle brisanja');
        console.log(this.selectedSeatsDep);
      }
    }
  }

  goToPassengersDetails() {
    this.passengersDetails = true;
  }
}
