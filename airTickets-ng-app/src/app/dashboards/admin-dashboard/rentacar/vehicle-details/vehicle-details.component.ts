import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { VehicleService } from 'src/app/shared/services/rentacar/vehicle.service';
import { Vehicle } from 'src/app/shared/model/rentacar/vehicle.model';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'vehicle-details',
  templateUrl: './vehicle-details.component.html',
  styleUrls: ['./vehicle-details.component.css']
})
export class VehicleDetailsComponent implements OnInit {

  vehicle: Observable<Vehicle>;
  vehicleModel: Vehicle;
  vehicleDetailsForm: FormGroup;

  constructor(
    private vehicleService: VehicleService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.vehicleDetailsForm = this.formBuilder.group({
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

    const id = +this.route.snapshot.paramMap.get('id');
    this.getVehicleById(id);

    
  }
  
  getVehicleById(id: number): void {
    this.vehicle = this.vehicleService.getVehicleById(id).pipe(
      tap(vehicle => this.vehicleDetailsForm.patchValue(vehicle))
    );
  }

  onSubmit() {
    // obavezna provera da li to vozilo pripada rentakaru za koga je korisnik ADMIN
    if (this.vehicleDetailsForm.valid) {
      this.vehicleService.updateVehicle(this.vehicleDetailsForm.value).subscribe((response) => {
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

  onRemove(vehicle: Vehicle) {
    this.vehicleService.removeVehicle(vehicle.id).subscribe(vehicle => this.vehicleModel = vehicle);
    this.location.back();
  }

}
