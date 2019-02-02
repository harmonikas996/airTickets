import { User } from './../../../shared/model/user/user.model';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/shared/services/user/user.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: Observable<User>;
  userProfileForm: FormGroup;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private token: TokenStorageService
  ) { }

  ngOnInit() {
    this.userProfileForm = this.formBuilder.group({
      id: [''],
      email: [''],
      company: [''],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      city: ['', Validators.required],
      phone: ['', Validators.required],
      activated: [''],
      bonusPoints: [''],
      lastPasswordResetDate: ['']
    });
    this.getUserById();
  }

  getUserById(): void {
    this.user = this.userService.getUserById().pipe(
      tap(user => this.userProfileForm.patchValue(user))
    );
  }

  onSubmit() {
    // if (this.userProfileForm.valid) {
    //   this.userService(this.aircompanyProfileForm.value).subscribe((response) => {
    //     console.log('Response is: ', response);
    //     location.reload();
    //  },
    //  (error) => {
    //     //catch the error
    //     console.error('An error occurred, ', error);
    //  });
    //  };
  }

}
