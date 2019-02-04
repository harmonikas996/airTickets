import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-rentacar-new',
  templateUrl: './rentacar-new.component.html',
  styleUrls: ['./rentacar-new.component.css']
})
export class RentacarNewComponent implements OnInit {

  newRentacarForm: FormGroup;

  constructor(
    private rentacarService: RentacarService,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }

  ngOnInit() {
    this.newRentacarForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      address: ['', Validators.required],
      description: ['']
    });
  }

  onSubmit() {
    if (this.newRentacarForm.valid) {
      this.rentacarService.addRentacar(this.newRentacarForm.value).subscribe((response) => {
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
