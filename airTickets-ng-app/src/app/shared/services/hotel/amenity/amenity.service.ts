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
  private amenityReservationUrl = 'http://localhost:8080/amenityReservations';


  constructor(
    private http: HttpClient
  ) { }

  getAmenities(): Observable<Amenity[]> {
    return this.http.get<Amenity[]>(this.amenitiesUrl + '/all');
  }

  getAmenitiesByHotel(id: number): Observable<Amenity[]> {
    return this.http.get<Amenity[]>(this.amenitiesUrl + '/hotel/' + id);
  }

  getAmenityById(id: number): Observable<Amenity> {
    return this.http.get<Amenity>(this.amenitiesUrl + '/' + id);
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

  makeReservation(amenities: any[], hotelReservationId: number): Observable<boolean> {
    return this.http.put<boolean>(this.amenityReservationUrl + '/makeReservation/' + hotelReservationId, amenities, httpOptions);
  }
}
