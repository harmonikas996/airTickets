import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { DashboardsRoutingModule } from './/dashboards-routing.module';
import { CompanyProfileComponent } from './admin-dashboard/rentacar/company-profile/company-profile.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    DashboardsRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [AdminDashboardComponent, CompanyProfileComponent],
  
})
export class DashboardsModule { }
