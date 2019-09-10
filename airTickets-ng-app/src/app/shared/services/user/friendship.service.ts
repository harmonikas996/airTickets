import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Friendship } from '../../model/user/friendship';
import { Observable } from 'rxjs';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FriendshipService {

  private friendshipUrl = 'http://localhost:8080/friendship';

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  getFriends(): Observable<Friendship[]> {
    return this.http.get<Friendship[]>(this.friendshipUrl + '/friends/' + this.token.getUsername());
  }

  getFriendRequests(): Observable<Friendship[]> {
    return this.http.get<Friendship[]>(this.friendshipUrl + '/request/' + this.token.getUsername());
  }

  removeFriendship(friend: Friendship | number): Observable<Friendship> {
    const id = typeof friend === 'number' ? friend : friend.id;
    return this.http.delete<Friendship>(this.friendshipUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted Friendship id=${id}`))
    );
  }

  accept(friend: Friendship): Observable<Object> {

    return this.http.put(this.friendshipUrl + '/' + friend.id + '/update', friend, httpOptions);
  }

  addFriend(friend: Friendship): Observable<Friendship> {
    return this.http.post<Friendship>(this.friendshipUrl + '/new', friend, httpOptions);
  }

}
