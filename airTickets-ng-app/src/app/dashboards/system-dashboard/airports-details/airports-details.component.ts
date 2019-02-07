import { AirportService } from './../../../shared/services/aircompany/airport.service';
import { Airport } from './../../../shared/model/aircompany/airport.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-airports-details',
  templateUrl: './airports-details.component.html',
  styleUrls: ['./airports-details.component.css']
})
export class AirportsDetailsComponent implements OnInit {

  airport: Observable<Airport>;
  airports: Airport[];
  aiportModel: Airport;
  aiportDetailsForm: FormGroup;

  constructor(
    private airportService: AirportService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.aiportDetailsForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      city: [''],
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.getAirportsById(id);
  }

  getAirportsById(id: number): void {
    this.airport = this.airportService.getAirportById(id).pipe(
      tap(airport => {
        this.aiportDetailsForm.patchValue(airport);
      })
    );
  }

  onSubmit() {
    // obavezna provera da li to vozilo pripada rentakaru za koga je korisnik ADMIN
    if (this.aiportDetailsForm.valid) {
      this.airportService.updateAirport(this.aiportDetailsForm.value).subscribe((response) => {
        console.log('Response is: ', response);
        this.location.back();
     },
     (error) => {
        // catch the error
        console.error('An error occurred, ', error);
     });
     }
    }

  onCancel() {
    this.location.back();
  }

  onRemove(airport: Airport) {
    this.airportService.removeAirport(airport.id).subscribe(airport => this.aiportModel = airport);
    this.location.back();
  }

}
