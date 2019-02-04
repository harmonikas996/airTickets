import { AirportService } from './../../shared/services/aircompany/airport.service';
import { Airport } from './../../shared/model/aircompany/airport.model';
import { FlightReservationService } from './../../shared/services/aircompany/flight-reservation.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { IMyDpOptions } from 'mydatepicker';

@Component({
  selector: 'app-flights-reservation',
  templateUrl: './flights-reservation.component.html',
  styleUrls: ['./flights-reservation.component.css']
})
export class FlightsReservationComponent implements OnInit {

  public myDatePickerOptions: IMyDpOptions = {
    // other options...
    //dateFormat: 'dd.mm.yyyy',
    dateFormat: 'yyyy-mm-dd'
  };

  flightResForm: FormGroup;
  airports: Airport[];

  constructor(
    private flightResService: FlightReservationService,
    private formBuilder: FormBuilder,
    private airportService: AirportService
  ) { }

  ngOnInit() {

    this.flightResForm = this.formBuilder.group({
      placeFromId: [null, Validators.required],
      placeToId: [null, Validators.required],
      timeBegin: [null, Validators.required],
      timeEnd: [null, Validators.required]
    });

    this.getAirPorts();
  }

  getAirPorts(): void {
      this.airportService.getAirports().subscribe(airport => this.airports = airport);
  }

  onSubmit() {

  }

  setDate(): void {
    // Set today date using the patchValue function
    let date = new Date();
    this.flightResForm.patchValue({myDate: {
    date: {
        year: date.getFullYear(),
        month: date.getMonth() + 1,
        day: date.getDate()}
    }});
  }

  clearDate(): void {
      // Clear the date using the patchValue function
      this.flightResForm.patchValue({myDate: null});
  }

}
