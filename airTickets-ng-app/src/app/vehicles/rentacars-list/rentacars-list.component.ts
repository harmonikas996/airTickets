import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { IMyDpOptions } from 'mydatepicker';
import { RentACar } from 'src/app/shared/model/rentacar/rentacar.model';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';
import * as moment from 'moment';
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
  locations: String[];
  name: String;
  location: String;
  timeBegin: String;
  timeEnd: String;

  constructor(
    private rentacarService: RentacarService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.rentacarSearchForm = this.formBuilder.group({
      rentacarName: [null],
      rentacarLocation: [null],
      datePeriod: [null, Validators.required]
    });

    this.getRentacars();
    this.getLocations();
  }

  getRentacars(): void {
    this.rentacarService.getRentacars().subscribe(rentacars => this.rentacars = rentacars);
  }

  getLocations(): void {
    this.rentacarService.getLocations().subscribe(locations => this.locations = locations);
  }

  searchRentacars(name: String, location: String, timeBegin: String, timeEnd: String): void {
    this.rentacarService.searchRentacars(name, location, timeBegin, timeEnd).subscribe(rentacars => this.rentacars = rentacars);
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
