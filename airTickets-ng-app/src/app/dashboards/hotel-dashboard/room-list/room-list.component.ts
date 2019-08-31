import { Hotel } from './../../../shared/model/hotel/hotel.model';
import { RoomService } from './../../../shared/services/hotel/room/room.service';
import { Component, OnInit } from '@angular/core';
import { Room } from 'src/app/shared/model/hotel/room.model';
import { HotelService } from 'src/app/shared/services/hotel/hotel.service';
import { UserService } from 'src/app/shared/services/user/user.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  rooms: Room[];
  room: Room;

  constructor(private roomService: RoomService, private hotelService: HotelService, private userService: UserService) { }

  ngOnInit() {
    this.getRoomsByHotelId();
  }

  getRoomsByHotelId(): void {
    this.userService.getUserById().subscribe(
      response => {
        this.roomService.getRoomsByHotel(response.company).subscribe(
          rooms => this.rooms = rooms as any,
          error => console.log('Error: ', error),
           () => this.getCompanyName()
        );
      }
    );
  }

  getCompanyName() : void {
    for(let r of this.rooms) {

      let h: Hotel;
      this.hotelService.getHotelById(r.hotel).subscribe(
        hotel => h = hotel,
        error => console.log('Error: ', error),
      () => r.hotel = h.name
      );
    }
  }

  onRemove(room: Room): void {
    this.rooms = this.rooms.filter(v => v !== room);
    this.roomService.removeRoom(room.id).subscribe(room => this.room = room);
  }
}
