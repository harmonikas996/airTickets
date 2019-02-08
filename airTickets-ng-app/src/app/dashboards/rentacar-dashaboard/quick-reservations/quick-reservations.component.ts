import { CarReservation } from './../../../shared/model/rentacar/car-reservation';
import { CarReservationService } from './../../../shared/services/rentacar/car-reservation.service';
import { Vehicle } from 'src/app/shared/model/rentacar/vehicle.model';
import { VehicleService } from 'src/app/shared/services/rentacar/vehicle.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';

@Component({
  selector: 'app-quick-reservations',
  templateUrl: './quick-reservations.component.html',
  styleUrls: ['./quick-reservations.component.css']
})
export class QuickReservationsComponent implements OnInit {

  quickForm: FormGroup;
  quickFormRes: FormGroup;
  timeBegin: String;
  timeEnd: String;
  vehicles: Vehicle[];
  user: String;
  carResObj: CarReservation = new CarReservation();

  //reservation

  vehicleIdRes: number;
  priceRes: number;

  constructor(
    private formBuilder: FormBuilder,
    private vehicleService: VehicleService,
    private token: TokenStorageService,
    private carReservationService: CarReservationService
  ) { }

  ngOnInit() {

    this.quickForm = this.formBuilder.group({

      datePeriod: [null, Validators.required]

    });

    this.quickFormRes = this.formBuilder.group({
      chVehicle: [null, Validators.required],
      price: [null, Validators.required]

    });

    this.vehicles = [];
    this.user = this.token.getUsername();

  }

  searchVehicles(timeBegin: String, timeEnd: String, user: String): void {
    this.vehicleService.searchVehicleByDate(timeBegin, timeEnd, user).subscribe(
      vehicles => this.vehicles = vehicles,
    );
  }

  onSubmit() {
    if (this.quickForm.valid) {
      this.prepareData();

      this.searchVehicles(this.timeBegin, this.timeEnd, this.user);

    }
  }

  reserve() {
    if (this.quickFormRes.valid) {

      this.prepareDataRes();

      this.carReservationService.addCarReservation(this.carResObj).subscribe((response) => {
        console.log("Response is: ", response);
     },
     (error) => {
        //catch the error
        console.error("An error occurred, ", error);
     });
     };
  }


  prepareDataRes() {

    this.carResObj.vehicleId = this.quickFormRes.controls['chVehicle'].value;
    this.carResObj.dateFrom = this.timeBegin;
    this.carResObj.dateTo = this.timeEnd;
    this.carResObj.price = this.quickFormRes.controls['price'].value;

  }

  prepareData() {

    this.timeBegin = moment(this.quickForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.timeEnd = moment(this.quickForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');

  }

}
