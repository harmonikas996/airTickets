import { RoomReservation } from './../../../model/hotel/room-reservation.model';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RoomReservationService {

  private roomsResUrl = 'http://localhost:8080/roomReservations';

  constructor(
    private http: HttpClient
  ) { }

  getRoomsRes(): Observable<RoomReservation[]> {
    return this.http.get<RoomReservation[]>(this.roomsResUrl + '/all');
  }

  getRoomReservationsByUser(id: string): Observable<RoomReservation[]> {
    return this.http.get<RoomReservation[]>(this.roomsResUrl + '/user/' + id);
  }

  getQuickRoomReservationsByCompanyId(id: number): Observable<RoomReservation[]> {
    return this.http.get<RoomReservation[]>(this.roomsResUrl + '/quick/' + id);
  }

  getRoomResById(id: number): Observable<RoomReservation> {
    return this.http.get<RoomReservation>(this.roomsResUrl + '/' + id);
  }

  addRoomRes(roomRes: RoomReservation): Observable<Object> {
    return this.http.post<RoomReservation>(this.roomsResUrl + '/new', roomRes, httpOptions);
  }

  addRoomReservation(timeBegin: String, timeEnd: String, user: String, roomId: Number, price: Number): Observable<Object[]> {
    return this.http.get<Object[]>(this.roomsResUrl +
      '/addQuick?timeBegin=' + timeBegin +
      '&timeEnd=' + timeEnd +
      '&user=' + user +
      '&roomId=' + roomId +
      '&price=' + price
      );
  }

  updateRoomRes(roomRes: RoomReservation): Observable<Object> {
    return this.http.put<RoomReservation>(this.roomsResUrl + '/' + roomRes.id + '/update', roomRes, httpOptions);
  }

  removeRoomRes(roomRes: RoomReservation | number): Observable<RoomReservation> {
    const id = typeof roomRes === 'number' ? roomRes : roomRes.id;
    return this.http.delete<RoomReservation>(this.roomsResUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted roomRes id=${id}`))
    );
  }


  makeReservation(selectedRoomsObj: any[], flightReservationId: number, from: any, to: any): Observable<number> {
    return this.http.put<number>(
      this.roomsResUrl + '/makeReservation/' + flightReservationId + '/' + from + '/' + to, selectedRoomsObj, httpOptions
      );
  }

  makeQuickReservation(flightReservationId: number, hotelReservationId: number): Observable<number> {
    return this.http.put<number>(
      this.roomsResUrl + '/makeQuickReservation/' + flightReservationId + '/' + hotelReservationId, httpOptions
      );
  }

}
