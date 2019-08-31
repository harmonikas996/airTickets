import { User } from 'src/app/shared/model/user/user.model';
import { Aircompany } from './../../model/aircompany/aircompany.model';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { UserService } from '../user/user.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AircompanyService {

  private aircomapnyurl = 'http://localhost:8080/aircompanies';

  constructor(
    private http: HttpClient,
    private userService: UserService
  ) { }

  getAircompanies(): Observable<Aircompany[]>{
    return this.http.get<Aircompany[]>(this.aircomapnyurl + '/all');
  }

  getAircompanyById(id: number): Observable<Aircompany> {
    return this.http.get<Aircompany>(this.aircomapnyurl + '/' + id);
  }

  updateAircompany(aircompany: Aircompany): Observable<Object> {
    return this.http.put(this.aircomapnyurl + '/' + aircompany.id + '/update', aircompany, httpOptions);
  }

  getAircompanyByAdminUsername(adminUsername: String): Observable<Aircompany> {
    return this.http.get<Aircompany>(this.aircomapnyurl + '/admin/' + adminUsername);
  }

  removeAirCompany(aircomapny: Aircompany | number): Observable<Aircompany> {
    const id = typeof aircomapny === 'number' ? aircomapny : aircomapny.id;
    return this.http.delete<Aircompany>(this.aircomapnyurl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted aircompany id=${id}`))
    );
  }

  addAircompany(aircomp: Aircompany): Observable<Object> {
    return this.http.post<Aircompany>(this.aircomapnyurl + '/new', aircomp, httpOptions);
  }

  addAdmin(companyId: any, adminEmail: String): void {
    let user: User;

    this.userService.getUserByEmail(adminEmail).subscribe(
      userToBeAdmin => user = userToBeAdmin,
      error => console.log('Error: ', error),
      () => {
        user.type = 'aircompany';
        user.company = companyId;
        this.userService.updateAuthority(user).subscribe();
      }
    );
  }
}

