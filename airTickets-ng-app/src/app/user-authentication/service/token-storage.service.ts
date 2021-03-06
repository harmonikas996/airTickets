import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUsername';
const USERID_KEY = 'UserId';
const AUTHORITIES_KEY = 'AuthAuthorities';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  private roles: Array<string> = [];
  constructor() { }

  signOut() {
    window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUsername(username: string) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, username);
  }

  public getUsername(): string {
    return sessionStorage.getItem(USERNAME_KEY);
  }

  public saveUserId(userId: number) {
    window.sessionStorage.removeItem(USERID_KEY);
    window.sessionStorage.setItem(USERID_KEY, userId.toString());
  }

  public getUserId(): string {
    return sessionStorage.getItem(USERID_KEY);
  }

  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];

    if (sessionStorage.getItem(TOKEN_KEY)) {
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach(authority => {
        this.roles.push(authority);
      });
    }

    return this.roles;
  }

  public isSysAdmin(): boolean {
    if (this.roles.includes('sysadmin')) {
      return true;
    } else {
      return false;
    }
  }

  public isHotel(): boolean {
    if (this.roles.includes('hotel')) {
    return true;
    } else {
      return false;
    }
  }

  public isRentacar(): boolean {
    if (this.roles.includes('rentacar')) {
    return true;
    } else {
      return false;
    }
  }

  public isAircompany(): boolean {
    if (this.roles.includes('aircompany')) {
      return true;
    } else {
      return false;
    }
  }

  public isUser(): boolean {
    if (this.roles.includes('client')) {
      return true;
    } else {
      return false;
    }
  }
}
