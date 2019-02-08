import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RoomService } from 'src/app/shared/services/hotel/room/room.service';
import { Room } from 'src/app/shared/model/hotel/room.model';
import * as moment from 'moment';


@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {

  hotelRoomForm: FormGroup;
  numberOfrooms: number[];
  id: number;
  rooms: Room[];


  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private roomService: RoomService
  ) { }

  ngOnInit() {

    this.hotelRoomForm = this.formBuilder.group({
      numberOfrooms1: [null],
      numberOfrooms2: [null],
      numberOfrooms3: [null],
      numberOfrooms4: [null],
      minprice: [null],
      maxprice: [null],
      guest: [null],
      datePeriod: [null, Validators.required]
    });

    const ajDi = +this.route.snapshot.paramMap.get('id');
    this.id = ajDi;
    this.numberOfrooms = [1, 2, 3, 4, 5, 6, 7, 8, 9];
  }

  onSubmit() {
      let timeBegin: string = moment(this.hotelRoomForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
      let timeEnd: string = moment(this.hotelRoomForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');

      this.roomService.searchRoomsByDate2(timeBegin, timeEnd, this.id).subscribe(
        rooms => this.rooms = rooms
      );
      // this.searchRooms();
  }


}
