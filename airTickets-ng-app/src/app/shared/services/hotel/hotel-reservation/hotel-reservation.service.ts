import { HotelReservation } from './../../../model/hotel/hotel-reservation';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HotelReservationService {

  private hotelReservationUrl = 'http://localhost:8080/hotelReservations';

  constructor(
    private http: HttpClient
  ) { }

  getReservations(): Observable<HotelReservation[]> {
    return this.http.get<HotelReservation[]>(this.hotelReservationUrl + '/all');
  }

  getReservationById(id: number): Observable<HotelReservation> {
    return this.http.get<HotelReservation>(this.hotelReservationUrl + '/' + id);
  }
}
