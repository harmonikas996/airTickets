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
    } else {
      this.RegisterForm = this.formBuilder.group({
        email: ['', Validators.required],
        // password: ['', Validators.required],
        password: this.formBuilder.group({
          password: ['', [Validators.required]],
          confirmPassword: ['']
        }, {validator: this.checkPasswords }),
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
      const formData = this.RegisterForm.value;
      formData.password = this.RegisterForm.get('password').get('password').value;

      this.auth.signUp(formData).subscribe(
        data => {
          this.message = data;
          this.isRegistrationFailed = false;
          location.assign('/thank-you/' + formData.firstName);
        },
        error => {
          this.message = error.error.result;
          this.isRegistrationFailed = true;
        }
      );
    }
  }

  checkPasswords(group: FormGroup) {
    const pass = group.get('password').value;
    const confirmPass = group.get('confirmPassword').value;

    if (pass === confirmPass) {
      return null;
    } else {
      return { notSame: true };
    }
  }

}
