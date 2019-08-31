import { Hotel } from './../../../shared/model/hotel/hotel.model';
import { HotelService } from 'src/app/shared/services/hotel/hotel.service';
import { AmenityService } from './../../../shared/services/hotel/amenity/amenity.service';
import { Amenity } from './../../../shared/model/hotel/amenity.model';
import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { UserService } from 'src/app/shared/services/user/user.service';

@Component({
  selector: 'app-amenity-list',
  templateUrl: './amenity-list.component.html',
  styleUrls: ['./amenity-list.component.css']
})
export class AmenityListComponent implements OnInit {

  amenities: Amenity[];
  amenity: Amenity;

  constructor(private amenityService: AmenityService, private hotelService: HotelService, private userService: UserService) { }

  ngOnInit() {
    this.getAmenitiesByHotelId();
  }

  getAmenitiesByHotelId(): void {
    this.userService.getUserById().subscribe(
      response => {
        this.amenityService.getAmenitiesByHotel(response.company).subscribe(
          amenities => this.amenities = amenities,
          error => console.log('Error: ', error),
           () => this.getCompanyName()
        );
    });
  }

  getCompanyName(): void {
    for (const r of this.amenities) {

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
    this.amenityService.removeAmenity(amenity.id).subscribe(a => this.amenity = a);
  }
}
