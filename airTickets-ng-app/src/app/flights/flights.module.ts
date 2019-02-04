import { FlightsReservationComponent } from './flights-reservation/flights-reservation.component';
import { AppModule } from './../app.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyDatePickerModule } from 'mydatepicker';
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MyDatePickerModule,
    NgSelectModule
  ],
  declarations: [FlightsReservationComponent]
})
export class FlightsModule { }
