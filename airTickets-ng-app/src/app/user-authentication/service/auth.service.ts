import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JwtResponse } from './jwt-response';
import { SignUpInfo } from './signup-info';
import { User } from 'src/app/shared/model/user/user.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8080/auth/login';
  private signupUrl = 'http://localhost:8080/auth/register';

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: Object): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(user: User): Observable<string> {
    return this.http.post<string>(this.signupUrl, user, httpOptions);
  }
}
