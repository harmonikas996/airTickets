import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { DashboardsRoutingModule } from './/dashboards-routing.module';
import { CompanyProfileComponent } from './admin-dashboard/rentacar/company-profile/company-profile.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VehiclesListComponent } from './admin-dashboard/rentacar/vehicles-list/vehicles-list.component';
import { VehicleDetailsComponent } from './admin-dashboard/rentacar/vehicle-details/vehicle-details.component';
import { VehicleNewComponent } from './admin-dashboard/rentacar/vehicle-new/vehicle-new.component';

@NgModule({
  imports: [
    CommonModule,
    DashboardsRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [AdminDashboardComponent, CompanyProfileComponent, VehiclesListComponent, VehicleDetailsComponent, VehicleNewComponent],
  
})
export class DashboardsModule { }
