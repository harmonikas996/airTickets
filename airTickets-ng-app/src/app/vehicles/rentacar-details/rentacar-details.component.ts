import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';
import { RentACar } from 'src/app/shared/model/rentacar/rentacar.model';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

import * as moment from 'moment';
import { VehicleService } from 'src/app/shared/services/rentacar/vehicle.service';
import { Vehicle } from 'src/app/shared/model/rentacar/vehicle.model';

@Component({
  selector: 'app-rentacar-details',
  templateUrl: './rentacar-details.component.html',
  styleUrls: ['./rentacar-details.component.css']
})
export class RentacarDetailsComponent implements OnInit {

  rentacar: RentACar;
  vehicles: Vehicle[];
  rentacarObservable: Observable<RentACar>;
  vehicleSearchForm: FormGroup;
  types: String[];
  locations: String[];
  passengers: Number[];
  prices: Number[];

  rentacarId: number;
  vehicleType: number;
  passengersSalji: number;
  priceFrom: number;
  priceTo: number;
  pickupDateTime: string;
  dropoffDateTime: string;
  pickupLocation: string;
  dropoffLocation: string;


  constructor(
    private rentacarService: RentacarService,
    private vehicleService: VehicleService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.vehicleSearchForm = this.formBuilder.group({
      vehicleType: ['', Validators.required],
      passengers: ['', Validators.required],
      priceFrom: [''],
      priceTo: [''],
      pickupDateTime: ['', Validators.required],
      dropoffDateTime: ['', Validators.required],
      pickupLocation: ['', Validators.required],
      dropoffLocation: ['', Validators.required],
    });

    this.types = [ 'Sedan', 'Station Wagon', 'Van', 'SUV' ];
    this.passengers = [1,2,3,4,5,6,7,8,9];
    this.prices = [10,25,50,75,100,150,200,500,1000];
    const id = +this.route.snapshot.paramMap.get('id');
    //this.vehicleSearchForm.controls['id'].value(id);
    this.getRentACarById(id);
    this.getLocations();
  }

  getRentACarById(id: number): void {
    this.rentacarObservable = this.rentacarService.getRentacarById(id).pipe(
      tap(rentacar => this.rentacar = rentacar)
    );
  }

  getLocations(): void {
    this.rentacarService.getLocations().subscribe(locations => this.locations = locations);
  }

  searchVehicles(
    priceFrom: number,
    priceTo: number,
    pickupDateTime: string,
    dropoffDateTime: string,
    pickupLocation: string,
    dropoffLocation: string,
    passengersSalji: number,
    rentacarId: number,
    vehicleType: number
    ) {

    this.vehicleService.searchVehicles(priceFrom, priceTo, pickupDateTime, dropoffDateTime, pickupLocation, dropoffLocation, passengersSalji, rentacarId, vehicleType).subscribe(
      vehicles => this.vehicles = vehicles,
     (error) => console.error('An error occurred, ', error),
     () => {}
    );
  }

  onSubmit() {
    if (this.vehicleSearchForm.valid) {
      this.prepareData();
      this.searchVehicles(this.priceFrom, this.priceTo, this.pickupDateTime, this.dropoffDateTime, this.pickupLocation, this.dropoffLocation, this.passengersSalji, this.rentacarId, this.vehicleType);
    }
  }

  prepareData() {

    if (this.vehicleSearchForm.controls['priceFrom'].value) {

      this.priceFrom = this.vehicleSearchForm.controls['priceFrom'].value;
    } else {
      this.priceFrom = -1
    }

    if (this.vehicleSearchForm.controls['priceTo'].value) {

      this.priceTo = this.vehicleSearchForm.controls['priceTo'].value;
    } else {
      this.priceTo = -1
    }
    this.pickupDateTime = moment(this.vehicleSearchForm.controls['pickupDateTime'].value).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.dropoffDateTime = moment(this.vehicleSearchForm.controls['dropoffDateTime'].value).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.pickupLocation = this.vehicleSearchForm.controls['pickupLocation'].value;
    this.dropoffLocation = this.vehicleSearchForm.controls['dropoffLocation'].value;
    this.passengersSalji = this.vehicleSearchForm.controls['passengers'].value;
    this.rentacarId = +this.route.snapshot.paramMap.get('id');
    this.vehicleType = this.vehicleSearchForm.controls['vehicleType'].value;
  }
}
