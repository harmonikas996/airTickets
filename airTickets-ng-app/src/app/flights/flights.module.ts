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
import { BetweenStepsComponent } from '../shared/utils/between-steps/between-steps.component';
import { SharedModule } from '../shared/shared.module';
import { RouterModule } from '@angular/router';
import { AircompanyDetailsComponent } from './aircompany-details/aircompany-details.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ReactiveFormsModule,
    MyDatePickerModule,
    NgSelectModule,
    SharedModule,
    RouterModule
  ],
  declarations: [
    FlightsReservationComponent,
    FlightsResListComponent,
    PassengersDetailsComponent,
    BetweenStepsComponent,
    AircompanyDetailsComponent
  ],
  exports: [
    FlightsReservationComponent,
    FlightsResListComponent,
    BetweenStepsComponent
  ]
})
export class FlightsModule { }
