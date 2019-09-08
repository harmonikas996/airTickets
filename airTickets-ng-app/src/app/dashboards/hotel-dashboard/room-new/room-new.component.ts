import { RoomService } from './../../../shared/services/hotel/room/room.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Room } from 'src/app/shared/model/hotel/room.model';
import { UserService } from 'src/app/shared/services/user/user.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-room-new',
  templateUrl: './room-new.component.html',
  styleUrls: ['./room-new.component.css']
})
export class RoomNewComponent implements OnInit {

  hotel: Observable<Room>;
  newRoomForm: FormGroup;

  constructor(
    private roomService: RoomService,
    private formBuilder: FormBuilder,
    private userService: UserService,
    private locationService: Location
  ) { }

  ngOnInit() {
    this.newRoomForm = this.formBuilder.group({
      id: [''],
      number: ['', Validators.required],
      noOfBeds: ['', Validators.required],
      floor: ['', Validators.required],
      type: ['', Validators.required],
      hotel: [''],
      image: ['']
    });
  }

  onSubmit() {
    if (this.newRoomForm.valid) {
      this.userService.getUserById().subscribe(
        response => {
          this.newRoomForm.controls.hotel.setValue(response.company);
          this.roomService.addRoom(this.newRoomForm.value).subscribe((res) => {
              // location.assign('/hotel-dashboard/rooms');
              this.locationService.back();
          },
          (error) => {
              // catch the error
              console.error('An error occurred, ', error);
          });
        });
     }
    }

  onCancel() {
    this.locationService.back();
  }

}
