import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { DashboardsRoutingModule } from './/dashboards-routing.module';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { CompanyProfileComponent } from './admin-dashboard/rentacar/company-profile/company-profile.component';
import { VehicleDetailsComponent } from './admin-dashboard/rentacar/vehicle-details/vehicle-details.component';
import { VehicleNewComponent } from './admin-dashboard/rentacar/vehicle-new/vehicle-new.component';
import { VehiclesListComponent } from './admin-dashboard/rentacar/vehicles-list/vehicles-list.component';
import { BranchesListComponent } from './admin-dashboard/rentacar/branches-list/branches-list.component';
import { BranchNewComponent } from './admin-dashboard/rentacar/branch-new/branch-new.component';
import { BranchDetailsComponent } from './admin-dashboard/rentacar/branch-details/branch-details.component';



@NgModule({
  imports: [
    CommonModule,
    DashboardsRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [
    AdminDashboardComponent,
    CompanyProfileComponent,
    VehiclesListComponent,
    VehicleDetailsComponent,
    VehicleNewComponent,
    BranchesListComponent,
    BranchNewComponent,
    BranchDetailsComponent
  ],

})
export class DashboardsModule { }
