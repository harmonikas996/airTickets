import { Room } from './../../../model/hotel/room.model';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private roomsUrl = 'http://localhost:8080/rooms';

  constructor(
    private http: HttpClient
  ) { }

  getRooms(): Observable<Room[]> {
    return this.http.get<Room[]>(this.roomsUrl + '/all');
  }

  getRoomsByHotel(id: number): Observable<Room[]> {
    return this.http.get<Room[]>(this.roomsUrl + '/hotel/' + id);
  }

  getRoomById(id: number): Observable<Room> {
    return this.http.get<Room>(this.roomsUrl + '/' + id);
  }

  addRoom(room: Room): Observable<Object> {
    return this.http.post<Room>(this.roomsUrl + '/new', room, httpOptions);
  }

  updateRoom(room: Room): Observable<Object> {
    return this.http.put<Room>(this.roomsUrl + '/' + room.id + '/update', room, httpOptions);
  }

  removeRoom(room: Room | number): Observable<Room> {
    const id = typeof room === 'number' ? room : room.id;
    return this.http.delete<Room>(this.roomsUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted room id=${id}`))
    );
  }

  searchRoomsByDate(datebegin: String, dateEnd: String, user: String): Observable<Room[]> {
    return this.http.get<Room[]>(this.roomsUrl +
      '/free?datebegin=' + datebegin +
      '&dateEnd=' + dateEnd +
      '&user=' + user
      );
  }

  searchRoomsByDate2(datebegin: String, dateEnd: String, hotelId: number): Observable<Room[]> {
    return this.http.get<Room[]>('http://localhost:8080/hotels' +
      '/freeRooms?id=' + hotelId +
      '&dateBegin=' + datebegin +
      '&dateEnd=' + dateEnd
      );
  }

  searchRoomsByDateWithBeds(datebegin: String, dateEnd: String, hotelId: number, beds: number): Observable<Room[]> {
    return this.http.get<Room[]>('http://localhost:8080/hotels' +
      '/freeRoomsWithBeds?id=' + hotelId +
      '&dateBegin=' + datebegin +
      '&dateEnd=' + dateEnd +
      '&beds=' + beds
      );
  }
}
