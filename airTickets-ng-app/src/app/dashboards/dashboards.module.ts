import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { DashboardsRoutingModule } from './/dashboards-routing.module';
import { RentacarDashboardComponent } from './admin-dashboard/rentacar-dashaboard/rentacar-dashboard.component';
import { CompanyProfileComponent } from './admin-dashboard/rentacar-dashaboard/company-profile/company-profile.component';
import { VehicleDetailsComponent } from './admin-dashboard/rentacar-dashaboard/vehicle-details/vehicle-details.component';
import { VehicleNewComponent } from './admin-dashboard/rentacar-dashaboard/vehicle-new/vehicle-new.component';
import { VehiclesListComponent } from './admin-dashboard/rentacar-dashaboard/vehicles-list/vehicles-list.component';
import { BranchesListComponent } from './admin-dashboard/rentacar-dashaboard/branches-list/branches-list.component';
import { BranchNewComponent } from './admin-dashboard/rentacar-dashaboard/branch-new/branch-new.component';
import { BranchDetailsComponent } from './admin-dashboard/rentacar-dashaboard/branch-details/branch-details.component';
import { AuthGuard } from '../shared/services/guards/auth-guard.service';
import { RoleGuard } from '../shared/services/guards/role-guard.service';



@NgModule({
  imports: [
    CommonModule,
    DashboardsRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [
    RentacarDashboardComponent,
    CompanyProfileComponent,
    VehiclesListComponent,
    VehicleDetailsComponent,
    VehicleNewComponent,
    BranchesListComponent,
    BranchNewComponent,
    BranchDetailsComponent
  ],
  providers: [
    AuthGuard,
    RoleGuard
  ]

})
export class DashboardsModule { }
