import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { AircompanyService } from 'src/app/shared/services/aircompany/aircompany.service';
import { Aircompany } from 'src/app/shared/model/aircompany/aircompany.model';
import { environment } from 'src/environments/environment';
import { SeatService } from './../../shared/services/aircompany/seat.service';
import { FlightsService } from './../../shared/services/aircompany/flights.service';
import { AirportService } from './../../shared/services/aircompany/airport.service';
import { Seat } from 'src/app/shared/model/aircompany/seat.model';
import * as moment from 'moment';

@Component({
  selector: 'app-aircompany-details',
  templateUrl: './aircompany-details.component.html',
  styleUrls: ['./aircompany-details.component.css']
})
export class AircompanyDetailsComponent implements OnInit {


  aircompany = [];
  id: number;
  quickReservations = [];
  isQuickSelected = false;
  selectedQuickReservation = {};
  passengerForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private tokenStorage: TokenStorageService,
    private aircompanyService: AircompanyService,
    private seatService: SeatService,
    private flightService: FlightsService,
    private airportService: AirportService,
    private http: HttpClient
  ) { }

  ngOnInit() {

    this.passengerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      passport: ['', Validators.required],
      contact: ['', Validators.required]
    });

    this.id = +this.route.snapshot.paramMap.get('id');
    this.getAircompanyById();
  }

  getAircompanyById() {
    this.aircompanyService.getAircompanyById(this.id).subscribe(aircompany => {
      this.http.jsonp('http://dev.virtualearth.net/REST/v1/Locations/' + aircompany.address +
        '?jsonp=JSONP_CALLBACK&key=' + environment.bingMapCredentials, 'JSONP_CALLBACK')
        .subscribe(
          (response: any) => {
            aircompany.address = response.resourceSets[0].resources[0].address.formattedAddress;
            this.aircompany = aircompany as any;
            this.getQuickSeats(aircompany.id);
          }
        );
    });
  }

  // flights => {
  //   // show flight only if it's 'timeBegin' field is 3hrs behind current time
  //   this.flights = flights.filter(flight => (moment(flight.timeBegin).isAfter(moment().add(1, 'hours'))));
  // }
  getQuickSeats(companyId: number) {
    this.seatService.quickSeatsByCompany(companyId).subscribe(
      seats => {
        for (const seat of seats) {
          this.flightService.getFlightById(seat.flightId).subscribe(
            flight => {
              this.airportService.getAirportById(flight.placeFromId).subscribe(
                airportFrom => {
                  this.airportService.getAirportById(flight.placeToId).subscribe(
                    airportTo => {
                      if ((moment(flight.timeBegin).isAfter(moment().add(1, 'hours')))) {
                        this.quickReservations.push({
                          id: seat.id,
                          clientId: seat.clientId,
                          passport: seat.passport,
                          flightId: seat.flightId,
                          reservationId: seat.flightResId,
                          loweredPrice: seat.price,
                          mark: seat.mark,
                          firstName: seat.firstName,
                          lastName: seat.lastName,
                          contact: seat.contact,
                          timeBegin: flight.timeBegin,
                          timeEnd: flight.timeEnd,
                          distance: flight.distance,
                          originalPrice: flight.price,
                          airplaneType: flight.airplaneType,
                          placeFrom: airportFrom.city,
                          placeTo: airportTo.city,
                          aircompanyId: flight.aircompanyId
                        });
                      }
                    });

                });
            }
          );
        }
      }
    );
  }

  onSelectQuickReservation(quickReservation: any) {
    if (this.selectedQuickReservation['id'] !== quickReservation.id) {
      this.selectedQuickReservation = quickReservation;
      this.isQuickSelected = true;
    } else if (this.selectedQuickReservation['id'] === quickReservation.id) {
      this.selectedQuickReservation = {};
      this.isQuickSelected = false;
    }
  }

  onSubmit() {

    const reservedSeat = {
      id: this.selectedQuickReservation['id'],
      clientId: +sessionStorage.getItem('UserId'),
      flightId: this.selectedQuickReservation['flightId'],
      flightResId: this.selectedQuickReservation['reservationId'],
      reservationId: this.selectedQuickReservation['reservationId'],
      price: this.selectedQuickReservation['loweredPrice'],
      mark: this.selectedQuickReservation['mark'],
      firstName: this.passengerForm.controls.firstName.value,
      lastName: this.passengerForm.controls.lastName.value,
      contact: this.passengerForm.controls.contact.value,
      passport: this.passengerForm.controls.passport.value
    };
    
    this.seatService.makeReservation([reservedSeat]).subscribe(
      response => location.assign('/')
    );
  }

}
