import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private profileUrl = 'http://localhost:8080/api/user/profile';

  constructor(private http: HttpClient) { }

  getProfile(): Observable<string> {
    return this.http.get(this.profileUrl, { responseType: 'text' });
  }
}
