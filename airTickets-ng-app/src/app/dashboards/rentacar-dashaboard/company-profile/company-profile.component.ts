import { Component, OnInit } from '@angular/core';
import { RentACar } from 'src/app/shared/model/rentacar/rentacar.model';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';

@Component({
  selector: 'company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent implements OnInit {

  rentacar: Observable<RentACar>;
  companyProfileForm: FormGroup;
  _address: string;
  searchForAddress: string;

  constructor(
    private rentacarService: RentacarService,
    private formBuilder: FormBuilder,
    private token: TokenStorageService
  ) { }

  ngOnInit() {
    this.companyProfileForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: [{ value: '', disabled: true }, Validators.required],
      textAddress: [''],
      description: ['']
    });
    // srediti preuzimanje ID-a kompanije tako sto proveris kojoj kompaniji je dodeljen ulogovani admin
    this.getRentacarById();
  }

  getRentacarById(): void {
    // this.rentacarService.getRentacarById(id).subscribe(rentacar => this.rentacar = rentacar);
    this.rentacar = this.rentacarService.getRentacarByAdminUsername(this.token.getUsername()).pipe(
      tap(rentacar => {
        this.companyProfileForm.patchValue(rentacar);
        this._address = rentacar.address;
      })
    );
  }

  onSubmit() {
    if (this.companyProfileForm.valid) {
      this.rentacarService.updateRentacar(this.companyProfileForm.getRawValue()).subscribe((response) => {
        console.log("Response is: ", response);
        location.reload();
     },
     (error) => {
        //catch the error
        console.error("An error occurred, ", error);
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

  searchAddress() {
    this.searchForAddress = this.companyProfileForm.controls['textAddress'].value;
  }
}
