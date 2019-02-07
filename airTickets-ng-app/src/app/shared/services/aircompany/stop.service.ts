import { Stop } from './../../model/aircompany/stop';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class StopService {

  private stopUrl = 'http://localhost:8080/stops';

  constructor(
    private http: HttpClient
  ) { }

  getStops(): Observable<Stop[]> {
    return this.http.get<Stop[]>(this.stopUrl + '/all');
  }

  getStopById(id: number): Observable<Stop> {
    return this.http.get<Stop>(this.stopUrl + '/' + id);
  }

  addStop(stop: Stop): Observable<Object> {
    return this.http.post<Stop>(this.stopUrl + '/new', stop, httpOptions);
  }

  updateStop(stop: Stop): Observable<Object> {
    return this.http.put<Stop>(this.stopUrl + '/' + stop.id + '/update', stop, httpOptions);
  }

  removeStop(stop: Stop | number): Observable<Stop> {
    const id = typeof stop === 'number' ? stop : stop.id;
    return this.http.delete<Stop>(this.stopUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted stop id=${id}`))
    );
  }

}
