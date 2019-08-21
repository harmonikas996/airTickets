import { Vehicle } from 'src/app/shared/model/rentacar/vehicle.model';
import { VehicleService } from './../../shared/services/rentacar/vehicle.service';
import { CarReservation } from './../../shared/model/rentacar/car-reservation';
import { CarReservationService } from './../../shared/services/rentacar/car-reservation.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { IMyDpOptions } from 'mydatepicker';
import { RentACar } from 'src/app/shared/model/rentacar/rentacar.model';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';
import * as moment from 'moment';
import { BranchOffice } from 'src/app/shared/model/rentacar/branchOffice.model';
import { RentacarsWithBranches } from 'src/app/shared/model/rentacar/rentacarsWithBranches.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-rentacars-list',
  templateUrl: './rentacars-list.component.html',
  styleUrls: ['./rentacars-list.component.css']
})
export class RentacarsListComponent implements OnInit {

  public myDatePickerOptions: IMyDpOptions = {
    dateFormat: 'yyyy-mm-dd'
  };

  rentacarSearchForm: FormGroup;
  rentacars: RentACar[];
  branchOffices: BranchOffice[];
  rentacarsPermanent: RentACar[];
  rentacarsWithBranches: RentacarsWithBranches[];
  locations: String[];
  name: String;
  location: String;
  timeBegin: String;
  timeEnd: String;
  carReservations: CarReservation[];
  carReservation: CarReservation;

  constructor(
    private rentacarService: RentacarService,
    private formBuilder: FormBuilder,
    private carReservationService: CarReservationService,
    private vehicleService: VehicleService,
    private http: HttpClient
  ) { }

  ngOnInit() {
    this.rentacarSearchForm = this.formBuilder.group({
      rentacarName: [null],
      rentacarLocation: [null],
      datePeriod: [null, Validators.required]
    });
    this.rentacarsWithBranches = [];
    this.rentacars = [];
    this.getRentacarsPermanent();
    this.getRentacars();
    this.getLocations();
    this.getQuickReservation();
  }

  getQuickReservation(): void {
    this.carReservationService.getCarReservations().subscribe(carReservations => this.carReservations = carReservations);
  }

  getRentacars(): void {
    this.rentacarService.getRentacars().subscribe(
      rentacars => {
        rentacars.forEach((rentacar) => {
          this.http.jsonp('http://dev.virtualearth.net/REST/v1/Locations/' + rentacar.address +
          '?jsonp=JSONP_CALLBACK&key=' + environment.bingMapCredentials, 'JSONP_CALLBACK')
          .subscribe(
            (response: any) => {
              rentacar.address = response.resourceSets[0].resources[0].address.formattedAddress;
            }
          );
        });
        this.rentacars = rentacars;
      });
  }

  getRentacarsPermanent(): void {
    this.rentacarService.getRentacars().subscribe(rentacars => this.rentacarsPermanent = rentacars);
  }

  getLocations(): void {
    this.rentacarService.getLocations().subscribe(locations => this.locations = locations);
  }

  searchRentacars(name: String, location: String, timeBegin: String, timeEnd: String): void {
    this.rentacarService.searchRentacars(name, location, timeBegin, timeEnd).subscribe(
      rentacars => this.rentacarsWithBranches = rentacars,
      (error) => console.error("An error occurred, ", error),
      () => this.rentacars = []
      );
  }

  onSubmit() {
    if (this.rentacarSearchForm.valid) {
      this.prepareData();
      this.searchRentacars(this.name, this.location, this.timeBegin, this.timeEnd);
    }
  }

  prepareData() {
    if(this.rentacarSearchForm.controls['rentacarName'].value && this.rentacarSearchForm.controls['rentacarLocation'].value) {
      this.name = this.rentacarSearchForm.controls['rentacarName'].value;
      this.location = this.rentacarSearchForm.controls['rentacarLocation'].value;
    } else if(this.rentacarSearchForm.controls['rentacarName'].value) {
      this.name = this.rentacarSearchForm.controls['rentacarName'].value;
      this.location = ' ';
    } else {
      this.name = ' ';
      this.location = this.rentacarSearchForm.controls['rentacarLocation'].value;
    }

    this.timeBegin = moment(this.rentacarSearchForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.timeEnd = moment(this.rentacarSearchForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');
  }
}
