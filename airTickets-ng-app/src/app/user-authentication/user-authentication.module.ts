import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ThankYouComponent } from './register/thank-you/thank-you.component';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
  declarations: [ThankYouComponent],
  exports: [
    ReactiveFormsModule,
    FormsModule
  ]
})
export class UserAuthenticationModule { }
