import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router, Route, CanActivateChild, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';

@Injectable()
export class RoleGuard implements CanActivate {


  constructor(private token: TokenStorageService, private router: Router) {
  }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const authorithies = this.token.getAuthorities();

    console.log('Role: ' + authorithies);
    console.log('Trazena uloga: ' + next.data.role);
    console.log(authorithies.includes(next.data.role));
    if (authorithies.includes(next.data.role)) {
      return true;
    }
    console.log('Nema dozvolu!');
    // navigate to not found page
    //this.router.navigate(['/login']);
    return false;
  }

}