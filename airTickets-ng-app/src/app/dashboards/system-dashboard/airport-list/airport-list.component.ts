import { AirportService } from './../../../shared/services/aircompany/airport.service';
import { Airport } from './../../../shared/model/aircompany/airport.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-airport-list',
  templateUrl: './airport-list.component.html',
  styleUrls: ['./airport-list.component.css']
})
export class AirportListComponent implements OnInit {


  airport: Airport;
  airports: Airport[];

  constructor(
    private airportService: AirportService
  ) { }

  ngOnInit() {
    this.getAirports();
  }

  getAirports(): void {
    this.airportService.getAirports().subscribe(
      airports => this.airports = airports,
      error => console.log('Error: ', error)
    );
  }

  onRemove(airport: Airport): void {
    this.airports = this.airports.filter(v => v !== airport);
    this.airportService.removeAirport(airport.id).subscribe(airport => this.airport = airport);
  }

}
