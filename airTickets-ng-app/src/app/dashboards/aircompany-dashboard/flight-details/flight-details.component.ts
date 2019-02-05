import { Airport } from './../../../shared/model/aircompany/airport.model';
import { AirportService } from './../../../shared/services/aircompany/airport.service';
import { Flight } from './../../../shared/model/aircompany/flight.model';
import { Observable } from 'rxjs';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { FlightsService } from './../../../shared/services/aircompany/flights.service';
import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs/operators';
import { Location } from '@angular/common';
import { IMyDpOptions } from 'mydatepicker';

@Component({
  selector: 'app-flight-details',
  templateUrl: './flight-details.component.html',
  styleUrls: ['./flight-details.component.css']
})
export class FlightDetailsComponent implements OnInit {

  public myDatePickerOptions: IMyDpOptions = {

    dateFormat: 'yyyy-mm-dd'
  };

  flight: Observable<Flight>;
  flightModel: Flight;
  flightDetailsForm: FormGroup;
  airportsFrom: Airport[];
  airportsTo: Airport[];
  types = [];

  constructor(
    private flightService: FlightsService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder,
    private airportService: AirportService
  ) { }

  ngOnInit() {

    this.flightDetailsForm = this.formBuilder.group({
      id: [''],
      timeBegin: ['', Validators.required],
      timeEnd: ['', Validators.required],
      distance: ['', Validators.required],
      price: ['', Validators.required],
      airplaneType: ['', Validators.required],
      loweredPrice: ['', Validators.required],
      placeFromId: ['', Validators.required],
      placeToId: ['', Validators.required],
      aircompanyId: ['', Validators.required]
    });

    this.types = [ 'AirbusA320', 'Boeing747', 'Boeing777'];
    const id = +this.route.snapshot.paramMap.get('id');
    this.getFlightById(id);
    this.getAirPortsFrom();
    this.getAirPortsTO();

  }

  getAirPortsFrom(): void {
    this.airportService.getAirports().subscribe(airport => this.airportsFrom = airport);

  }

  getAirPortsTO(): void {
    this.airportService.getAirports().subscribe(airport => this.airportsTo = airport);

  }

  getFlightById(id: number): void {
    this.flight = this.flightService.getFlightById(id).pipe(
      tap(flight => this.flightDetailsForm.patchValue(flight))
    );
  }

  onSubmit(){
    if (this.flightDetailsForm.valid) {
      this.flightService.updateFlight(this.flightDetailsForm.value).subscribe((response) => {
        console.log('Response is: ', response);
        this.location.back();
     },
     (error) => {
        // catch the error
        console.error('An error occurred, ', error);
     });
     }
  }

  onCancel() {
    this.location.back();
  }

  onRemove(flight1: Flight){
    this.flightService.removeFlight(flight1.id).subscribe(flight1 => this.flightModel = flight1);
    this.location.back();
  }

}
