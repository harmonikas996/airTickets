import { RoomPriceService } from './../../../shared/services/hotel/room-price/room-price.service';
import { RoomService } from './../../../shared/services/hotel/room/room.service';
import { RoomPrice } from './../../../shared/model/hotel/room-price.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-room-price-list',
  templateUrl: './room-price-list.component.html',
  styleUrls: ['./room-price-list.component.css']
})
export class RoomPriceListComponent implements OnInit {

  roomPrices: RoomPrice[];
  roomprice: RoomPrice;

  constructor(
    private roomPriceService: RoomPriceService
  ) { }

  ngOnInit() {
    this.getRoomsPricesByHotelId();
  }

  getRoomsPricesByHotelId(): void {
    this.roomPriceService.getRoomsPrice().subscribe(
      roomPrices => this.roomPrices = roomPrices,
      error => console.log('Error: ', error),
       () => this.getRoomName()
    );
  }

  getRoomName(): void {
    // Ovde odraditiiii
  }

  onRemove() {

  }

}
