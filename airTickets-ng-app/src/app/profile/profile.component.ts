import { RoomReservation } from './../shared/model/hotel/room-reservation.model';
import { RoomReservationService } from './../shared/services/hotel/room-reservation/room-reservation.service';
import { CarReservation } from './../shared/model/rentacar/car-reservation';
import { CarReservationService } from './../shared/services/rentacar/car-reservation.service';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../user-authentication/service/token-storage.service';
import { FlightReservationService } from '../shared/services/aircompany/flight-reservation.service';
import { FlightReservation } from '../shared/model/aircompany/flight-reservation.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  flightReservations = [];
  flightReservation: FlightReservation;

  carReservations = [];
  carReservation: CarReservation;

  roomReservations = [];
  roomReservation: RoomReservation;

  constructor(
    private flightReservationService: FlightReservationService,
    private carReservationService: CarReservationService,
    private roomReservationService: RoomReservationService,
    private tokenStorageService: TokenStorageService
  ) { }

  ngOnInit() {
    this.getFlightReservationsByUser();
    this.getCarReservationsByUser();
    this.getRoomReservationsByUser();
  }

  getFlightReservationsByUser(): void {
    this.flightReservationService.getFlightReservationsByUser(this.tokenStorageService.getUserId())
    .subscribe(flightReservations => this.flightReservations = flightReservations);
  }

  getCarReservationsByUser(): void {
    this.carReservationService.getCarReservationsByUser(this.tokenStorageService.getUserId())
    .subscribe(carReservations => this.carReservations = carReservations);
  }

  getRoomReservationsByUser(): void {
    this.roomReservationService.getRoomReservationsByUser(this.tokenStorageService.getUserId())
    .subscribe(roomReservations => this.roomReservations = roomReservations);
  }

}
