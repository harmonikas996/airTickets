import { Specialoffer } from './../../../shared/model/hotel/specialoffer.model';
import { SpecialofferService } from './../../../shared/services/hotel/specialoffer/specialoffer.service';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-specialoffer-details',
  templateUrl: './specialoffer-details.component.html',
  styleUrls: ['./specialoffer-details.component.css']
})
export class SpecialofferDetailsComponent implements OnInit {

  specialOfferDetailsForm: FormGroup;
  specialOffers: Specialoffer[];
  specialOffer: Specialoffer;

  constructor(
    private specialOfferService: SpecialofferService
  ) { }

  ngOnInit() {
    this.getSpecialOffer();

  }

  getSpecialOffer(): void {
    this.specialOfferService.getSpecialoffer().subscribe(
      specialOffer => this.specialOffers = specialOffer
    );
  }

  onRemove(specialOffer: Specialoffer): void {
    this.specialOffers = this.specialOffers.filter(v => v !== specialOffer);
    this.specialOfferService.removeSpecialoffer(specialOffer.id).subscribe(specialOffer => this.specialOffer = specialOffer);
  }

}
