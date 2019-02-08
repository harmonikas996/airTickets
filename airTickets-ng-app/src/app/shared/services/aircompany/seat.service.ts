import { Seat } from './../../model/aircompany/seat.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { FlightReservation } from '../../model/aircompany/flight-reservation.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class SeatService {

  private seatsUrl = 'http://localhost:8080/seats';

  constructor(
    private http: HttpClient
  ) { }

  getSeats(): Observable<Seat[]> {
    return this.http.get<Seat[]>(this.seatsUrl + '/all');
  }

  getSeatById(id: number): Observable<Seat> {
    return this.http.get<Seat>(this.seatsUrl + '/' + id);
  }

  addSeat(seat: Seat): Observable<Object> {
    return this.http.post<Seat>(this.seatsUrl + '/new', seat, httpOptions);
  }

  updateSeat(seat: Seat): Observable<Object> {
    return this.http.put<Seat>(this.seatsUrl + '/' + seat.id + '/update', seat, httpOptions);
  }

  generateSets(number: number, flightid: number): Observable<Object> {
    return this.http.get<Seat>(this.seatsUrl + '/generate?id=' + flightid + '&number=' + number);
  }

  seatsByFlight(id: number): Observable<Seat[]> {
    return this.http.get<Seat[]>(this.seatsUrl + '/seatsByFlight?id=' + id);
  }

  removeSeat(seat: Seat | number): Observable<Seat> {
    const id = typeof seat === 'number' ? seat : seat.id;
    return this.http.delete<Seat>(this.seatsUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted seat id=${id}`))
    );
  }

  makeReservation(seats: Seat[]): Observable<FlightReservation> {
    return this.http.put<FlightReservation>(this.seatsUrl + '/makeReservation', seats, httpOptions);
  }

}
