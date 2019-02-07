import { AirportService } from './../../../shared/services/aircompany/airport.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'app-airport-new',
  templateUrl: './airport-new.component.html',
  styleUrls: ['./airport-new.component.css']
})
export class AirportNewComponent implements OnInit {

  newAirportForm: FormGroup;

  constructor(
    private airportService: AirportService,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }

  ngOnInit() {

    this.newAirportForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      city: ['']
    });
  }

  onSubmit() {
    if (this.newAirportForm.valid) {
      this.airportService.addAirport(this.newAirportForm.value).subscribe((response) => {
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
