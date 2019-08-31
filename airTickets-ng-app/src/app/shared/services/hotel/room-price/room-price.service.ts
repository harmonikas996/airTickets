import { RoomPrice } from './../../../model/hotel/room-price.model';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Room } from 'src/app/shared/model/hotel/room.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RoomPriceService {

  private priceUrl = 'http://localhost:8080/roomPrices';

  constructor(
    private http: HttpClient
  ) { }

  getRoomsPrice(): Observable<RoomPrice[]> {
    return this.http.get<RoomPrice[]>(this.priceUrl + '/all');
  }

  getRoomPriceByHotel(id: number): Observable<RoomPrice[]> {
    return this.http.get<RoomPrice[]>(this.priceUrl + '/hotel/' + id);
  }

  getRoomPriceById(id: number): Observable<RoomPrice> {
    return this.http.get<RoomPrice>(this.priceUrl + '/' + id);
  }

  addRoomPrice(roomPrice: RoomPrice): Observable<Object> {
    return this.http.post<RoomPrice>(this.priceUrl + '/new', roomPrice, httpOptions);
  }

  updateRoomPrice(roomPrice: RoomPrice): Observable<Object> {
    return this.http.put<RoomPrice>(this.priceUrl + '/' + roomPrice.id + '/update', roomPrice, httpOptions);
  }

  removeRoomPrice(roomPrice: RoomPrice | number): Observable<RoomPrice> {
    const id = typeof roomPrice === 'number' ? roomPrice : roomPrice.id;
    return this.http.delete<RoomPrice>(this.priceUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted roomPrice id=${id}`))
    );
  }

  searchRoomPriceForDateRange(roomId: number, datoFrom: string, datoTo: string): Observable<RoomPrice> {
    return this.http.get<RoomPrice>(
      this.priceUrl + '/search?roomId=' + roomId + '&datoFrom=' + datoFrom + '&datoTo=' + datoTo, httpOptions);
  }

}
