import { Location } from '@angular/common';
import { Observable } from 'rxjs';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

import { AuthService } from '../../user-authentication/service/auth.service';
import { TokenStorageService } from '../../user-authentication/service/token-storage.service';
import { User } from 'src/app/shared/model/user/user.model';
import { UserService } from 'src/app/shared/services/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  user: Observable<User>;
  LoginForm: FormGroup;

  constructor(
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private location: Location,
    private router: Router
  ) { }

  ngOnInit() {
    // srediti preuzimanje ID-a tako sto proveris kojoj kompaniji je dodeljen ulogovani admin
    

    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.router.navigate(['/']);
    }
    else {
      this.LoginForm = this.formBuilder.group({
        username: ['', Validators.required],
        password: ['', Validators.required]
      });
    }
  }

  onSubmit() {
    if (this.LoginForm.valid) {

      console.log(this.LoginForm);

      this.authService.attemptAuth(this.LoginForm.value).subscribe(
        data => {
          this.tokenStorage.saveToken(data.accessToken);
          this.tokenStorage.saveUsername(data.username);
          // console.log('Korisnik: ' + data.username);
          // alert('EXPIRES IN: ' + data.expiresIn);
          this.tokenStorage.saveUserId(data.userId);
          this.tokenStorage.saveAuthorities(data.authorities);
          this.isLoginFailed = false;
          this.isLoggedIn = true;
          this.roles = this.tokenStorage.getAuthorities();
          this.location.replaceState('/');
          this.reloadPage();
        },
        error => {
          console.log(error);
          this.errorMessage = error.error;
          this.isLoginFailed = true;
        }
      );
    }
  }

  onCancel() {
    this.location.back();
  }

  reloadPage() {
    window.location.reload();
  }
}


