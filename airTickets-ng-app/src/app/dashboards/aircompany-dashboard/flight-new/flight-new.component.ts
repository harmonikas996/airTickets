import { FlightsService } from './../../../shared/services/aircompany/flights.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Flight } from './../../../shared/model/aircompany/flight.model';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { IMyDpOptions } from 'mydatepicker';

@Component({
  selector: 'app-flight-new',
  templateUrl: './flight-new.component.html',
  styleUrls: ['./flight-new.component.css']
})
export class FlightNewComponent implements OnInit {

  public myDatePickerOptions: IMyDpOptions = {

    dateFormat: 'yyyy-mm-dd'
  };

  flight: Observable<Flight>;
  newFlightForm: FormGroup;

  constructor(
    private flightsService: FlightsService,
    private formBuilder: FormBuilder,
    private location: Location,
  ) { }

  ngOnInit() {


    this.newFlightForm = this.formBuilder.group({
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

  }

  onSubmit(){
    if (this.newFlightForm.valid) {
      this.flightsService.addFlight(this.newFlightForm.value).subscribe((response) => {
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

}
