import { AmenityService } from './../../../shared/services/hotel/amenity/amenity.service';
import { Amenity } from './../../../shared/model/hotel/amenity.model';
import { Observable } from 'rxjs';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { UserService } from 'src/app/shared/services/user/user.service';

@Component({
  selector: 'app-amenity-new',
  templateUrl: './amenity-new.component.html',
  styleUrls: ['./amenity-new.component.css']
})
export class AmenityNewComponent implements OnInit {

  hotel: Observable<Amenity>;
  newAmenityForm: FormGroup;

  constructor(
    private amenityService: AmenityService,
    private formBuilder: FormBuilder,
    private location: Location,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.newAmenityForm = this.formBuilder.group({
      id: [''],
      title: ['', Validators.required],
      price: ['', Validators.required],
      hotelId: ['']
    });
  }

  onSubmit() {
    if (this.newAmenityForm.valid) {
      this.userService.getUserById().subscribe(
        user => {
          this.newAmenityForm.controls.hotelId.setValue(user.company);
          this.amenityService.addAmenity(this.newAmenityForm.value).subscribe((response) => {
              console.log('Response is: ', response);
              this.location.back();
          },
          (error) => {
              // catch the error
              console.error('An error occurred, ', error);
          });
        }
      );
     }
    }

  onCancel() {
    this.location.back();
  }

}
