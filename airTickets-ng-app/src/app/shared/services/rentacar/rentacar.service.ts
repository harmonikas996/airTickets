import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { RentACar } from '../../model/rentacar/rentacar.model';
import { UserService } from '../user/user.service';
import { User } from '../../model/user/user.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class RentacarService {

  private rentacarsUrl = 'http://localhost:8080/rentacars';

  constructor(
    private http: HttpClient,
    private userService: UserService
  ) { }

  getRentacars(): Observable<RentACar[]> {
    return this.http.get<RentACar[]>(this.rentacarsUrl + '/all');
  }
  
  getLocations(): Observable<String[]> {
    return this.http.get<String[]>("http://localhost:8080/branchoffices/locations");
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

  removeRentacar(rentacar: RentACar | number):  Observable<RentACar> {
    const id = typeof rentacar === 'number' ? rentacar : rentacar.id;
    return this.http.delete<RentACar>(this.rentacarsUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted rentacar id=${id}`))
    );
  }

  addRentacar(room: RentACar): Observable<Object> {
    return this.http.post<RentACar>(this.rentacarsUrl + '/new', room, httpOptions);
  }

  addAdmin(companyId: any, adminEmail: String): void {
    let user: User;

    this.userService.getUserByEmail(adminEmail).subscribe(
      userToBeAdmin => user = userToBeAdmin,
      error => console.log('Error: ', error),
      () => {
        user.type = 'rentacar';
        user.company = companyId;
        this.userService.updateNotLoggedUser(user).subscribe();
      }
    );
  }

  searchRentacars(name: String, location: String, timeBegin: String, timeEnd: String): Observable<RentACar[]> {
    return this.http.get<RentACar[]>(this.rentacarsUrl + '/search?name=' + name + '&location=' + location + '&timeBegin=' + timeBegin + '&timeEnd=' + timeEnd);
  }
}
