import { AircompanyService } from './../../../shared/services/aircompany/aircompany.service';
import { Aircompany } from './../../../shared/model/aircompany/aircompany.model';
import { AirportService } from './../../../shared/services/aircompany/airport.service';
import { Airport } from './../../../shared/model/aircompany/airport.model';
import { FlightsService } from './../../../shared/services/aircompany/flights.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Flight } from './../../../shared/model/aircompany/flight.model';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { IMyDpOptions } from 'mydatepicker';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';

@Component({
  selector: 'app-flight-new',
  templateUrl: './flight-new.component.html',
  styleUrls: ['./flight-new.component.css']
})
export class FlightNewComponent implements OnInit {

  public dateTimeRange: Date[];

  // public myDatePickerOptions: IMyDpOptions = {

  //   dateFormat: 'yyyy-mm-dd, HH:mm:ss'
  // };

  flight: Observable<Flight>;
  newFlightForm: FormGroup;
  types = [];
  airportsFrom: Airport[];
  airportsTo: Airport[];
  flightObj: Flight;
  timePr: String;
  nastavak: String;
  aircompany: Aircompany;

  constructor(
    private flightsService: FlightsService,
    private formBuilder: FormBuilder,
    private location: Location,
    private airportService: AirportService,
    private aircompanyService: AircompanyService,
    private token: TokenStorageService
  ) { }

  ngOnInit() {


    this.newFlightForm = this.formBuilder.group({
      aircompanyId: [''],
      timeBegin: ['', Validators.required],
      timeEnd: ['', Validators.required],
      distance: ['', Validators.required],
      price: ['', Validators.required],
      airplaneType: ['', Validators.required],
      loweredPrice: ['', Validators.required],
      placeFromId: ['', Validators.required],
      placeToId: ['', Validators.required]
    });

    this.setDate();
    this.types = [ 'AirbusA320', 'Boeing747', 'Boeing777'];
    this.getAirPortsFrom();
    this.getAirPortsTO();

  }

  setDate(): void {
    let date = new Date();
    this.newFlightForm.patchValue({myDate: {
    date: {
        year: date.getFullYear(),
        month: date.getMonth() + 1,
        day: date.getDate(),
        hours: date.getHours(),
        minutes: date.getMinutes(),
        seconds: date.getSeconds(),
      }
    }});
  }

  getAirPortsFrom(): void {
    this.airportService.getAirports().subscribe(airport => this.airportsFrom = airport);

  }

  getAirPortsTO(): void {
    this.airportService.getAirports().subscribe(airport => this.airportsTo = airport);

  }

  getAircompanyById(): void {
    // this.aircompany = this.aircompanyService.getAircompanyByAdminUsername(this.token.getUsername()).pipe(
    //   tap(aircompany => this)
    // );

    this.aircompanyService.getAircompanyByAdminUsername(this.token.getUsername()).subscribe(
      company => this.flightObj.aircompanyId = company.id,
      error => console.log('Error: ', error),
      () => this.submitFlight()
    );
  }

  onSubmit() {
    if (this.newFlightForm.valid) {

      this.flightObj = this.newFlightForm.value;
      this.nastavak = 'T00:00:00.000';
      this.flightObj.timeBegin = this.newFlightForm.controls['timeBegin'].value.formatted + this.nastavak;
      console.log(this.flightObj);

      this.flightObj.timeEnd = this.newFlightForm.controls['timeEnd'].value.formatted + this.nastavak;
      this.getAircompanyById();
    }
  }

  submitFlight() {
    console.log(this.flightObj);
    this.flightsService.addFlight(this.flightObj).subscribe(
      (response) => {
        console.log('Response is: ', response);
        this.location.back();
      },
      (error) => {
          // catch the error
          console.error('An error occurred, ', error);
      });

  }



  onCancel() {
    this.location.back();
  }

}
