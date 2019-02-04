import { HotelService } from './../../../shared/services/hotel/hotel.service';
import { Hotel } from './../../../shared/model/hotel/hotel.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-hotels-list',
  templateUrl: './hotels-list.component.html',
  styleUrls: ['./hotels-list.component.css']
})
export class HotelsListComponent implements OnInit {

  hotels: Hotel[];
  hotel: Hotel;

  constructor(
    private hotelService: HotelService
  ) { }

  ngOnInit() {
    this.getHotels();
  }

  getHotels(): void {
    this.hotelService.gethotels().subscribe(
      hotels => this.hotels = hotels,
      error => console.log('Error: ', error)
    );
  }

  onRemove(hotel: Hotel): void {
    this.hotels = this.hotels.filter(v => v !== hotel);
    this.hotelService.removeHotel(hotel.id).subscribe(hotel => this.hotel = hotel);
  }
}
