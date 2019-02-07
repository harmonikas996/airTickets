import { Specialoffer } from './../../../shared/model/hotel/specialoffer.model';
import { SpecialofferService } from './../../../shared/services/hotel/specialoffer/specialoffer.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-special-offer-update',
  templateUrl: './special-offer-update.component.html',
  styleUrls: ['./special-offer-update.component.css']
})
export class SpecialOfferUpdateComponent implements OnInit {

  updateSpecialOfferForm: FormGroup;
  specialOffer: Observable<Specialoffer>;

  constructor(
    private formBuilder: FormBuilder,
    private specialOfferService: SpecialofferService,
    private location: Location,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.updateSpecialOfferForm = this.formBuilder.group({
      id: [''],
      title: ['', Validators.required],
      percentage: ['', Validators.required],
    });

    const id = +this.route.snapshot.paramMap.get('id');
    this.getSpecialOfferById(id);
  }

  getSpecialOfferById(id: number): void {
    this.specialOffer = this.specialOfferService.getSpecialOfferById(id).pipe(
      tap(specialOffer => this.updateSpecialOfferForm.patchValue(specialOffer))
    );
  }

  onSubmit() {
    if (this.updateSpecialOfferForm.valid) {
      this.specialOfferService.updateSpecialoffer(this.updateSpecialOfferForm.value).subscribe((response) => {
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
