import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HotelsListComponent } from './hotels-list/hotels-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyDatePickerModule } from 'mydatepicker';
import { NgSelectModule } from '@ng-select/ng-select';
import { OwlDateTimeModule, OwlNativeDateTimeModule, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { HotelDetailsComponent } from './hotel-details/hotel-details.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';

export const DATE_NATIVE_FORMATS = {
  fullPickerInput: {year: 'numeric', month: 'numeric', day: 'numeric', hour: 'numeric', minute: 'numeric', hour12: false},
  datePickerInput: {year: 'numeric', month: 'numeric', day: 'numeric', hour12: false},
  timePickerInput: {hour: 'numeric', minute: 'numeric', hour12: false},
  monthYearLabel: {year: 'numeric', month: 'short'},
  dateA11yLabel: {year: 'numeric', month: 'long', day: 'numeric'},
  monthYearA11yLabel: {year: 'numeric', month: 'long'},
  };

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    MyDatePickerModule,
    NgSelectModule,
    SharedModule,
    RouterModule
  ],
  declarations: [HotelsListComponent, HotelDetailsComponent],
  providers: [
    // { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
    { provide: OWL_DATE_TIME_FORMATS, useValue: DATE_NATIVE_FORMATS  }
  ]
})
export class HotelsModule { }
