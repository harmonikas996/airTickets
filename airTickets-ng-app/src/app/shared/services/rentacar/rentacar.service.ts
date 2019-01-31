import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators'

import { RentACar } from '../../model/rentacar/rentacar.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RentacarService {

  private rentacarsUrl = 'http://localhost:8080/rentacars';

  constructor(
    private http: HttpClient
  ) { }

  getRentacars(): Observable<RentACar[]> {
    return this.http.get<RentACar[]>(this.rentacarsUrl + '/all');
  } 

  getRentacarById(id: number): Observable<RentACar> {
    return this.http.get<RentACar>(this.rentacarsUrl + '/' + id);
  } 
  
  getRentacarByAdminUsername(adminUsername: String): Observable<RentACar> {
    return this.http.get<RentACar>(this.rentacarsUrl + '/admin/' + adminUsername);
  } 

  updateRentacar(rentacar: RentACar): Observable<Object> {
    return this.http.put(this.rentacarsUrl + '/' + rentacar.id + '/update', rentacar, httpOptions);
  }
}
