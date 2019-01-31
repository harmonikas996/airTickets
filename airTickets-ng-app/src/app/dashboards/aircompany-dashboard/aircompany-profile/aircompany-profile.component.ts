import { Aircompany } from './../../../shared/model/aircompany/aircompany.model';
import { Observable } from 'rxjs';
import { AircompanyService } from './../../../shared/services/aircompany/aircompany.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { tap } from 'rxjs/operators';

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
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.aircompanyProfileForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['']
    });
    this.getAircompanyById(9);
  }

  getAircompanyById(id: number): void {
    this.aircompany = this.aircompanyService.getAircompanyById(id).pipe(
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
