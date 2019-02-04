import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehiclesListComponent } from './vehicles-list/vehicles-list.component';
import { VehiclesRoutingModule } from './vehicles-routing.module';
import { RentacarsListComponent } from './rentacars-list/rentacars-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MyDatePickerModule } from 'mydatepicker';
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MyDatePickerModule,
    NgSelectModule,
    VehiclesRoutingModule,
  ],
  declarations: [VehiclesListComponent, RentacarsListComponent]
})
export class VehiclesModule { }
