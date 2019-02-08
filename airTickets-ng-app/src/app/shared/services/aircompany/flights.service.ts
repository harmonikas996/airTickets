import { Flight } from './../../model/aircompany/flight.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FlightsService {

private flightUrl = 'http://localhost:8080/flights';

  constructor(
    private http: HttpClient
  ) { }

getFlights(): Observable<Flight[]> {
  return this.http.get<Flight[]>(this.flightUrl + '/all');
}

getFlightById(id: number): Observable<Flight> {
  return this.http.get<Flight>(this.flightUrl + '/' + id);
}

// Ovdeeee letovi samo za aircomapany koja je izabrana
getFlightsByAircompanyId(id: number): Observable<Flight>{
  return null;
}

getAircompanyByAdminUsername(adminUsername: String): Observable<Flight> {
  return this.http.get<Flight>(this.flightUrl + '/admin/' + adminUsername);
}

addFlight(flight: Flight): Observable<Flight> {
  return this.http.post<Flight>(this.flightUrl + '/new', flight, httpOptions);
}

updateFlight(flight: Flight): Observable<Flight> {
  return this.http.put<Flight>(this.flightUrl + '/' + flight.id + '/update', flight, httpOptions);
}

removeFlight(flight: Flight | number): Observable<Flight> {
  const id = typeof flight === 'number' ? flight : flight.id;
  return this.http.delete<Flight>(this.flightUrl + '/' + id + '/delete', httpOptions).pipe(
    tap(_ => console.log(`deleted flight id=${id}`))
  );
}

searchFlights(placeFromId: Number, placeToId: Number, timeBegin: String): Observable<Flight[]> {
  return this.http.get<Flight[]>(this.flightUrl + '/search?placeFromId=' + placeFromId + '&placeToId=' + placeToId + '&timeBegin=' + timeBegin);
}

}
