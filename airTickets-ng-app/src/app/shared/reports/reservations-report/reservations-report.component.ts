import { HotelService } from './../../services/hotel/hotel.service';
import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { VehicleService } from '../../services/rentacar/vehicle.service';
import { RentacarService } from '../../services/rentacar/rentacar.service';
import * as moment from 'moment';
import { RentACar } from '../../model/rentacar/rentacar.model';
import { Vehicle } from '../../model/rentacar/vehicle.model';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';

@Component({
  selector: 'app-reservations-report',
  templateUrl: './reservations-report.component.html',
  styleUrls: ['./reservations-report.component.css']
})
export class ReservationsReportComponent implements OnInit {

  rentacar: RentACar;
  freeVehiclesObservable: Observable<any[]>;
  reservedVehiclesObservable: Observable<any[]>;

  freeVehicles: any[];
  reservedVehicles: any[];
  vehicleReportForm: FormGroup;
  id: number;
  datePeriod: String;
  dateBegin: String;
  dateEnd: String;

  constructor(
    private rentacarService: RentacarService,
    private vehicleService: VehicleService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder,
    private token: TokenStorageService,
    private hotelService: HotelService
  ) { }

  ngOnInit() {

    this.vehicleReportForm = this.formBuilder.group({
      datePeriod: ['', Validators.required]
    });
  }

  getUserType() {

      return this.token.isRentacar();


  }

  onSubmit() {

    this.dateBegin = moment(this.vehicleReportForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.dateEnd = moment(this.vehicleReportForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');


    if (this.token.getAuthorities().includes('rentacar')) {

      this.rentacarService.getRentacarByAdminUsername(this.token.getUsername()).subscribe(
        data => {

          this.id = data.id;

          this.getFv();
          this.getRv();
        });


    } else if (this.token.getAuthorities().includes('hotel')) {

      this.hotelService.getHotelByAdminUsername(this.token.getUsername()).subscribe(data => {

        this.id = data.id;

        this.getFv();
        this.getRv();

      });

    }

  }

  getFv() {

    if (this.token.getAuthorities().includes('rentacar')) {

      this.freeVehiclesObservable = this.rentacarService.getFreeVehicles(this.id, this.dateBegin, this.dateEnd).pipe(
        tap(vehicles => this.freeVehicles = vehicles)
      );


    } else if (this.token.getAuthorities().includes('hotel')) {

      this.freeVehiclesObservable = this.hotelService.getFreeRooms(this.token.getUsername(), this.dateBegin, this.dateEnd).pipe(
        tap(hotels => this.freeVehicles = hotels)
      );

    }

  }

  getRv() {
    if (this.token.getAuthorities().includes('rentacar')) {

      this.reservedVehiclesObservable = this.rentacarService.getReservedVehicles(this.id, this.dateBegin, this.dateEnd).pipe(
        tap(vehicles => this.reservedVehicles = vehicles)
      );


    } else if (this.token.getAuthorities().includes('hotel')) {

      this.reservedVehiclesObservable = this.hotelService.getReservedRooms(this.id, this.dateBegin, this.dateEnd).pipe(
        tap(vehicles => this.reservedVehicles = vehicles)
      );

    }


  }

}
