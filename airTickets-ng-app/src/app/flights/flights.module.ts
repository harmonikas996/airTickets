import { FlightsReservationComponent } from './flights-reservation/flights-reservation.component';
import { AppModule } from './../app.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyDatePickerModule } from 'mydatepicker';
import { NgSelectModule } from '@ng-select/ng-select';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { FlightsResListComponent } from './flights-res-list/flights-res-list.component';
import { PassengersDetailsComponent } from './passengers-details/passengers-details.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ReactiveFormsModule,
    MyDatePickerModule,
    NgSelectModule
  ],
  declarations: [
    FlightsReservationComponent,
    FlightsResListComponent,
    PassengersDetailsComponent,
  ],
  exports: [
    FlightsReservationComponent,
    FlightsResListComponent,
  ]
})
export class FlightsModule { }
