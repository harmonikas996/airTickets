import { HotelRating } from '../../../model/hotel/hotel-rating.model';
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
export class HotelRatingService {

  private hotelRatingUrl = 'http://localhost:8080/hotelRatings';

  constructor(
    private http: HttpClient
  ) { }

  getRatings(): Observable<HotelRating[]> {
    return this.http.get<HotelRating[]>(this.hotelRatingUrl + '/all');
  }

  getRatingById(id: number): Observable<HotelRating> {
    return this.http.get<HotelRating>(this.hotelRatingUrl + '/' + id);
  }

  getRatingByHotelId(id: number): Observable<HotelRating> {
    return this.http.get<HotelRating>(this.hotelRatingUrl + '/hotel/' + id);
  }

  getRatingsByHotel(id: number): Observable<number> {
    return this.http.get<number>(this.hotelRatingUrl + '/all/' + id);
  }

  addRating(rating: HotelRating): Observable<Object> {
    return this.http.post<HotelRating>(this.hotelRatingUrl + '/new', rating, httpOptions);
  }

  updateRating(rating: HotelRating): Observable<Object> {
    return this.http.put<HotelRating>(this.hotelRatingUrl + '/' + rating.id + '/update', rating, httpOptions);
  }

  removeRating(rating: HotelRating | number): Observable<HotelRating> {
    const id = typeof rating === 'number' ? rating : rating.id;
    return this.http.delete<HotelRating>(this.hotelRatingUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted HotelRating id=${id}`))
    );
  }
}
