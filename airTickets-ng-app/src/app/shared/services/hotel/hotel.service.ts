import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Hotel } from '../../model/hotel/hotel.model';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  private hotelsUrl = 'http://localhost:8080/hotels';

  constructor(
    private http: HttpClient
  ) { }

  gethotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.hotelsUrl + '/all');
  }

  getHotelById(id: number): Observable<Hotel> {
    return this.http.get<Hotel>(this.hotelsUrl + '/' + id);
  }

  getHotelByAdminUsername(adminUsername: String): Observable<Hotel> {
    return this.http.get<Hotel>(this.hotelsUrl + '/admin/' + adminUsername);
  }

  updateHotel(hotel: Hotel): Observable<Object> {
    return this.http.put(this.hotelsUrl + '/' + hotel.id + '/update', hotel, httpOptions);
  }

  removeHotel(hotel: Hotel | number):  Observable<Hotel> {
    const id = typeof hotel === 'number' ? hotel : hotel.id;
    return this.http.delete<Hotel>(this.hotelsUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted hotel id=${id}`))
    );
  }

  addHotel(hotel: Hotel): Observable<Object> {
    return this.http.post<Hotel>(this.hotelsUrl + '/new', hotel, httpOptions);
  }
}
