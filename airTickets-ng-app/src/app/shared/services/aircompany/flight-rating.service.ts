import { AirCompanyRating } from './../../model/aircompany/aircompany-rating';
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
  private aircompanyRatingUrl = 'http://localhost:8080/aircompanyratings';

  constructor(
    private http: HttpClient
  ) { }

  /***************** AIR COMAPANY *********************/

  getAirCompanyRatingById(id: number): Observable<AirCompanyRating> {
    return this.http.get<AirCompanyRating>(this.aircompanyRatingUrl + '/' + id);
  }

  getRatingByAirCompanyId(id: number): Observable<AirCompanyRating> {
    return this.http.get<AirCompanyRating>(this.aircompanyRatingUrl + '/company/' + id);
  }

  addAirCompanyRating(rating: AirCompanyRating): Observable<Object> {
    return this.http.post<AirCompanyRating>(this.aircompanyRatingUrl + '/new', rating, httpOptions);
  }

  getRatingsByAirCompany(id: number): Observable<number> {
    return this.http.get<number>(this.aircompanyRatingUrl + '/all/' + id);
  }

  /***************** FLIGHT *********************/

  getRatings(): Observable<FlightRating[]> {
    return this.http.get<FlightRating[]>(this.flightRatingUrl + '/all');
  }

  getRatingById(id: number): Observable<FlightRating> {
    return this.http.get<FlightRating>(this.flightRatingUrl + '/' + id);
  }

  getRatingByFlightId(id: number): Observable<FlightRating> {
    return this.http.get<FlightRating>(this.flightRatingUrl + '/flight/' + id);
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
