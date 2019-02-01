import { FlightRating } from './../../model/aircompany/flight-rating.model';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FlightRatingService {

  private flightRatingUrl = 'http://localhost:8080/flightratings';

  constructor(
    private http: HttpClient
  ) { }

  getRatings(): Observable<FlightRating[]> {
    return this.http.get<FlightRating[]>(this.flightRatingUrl + '/all');
  }

  getRatingById(id: number): Observable<FlightRating> {
    return this.http.get<FlightRating>(this.flightRatingUrl + '/' + id);
  }

  addRating(rating: FlightRating): Observable<Object> {
    return this.http.post<FlightRating>(this.flightRatingUrl + '/new', rating, httpOptions);
  }

  updateRating(rating: FlightRating): Observable<Object> {
    return this.http.put<FlightRating>(this.flightRatingUrl + '/' + rating.id + '/update', rating, httpOptions);
  }

  removeRating(rating: FlightRating | number): Observable<FlightRating> {
    const id = typeof rating === 'number' ? rating : rating.id;
    return this.http.delete<FlightRating>(this.flightRatingUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted flightRating id=${id}`))
    );
  }

}
