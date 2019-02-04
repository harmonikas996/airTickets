import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { IMyDpOptions } from 'mydatepicker';
import { RentACar } from 'src/app/shared/model/rentacar/rentacar.model';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';

@Component({
  selector: 'app-rentacars-list',
  templateUrl: './rentacars-list.component.html',
  styleUrls: ['./rentacars-list.component.css']
})
export class RentacarsListComponent implements OnInit {

  public myDatePickerOptions: IMyDpOptions = {
    dateFormat: 'yyyy-mm-dd'
  };

  rentacarSearchForm: FormGroup;
  rentacars: RentACar[];
  locations: String[];

  constructor(
    private rentacarService: RentacarService,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit() {
    this.rentacarSearchForm = this.formBuilder.group({
      rentacarName: [null, Validators.required],
      rentacarLocation: [null, Validators.required],
      dateFrom: [null, Validators.required],
      dateTo: [null, Validators.required]
    });

    this.getRentacars();
    this.getLocations();
  }

  getRentacars(): void {
    this.rentacarService.getRentacars().subscribe(rentacars => this.rentacars = rentacars);
  }

  getLocations(): void {
    this.rentacarService.getLocations().subscribe(locations => this.locations = locations);
  }

  onSubmit() {

  }

  setDate(): void {
    // Set today date using the patchValue function
    let date = new Date();
    this.rentacarSearchForm.patchValue({myDate: {
    date: {
        year: date.getFullYear(),
        month: date.getMonth() + 1,
        day: date.getDate()}
    }});
  }

  clearDate(): void {
      // Clear the date using the patchValue function
      this.rentacarSearchForm.patchValue({myDate: null});
  }

}
