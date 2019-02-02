import { Aircompany } from './../../model/aircompany/aircompany.model';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AircompanyService {

  private aircomapnyurl = 'http://localhost:8080/aircompanies';

  constructor(private http: HttpClient) { }

  getAircompanies(): Observable<Aircompany[]>{
    return this.http.get<Aircompany[]>(this.aircomapnyurl + '/all');
  }

  getAircompanyById(id: number): Observable<Aircompany>{
    return this.http.get<Aircompany>(this.aircomapnyurl + '/' + id);
  }

  updateAircompany(aircompany: Aircompany): Observable<Object>{
    return this.http.put(this.aircomapnyurl + '/' + aircompany.id + '/update', aircompany, httpOptions);
  }

  getAircompanyByAdminUsername(adminUsername: String): Observable<Aircompany>{
    return this.http.get<Aircompany>(this.aircomapnyurl + '/admin/' + adminUsername);
  }

  removeAirCompany(aircomapny: Aircompany | number): Observable<Aircompany>{
    const id = typeof aircomapny === 'number' ? aircomapny : aircomapny.id;
    return this.http.delete<Aircompany>(this.aircomapnyurl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted aircompany id=${id}`))
    );
  }

}

