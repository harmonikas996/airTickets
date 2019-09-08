import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/services/user/user.service';

import { Airport } from '../../../shared/model/aircompany/airport.model';
import { Aircompany } from './../../../shared/model/aircompany/aircompany.model';
import { Flight } from './../../../shared/model/aircompany/flight.model';
import { AircompanyService } from './../../../shared/services/aircompany/aircompany.service';
import { AirportService } from './../../../shared/services/aircompany/airport.service';
import { FlightsService } from './../../../shared/services/aircompany/flights.service';

@Component({
  selector: 'app-flights',
  templateUrl: './flights.component.html',
  styleUrls: ['./flights.component.css']
})
export class FlightsComponent implements OnInit {

  flights: Flight[];
  flight: Flight;

  constructor(
    private flightService: FlightsService,
    private airportService: AirportService,
    private aircompanyService: AircompanyService,
    private userService: UserService
  ) { }

  ngOnInit() {
    // sredicemo za izabranu aviokompaniju
    this.getFlightsByAirCompanyId();
  }

  getFlightsByAirCompanyId(): void {
    this.userService.getUserById().subscribe(
      response => {

        this.flightService.getFlightsByCompanyId(response.company).subscribe(
          flights => this.flights = flights,
          error => console.log('Error: ', error),
          () => this.getCompanyName()
          );
      }
    );
  }

  getCompanyName(): void {
    for (let f of this.flights) {

      let c: Aircompany;
      let a: Airport;
      this.airportService.getAirportById(f.placeFromId).subscribe(
        airport => a = airport,
        error => console.log('Error: ', error),
      () => f.placeFromId = a.city
      );
      this.airportService.getAirportById(f.placeToId).subscribe(
        airport => a = airport,
        error => console.log('Error: ', error),
      () => f.placeToId = a.city
      );
      this.aircompanyService.getAircompanyById(f.aircompanyId).subscribe(
        aircompany => c = aircompany,
        error => console.log('Error: ', error),
      () => f.aircompanyId = c.name
      );
    }
  }

  onRemove(flight: Flight): void {
    this.flights = this.flights.filter(f => f !== flight);
    this.flightService.removeFlight(flight.id).subscribe(flight => this.flight = flight);
  }

}
