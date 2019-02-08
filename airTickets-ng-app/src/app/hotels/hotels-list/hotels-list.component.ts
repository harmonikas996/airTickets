import { RoomReservationService } from './../../shared/services/hotel/room-reservation/room-reservation.service';
import { Room } from './../../shared/model/hotel/room.model';
import { RoomReservation } from './../../shared/model/hotel/room-reservation.model';
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
  locations: String[];
  hotelsRes: Hotel[];


  roomReservations: RoomReservation[];
  roomReservation: RoomReservation;

  constructor(
    private formBuilder: FormBuilder,
    private hotelService: HotelService,
    private roomReservationService: RoomReservationService

  ) { }

  ngOnInit() {

    this.hotelSearchForm = this.formBuilder.group({
      hotelName: [null],
      hotelLocation: [null],
      datePeriod: [null, Validators.required]
    });
    this.hotelsRes = [];
    this.hotels = [];


    this.getHotelsPermament();
    this.getHotels();
    this.getLocations();
    this.getQuickReservation();

  }

  getQuickReservation(): void {
    this.roomReservationService.getRoomsRes().subscribe(roomReservations => this.roomReservations = roomReservations);
  }

  getHotels(): void {
    this.hotelService.gethotels().subscribe(hotels => this.hotels = hotels);
  }

  getHotelsPermament(): void {
      this.hotelService.gethotels().subscribe(hotels => this.hotelsPermanent = hotels);
  }

  getLocations(): void {
      this.hotelService.getLocation().subscribe(locations => this.locations = locations);
  }

  searchHotels(name: String, location: String, timeBegin: String, timeEnd: String): void {
    this.hotelService.searchHotels(name, location, timeBegin, timeEnd).subscribe(
      hotels => this.hotelsRes = hotels,
      (error) => console.error("An error occurred, ", error),
      () => this.hotels = []
      );
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
