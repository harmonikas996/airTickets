import { AircompanyService } from './../../../shared/services/aircompany/aircompany.service';
import { Aircompany } from './../../../shared/model/aircompany/aircompany.model';
import { AirportService } from './../../../shared/services/aircompany/airport.service';
import { Airport } from '../../../shared/model/aircompany/airport.model';
import { FlightsService } from './../../../shared/services/aircompany/flights.service';
import { Flight } from './../../../shared/model/aircompany/flight.model';
import { Component, OnInit } from '@angular/core';

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
    private aircompanyService: AircompanyService
  ) { }

  ngOnInit() {
    // sredicemo za izabranu aviokompaniju
    this.getFlightsByAirCompanyId();
  }

  getFlightsByAirCompanyId(): void {
    this.flightService.getFlights().subscribe(
      flights => this.flights = flights,
      error => console.log('Error: ', error),
      () => this.getCompanyName()
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
