import { Amenity } from './../../../model/hotel/amenity.model';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AmenityService {

  private amenitiesUrl = 'http://localhost:8080/amenities';

  constructor(
    private http: HttpClient
  ) { }

  getAmenities(): Observable<Amenity[]> {
    return this.http.get<Amenity[]>(this.amenitiesUrl + '/all');
  }

  getAmenityById(id: number): Observable<Amenity> {
    return this.http.get<Amenity>(this.amenitiesUrl + '/' + id);
  }

  // TODO
  getAmenitiesByHotelId(id: number): Observable<Amenity> {
    return null;
  }

  addAmenity(amenity: Amenity): Observable<Object> {
    return this.http.post<Amenity>(this.amenitiesUrl + '/new', amenity, httpOptions);
  }

  updateAmenity(amenity: Amenity): Observable<Object> {
    return this.http.put<Amenity>(this.amenitiesUrl + '/' + amenity.id + '/update', amenity, httpOptions);
  }

  removeAmenity(amenity: Amenity | number): Observable<Amenity> {
    const id = typeof amenity === 'number' ? amenity : amenity.id;
    return this.http.delete<Amenity>(this.amenitiesUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted amenity id=${id}`))
    );
  }
}
