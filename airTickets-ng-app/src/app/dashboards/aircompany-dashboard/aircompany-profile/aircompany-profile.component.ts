import { Aircompany } from './../../../shared/model/aircompany/aircompany.model';
import { Observable } from 'rxjs';
import { AircompanyService } from './../../../shared/services/aircompany/aircompany.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { tap } from 'rxjs/operators';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';

@Component({
  selector: 'app-aircompany-profile',
  templateUrl: './aircompany-profile.component.html',
  styleUrls: ['./aircompany-profile.component.css']
})
export class AircompanyProfileComponent implements OnInit {

  aircompany: Observable<Aircompany>;
  aircompanyProfileForm: FormGroup;
  _address: string;
  searchForAddress: string;

  constructor(
    private aircompanyService: AircompanyService,
    private formBuilder: FormBuilder,
    private token: TokenStorageService
  ) {
  }

  ngOnInit() {
    this.aircompanyProfileForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: [{ value: '', disabled: true }, Validators.required],
      textAddress: [''],
      description: [''],
      image: ['']
    });
    this.getAircompanyById();
  }

  getAircompanyById(): void {
    this.aircompany = this.aircompanyService.getAircompanyByAdminUsername(this.token.getUsername()).pipe(
      tap(aircompany => {
        this.aircompanyProfileForm.patchValue(aircompany);
        this._address = aircompany.address;
      })
    );
  }

  onSubmit() {
    if (this.aircompanyProfileForm.valid) {
      console.log('Sending: ', this.aircompanyProfileForm.value);
      this.aircompanyService.updateAircompany(this.aircompanyProfileForm.getRawValue()).subscribe((response) => {
        console.log('Response is: ', response);
        location.reload();
     },
     (error) => {
        // catch the error
        console.error('An error occurred, ', error);
     });
     };
  }

  onCancel() {
    location.reload();
  }

  onSelectedCoordsChange(coords: string) {
    this.aircompanyProfileForm.controls['address'].setValue(coords);
  }

  onSelectedAddressChange(address: string) {
    this.aircompanyProfileForm.controls['textAddress'].setValue(address);
  }

  searchAddress() {
    this.searchForAddress = this.aircompanyProfileForm.controls['textAddress'].value;
  }
}
