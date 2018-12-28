import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Vehicle } from 'src/app/shared/model/rentacar/vehicle.model';
import { VehicleService } from 'src/app/shared/services/rentacar/vehicle.service';

@Component({
  selector: 'app-vehicle-new',
  templateUrl: './vehicle-new.component.html',
  styleUrls: ['./vehicle-new.component.css']
})

export class VehicleNewComponent implements OnInit {

  rentacar: Observable<Vehicle>;
  //rentacarModel: RentACar;
  newVehicleForm: FormGroup;
  

  constructor(
    private vehicleService: VehicleService, 
    private formBuilder: FormBuilder, 
    private location: Location,
  ) { }

  ngOnInit() {
    // srediti preuzimanje ID-a tako sto proveris kojoj kompaniji je dodeljen ulogovani admin
    this.newVehicleForm = this.formBuilder.group({
      id: [''],
      name: ['', Validators.required],
      brand: ['', Validators.required],
      model: ['', Validators.required],
      yearOfProduction: ['', Validators.required],
      numberOfSeats: ['', Validators.required],
      type: ['', Validators.required],
      pricePerDay: ['', Validators.required],
      rentACarId: ['', Validators.required],
      image: [''],
    });
    
  }

  onSubmit() {
    if (this.newVehicleForm.valid) {
      this.vehicleService.addVehicle(this.newVehicleForm.value).subscribe((response) => {
        console.log("Response is: ", response);
        this.location.back();
     },
     (error) => {
        //catch the error
        console.error("An error occurred, ", error);
     });
     };
    }

  onCancel() {
    this.location.back();
  }
}
