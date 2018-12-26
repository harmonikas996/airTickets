import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyProfileComponent } from './admin-dashboard/rentacar/company-profile/company-profile.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';

const dashboardsRoutes: Routes = [
  { path: 'admin-dashboard', component: AdminDashboardComponent, children: [
    { path: 'company-profile', component: CompanyProfileComponent}
  ] }
  
];

@NgModule({
  imports: [ 
    RouterModule.forChild(dashboardsRoutes) 
  ],
  exports: [ RouterModule ]
})
export class DashboardsRoutingModule { }

