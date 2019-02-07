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
  freeVehiclesObservable: Observable<Vehicle[]>;
  reservedVehiclesObservable: Observable<Vehicle[]>;
  freeVehicles: Vehicle[];
  reservedVehicles: Vehicle[];
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
    private token: TokenStorageService
  ) { }

  ngOnInit() {

    this.vehicleReportForm = this.formBuilder.group({
      datePeriod: ['', Validators.required]
    });
  }

  onSubmit() {

    this.dateBegin = moment(this.vehicleReportForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.dateEnd = moment(this.vehicleReportForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');

    this.rentacarService.getRentacarByAdminUsername(this.token.getUsername()).subscribe(
      rentacar => this.id = rentacar.id,
      (error) => console.error('An error occurred, ', error),
      () => {
        // this.rentacarService.getFreeVehicles(this.id, this.dateBegin, this.dateEnd).subscribe(
        //   data => this.freeVehicles = data,
        //   (error) => console.error('An error occurred, ', error),
        //   () => {
        //     this.rentacarService.getReservedVehicles(this.id, this.dateBegin, this.dateEnd).subscribe(
        //       data2 => this.reservedVehicles = data2,
        //     );
        //   }

        // );

        this.getFv();
        this.getRv();
      }
    );
  }
  
  getFv() {
    this.freeVehiclesObservable = this.rentacarService.getFreeVehicles(this.id, this.dateBegin, this.dateEnd).pipe(
      tap(vehicles => this.freeVehicles = vehicles)
    );
  }

  getRv() {
    this.reservedVehiclesObservable = this.rentacarService.getReservedVehicles(this.id, this.dateBegin, this.dateEnd).pipe(
      tap(vehicles => this.reservedVehicles = vehicles)
    );
  }

}
