import { RoomReservation } from './../shared/model/hotel/room-reservation.model';
import { RoomReservationService } from './../shared/services/hotel/room-reservation/room-reservation.service';
import { CarReservation } from './../shared/model/rentacar/car-reservation';
import { CarReservationService } from './../shared/services/rentacar/car-reservation.service';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../user-authentication/service/token-storage.service';
import { FlightReservationService } from '../shared/services/aircompany/flight-reservation.service';
import { FlightReservation } from '../shared/model/aircompany/flight-reservation.model';
import { RentacarService } from '../shared/services/rentacar/rentacar.service';
import { HotelService } from '../shared/services/hotel/hotel.service';
import { AircompanyService } from '../shared/services/aircompany/aircompany.service';

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

  aircompanies = [];
  rentacars = [];
  hotels = [];

  constructor(
    private flightReservationService: FlightReservationService,
    private carReservationService: CarReservationService,
    private roomReservationService: RoomReservationService,
    private tokenStorageService: TokenStorageService,
    private aircompanyService: AircompanyService,
    private rentacarService: RentacarService,
    private hotelService: HotelService
  ) { }

  ngOnInit() {
    this.getFlightReservationsByUser();
    this.getCarReservationsByUser();
    this.getRoomReservationsByUser();
    this.getAircompanies();
    this.getRentacars();
    this.getHotels();
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

  getAircompanies(): void {
    this.aircompanyService.getAircompanies()
    .subscribe(aircompanies => this.aircompanies = aircompanies);
  }

  getRentacars(): void {
    this.rentacarService.getRentacars()
    .subscribe(rentacars => this.rentacars = rentacars);
  }

  getHotels(): void {
    this.hotelService.gethotels()
    .subscribe(hotels => this.hotels = hotels);
  }

}
