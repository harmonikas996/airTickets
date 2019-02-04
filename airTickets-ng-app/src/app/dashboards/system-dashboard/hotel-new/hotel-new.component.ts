import { HotelService } from './../../../shared/services/hotel/hotel.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-hotel-new',
  templateUrl: './hotel-new.component.html',
  styleUrls: ['./hotel-new.component.css']
})
export class HotelNewComponent implements OnInit {

  newHotelForm: FormGroup;

  constructor(
    private hotelService: HotelService,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }

  ngOnInit() {
    this.newHotelForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['']
    });
  }

  onSubmit() {
    if (this.newHotelForm.valid) {
      this.hotelService.addHotel(this.newHotelForm.value).subscribe((response) => {
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
