import { Flight } from './../../../shared/model/aircompany/flight.model';
import { FlightRating } from './../../../shared/model/aircompany/flight-rating.model';
import { FlightRatingService } from './../../../shared/services/aircompany/flight-rating.service';
import { Component, OnInit } from '@angular/core';
import { AircompanyService } from './../../../shared/services/aircompany/aircompany.service';
import { Aircompany } from './../../../shared/model/aircompany/aircompany.model';

@Component({
  selector: 'app-flight-rating',
  templateUrl: './flight-rating.component.html',
  styleUrls: ['./flight-rating.component.css']
})
export class FlightRatingComponent implements OnInit {

  flightRatings: FlightRating[];
  flightR: FlightRating;

  constructor(
    private flightRatingService: FlightRatingService
  ) { }

  ngOnInit() {
    this.getRatingByAirCompanyId();
  }

  getRatingByAirCompanyId(): void {
    this.flightRatingService.getRatings().subscribe(
      flightRatings => this.flightRatings = flightRatings
    );
  }

}
