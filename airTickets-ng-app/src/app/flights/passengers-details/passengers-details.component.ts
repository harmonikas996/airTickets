import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Seat } from 'src/app/shared/model/aircompany/seat.model';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { UserService } from 'src/app/shared/services/user/user.service';
import { SeatService } from 'src/app/shared/services/aircompany/seat.service';

@Component({
  selector: 'app-passengers-details',
  templateUrl: './passengers-details.component.html',
  styleUrls: ['./passengers-details.component.css']
})
export class PassengersDetailsComponent implements OnInit {

  @Input() flightStart: string;
  @Input() passengers: number;
  @Input() flightDepartureId: number;
  @Input() flightReturnId: number;
  @Input() selectedSeatsDep: {id: number, version: number}[] = []; 
  @Input() selectedSeatsRet: {id: number, version: number}[] = [];
  @Input() oneWay: boolean;

  flightReservationSuccess: boolean = false;

  passengersForm: FormGroup;
  passengersFormRet: FormGroup;
  items: FormArray;
  clientId: number;
  reservationId: number;
  errorMessage = '';

  constructor(
    private formBuilder: FormBuilder, 
    private token: TokenStorageService, 
    private userService: UserService,
    private seatService: SeatService
  ) {}

  ngOnInit() {
    this.passengersForm = this.formBuilder.group({
      flightId: '',
      items: this.formBuilder.array([ this.createItem(this.selectedSeatsDep[0]) ])
    });

    if(!this.oneWay) {

      this.passengersFormRet = this.formBuilder.group({
        flightId: '',
        items: this.formBuilder.array([ this.createItem(this.selectedSeatsRet[0]) ])
      });
    }

    this.createForm(this.passengers);
    this.getUser();
  }

  getUser() {
    this.userService.getUserByEmail(this.token.getUsername()).subscribe( data => this.clientId = data.id );
  }

  createItem(seat: {id: number, version: number}): FormGroup {
    return this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      passport: ['', Validators.required],
      contact: ['', Validators.required],
      seat: seat.id,
      version: seat.version
    });
  }

  createForm(val: number) {

    for(let i=0;i<val;i++) {
      this.addItem(this.selectedSeatsDep[i], i);
      if(!this.oneWay) {
      this.addItemRet(this.selectedSeatsRet[i], i);
      }
    }
  }

  addItem(seat: {id: number, version: number}, idx: number): void {
    this.items = this.passengersForm.get('items') as FormArray;
    if(idx==0)
      this.items.removeAt(0);
    this.items.push(this.createItem(seat));
  }

  addItemRet(seat: {id: number, version: number}, idx: number): void {
    this.items = this.passengersFormRet.get('items') as FormArray;
    if(idx==0)
      this.items.removeAt(0);
    this.items.push(this.createItem(seat));
  }

  onSubmit() {
    window.sessionStorage.setItem('flightStart', this.flightStart);
    let seats: Seat[] = [];

    for(let i=0; i<this.passengers; i++) {
      let sedisteDep: Seat = new Seat();
      
      sedisteDep.firstName = this.passengersForm.controls['items'].value[i].firstName;
      sedisteDep.lastName = this.passengersForm.controls['items'].value[i].lastName;
      sedisteDep.contact = this.passengersForm.controls['items'].value[i].contact;
      sedisteDep.passport = this.passengersForm.controls['items'].value[i].passport;
      sedisteDep.flightId = this.flightDepartureId;
      sedisteDep.id = this.passengersForm.controls['items'].value[i].seat;
      sedisteDep.clientId = this.clientId;
      sedisteDep.version = this.passengersForm.controls['items'].value[i].version;
      seats.push(sedisteDep);
      
      if(!this.oneWay) {
        let sedisteRet: Seat = new Seat();
        sedisteRet.firstName = this.passengersFormRet.controls['items'].value[i].firstName;
        sedisteRet.lastName = this.passengersFormRet.controls['items'].value[i].lastName;
        sedisteRet.contact = this.passengersFormRet.controls['items'].value[i].contact;
        sedisteRet.passport = this.passengersFormRet.controls['items'].value[i].passport;
        sedisteRet.flightId = this.flightReturnId;
        sedisteRet.id = this.passengersFormRet.controls['items'].value[i].seat;
        sedisteRet.clientId = this.clientId;
        sedisteRet.version = this.passengersFormRet.controls['items'].value[i].version;
        seats.push(sedisteRet);
      }

    }

    this.seatService.makeReservation(seats).subscribe(
      reservation => this.reservationId = reservation.id,
      (error) => {console.error('An error occurred, ', error); this.errorMessage = error.error.message;},
      () => {
        let key = 'reservationId';
        window.sessionStorage.setItem(key, this.reservationId.toString());
        this.flightReservationSuccess = true;
      }
     );
  }

}
