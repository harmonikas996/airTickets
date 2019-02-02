import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { RoomService } from './../../../shared/services/hotel/room/room.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Room } from './../../../shared/model/hotel/room.model';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.css']
})
export class RoomDetailsComponent implements OnInit {

  room: Observable<Room>;
  roomModel: Room;
  roomDetailsForm: FormGroup;

  constructor(
    private roomService: RoomService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.roomDetailsForm = this.formBuilder.group({
      id: [''],
      number: ['', Validators.required],
      floor: ['', Validators.required],
      noOfBeds: ['', Validators.required],
      type: ['', Validators.required],
      hotel: ['', Validators.required],
      image: ['']
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.getRoomById(id);
  }

  getRoomById(id: number): void {
    this.room = this.roomService.getRoomById(id).pipe(
      tap(room => this.roomDetailsForm.patchValue(room))
    );
  }

  getCompanyName(): void {

  }

  onSubmit() {
    // obavezna provera da li to vozilo pripada rentakaru za koga je korisnik ADMIN
    if (this.roomDetailsForm.valid) {
      this.roomService.updateRoom(this.roomDetailsForm.value).subscribe((response) => {
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

  onRemove(room: Room) {
    this.roomService.removeRoom(room.id).subscribe(room => this.roomModel = room);
    this.location.back();
  }
}
