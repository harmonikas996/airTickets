import { Hotel } from './../../../shared/model/hotel/hotel.model';
import { HotelService } from 'src/app/shared/services/hotel/hotel.service';
import { AmenityService } from './../../../shared/services/hotel/amenity/amenity.service';
import { Amenity } from './../../../shared/model/hotel/amenity.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-amenity-list',
  templateUrl: './amenity-list.component.html',
  styleUrls: ['./amenity-list.component.css']
})
export class AmenityListComponent implements OnInit {

  amenities: Amenity[];
  amenity: Amenity;

  constructor(private amenityService: AmenityService, private hotelService: HotelService) { }

  ngOnInit() {
    this.getAmenitiesByHotelId();
  }

  getAmenitiesByHotelId(): void {
    this.amenityService.getAmenities().subscribe(
      amenities => this.amenities = amenities,
      error => console.log('Error: ', error),
       () => this.getCompanyName()
    );
  }

  getCompanyName() : void {
    for(let r of this.amenities) {

      let h: Hotel;
      this.hotelService.getHotelById(r.hotelId).subscribe(
        hotelId => h = hotelId,
        error => console.log('Error: ', error),
      () => r.hotelId = h.name
      );
    }
  }

  onRemove(amenity: Amenity): void {
    this.amenities = this.amenities.filter(v => v !== amenity);
    this.amenityService.removeAmenity(amenity.id).subscribe(amenity => this.amenity = amenity);
  }
}
