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
    private flightService: FlightsService
  ) { }

  ngOnInit() {
    //sredicemo za izabranu aviokompaniju
    this.getFlightsByAirCompanyId();
  }

  getFlightsByAirCompanyId(): void {
    this.flightService.getFlights().subscribe(flights => this.flights = flights);
  }

  onRemove(flight: Flight): void {
    this.flights = this.flights.filter(f => f !== flight);
    this.flightService.removeFlight(flight.id).subscribe(flight => this.flight = flight);
  }
}
