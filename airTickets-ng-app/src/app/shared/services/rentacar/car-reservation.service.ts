import { CarReservation } from './../../model/rentacar/car-reservation';
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
export class CarReservationService {

  private carResUrl = 'http://localhost:8080/carreservations';

  constructor(
    private http: HttpClient
  ) { }

  getCarReservations(): Observable<CarReservation[]> {
    return this.http.get<CarReservation[]>(this.carResUrl + '/all');
  }

  getCarReservationsByUser(id: string): Observable<CarReservation[]> {
    return this.http.get<CarReservation[]>(this.carResUrl + '/user/' + id);
  }

  getQuickCarReservationsByCompanyId(id: number): Observable<CarReservation[]> {
    return this.http.get<CarReservation[]>(this.carResUrl + '/quick/' + id);
  }

  addCarReservation(res: CarReservation): Observable<Object> {
    return this.http.post<CarReservation>(this.carResUrl + '/new', res, httpOptions);
  }

  updateBranch(res: CarReservation): Observable<Object> {
    return this.http.put<CarReservation>(this.carResUrl + '/' + res.id + '/update', res, httpOptions);
  }

  removeBranch(res: CarReservation | number): Observable<CarReservation> {
    const id = typeof res === 'number' ? res : res.id;
    return this.http.delete<CarReservation>(this.carResUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted CarReservation id=${id}`))
    );
  }

}
