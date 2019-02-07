import { SpecialofferService } from './../../../shared/services/hotel/specialoffer/specialoffer.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
  selector: 'app-specialoffer-new',
  templateUrl: './specialoffer-new.component.html',
  styleUrls: ['./specialoffer-new.component.css']
})
export class SpecialofferNewComponent implements OnInit {

  newSpecialOfferForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private specialOfferService: SpecialofferService,
    private location: Location
  ) { }

  ngOnInit() {

    this.newSpecialOfferForm = this.formBuilder.group({
      id: [''],
      title: ['', Validators.required],
      percentage: ['', Validators.required],
    });

  }

  onSubmit() {
    if (this.newSpecialOfferForm.valid) {
      this.specialOfferService.addSpecialoffer(this.newSpecialOfferForm.value).subscribe((response) => {
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
