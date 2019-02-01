import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../model/user/user.model'
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { tokenKey } from '@angular/core/src/view';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private profileUrl = 'http://localhost:8080/api/user/profile';

  constructor(
    private http: HttpClient,
    private token: TokenStorageService
    ) { }

  getUserById(): Observable<User> {
    return this.http.get<User>(this.profileUrl + '/' + this.token.getUsername());
  }
}