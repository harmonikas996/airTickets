import { Airport } from '../../model/aircompany/airport.model';
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
export class AirportService {

  private airporturl = 'http://localhost:8080/airports';

  constructor(private http: HttpClient) { }

  getAirports(): Observable<Airport[]> {
    return this.http.get<Airport[]>(this.airporturl + '/all');
  }

  getAirportById(id: number): Observable<Airport> {
    return this.http.get<Airport>(this.airporturl + '/' + id);
  }

  updateAirport(airport: Airport): Observable<Object> {
    return this.http.put(this.airporturl + '/' + airport.id + '/update', airport, httpOptions);
  }

  addAirport(airport: Airport): Observable<Object> {
    return this.http.post<Airport>(this.airporturl + '/new', airport, httpOptions);
  }

  removeAirport(airport: Airport | number): Observable<Airport> {
    const id = typeof airport === 'number' ? airport : airport.id;
    return this.http.delete<Airport>(this.airporturl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted airport id=${id}`))
    );
  }

}
