import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared/services/user/user.service';
import { FormBuilder } from '@angular/forms';
import { TokenStorageService } from '../service/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(
    private tokenStorage: TokenStorageService,
    private userService: UserService,
    private formBuilder: FormBuilder,
    private location: Location,
    private router: Router
  ) { }

  ngOnInit() {
    if(this.tokenStorage.getToken) {
      this.router.navigate(['/']);
    }
  }

}
