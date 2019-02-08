import { Vehicle } from 'src/app/shared/model/rentacar/vehicle.model';
import { VehicleService } from 'src/app/shared/services/rentacar/vehicle.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

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

  constructor(
    private formBuilder: FormBuilder,
    private vehicleService: VehicleService
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

  }

  searchVehicles(timeBegin: String, timeEnd: String): void {
    this.vehicleService.searchVehicleByDate(timeBegin, timeEnd).subscribe(
      vehicles => this.vehicles = vehicles,
    );
  }

  onSubmit() {
    if (this.quickForm.valid) {
      this.prepareData();

      this.searchVehicles(this.timeBegin, this.timeEnd);

    }
  }

  prepareData() {

    this.timeBegin = moment(this.quickForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.timeEnd = moment(this.quickForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');

  }

}
