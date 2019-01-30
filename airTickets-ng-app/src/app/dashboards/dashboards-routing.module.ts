import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyProfileComponent } from './admin-dashboard/rentacar-dashaboard/company-profile/company-profile.component';
import { RentacarDashboardComponent } from './admin-dashboard/rentacar-dashaboard/rentacar-dashboard.component';
import { VehiclesListComponent } from './admin-dashboard/rentacar-dashaboard/vehicles-list/vehicles-list.component';
import { VehicleDetailsComponent } from './admin-dashboard/rentacar-dashaboard/vehicle-details/vehicle-details.component';
import { VehicleNewComponent } from './admin-dashboard/rentacar-dashaboard/vehicle-new/vehicle-new.component';
import { BranchesListComponent } from './admin-dashboard/rentacar-dashaboard/branches-list/branches-list.component';
import { BranchNewComponent } from './admin-dashboard/rentacar-dashaboard/branch-new/branch-new.component';
import { BranchDetailsComponent } from './admin-dashboard/rentacar-dashaboard/branch-details/branch-details.component';
import { AuthGuard } from '../shared/services/guards/auth-guard.service';
import { RoleGuard } from '../shared/services/guards/role-guard.service';

const dashboardsRoutes: Routes = [
  { path: 'rentacar-dashboard', 
    component: RentacarDashboardComponent, 
    canActivate: [RoleGuard],
    data: {role: 'rentacar'},
    children: [
      { path: 'company-profile', component: CompanyProfileComponent },
      { path: 'vehicles', component: VehiclesListComponent },
      { path: 'vehicles/new', component: VehicleNewComponent },
      { path: 'vehicle-details/:id', component: VehicleDetailsComponent },
      { path: 'branches', component: BranchesListComponent },
      { path: 'branches/new', component: BranchNewComponent },
      { path: 'branch-details/:id', component: BranchDetailsComponent },
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

