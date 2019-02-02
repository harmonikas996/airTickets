import { Location } from '@angular/common';
import { RoomService } from './../../../shared/services/hotel/room/room.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Room } from 'src/app/shared/model/hotel/room.model';

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
    private location: Location
  ) { }

  ngOnInit() {
    this.newRoomForm = this.formBuilder.group({
      id: [''],
      number: ['', Validators.required],
      noOfBeds: ['', Validators.required],
      floor: ['', Validators.required],
      type: ['', Validators.required],
      hotel: ['', Validators.required],
      image: ['']
    });
  }

  onSubmit() {
    if (this.newRoomForm.valid) {
      this.roomService.addRoom(this.newRoomForm.value).subscribe((response) => {
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
