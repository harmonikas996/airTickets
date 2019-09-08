import { RoomRatingService } from './../shared/services/hotel/hotel-rating/room-rating.service';
import { RoomService } from './../shared/services/hotel/room/room.service';
import { AirCompanyRating } from './../shared/model/aircompany/aircompany-rating';
import { CarRating } from './../shared/model/rentacar/car-rating';
import { FlightRating } from './../shared/model/aircompany/flight-rating.model';
import { FlightRatingService } from './../shared/services/aircompany/flight-rating.service';
import { AirportService } from './../shared/services/aircompany/airport.service';
import { FlightsService } from 'src/app/shared/services/aircompany/flights.service';
import { RentAcarRating } from './../shared/model/rentacar/rentAcar-rating';
import { CarRatingService } from './../shared/services/rentacar/car-rating.service';
import { HotelReservation } from './../shared/model/hotel/hotel-reservation';
import { HotelReservationService } from './../shared/services/hotel/hotel-reservation/hotel-reservation.service';
import { HotelRating } from '../shared/model/hotel/hotel-rating.model';
import { HotelRatingService } from './../shared/services/hotel/hotel-rating/hotel-rating.service';
import { RoomReservation } from './../shared/model/hotel/room-reservation.model';
import { RoomReservationService } from './../shared/services/hotel/room-reservation/room-reservation.service';
import { CarReservation } from './../shared/model/rentacar/car-reservation';
import { CarReservationService } from './../shared/services/rentacar/car-reservation.service';
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { TokenStorageService } from '../user-authentication/service/token-storage.service';
import { FlightReservationService } from '../shared/services/aircompany/flight-reservation.service';
import { FlightReservation } from '../shared/model/aircompany/flight-reservation.model';
import { RentacarService } from '../shared/services/rentacar/rentacar.service';
import { HotelService } from '../shared/services/hotel/hotel.service';
import { AircompanyService } from '../shared/services/aircompany/aircompany.service';
import { RoomRating } from '../shared/model/hotel/room-rating';
import { VehicleService } from '../shared/services/rentacar/vehicle.service';
import * as moment from 'moment';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @ViewChild ('carResRating') carResRating: ElementRef;
  @ViewChild ('hotelrating') hotelRating: ElementRef;
  @ViewChild ('roomrating') roomRating: ElementRef;
  @ViewChild ('flightResRating') flightRating: ElementRef;

  flightReservations = [];
  flightReservation: FlightReservation;

  carReservations = [];
  carReservation: CarReservation;

  roomReservations = [];
  roomReservation: RoomReservation;

  aircompanies = [];
  rentacars = [];
  hotels = [];

  roomRatingObj: RoomRating = new RoomRating();
  hotelRatingObj: HotelRating = new HotelRating();

  flightRatingObj: FlightRating = new FlightRating();
  airCompanyRatingObj: AirCompanyRating = new AirCompanyRating();

  rentACarRatingObj: RentAcarRating = new RentAcarRating();
  carRatingObj: CarRating = new CarRating();


  hotelReservation: HotelReservation;

  flightRatingW = [];
  carRatingW = [];
  roomRatingW = [];
  airCompId: number;

  currentRateRentACar = {};
  currentRateCar = {};

  currentRateAirCompany = {};
  currentRateFlight = {};

  currentRateRoom = {};
  currentHotelRate = {};

  constructor(
    private flightReservationService: FlightReservationService,
    private carReservationService: CarReservationService,
    private roomReservationService: RoomReservationService,
    private tokenStorageService: TokenStorageService,
    private aircompanyService: AircompanyService,
    private rentacarService: RentacarService,
    private hotelService: HotelService,
    private roomRatingService: RoomRatingService,
    private hotelRatingService: HotelRatingService,
    private carRatingService: CarRatingService,
    private flightsService: FlightsService,
    private airportService: AirportService,
    private flightRatingService: FlightRatingService,
    private vehicleService: VehicleService,
    private roomService: RoomService,
    private hotelReservationService: HotelReservationService

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
      .subscribe(flightReservations =>  {

        this.flightRatingW = [];

          for (let flightRes of flightReservations) {

              this.flightsService.getFlightById(flightRes.flightId).subscribe(

              flight => {

                this.flightRatingService.getRatingByFlightId(flight.id).subscribe(

                  flightRating => {

                    this.flightRatingService.getRatingByAirCompanyId(flight.aircompanyId).subscribe(

                      aircompanyRating => {

                        this.aircompanyService.getAircompanyById(flight.aircompanyId).subscribe(

                          aircompany => {

                            this.airportService.getAirportById(flight.placeFromId).subscribe(

                              placeFrom => {

                                this.airportService.getAirportById(flight.placeToId).subscribe(

                                  placeTo => {

                                    this.flightRatingW.push({
                                      idFlight: flight.id,
                                      idAircompany: aircompany.id,
                                      timeBegin: flight.timeBegin,
                                      timeEnd: flight.timeEnd,
                                      placeFrom: placeFrom.name,
                                      placeTo: placeTo.name,
                                      aircompanyName: aircompany.name,
                                      ratingFlight: flightRating.rating,
                                      ratingAirCompany: aircompanyRating.rating

                                    }
                                  );
                                  }
                                );
                              }
                            );
                          }
                        );
                        }
                      );
                  }
                );
            }
          );
          }
      });

  }

  getCarReservationsByUser(): void {
    this.carReservationService.getCarReservationsByUser(this.tokenStorageService.getUserId())
    .subscribe(carReservations => {

        this.carRatingW = [];

        for(let carRes of carReservations) {

          this.vehicleService.getVehicleById(carRes.vehicleId).subscribe(

            vehicle => {

              this.carRatingService.getCarRatingByVehicleId(vehicle.id).subscribe(

                ratingCar => {

                  this.carRatingService.getRentACarRatingByRentACarId(vehicle.rentACarId).subscribe(

                    ratingRentAcar => {

                      this.rentacarService.getRentacarById(vehicle.rentACarId).subscribe(

                        rentAcar => {

                          this.carRatingW.push({
                            rentAcar: rentAcar.name,
                            idRentAcar: rentAcar.id,
                            vehicleName: vehicle.name,
                            idVehicle: vehicle.id,
                            dateFrom: carRes.dateFrom,
                            dateTo: carRes.dateTo,
                            price: carRes.price,
                            carRating: ratingCar.rating,
                            rentACarRating: ratingRentAcar.rating
                          });
                        }
                      );
                  }
                );
                }
              );
            }
          );
        }
      }
    );
  }

  getRoomReservationsByUser(): void {
    this.roomReservationService.getRoomReservationsByUser(this.tokenStorageService.getUserId())
    .subscribe(
      roomReservations => {

        this.roomRatingW = [];

        for (let roomRes of roomReservations) {

          this.hotelReservationService.getReservationById(roomRes.hotelReservationId).subscribe(

            hotelRes => {

              this.roomService.getRoomById(roomRes.roomId).subscribe(

                room => {

                  this.hotelService.getHotelById(room.hotel).subscribe(

                    hotel => {

                      this.roomRatingService.getRatingByRoomId(room.id).subscribe(

                        ratingRoom => {

                          this.hotelRatingService.getRatingByHotelId(hotel.id).subscribe(

                            ratingHotel => {

                              this.roomRatingW.push({
                                roomId: room.id,
                                idHotel: hotel.id,
                                roomName: room.number,
                                roomType: room.type,
                                timeBegin: hotelRes.dateFrom,
                                timeEnd: hotelRes.dateTo,
                                hotelName: hotel.name,
                                hotelAdress: hotel.address,
                                roomRating: ratingRoom.rating,
                                hotelRating: ratingHotel.rating
                              });
                            }
                          );
                        }
                    );
                    }
                  );
                }
              );
          }
         );
        }
      }
    );
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

  ratingHotel(r) {

    this.roomRatingObj.rating = this.currentRateRoom[r.roomId];
    this.roomRatingObj.roomId = r.roomId;
    this.roomRatingObj.userId = this.tokenStorageService.getUserId();

    this.hotelRatingObj.hotelId = r.idHotel;
    this.hotelRatingObj.rating = this.currentHotelRate[r.hotelId];
    this.hotelRatingObj.userId = this.tokenStorageService.getUserId();

    if ((!(this.currentRateRoom[r.roomId] == undefined)) && (!(this.currentHotelRate[r.hotelId] == undefined))) {

      this.roomRatingService.addRating(this.roomRatingObj).subscribe((response) => {

        this.currentRateRoom[r.roomId] = undefined;

        this.hotelRatingService.addRating(this.hotelRatingObj).subscribe((response) => {

          this.currentHotelRate[r.hotelId] = undefined;

          this.getRoomReservationsByUser();
        });

      });

    } else if (!(this.currentHotelRate[r.hotelId] == undefined) ) {

      this.hotelRatingService.addRating(this.hotelRatingObj).subscribe((response) => {

        this.currentHotelRate[r.hotelId] = undefined;

        this.getRoomReservationsByUser();
      });

    } else if (!(this.currentRateRoom[r.roomId] == undefined && this.currentHotelRate[r.hotelId] == undefined)) {

      this.roomRatingService.addRating(this.roomRatingObj).subscribe((response) => {

        this.currentRateRoom[r.roomId] = undefined;

        this.getRoomReservationsByUser();
      });


    }

  }

  ratingCarReservation(carRes) {

    this.rentACarRatingObj.clientId = +this.tokenStorageService.getUserId();
    this.rentACarRatingObj.rating = this.currentRateRentACar[carRes.idRentAcar];
    this.rentACarRatingObj.rentACarId = carRes.idRentAcar;

    this.carRatingObj.clientId = +this.tokenStorageService.getUserId();
    this.carRatingObj.rating = this.currentRateCar[carRes.idVehicle];
    this.carRatingObj.vehicleId = carRes.idVehicle;

    if ((!(this.currentRateRentACar[carRes.idRentAcar] == undefined)) && (!(this.currentRateCar[carRes.idVehicle] == undefined))) {

      this.carRatingService.addRentACarRating(this.rentACarRatingObj).subscribe((response) => {

        this.currentRateRentACar[carRes.idRentAcar] = undefined;

        this.carRatingService.addCarRating(this.carRatingObj).subscribe((response) => {

          this.currentRateCar[carRes.idVehicle] = undefined;
          this.getCarReservationsByUser();
        });

      });

    } else if (!(this.currentRateCar[carRes.idVehicle] == undefined) ) {

      this.carRatingService.addCarRating(this.carRatingObj).subscribe((response) => {

        this.currentRateCar[carRes.idVehicle] = undefined;
        this.getCarReservationsByUser();
      });

    } else if (!(this.currentRateRentACar[carRes.idRentAcar] == undefined && this.currentRateCar[carRes.idVehicle] == undefined)) {

      this.carRatingService.addRentACarRating(this.rentACarRatingObj).subscribe((response) => {

        this.currentRateRentACar[carRes.idRentAcar] = undefined;
        this.getCarReservationsByUser();
      });

    }




  }


  ratingFlight(f) {

    this.flightRatingObj.clientId = this.tokenStorageService.getUserId();
    this.flightRatingObj.flightId = f.idFlight;
    this.flightRatingObj.rating = this.currentRateFlight[f.idFlight];

    this.airCompanyRatingObj.clientId = this.tokenStorageService.getUserId();
    this.airCompanyRatingObj.aircompanyId = f.idAircompany;
    this.airCompanyRatingObj.rating = this.currentRateAirCompany[f.idAircompany];

    if ((!(this.currentRateFlight[f.idFlight] == undefined)) && (!(this.currentRateAirCompany[f.idAircompany] == undefined))) {

      this.flightRatingService.addRating(this.flightRatingObj).subscribe((response) => {

        this.currentRateFlight[f.idFlight] = undefined;

        this.flightRatingService.addAirCompanyRating(this.airCompanyRatingObj).subscribe((response) => {

          this.currentRateAirCompany[f.idAircompany] = undefined;
          this.getFlightReservationsByUser();
        });
      });

    } else if (!(this.currentRateAirCompany[f.idAircompany] == undefined)) {

      this.flightRatingService.addAirCompanyRating(this.airCompanyRatingObj).subscribe((response) => {

        this.currentRateAirCompany[f.idAircompany] = undefined;
        this.getFlightReservationsByUser();
      });

    } else if (!(this.currentRateFlight[f.idFlight] == undefined && this.currentRateAirCompany[f.idAircompany] == undefined)) {

      this.flightRatingService.addRating(this.flightRatingObj).subscribe((response) => {

        this.currentRateFlight[f.idFlight] = undefined;
        this.getFlightReservationsByUser();
      });

    }


  }

  isRatableCar(c): boolean {
    return moment(c.dateTo).isBefore(moment());
  }

  isRatableFlight(c): boolean {
    return moment(c.timeEnd).isBefore(moment());
  }

  isRatableRoom(c): boolean {
    return moment(c.timeEnd).isBefore(moment());
  }


}
