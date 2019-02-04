import { AircompanyService } from './../../../shared/services/aircompany/aircompany.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'app-aircompany-new',
  templateUrl: './aircompany-new.component.html',
  styleUrls: ['./aircompany-new.component.css']
})
export class AircompanyNewComponent implements OnInit {

  newAircompanyForm: FormGroup;

  constructor(
    private aircompanyService: AircompanyService,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }

  ngOnInit() {
    this.newAircompanyForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['']
    });
  }

  onSubmit() {
    if (this.newAircompanyForm.valid) {
      this.aircompanyService.addAircompany(this.newAircompanyForm.value).subscribe((response) => {
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
