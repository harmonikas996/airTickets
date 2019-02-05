import { Aircompany } from './../../../shared/model/aircompany/aircompany.model';
import { AircompanyService } from './../../../shared/services/aircompany/aircompany.service';
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
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import * as moment from 'moment';

@Component({
  selector: 'app-flight-details',
  templateUrl: './flight-details.component.html',
  styleUrls: ['./flight-details.component.css']
})
export class FlightDetailsComponent implements OnInit {

  public dateTimeRange: Date[];

  public myDatePickerOptions: IMyDpOptions = {

    dateFormat: 'yyyy-mm-dd'
  };

  flight: Observable<Flight>;
  flightModel: Flight;
  flightDetailsForm: FormGroup;
  airportsFrom: Airport[];
  airportsTo: Airport[];
  types = [];
  flightObj: Flight = new Flight();
  aircompany: Aircompany;

  constructor(
    private flightService: FlightsService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder,
    private airportService: AirportService,
    private aircompanyService: AircompanyService,
    private token: TokenStorageService
  ) { }

  ngOnInit() {

    this.flightDetailsForm = this.formBuilder.group({
      id: [''],
      datePeriod: ['', Validators.required],
      distance: ['', Validators.required],
      price: ['', Validators.required],
      airplaneType: ['', Validators.required],
      loweredPrice: ['', Validators.required],
      placeFromId: ['', Validators.required],
      placeToId: ['', Validators.required]
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

  onSubmit() {
    if (this.flightDetailsForm.valid) {

      this.getAircompanyById();
    }
  }

  prepareData() {

    this.flightObj.id = this.flightDetailsForm.controls['id'].value;
    this.flightObj.aircompanyId = this.aircompany.id;
    this.flightObj.distance = Number(this.flightDetailsForm.controls['distance'].value);
    this.flightObj.timeBegin = moment(this.flightDetailsForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.flightObj.timeEnd = moment(this.flightDetailsForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.flightObj.price = Number(this.flightDetailsForm.controls['price'].value);
    this.flightObj.airplaneType =  this.flightDetailsForm.controls['airplaneType'].value;
    this.flightObj.loweredPrice = Number(this.flightDetailsForm.controls['loweredPrice'].value);
    this.flightObj.placeFromId =  this.flightDetailsForm.controls['placeFromId'].value;
    this.flightObj.placeToId =  this.flightDetailsForm.controls['placeToId'].value;

    console.log('Saljem: ', this.flightObj);

    this.flightService.updateFlight(this.flightObj).subscribe((response) => {
      console.log('Response is: ', response);
      this.location.back();
   },
   (error) => {
      // catch the error
      console.error('An error occurred, ', error);
   });
   }

  getAircompanyById(): void {

    this.aircompanyService.getAircompanyByAdminUsername(this.token.getUsername()).subscribe(

    (response) => this.aircompany = response,
    (error) => console.error("An error occurred, ", error),
    () => this.prepareData()

    );

  }


  onCancel() {
    this.location.back();
  }

  onRemove(flight1: Flight) {
    this.flightService.removeFlight(flight1.id).subscribe(flight1 => this.flightModel = flight1);
    this.location.back();
  }

}
