import { TokenStorageService } from './../../../user-authentication/service/token-storage.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { User } from '../../model/user/user.model';

@Component({
  selector: 'app-login-widget',
  templateUrl: './login-widget.component.html',
  styleUrls: ['./login-widget.component.css']
})
export class LoginWidgetComponent implements OnInit {

  info: any;
  user: User;
  constructor(private token: TokenStorageService, private userService: UserService) { }

  ngOnInit() {
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
  }

  getProfile() {
    this.userService.getUserById().subscribe(user => this.user = user);
  }

  logout() {
    this.token.signOut();
    window.location.reload();
  }

}
