import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { HotelService } from './../../../shared/services/hotel/hotel.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Hotel } from 'src/app/shared/model/hotel/hotel.model';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-hotel-profile',
  templateUrl: './hotel-profile.component.html',
  styleUrls: ['./hotel-profile.component.css']
})
export class HotelProfileComponent implements OnInit {

  hotel: Observable<Hotel>;
  companyProfileForm: FormGroup;
  _address: string;
  searchForAddress: string;

  constructor(
    private hotelService: HotelService,
    private formBuilder: FormBuilder,
    private token: TokenStorageService
  ) { }

  ngOnInit() {
    this.companyProfileForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      city: [''],
      address: [{ value: '', disabled: true }, Validators.required],
      textAddress: ['', Validators.required],
      description: [''],
      image: ['']
    });
    // srediti preuzimanje ID-a kompanije tako sto proveris kojoj kompaniji je dodeljen ulogovani admin
    this.getHotelById();
  }

  getHotelById(): void {
    // this.hotelService.getHotelById(id).subscribe(hotel => this.hotel = hotel);
    this.hotel = this.hotelService.getHotelByAdminUsername(this.token.getUsername()).pipe(
      tap(hotel => {
        this.companyProfileForm.patchValue(hotel);
        this._address = hotel.address;
      })
    );
  }

  onSubmit() {
    if (this.companyProfileForm.valid) {
      this.hotelService.updateHotel(this.companyProfileForm.getRawValue()).subscribe((response) => {
        console.log('Response is: ', response);
        location.reload();
     },
     (error) => {
        // catch the error
        console.error('An error occurred, ', error);
     });
     }
    }

  onCancel() {
    location.reload();
  }

  onSelectedCoordsChange(coords: string) {
    this.companyProfileForm.controls['address'].setValue(coords);
  }

  onSelectedAddressChange(address: string) {
    this.companyProfileForm.controls['textAddress'].setValue(address);
  }

  onSelectedCityChange(city: string) {
    this.companyProfileForm.controls['city'].setValue(city);
  }

  searchAddress() {
    this.searchForAddress = this.companyProfileForm.controls['textAddress'].value;
  }
}
