import { RoomRating } from './../../../model/hotel/room-rating';
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
export class RoomRatingService {

  private roomRatingUrl = 'http://localhost:8080/roomRatings';

  constructor(
    private http: HttpClient
  ) { }

  getRatings(): Observable<RoomRating[]> {
    return this.http.get<RoomRating[]>(this.roomRatingUrl + '/all');
  }

  getRatingById(id: number): Observable<RoomRating> {
    return this.http.get<RoomRating>(this.roomRatingUrl + '/' + id);
  }

  getRatingByRoomId(id: number): Observable<RoomRating> {
    return this.http.get<RoomRating>(this.roomRatingUrl + '/room/' + id);
  }

  addRating(rating: RoomRating): Observable<Object> {
    return this.http.post<RoomRating>(this.roomRatingUrl + '/new', rating, httpOptions);
  }

  updateRating(rating: RoomRating): Observable<Object> {
    return this.http.put<RoomRating>(this.roomRatingUrl + '/' + rating.id + '/update', rating, httpOptions);
  }

  removeRating(rating: RoomRating | number): Observable<RoomRating> {
    const id = typeof rating === 'number' ? rating : rating.id;
    return this.http.delete<RoomRating>(this.roomRatingUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted RoomRating id=${id}`))
    );
  }
}
