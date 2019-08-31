import { RoomPriceService } from './../../../shared/services/hotel/room-price/room-price.service';
import { RoomService } from './../../../shared/services/hotel/room/room.service';
import { RoomPrice } from './../../../shared/model/hotel/room-price.model';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/services/user/user.service';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-room-price-list',
  templateUrl: './room-price-list.component.html',
  styleUrls: ['./room-price-list.component.css']
})
export class RoomPriceListComponent implements OnInit {

  roomPrices = [];

  constructor(
    private roomPriceService: RoomPriceService,
    private roomService: RoomService,
    private userService: UserService,
    private tokenStorageService: TokenStorageService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getRoomsPricesByHotelId();
  }

  getRoomsPricesByHotelId(): void {
    this.userService.getUserById().subscribe(
      response => {
        this.roomPriceService.getRoomPriceByHotel(response.company).subscribe(
          roomPrices => {
            // this.roomPrices = roomPrices as any;
            for (const roomPrice of roomPrices) {
              this.roomService.getRoomById(roomPrice.roomId).subscribe(
                room => {
                  this.roomPrices.push({
                    id: roomPrice.id,
                    dateFrom: roomPrice.datoFrom,
                    dateTo: roomPrice.datoTo,
                    price: roomPrice.price,
                    roomId: roomPrice.roomId,
                    number: room.number
                  });
                }
              );
            }
          },
          error => console.log('Error: ', error),
           () => this.getRoomName()
        );
      }
    );

    
  }

  getRoomName(): void {
    // Ovde odraditiiii
  }

  onRemove(roomPrice: RoomPrice) {
    // this.router.navigateByUrl('hotel-dashboard/rooms-prices', { skipLocationChange: true });
    this.roomPriceService.removeRoomPrice(roomPrice.id).subscribe(
      response => {
        this.roomPrices.splice(this.roomPrices.indexOf(roomPrice), 1);
      }
    );
  }

}
