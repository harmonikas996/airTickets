import { RoomService } from './../../../shared/services/hotel/room/room.service';
import { RoomPrice } from './../../../shared/model/hotel/room-price.model';
import { RoomPriceService } from './../../../shared/services/hotel/room-price/room-price.service';
import { Room } from './../../../shared/model/hotel/room.model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import * as moment from 'moment';
import { UserService } from './../../../shared/services/user/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-room-price-new',
  templateUrl: './room-price-new.component.html',
  styleUrls: ['./room-price-new.component.css']
})
export class RoomPriceNewComponent implements OnInit {

  newRoomPriceForm: FormGroup;
  roomsItem: Room[] = [];
  roomPriceObj: RoomPrice;
  ajDi: string;
  title: string;
  buttonTitle: string;
  todayDate = new Date(moment().subtract(1, 'days').toISOString());

  constructor(
    private formBuilder: FormBuilder,
    private roomPriceService: RoomPriceService,
    private locationService: Location,
    private roomService: RoomService,
    private userService: UserService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {

    this.newRoomPriceForm = this.formBuilder.group({
      id: [''],
      roomId: ['', Validators.required],
      price: ['', Validators.required],
      datePeriod: ['']
    });


    this.ajDi = this.activatedRoute.snapshot.paramMap.get('id');

    if (this.ajDi !== null) {
      this.getRoomPriceById();
      this.title = 'Room Price';
      this.buttonTitle = 'Save Changes';
    } else {
      this.getRoomsByHotel();
      this.title = 'New Room Price';
      this.buttonTitle = 'Add Room Price';
    }

  }

  getRoomPriceById() {
    this.roomPriceService.getRoomPriceById(+this.ajDi).subscribe(
      roomPrice => {
        this.newRoomPriceForm.controls.price.setValue(roomPrice.price);
        this.newRoomPriceForm.controls.roomId.setValue(roomPrice.roomId);
        this.newRoomPriceForm.controls.id.setValue(roomPrice.id);
        this.newRoomPriceForm.controls.datePeriod.setValue([new Date(roomPrice.datoFrom), new Date(roomPrice.datoTo)]);
        this.roomService.getRoomById(roomPrice.roomId).subscribe(room => {
          const tmp: Room[] = [];
          tmp.push(room);
          this.roomsItem = tmp;
        });
      }
    );
  }

  getRoomsByHotel(): void {
    this.userService.getUserById().subscribe(
      response => {
        this.roomService.getRoomsByHotel(response.company).subscribe(roomsItem => {
          this.roomsItem = roomsItem as any;
        });
      });

  }

  prepareData() {

    this.roomPriceObj = this.newRoomPriceForm.value;

    this.roomPriceObj.datoFrom = moment(this.newRoomPriceForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    this.roomPriceObj.datoTo = moment(this.newRoomPriceForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');
  }

  onSubmit() {
    if (this.newRoomPriceForm.valid) {
      this.prepareData();

      if (this.ajDi == null) {
        this.roomPriceService.addRoomPrice(this.roomPriceObj).subscribe((response) => {
          location.assign('hotel-dashboard/rooms-prices');
        },
        (error) => {
            // catch the error
            console.error('An error occurred, ', error);
        });
      } else {
        this.roomPriceService.updateRoomPrice(this.roomPriceObj).subscribe((response) => {
          location.assign('hotel-dashboard/rooms-prices');
        },
        (error) => {
            // catch the error
            console.error('An error occurred, ', error);
        });
      }
    }
  }

    onCancel() {
      this.locationService.back();
    }

}
