import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyProfileComponent } from './admin-dashboard/rentacar/company-profile/company-profile.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { VehiclesListComponent } from './admin-dashboard/rentacar/vehicles-list/vehicles-list.component';
import { VehicleDetailsComponent } from './admin-dashboard/rentacar/vehicle-details/vehicle-details.component';
import { VehicleNewComponent } from './admin-dashboard/rentacar/vehicle-new/vehicle-new.component';

const dashboardsRoutes: Routes = [
  { path: 'admin-dashboard', component: AdminDashboardComponent, children: [
    { path: 'company-profile', component: CompanyProfileComponent },
    { path: 'vehicles', component: VehiclesListComponent },
    { path: 'vehicles/new', component: VehicleNewComponent },
    { path: 'vehicle-details/:id', component: VehicleDetailsComponent },
    { path: '', redirectTo: 'company-profile', pathMatch: 'full' },
    { path: '**', redirectTo: 'company-profile', pathMatch: 'full' }
  ] }
  
];

@NgModule({
  imports: [ 
    RouterModule.forChild(dashboardsRoutes) 
  ],
  exports: [ RouterModule ]
})
export class DashboardsRoutingModule { }
