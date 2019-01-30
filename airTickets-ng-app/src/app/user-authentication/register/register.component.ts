import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { TokenStorageService } from '../service/token-storage.service';
import { tokenKey } from '@angular/core/src/view';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  RegisterForm: FormGroup;
  message: string;
  isRegistrationFailed: boolean;

  constructor(
    private auth: AuthService,
    private token: TokenStorageService,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    if (this.token.getToken()) {
      this.router.navigate(['/']);
    }
    else {
      this.RegisterForm = this.formBuilder.group({
        email: ['', Validators.required],
        password: ['', Validators.required],
        // repeatPassword: ['', Validators.required],
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        city: [''],
        phone: ['']
      });
    }
  }

  onSubmit() {
    if (this.RegisterForm.valid) {
      console.log(this.RegisterForm);

      // if (this.RegisterForm.controls.password.value != this.RegisterForm.controls.repeatPassword.value) {
      //   this.RegisterForm.controls.repeatPassword.setErrors({'incorrect': true});
        
      // }

      this.auth.signUp(this.RegisterForm.value).subscribe(
        data => {
          this.message = data;
          // if(this.message == '')
          this.isRegistrationFailed = false;
        },
        error => {
          //console.log(error);
          this.message = error.error.result;
          this.isRegistrationFailed = true;
        }
      )
    }
  }

}
