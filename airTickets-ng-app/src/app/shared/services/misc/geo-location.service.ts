import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Geo } from '../../model/misc/geo.model';

@Injectable({
  providedIn: 'root'
})
export class GeoLocationService {

  private geoUrl = 'https://eu1.locationiq.com/v1/reverse.php?key=29491cc2444b02';

  constructor(
    private http: HttpClient
  ) { }

  getAddress(lat: string, long: string): Observable<String> {
    return this.http.get<String>(this.geoUrl + '&lat=' + lat + '&lon=' + long + '&format=json');
  }
}
