import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Airport } from 'src/app/shared/model/aircompany/airport.model';
import { Flight } from 'src/app/shared/model/aircompany/flight.model';
import { FlightsService } from 'src/app/shared/services/aircompany/flights.service';
import { AirportService } from 'src/app/shared/services/aircompany/airport.service';
import { AircompanyService } from 'src/app/shared/services/aircompany/aircompany.service';
import * as moment from 'moment';
import { SeatService } from 'src/app/shared/services/aircompany/seat.service';
import { UserService } from './../../../shared/services/user/user.service';
import { Seat } from 'src/app/shared/model/aircompany/seat.model';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';

@Component({
  selector: 'app-quick-res-aircompany',
  templateUrl: './quick-res-aircompany.component.html',
  styleUrls: ['./quick-res-aircompany.component.css']
})
export class QuickResAircompanyComponent implements OnInit {

  seatSelection: boolean = false;
  searchFlight: boolean = true;

  flightOptionForm: FormGroup;
  flightResForm: FormGroup;
  quickReservationForm: FormGroup;
  airports: Airport[];
  flightObj: Flight = new Flight();
  flights: Flight[];
  returnFlights: Flight[];

  aircompanies = [];

  sDep: number;
  selectedFlightsDep: {aircompanyId: number, id: number}[];
  passengers: number;

  placeFromId: any;
  placeToId: number;
  timeBegin: String;
  timeReturn: String;
  nameFlightFrom: String;
  nameFlightTo: String;
  cdateFrom: String;
  cdateTo: String;
  todayDate = new Date(moment().subtract(1, 'days').toLocaleString());

  seatsDeparture = [];
  selectedSeatDep = 0;

  constructor(
    private flightService: FlightsService,
    private formBuilder: FormBuilder,
    private airportService: AirportService,
    private aircompanyService: AircompanyService,
    private seatsService: SeatService,
    private userService: UserService

  ) { }

  searchFlights(placeFromId: Number, placeToId: Number, timeBegin: String) {

    this.flights = [];
    this.returnFlights = [];

    this.userService.getUserById().subscribe(
      response => {
        this.flightService.searchFlightsByCompany(placeFromId, placeToId, timeBegin, response.company).subscribe(
          flights => {
            // show flight only if it's 'timeBegin' field is 3hrs behind current time
            this.flights = flights.filter(flight => (moment(flight.timeBegin).isAfter(moment().add(3, 'hours'))));
          },
         (error) => console.error('An error occurred, ', error)
        );
      }
    );
  }

  ngOnInit() {

    this.quickReservationForm = this.formBuilder.group({
      price: ['', Validators.required]
    });

    this.flightOptionForm = this.formBuilder.group({
      val: ['one', Validators.required]
    });

    this.flightResForm = this.formBuilder.group({
      placeFromId: [null, Validators.required],
      placeToId: [null, Validators.required],
      datePeriod: [null, Validators.required],
      passengers: [1]
    });

    this.getAirPorts();
    this.getAirCompanies();
    this.selectedFlightsDep = [];
    this.sDep = 0;
    this.seatSelection = false;

  }

  getAirCompanies() {
    this.aircompanyService.getAircompanies().subscribe(
      response => this.aircompanies = response
    );
  }

  goToSeatSelection() {
    // this.sDep.aircompanyId = this.selectedFlightsDep[0].aircompanyId;
    this.sDep = this.selectedFlightsDep[0].id;
    // this.sRet.aircompanyId = this.selectedFlightsRet[0].aircompanyId;
    this.passengers = this.flightResForm.controls['passengers'].value;
    this.seatSelection = true;
    this.getSeats();
    // this.searchFlight = true;
  }

  selectFlightDep(aircompanyId: number, flightId: number, e) {

    let data: {aircompanyId: number, id: number} = {aircompanyId: 0, id: 0};
    data.aircompanyId = aircompanyId;
    data.id = flightId;

    if (e.target.checked) {
      if(this.selectedFlightsDep.length < 1) {
        this.selectedFlightsDep.push(data);
        this.goToSeatSelection();
      }
    } else {
      let jel = this.getSelectedIndexDep(aircompanyId, flightId);
      if (jel !== -1) {

        this.selectedFlightsDep.splice(jel, 1);

        this.sDep = 0;
        // this.sRet.aircompanyId = this.selectedFlightsRet[0].aircompanyId;
        this.passengers = 0;
        this.seatSelection = false;
        this.seatsDeparture = [];
        this.selectedSeatDep = 0;
        this.quickReservationForm.controls.price.setValue('');
      }
    }
  }

  getSelectedIndexDep(aircompanyId: number, flightId: number): number {
    for(let i=0; i<this.selectedFlightsDep.length; i++) {
      let data: {aircompanyId: number, id: number} = this.selectedFlightsDep[i];
      if (data.aircompanyId === aircompanyId && data.id === flightId) {
        return i;
      }
    }
    return -1;
  }

  isSelectedDep(aircompanyId: number, flightId: number): boolean {
    for(let i=0; i<this.selectedFlightsDep.length; i++) {
      let data: {aircompanyId: number, id: number} = this.selectedFlightsDep[i];
      if(data.aircompanyId == aircompanyId && data.id == flightId) {
        return true;
      }
    }

    return false;
  }

  getAirPorts(): void {
      this.airportService.getAirports().subscribe(airport => this.airports = airport);
  }

  getAirportsById(): void {
    let a: Airport;

    
    
    this.airportService.getAirportById(this.placeFromId).subscribe(
      airport => a = airport,
      error => console.log('Error: ', error),
      () => this.nameFlightFrom = a.city
    );

    if(this.flightResForm.controls['datePeriod'].value.length > 1) {
      
      this.airportService.getAirportById(this.placeToId).subscribe(
        airport => a = airport,
        error => console.log('Error: ', error),
        () => this.nameFlightTo = a.city
      );
    }
  }

  onSubmit() {
    if (this.flightResForm.valid) {
      this.prepareData();
      this.getAirportsById();
      this.searchFlights(this.placeFromId, this.placeToId, this.timeBegin);
      this.selectedFlightsDep = [];
    }
  }

  prepareData() {

    if (this.flightResForm.controls['placeFromId'].value) {

      this.placeFromId = this.flightResForm.controls['placeFromId'].value;
    } else {
      this.placeFromId = -1;
    }

    if (this.flightResForm.controls['placeToId'].value) {

      this.placeToId = this.flightResForm.controls['placeToId'].value;
    } else {
      this.placeToId = -1;
    }

    if (this.flightResForm.controls['datePeriod'].value.length < 2) {
      this.timeBegin = moment(this.flightResForm.controls['datePeriod'].value).format('YYYY-MM-DDTHH:mm:ss.SSS');
      // this.timeBegin = this.flightResForm.controls['datePeriod'].value;
    } else {
      this.timeBegin = moment(this.flightResForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
      this.timeReturn = moment(this.flightResForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');
      // this.timeBegin = this.flightResForm.controls['datePeriod'].value[0];
      // this.timeReturn = this.flightResForm.controls['datePeriod'].value[1];
    }
    this.passengers = this.flightResForm.controls['passengers'].value;
  }


  clearDate(): void {
      // Clear the date using the patchValue function
      this.flightResForm.patchValue({myDate: null});
  }

  getSeats() {
      this.seatsService.seatsByFlight(this.sDep).subscribe(
        data => this.seatsDeparture = data
      );
  }

  selectSeatDep(seatId: number, e) {
    if (e.target.checked) {
      if (this.selectedSeatDep === 0) {
        this.selectedSeatDep = seatId;
      }
    } else {
      if (this.selectedSeatDep !== 0) {
        this.selectedSeatDep = 0;
      }
    }
  }

  isSeatSelected(seatId: number): boolean {
    return (this.selectedSeatDep === seatId) ? true : false;
  }

  createQuickReservation() {

    const seat: Seat = new Seat();
    console.log(this.quickReservationForm.controls.price.value + ' cena')
    seat.price = +this.quickReservationForm.controls.price.value;
    seat.id = this.selectedSeatDep;
    seat.flightId = this.selectedFlightsDep[0].id;
    seat.firstName = null;
    seat.lastName = null;
    seat.passport = null;
    seat.contact = null;
    console.log([seat]);
    this.userService.getUserById().subscribe(
      user => {
        seat.clientId = user.id;
        this.seatsService.makeReservation([seat]).subscribe(
          response => { 
            location.reload();
          }
        );
      }
    );

    
  }
}

