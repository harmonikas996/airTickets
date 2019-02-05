import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehiclesListComponent } from './vehicles-list/vehicles-list.component';
import { VehiclesRoutingModule } from './vehicles-routing.module';
import { RentacarsListComponent } from './rentacars-list/rentacars-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyDatePickerModule } from 'mydatepicker';
import { NgSelectModule } from '@ng-select/ng-select';
import { OwlDateTimeModule, OwlNativeDateTimeModule, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { RentacarDetailsComponent } from './rentacar-details/rentacar-details.component';

// export const MY_MOMENT_FORMATS = {
//   parseInput: 'dd.DD.YYYY LT',
//   fullPickerInput: 'dd.DD.YYYY LT',
//   datePickerInput: 'dd.DD.YYYY',
//   timePickerInput: 'LT',
//   monthYearLabel: 'MMM YYYY',
//   dateA11yLabel: 'LL',
//   monthYearA11yLabel: 'MMMM YYYY',
// };

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
    VehiclesRoutingModule,
  ],
  declarations: [VehiclesListComponent, RentacarsListComponent, RentacarDetailsComponent],
  providers: [
    // { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
    { provide: OWL_DATE_TIME_FORMATS, useValue: DATE_NATIVE_FORMATS  }
  ]
})
export class VehiclesModule { }
