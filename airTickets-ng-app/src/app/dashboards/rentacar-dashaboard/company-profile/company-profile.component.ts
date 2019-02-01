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
  //rentacarModel: RentACar;
  companyProfileForm: FormGroup;
  

  constructor(
    private rentacarService: RentacarService,
    private formBuilder: FormBuilder,
    private token: TokenStorageService
  ) { }

  ngOnInit() {
    this.companyProfileForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['']
    });
    // srediti preuzimanje ID-a kompanije tako sto proveris kojoj kompaniji je dodeljen ulogovani admin
    this.getRentacarById();
    
  }

  getRentacarById(): void {
    // this.rentacarService.getRentacarById(id).subscribe(rentacar => this.rentacar = rentacar);
    this.rentacar = this.rentacarService.getRentacarByAdminUsername(this.token.getUsername()).pipe(
      tap(rentacar => this.companyProfileForm.patchValue(rentacar))
    );
  }

  onSubmit() {
    if (this.companyProfileForm.valid) {
      this.rentacarService.updateRentacar(this.companyProfileForm.value).subscribe((response) => {
        console.log("Response is: ", response);
        location.reload();
     },
     (error) => {
        //catch the error
        console.error("An error occurred, ", error);
     });
     };
    }

  onCancel() {
    location.reload();
  }
}