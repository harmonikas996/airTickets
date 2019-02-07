import { SeatService } from './../../../shared/services/aircompany/seat.service';
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
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import * as moment from 'moment';

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
  flightObj: Flight = new Flight();
  timePr: String;
  nastavak: String;
  aircompany: Aircompany;

  id: number;
  timeBegin: any;
  timeEnd: any;
  distance: number;
  price: number;
  airplaneType: string;
  loweredPrice: number;
  placeFromId: any;
  placeToId: any;
  aircompanyId: any;


  constructor(
    private flightsService: FlightsService,
    private formBuilder: FormBuilder,
    private location: Location,
    private airportService: AirportService,
    private aircompanyService: AircompanyService,
    private token: TokenStorageService,
    private seatsService: SeatService

  ) { }

  ngOnInit() {


    this.newFlightForm = this.formBuilder.group({
      id: [''],
      datePeriod: ['', Validators.required],
      distance: ['', Validators.required],
      price: ['', Validators.required],
      airplaneType: ['', Validators.required],
      loweredPrice: ['', Validators.required],
      placeFromId: ['', Validators.required],
      placeToId: ['', Validators.required],
      numberSeats: ['', Validators.required]
    });

    this.types = [ 'AirbusA320', 'Boeing747', 'Boeing777'];
    this.getAirPortsFrom();
    this.getAirPortsTO();

  }

  getAirPortsFrom(): void {
    this.airportService.getAirports().subscribe(airport => this.airportsFrom = airport);

  }

  getAirPortsTO(): void {
    this.airportService.getAirports().subscribe(airport => this.airportsTo = airport);

  }


  onSubmit() {
    if (this.newFlightForm.valid) {
      console.log(this.newFlightForm.value);
      this.getAircompanyById();


     }

  }


  prepareData() {

    this.flightObj.aircompanyId = this.aircompany.id;
    this.flightObj.distance = this.newFlightForm.controls['distance'].value;
    this.flightObj.timeBegin = moment(this.newFlightForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.flightObj.timeEnd = moment(this.newFlightForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.flightObj.price = this.newFlightForm.controls['price'].value;
    this.flightObj.airplaneType =  this.newFlightForm.controls['airplaneType'].value;
    this.flightObj.loweredPrice = this.newFlightForm.controls['loweredPrice'].value;
    this.flightObj.placeFromId =  this.newFlightForm.controls['placeFromId'].value;
    this.flightObj.placeToId =  this.newFlightForm.controls['placeToId'].value;

    this.flightsService.addFlight(this.flightObj).subscribe((response) => {
      console.log("Response is: ", response);
      this.location.back();
   },
   (error) => console.error("An error occurred, ", error),
   () => {
    //  this.seatsService.
   }
   );
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

}
