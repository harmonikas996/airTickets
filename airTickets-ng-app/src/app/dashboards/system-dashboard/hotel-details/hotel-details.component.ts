import { ActivatedRoute } from '@angular/router';
import { HotelService } from './../../../shared/services/hotel/hotel.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Hotel } from './../../../shared/model/hotel/hotel.model';
import { Component, OnInit } from '@angular/core';
import { tap } from 'rxjs/operators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {

  hotel: Observable<Hotel>;
  hotelModel: Hotel;
  hotelDetailsForm: FormGroup;

  constructor(
    private hotelService: HotelService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.hotelDetailsForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['']
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.getHotelById(id);
  }

  getHotelById(id: number): void {
    this.hotel = this.hotelService.getHotelById(id).pipe(
      tap(hotel => this.hotelDetailsForm.patchValue(hotel))
    );
  }

  onSubmit() {
    // obavezna provera da li to vozilo pripada rentakaru za koga je korisnik ADMIN
    if (this.hotelDetailsForm.valid) {
      this.hotelService.updateHotel(this.hotelDetailsForm.value).subscribe((response) => {
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

  onRemove(hotel: Hotel) {
    this.hotelService.removeHotel(hotel.id).subscribe(hotel => this.hotelModel = hotel);
    this.location.back();
  }
}
