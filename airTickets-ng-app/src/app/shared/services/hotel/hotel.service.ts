import { Room } from './../../model/hotel/room.model';
import { Hotel } from 'src/app/shared/model/hotel/hotel.model';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { UserService } from '../user/user.service';
import { User } from '../../model/user/user.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  private hotelsUrl = 'http://localhost:8080/hotels';

  constructor(
    private http: HttpClient,
    private userService: UserService
  ) { }

  gethotels(): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.hotelsUrl + '/all');
  }

  getHotelById(id: number): Observable<Hotel> {
    return this.http.get<Hotel>(this.hotelsUrl + '/' + id);
  }

  getFreeRooms(email: string, datebegin: String, dateEnd: String): Observable<Hotel[]> {
    return this.http.get<Hotel[]>('http://localhost:8080/rooms/free?user=' + email + '&datebegin=' + datebegin + '&dateEnd=' + dateEnd);
  }

  getHotelByAdminUsername(adminUsername: String): Observable<Hotel> {
    return this.http.get<Hotel>(this.hotelsUrl + '/admin/' + adminUsername);
  }

  updateHotel(hotel: Hotel): Observable<Object> {
    return this.http.put(this.hotelsUrl + '/' + hotel.id + '/update', hotel, httpOptions);
  }

  getLocation(): Observable<String[]>{
    return this.http.get<String[]>(this.hotelsUrl + '/locations');
  }

  removeHotel(hotel: Hotel | number):  Observable<Hotel> {
    const id = typeof hotel === 'number' ? hotel : hotel.id;
    return this.http.delete<Hotel>(this.hotelsUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted hotel id=${id}`))
    );
  }

  addHotel(hotel: Hotel): Observable<Object> {
    return this.http.post<Hotel>(this.hotelsUrl + '/new', hotel, httpOptions);
  }

  addAdmin(companyId: any, adminEmail: String): Observable<User> {
    let user: User;

    this.userService.getUserByEmail(adminEmail).subscribe(
      userToBeAdmin => user = userToBeAdmin,
      error => console.log('Error: ', error),
      () => {
        user.type = 'hotel';
        user.company = companyId;
        this.userService.updateAuthority(user).subscribe(
          userToBeAdmin => user = userToBeAdmin,
          error => console.log('Error: ', error),
          () => {
            return user;
          }
        );
      }
    );

    return null;
  }

  searchHotels(name: String, location: String, timeBegin: String, timeEnd: String ): Observable<Hotel[]> {
    return this.http.get<Hotel[]>(this.hotelsUrl +
      '/search?name=' + name +
      '&location=' + location +
      '&timeBegin=' + timeBegin +
      '&timeEnd=' + timeEnd
      );
  }

  getReservedRooms(rentacarId: number, dateBegin: String, dateEnd: String): Observable<Room[]> {
    return this.http.get<Room[]>(this.hotelsUrl + '/reservedRooms?id=' + rentacarId + '&dateBegin=' + dateBegin + '&dateEnd=' + dateEnd);
  }


}
