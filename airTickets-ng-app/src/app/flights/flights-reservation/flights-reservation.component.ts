import { FlightRatingService } from './../../shared/services/aircompany/flight-rating.service';
import { AirCompanyRating } from './../../shared/model/aircompany/aircompany-rating';
import { Aircompany } from './../../shared/model/aircompany/aircompany.model';
import { AircompanyService } from './../../shared/services/aircompany/aircompany.service';
import { FlightsService } from './../../shared/services/aircompany/flights.service';
import { Flight } from './../../shared/model/aircompany/flight.model';
import { AirportService } from './../../shared/services/aircompany/airport.service';
import { Airport } from './../../shared/model/aircompany/airport.model';
import { FlightReservationService } from './../../shared/services/aircompany/flight-reservation.service';
import { Component, OnInit, Input, HostListener } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import * as moment from 'moment';
import { Event } from '@angular/router';
import { FlightsResListComponent } from '../flights-res-list/flights-res-list.component';
import { windowWhen } from 'rxjs/operators';

@Component({
  selector: 'app-flights-reservation',
  templateUrl: './flights-reservation.component.html',
  styleUrls: ['./flights-reservation.component.css']
})
export class FlightsReservationComponent implements OnInit {

  tekst: String = 'NOVI NASLOV';
  seatSelection: boolean = false;
  searchFlight: boolean = true;

  flightOptionForm: FormGroup;
  flightResForm: FormGroup;
  airports: Airport[];
  flightObj: Flight = new Flight();
  flights: Flight[];
  returnFlights: Flight[];

  aircompanies: Aircompany[];

  sDep: number;
  sRet: number;
  selectedFlightsDep: {aircompanyId: number, id: number}[];
  selectedFlightsRet: {aircompanyId: number, id: number}[];
  passengers: number;

  placeFromId: any;
  placeToId: number;
  timeBegin: String;
  timeReturn: String;
  nameFlightFrom: String;
  nameFlightTo: String;
  cdateFrom: String;
  cdateTo: String;

  airCompanyRating = [];

  constructor(
    private flightService: FlightsService,
    private formBuilder: FormBuilder,
    private airportService: AirportService,
    private aircompanyService: AircompanyService,
    private flightRatingService: FlightRatingService

  ) { }

  searchFlights(placeFromId: Number, placeToId: Number, timeBegin: String) {

    this.flights = [];
    this.returnFlights = [];

    this.flightService.searchFlights(placeFromId, placeToId, timeBegin).subscribe(
      flights => {
        this.flights = flights;
      },
     (error) => console.error('An error occurred, ', error),
     () => {
       if(this.flightResForm.controls['datePeriod'].value.length > 1) {
         this.searchReturnFlights(this.placeToId, this.placeFromId, this.timeReturn)
       }
    }
    );
  }

  searchReturnFlights(placeFromId: Number, placeToId: Number, timeBegin: String) {
    this.flightService.searchFlights(placeFromId, placeToId, timeBegin).subscribe(
      flights => this.returnFlights = flights
    );
  }

  ngOnInit() {

    this.flightOptionForm = this.formBuilder.group({
      val: ['round', Validators.required]
    });

    this.flightResForm = this.formBuilder.group({
      placeFromId: [null, Validators.required],
      placeToId: [null, Validators.required],
      datePeriod: [null, Validators.required],
      passengers: [null, Validators.required]
    });

    this.getAirPorts();
    this.getAirCompanies();
    this.selectedFlightsDep = [];
    this.selectedFlightsRet = [];
    this.sDep = 0;
    this.sRet = 0;

  }

  getAirCompanies() {
    this.aircompanyService.getAircompanies().subscribe(
      response =>  {
        this.aircompanies = response;

        for(let a of this.aircompanies) {

          this.flightRatingService.getRatingsByAirCompany(a.id).subscribe(rating => this.airCompanyRating[a.id] = rating);

        }
      }
    );
  }

  goToSeatSelection() {
    // this.sDep.aircompanyId = this.selectedFlightsDep[0].aircompanyId;
    this.sDep = this.selectedFlightsDep[0].id;
    // this.sRet.aircompanyId = this.selectedFlightsRet[0].aircompanyId;
    if(this.flightOptionForm.controls['val'].value == 'round') {

      this.sRet = this.selectedFlightsRet[0].id;
    }
    this.passengers = this.flightResForm.controls['passengers'].value;
    console.log(this.passengers);
    this.seatSelection = true;
    this.searchFlight = false;
  }

  selectSeatDep(aircompanyId: number, flightId: number, e) {

    let data: {aircompanyId: number, id: number} = {aircompanyId: 0, id: 0};
    data.aircompanyId = aircompanyId;
    data.id = flightId;

    if (e.target.checked) {
      if(this.selectedFlightsDep.length < 1) {
        this.selectedFlightsDep.push(data);
        console.log('Rezervisana sedista Departure:');
        console.log(this.selectedFlightsDep);
      }
    } else {
      let jel = this.getSelectedIndexDep(aircompanyId, flightId);
      console.log('Da li je bio rezervisan vec?     ' + jel);
      if(jel != -1) {

        this.selectedFlightsDep.splice(jel, 1);

        console.log('Posle brisanja');
        console.log(this.selectedFlightsDep);
      }
    }
  }

  selectSeatRet(aircompanyId: number, flightId: number, e) {
    console.log("selectSeatRet");
    let data: {aircompanyId: number, id: number} = {aircompanyId: 0, id: 0};
    data.aircompanyId = aircompanyId;
    data.id = flightId;

    if (e.target.checked) {
      if(this.selectedFlightsRet.length < 1) {
        this.selectedFlightsRet.push(data);
        console.log('Rezervisana sedista Departure:');
        console.log(this.selectedFlightsRet);
      }
    } else {
      let jel = this.getSelectedIndexRet(aircompanyId, flightId);
      console.log('Da li je bio rezervisan vec?     ' + jel);
      if(jel != -1) {

        this.selectedFlightsRet.splice(jel, 1);

        console.log('Posle brisanja');
        console.log(this.selectedFlightsRet);
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

  getSelectedIndexRet(aircompanyId: number, flightId: number): number {
    console.log("getSelectedIndexRet");
    for(let i=0; i<this.selectedFlightsRet.length; i++) {
      let data: {aircompanyId: number, id: number} = this.selectedFlightsRet[i];
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

  isSelectedRet(aircompanyId: number, flightId: number): boolean {
    console.log("isSelectedRet");
    for(let i=0; i<this.selectedFlightsRet.length; i++) {
      let data: {aircompanyId: number, id: number} = this.selectedFlightsRet[i];
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
      this.selectedFlightsRet = [];
    }
  }

  prepareData() {

    if (this.flightResForm.controls['placeFromId'].value) {

      this.placeFromId = this.flightResForm.controls['placeFromId'].value;
    } else {
      this.placeFromId = -1
    }

    if (this.flightResForm.controls['placeToId'].value) {

      this.placeToId = this.flightResForm.controls['placeToId'].value;
    } else {
      this.placeToId = -1
    }

    console.log(this.flightResForm);

    if(this.flightResForm.controls['datePeriod'].value.length < 2) {
      this.timeBegin = moment(this.flightResForm.controls['datePeriod'].value).format('YYYY-MM-DDTHH:mm:ss.SSS');
    }
    else {
      this.timeBegin = moment(this.flightResForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
      this.timeReturn = moment(this.flightResForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    }
    this.passengers = this.flightResForm.controls['passengers'].value;
  }


  clearDate(): void {
      // Clear the date using the patchValue function
      this.flightResForm.patchValue({myDate: null});
  }

}
