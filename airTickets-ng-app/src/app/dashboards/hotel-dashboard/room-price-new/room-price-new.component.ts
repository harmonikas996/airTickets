import { RoomService } from './../../../shared/services/hotel/room/room.service';
import { RoomPrice } from './../../../shared/model/hotel/room-price.model';
import { RoomPriceService } from './../../../shared/services/hotel/room-price/room-price.service';
import { Room } from './../../../shared/model/hotel/room.model';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import * as moment from 'moment';

@Component({
  selector: 'app-room-price-new',
  templateUrl: './room-price-new.component.html',
  styleUrls: ['./room-price-new.component.css']
})
export class RoomPriceNewComponent implements OnInit {

  newRoomPriceForm: FormGroup;
  roomsItem: Room[];
  roomPriceObj: RoomPrice;

  constructor(
    private formBuilder: FormBuilder,
    private roomPriceService: RoomPriceService,
    private location: Location,
    private roomService: RoomService
  ) { }

  ngOnInit() {

    this.newRoomPriceForm = this.formBuilder.group({
      id: [''],
      roomCh: ['', Validators.required],
      price: ['', Validators.required],
      datePeriod: ['']
    });

    this.getRooms();
    this.prepareData();

  }

  // getRoomsByHotelId
  getRooms(): void {
    this.roomService.getRooms().subscribe(roomsItem => this.roomsItem = roomsItem);

  }

  prepareData() {

    this.roomPriceObj = this.newRoomPriceForm.value;

    this.roomPriceObj.datoFrom = moment(this.newRoomPriceForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.roomPriceObj.datoTo = moment(this.newRoomPriceForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');
  }

  onSubmit() {
    if (this.newRoomPriceForm.valid) {
      this.roomPriceService.addRoomPrice(this.roomPriceObj).subscribe((response) => {
        console.log('Response is: ', response);
        this.location.back();
     },
     (error) => {
        // catch the error
        console.error('An error occurred, ', error);
     });
     }
    }

    onCancel() {
      this.location.back();
    }

}
