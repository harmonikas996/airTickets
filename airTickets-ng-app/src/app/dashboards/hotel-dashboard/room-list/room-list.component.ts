import { RegisterComponent } from './../../../user-authentication/register/register.component';
import { Hotel } from './../../../shared/model/hotel/hotel.model';
import { RoomService } from './../../../shared/services/hotel/room/room.service';
import { Component, OnInit } from '@angular/core';
import { Room } from 'src/app/shared/model/hotel/room.model';
import { HotelService } from 'src/app/shared/services/hotel/hotel.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css']
})
export class RoomListComponent implements OnInit {

  rooms: Room[];
  room: Room;

  constructor(private roomService: RoomService, private hotelService: HotelService) { }

  ngOnInit() {
    this.getRoomsByHotelId();
  }

  getRoomsByHotelId(): void {
    this.roomService.getRooms().subscribe(
      rooms => this.rooms = rooms,
      error => console.log('Error: ', error),
       () => this.getCompanyName()
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
