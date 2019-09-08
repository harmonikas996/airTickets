import { FlightRatingService } from './../../shared/services/aircompany/flight-rating.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { AircompanyService } from 'src/app/shared/services/aircompany/aircompany.service';
import { Aircompany } from 'src/app/shared/model/aircompany/aircompany.model';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-aircompany-details',
  templateUrl: './aircompany-details.component.html',
  styleUrls: ['./aircompany-details.component.css']
})
export class AircompanyDetailsComponent implements OnInit {


  aircompany = [];
  id: number;
  airCompanyRating: number;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private tokenStorage: TokenStorageService,
    private aircompanyService: AircompanyService,
    private http: HttpClient,
    private flightRatingService: FlightRatingService
  ) { }

  ngOnInit() {

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
          }
        );
        this.flightRatingService.getRatingsByAirCompany(this.id).subscribe(rating => this.airCompanyRating = rating);
      this.aircompany = aircompany as any;
    });
  }

}
