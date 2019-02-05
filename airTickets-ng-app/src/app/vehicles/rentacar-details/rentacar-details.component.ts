import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';
import { RentACar } from 'src/app/shared/model/rentacar/rentacar.model';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-rentacar-details',
  templateUrl: './rentacar-details.component.html',
  styleUrls: ['./rentacar-details.component.css']
})
export class RentacarDetailsComponent implements OnInit {

  rentacar: RentACar;
  rentacarObservable: Observable<RentACar>;
  vehicleSearchForm: FormGroup;

  constructor(
    private rentacarService: RentacarService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {

    this.vehicleSearchForm = this.formBuilder.group({
      id: ['', Validators.required],
      vehicleType: ['', Validators.required],
      passengers: ['', Validators.required],
      priceFrom: [''],
      priceTo: [''],
      pickupDateTime: ['', Validators.required],
      dropoffDateTime: ['', Validators.required],
      pickupLocation: ['', Validators.required],
      dropoffLocation: ['', Validators.required],
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.vehicleSearchForm.controls['id'].value(id);
    this.getRentACarById(id);
  }

  getRentACarById(id: number): void {
    // this.rentacarService.getRentacarById(id).subscribe(
    //   rentacar => this.rentacar = rentacar
    // );

    this.rentacarObservable = this.rentacarService.getRentacarById(id).pipe(
      tap(rentacar => this.rentacar = rentacar)
    );
  }
}
