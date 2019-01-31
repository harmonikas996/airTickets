import { Flight } from './../../../shared/model/aircompany/flight.model';
import { Observable } from 'rxjs';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { FlightsService } from './../../../shared/services/aircompany/flights.service';
import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs/operators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-flight-details',
  templateUrl: './flight-details.component.html',
  styleUrls: ['./flight-details.component.css']
})
export class FlightDetailsComponent implements OnInit {

  flight: Observable<Flight>;
  flightModel: Flight;
  flightDetailsForm: FormGroup;

  constructor(
    private flightService: FlightsService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.flightDetailsForm = this.formBuilder.group({
      id: [''],
      timeBegin: ['', Validators.required],
      timeEnd: ['', Validators.required],
      distance: ['', Validators.required],
      price: ['', Validators.required],
      airplaneType: ['', Validators.required],
      loweredPrice: ['', Validators.required],
      placeFromId: ['', Validators.required],
      placeToId: ['', Validators.required],
      aircompanyId: ['', Validators.required]
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.getFlightById(id);

  }

  getFlightById(id: number): void {
    this.flight = this.flightService.getFlightById(id).pipe(
      tap(flight => this.flightDetailsForm.patchValue(flight))
    );
  }

  onSubmit(){
    if (this.flightDetailsForm.valid) {
      this.flightService.updateFlight(this.flightDetailsForm.value).subscribe((response) => {
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

  onRemove(flight1: Flight){
    this.flightService.removeFlight(flight1.id).subscribe(flight1 => this.flightModel = flight1);
    this.location.back();
  }

}
