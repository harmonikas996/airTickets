import { AircompanyService } from './../../../shared/services/aircompany/aircompany.service';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Aircompany } from './../../../shared/model/aircompany/aircompany.model';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-aircompany-details',
  templateUrl: './aircompany-details.component.html',
  styleUrls: ['./aircompany-details.component.css']
})
export class AircompanyDetailsComponent implements OnInit {

  aircompany: Observable<Aircompany>;
  aircompanyModel: Aircompany;
  aircompanyDetailsForm: FormGroup;

  constructor(
    private aircompanyService: AircompanyService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.aircompanyDetailsForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['']
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.getAircompanyById(id);
  }

  getAircompanyById(id: number): void {
    this.aircompany = this.aircompanyService.getAircompanyById(id).pipe(
      tap(aircompany => this.aircompanyDetailsForm.patchValue(aircompany))
    );
  }

  onSubmit() {
    // obavezna provera da li to vozilo pripada rentakaru za koga je korisnik ADMIN
    if (this.aircompanyDetailsForm.valid) {
      this.aircompanyService.updateAircompany(this.aircompanyDetailsForm.value).subscribe((response) => {
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

  onRemove(aircomp: Aircompany) {
    this.aircompanyService.removeAirCompany(aircomp.id).subscribe(aircompany => this.aircompanyModel = aircompany);
    this.location.back();
  }
}
