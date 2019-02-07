import { Hotel } from 'src/app/shared/model/hotel/hotel.model';
import { HotelService } from 'src/app/shared/services/hotel/hotel.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import * as moment from 'moment';

@Component({
  selector: 'app-hotels-list',
  templateUrl: './hotels-list.component.html',
  styleUrls: ['./hotels-list.component.css']
})
export class HotelsListComponent implements OnInit {

  hotelSearchForm: FormGroup;
  hotels: Hotel[];
  hotelsPermanent: Hotel[];
  name: String;
  location: String;
  timeBegin: String;
  timeEnd: String;
  locations: Hotel[]; // Popunitiii*********************

  constructor(
    private formBuilder: FormBuilder,
    private hotelService: HotelService

  ) { }

  ngOnInit() {

    this.hotelSearchForm = this.formBuilder.group({
      hotelName: [null],
      hotelLocation: [null],
      datePeriod: [null, Validators.required]
    });

    this.getHotelsPermament();
    this.getHotels();
    this.getLocations();
  }

  getHotels(): void {
    this.hotelService.gethotels().subscribe(hotels => this.hotels = hotels);
  }

  getHotelsPermament(): void {
      this.hotelService.gethotels().subscribe(hotels => this.hotelsPermanent = hotels);
  }

  getLocations(): void {

  }

  searchHotels(name: String, location: String, timeBegin: String, timeEnd: String): void {

  }

 onSubmit() {
    if (this.hotelSearchForm.valid) {
      this.prepareData();
      this.searchHotels(this.name, this.location, this.timeBegin, this.timeEnd);
    }
  }

  prepareData() {
    if (this.hotelSearchForm.controls['hotelName'].value && this.hotelSearchForm.controls['hotelLocation'].value) {
      this.name = this.hotelSearchForm.controls['hotelName'].value;
      this.location = this.hotelSearchForm.controls['hotelLocation'].value;
    } else if(this.hotelSearchForm.controls['hotelName'].value) {
      this.name = this.hotelSearchForm.controls['hotelName'].value;
      this.location = ' ';
    } else {
      this.name = ' ';
      this.location = this.hotelSearchForm.controls['hotelLocation'].value;
    }

    this.timeBegin = moment(this.hotelSearchForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.timeEnd = moment(this.hotelSearchForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');
  }


}
