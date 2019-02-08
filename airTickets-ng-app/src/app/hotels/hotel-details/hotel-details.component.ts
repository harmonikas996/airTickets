import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {

  hotelRoomForm: FormGroup;
  numberOfrooms: number[];
  id: number;


  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {

    this.hotelRoomForm = this.formBuilder.group({
      numberOfrooms1: [null],
      numberOfrooms2: [null],
      numberOfrooms3: [null],
      numberOfrooms4: [null],
      minprice: [null],
      maxprice: [null],
      guest: [null, Validators.required],
      datePeriod: [null, Validators.required]
    });

    // za datum
    // this.timeBegin = moment(this.flightResForm.controls['datePeriod'].value[0]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    // this.timeReturn = moment(this.flightResForm.controls['datePeriod'].value[1]).format('YYYY-MM-DDTHH:mm:ss.SSS');
    const ajDi = +this.route.snapshot.paramMap.get('id');
    this.id = ajDi;
    this.numberOfrooms = [1, 2, 3, 4, 5, 6, 7, 8, 9];
  }

  onSubmit() {
    if (this.hotelRoomForm.valid) {

    }
  }

}
