import { FlightReservation } from './../../model/aircompany/flight-reservation.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FlightReservationService {

  private flightResUrl = 'http://localhost:8080/flightreservations';

  constructor(
    private http: HttpClient
  ) { }

  getFlightReservation(): Observable<FlightReservation[]> {
    return this.http.get<FlightReservation[]>(this.flightResUrl + '/all');
  }

  getFlightResById(id: number): Observable<FlightReservation> {
    return this.http.get<FlightReservation>(this.flightResUrl + '/' + id);
  }

  addFlightRes(flighRes: FlightReservation): Observable<Object> {
    return this.http.post<FlightReservation>(this.flightResUrl + '/new', flighRes, httpOptions);
  }

  updateVehicle(flighRes: FlightReservation): Observable<Object> {
    return this.http.put<FlightReservation>(this.flightResUrl + '/' + flighRes.id + '/update', flighRes, httpOptions);
  }

  removeVehicle(flighRes: FlightReservation | number): Observable<FlightReservation> {
    const id = typeof flighRes === 'number' ? flighRes : flighRes.id;
    return this.http.delete<FlightReservation>(this.flightResUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted vehicle id=${id}`))
    );
  }


}
