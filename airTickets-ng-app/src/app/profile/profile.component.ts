import { RoomReservation } from './../shared/model/hotel/room-reservation.model';
import { RoomReservationService } from './../shared/services/hotel/room-reservation/room-reservation.service';
import { CarReservation } from './../shared/model/rentacar/car-reservation';
import { CarReservationService } from './../shared/services/rentacar/car-reservation.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  carReservations: CarReservation[];
  carReservation: CarReservation;

  roomReservations: RoomReservation[];
  roomReservation: RoomReservation;

  constructor(
    private carReservationService: CarReservationService,
    private roomReservationService: RoomReservationService
  ) { }

  ngOnInit() {

    this.getQuickReservation();
    this.getQuickReservationRoom();
  }

  getQuickReservation(): void {
    this.carReservationService.getCarReservations().subscribe(carReservations => this.carReservations = carReservations);
  }

  getQuickReservationRoom(): void {
    this.roomReservationService.getRoomsRes().subscribe(roomReservations => this.roomReservations = roomReservations);
  }

}
