import { Aircompany } from './../../shared/model/aircompany/aircompany.model';
import { AircompanyService } from './../../shared/services/aircompany/aircompany.service';
import { FlightsService } from './../../shared/services/aircompany/flights.service';
import { Flight } from './../../shared/model/aircompany/flight.model';
import { AirportService } from './../../shared/services/aircompany/airport.service';
import { Airport } from './../../shared/model/aircompany/airport.model';
import { FlightReservationService } from './../../shared/services/aircompany/flight-reservation.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import * as moment from 'moment';

@Component({
  selector: 'app-flights-reservation',
  templateUrl: './flights-reservation.component.html',
  styleUrls: ['./flights-reservation.component.css']
})
export class FlightsReservationComponent implements OnInit {

  flightOptionForm: FormGroup;
  flightResForm: FormGroup;
  airports: Airport[];
  flightObj: Flight = new Flight();
  flights: Flight[];
  returnFlights: Flight[];

  placeFromId: any;
  placeToId: number;
  timeBegin: String;
  timeReturn: String;
  nameFlightFrom: String;
  nameFlightTo: String;
  cdateFrom: String;
  cdateTo: String;

  constructor(
    private flightService: FlightsService,
    private formBuilder: FormBuilder,
    private airportService: AirportService,
    private aircompanyService: AircompanyService

  ) { }

  searchFlights(placeFromId: Number, placeToId: Number, timeBegin: String) {

    this.flightService.searchFlights(placeFromId, placeToId, timeBegin).subscribe(
      flights => this.flights = flights,
     (error) => console.error('An error occurred, ', error),
     () => this.searchReturnFlights(this.placeToId, this.placeFromId, this.timeReturn)
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
      datePeriod: [null, Validators.required]
    });

    this.getAirPorts();

  }

  // getCompanyName(): void {
  //   for (let f of this.flights) {

  //     let c: Aircompany;

  //     this.aircompanyService.getAircompanyById(f.aircompanyId).subscribe(
  //       aircompany => c = aircompany,
  //       error => console.log('Error: ', error),
  //     () => f.aircompanyId = c.name
  //     );
  //   }
  // }


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

    this.airportService.getAirportById(this.placeToId).subscribe(
      airport => a = airport,
      error => console.log('Error: ', error),
      () => this.nameFlightTo = a.city
    );
  }

  onSubmit() {
    if (this.flightResForm.valid) {
      this.prepareData();
      this.getAirportsById();

      this.searchFlights(this.placeFromId, this.placeToId, this.timeBegin);
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
    this.timeBegin = moment(this.flightResForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.timeReturn = moment(this.flightResForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');
  }


  clearDate(): void {
      // Clear the date using the patchValue function
      this.flightResForm.patchValue({myDate: null});
  }

}
