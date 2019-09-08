import { RoomReservationService } from './../../../shared/services/hotel/room-reservation/room-reservation.service';
import { RoomReservation } from './../../../shared/model/hotel/room-reservation.model';
import { Room } from 'src/app/shared/model/hotel/room.model';
import { RoomService } from './../../../shared/services/hotel/room/room.service';
import { Hotel } from './../../../shared/model/hotel/hotel.model';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import * as moment from 'moment';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-quick-res-hotel',
  templateUrl: './quick-res-hotel.component.html',
  styleUrls: ['./quick-res-hotel.component.css']
})
export class QuickResHotelComponent implements OnInit {

  quickForm: FormGroup;
  quickFormRes: FormGroup;
  timeBegin: String;
  timeEnd: String;
  hotels: Hotel[];
  user: String;
  roomsRes: Room[];
  roomID: number;
  price: number;
  roomsResObj: RoomReservation = new RoomReservation();

  constructor(
    private formBuilder: FormBuilder,
    private token: TokenStorageService,
    private roomService: RoomService,
    private roomResService: RoomReservationService,
    private locationService: Location
  ) { }

  ngOnInit() {

    this.quickForm = this.formBuilder.group({

      datePeriod: [null, Validators.required]

    });

    this.quickFormRes = this.formBuilder.group({
      chRooms: [null, Validators.required],
      price: [null, Validators.required]

    });

    this.user = this.token.getUsername();

  }

  onSubmit() {
    if (this.quickForm.valid) {
      this.prepareData();

      this.searchRooms(this.timeBegin, this.timeEnd, this.user);

    }
  }

  searchRooms(timeBegin: String, timeEnd: String, user: String): void {

    this.roomService.searchRoomsByDate(timeBegin, timeEnd, user).subscribe(
      roomsRes => this.roomsRes = roomsRes
    );

  }

  prepareData() {

    this.timeBegin = moment(this.quickForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.timeEnd = moment(this.quickForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');

  }

  reserve() {

    if (this.quickFormRes.valid) {

      this.roomID = this.quickFormRes.controls['chRooms'].value;
      this.price = this.quickFormRes.controls['price'].value;
      this.roomResService.addRoomReservation(this.timeBegin, this.timeEnd, this.user, this.roomID, this.price).subscribe((response) => {
        location.replace('./hotel-dashboard');
     },
     (error) => {
        //catch the error
        console.error("An error occurred, ", error)
     });
     }
  }

}
