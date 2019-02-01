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

  constructor(
    private aircompanyService: AircompanyService,
    private formBuilder: FormBuilder,
    private token: TokenStorageService
  ) { }

  ngOnInit() {
    this.aircompanyProfileForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['']
    });
    this.getAircompanyById();
  }

  getAircompanyById(): void {
    this.aircompany = this.aircompanyService.getAircompanyByAdminUsername(this.token.getUsername()).pipe(
      tap(aircompany => this.aircompanyProfileForm.patchValue(aircompany))
    );
  }

  onSubmit() {
    if (this.aircompanyProfileForm.valid) {
      this.aircompanyService.updateAircompany(this.aircompanyProfileForm.value).subscribe((response) => {
        console.log('Response is: ', response);
        location.reload();
     },
     (error) => {
        //catch the error
        console.error('An error occurred, ', error);
     });
     };
  }

  onCancel() {
    location.reload();
  }
}
