import { CarRating } from './../../model/rentacar/car-rating';
import { RentAcarRating } from './../../model/rentacar/rentAcar-rating';
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
export class CarRatingService {

  private rentcarRatingUrl = 'http://localhost:8080/rentacarratings';
  private carRatingUrl = 'http://localhost:8080/carratings';

  constructor(
    private http: HttpClient
  ) { }

  /************ CAR RATING *********************/

  getCarRatings(): Observable<CarRating[]> {
    return this.http.get<CarRating[]>(this.carRatingUrl + '/all');
  }

  getCarRatingById(id: number): Observable<CarRating> {
    return this.http.get<CarRating>(this.carRatingUrl + '/' + id);
  }

  addCarRating(rating: any): Observable<Object> {
    return this.http.post<CarRating>(this.carRatingUrl + '/new', rating, httpOptions);
  }

  getCarRatingByVehicleId(id: number): Observable<CarRating> {
    return this.http.get<CarRating>(this.carRatingUrl + '/car/' + id);
  }

  getRatingByVehicle(id: number): Observable<number> {
    return this.http.get<number>(this.carRatingUrl + '/all/' + id);
  }

  /************ RENT A CAR RATING *********************/
  getRatingByRentACar(id: number): Observable<number> {
    return this.http.get<number>(this.rentcarRatingUrl + '/all/' + id);
  }

  getRentACarRatings(): Observable<RentAcarRating[]> {
    return this.http.get<RentAcarRating[]>(this.rentcarRatingUrl + '/all');
  }

  getRentACarRatingById(id: number): Observable<RentAcarRating> {
    return this.http.get<RentAcarRating>(this.rentcarRatingUrl + '/' + id);
  }

  getRentACarRatingByRentACarId(id: number): Observable<RentAcarRating> {
    return this.http.get<RentAcarRating>(this.rentcarRatingUrl + '/rentacar/' + id);
  }

  addRentACarRating(rating: any): Observable<Object> {
    return this.http.post<RentAcarRating>(this.rentcarRatingUrl + '/new', rating, httpOptions);
  }

  updateRentACarRating(rating: RentAcarRating): Observable<Object> {
    return this.http.put<RentAcarRating>(this.rentcarRatingUrl + '/' + rating.id + '/update', rating, httpOptions);
  }

  removeRentACarRating(rating: RentAcarRating | number): Observable<RentAcarRating> {
    const id = typeof rating === 'number' ? rating : rating.id;
    return this.http.delete<RentAcarRating>(this.rentcarRatingUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted RentAcarRating id=${id}`))
    );
  }
}
