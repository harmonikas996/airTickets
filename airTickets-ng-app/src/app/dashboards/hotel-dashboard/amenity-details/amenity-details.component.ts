import { ActivatedRoute } from '@angular/router';
import { AmenityService } from './../../../shared/services/hotel/amenity/amenity.service';
import { Amenity } from './../../../shared/model/hotel/amenity.model';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-amenity-details',
  templateUrl: './amenity-details.component.html',
  styleUrls: ['./amenity-details.component.css']
})
export class AmenityDetailsComponent implements OnInit {

  amenity: Observable<Amenity>;
  amenityModel: Amenity;
  amenityDetailsForm: FormGroup;

  constructor(
    private amenityService: AmenityService,
    private route: ActivatedRoute,
    private location: Location,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.amenityDetailsForm = this.formBuilder.group({
      id: [''],
      title: ['', Validators.required],
      price: ['', Validators.required],
      hotelId: ['', Validators.required]
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.getAmenityById(id);
  }

  getAmenityById(id: number): void {
    this.amenity = this.amenityService.getAmenityById(id).pipe(
      tap(amenity => this.amenityDetailsForm.patchValue(amenity))
    );
  }

  getCompanyName(): void {

  }

  onSubmit() {
    // obavezna provera da li to vozilo pripada rentakaru za koga je korisnik ADMIN
    if (this.amenityDetailsForm.valid) {
      this.amenityService.updateAmenity(this.amenityDetailsForm.value).subscribe((response) => {
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

  onRemove(amenity: Amenity) {
    this.amenityService.removeAmenity(amenity.id).subscribe(amenity => this.amenityModel = amenity);
    this.location.back();
  }
}
