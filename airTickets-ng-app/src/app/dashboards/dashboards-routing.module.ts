import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RentacarDashboardComponent } from './rentacar-dashaboard/rentacar-dashboard.component';
import { CompanyProfileComponent } from './rentacar-dashaboard/company-profile/company-profile.component';
import { VehicleDetailsComponent } from './rentacar-dashaboard/vehicle-details/vehicle-details.component';
import { VehicleNewComponent } from './rentacar-dashaboard/vehicle-new/vehicle-new.component';
import { VehiclesListComponent } from './rentacar-dashaboard/vehicles-list/vehicles-list.component';
import { BranchesListComponent } from './rentacar-dashaboard/branches-list/branches-list.component';
import { BranchNewComponent } from './rentacar-dashaboard/branch-new/branch-new.component';
import { BranchDetailsComponent } from './rentacar-dashaboard/branch-details/branch-details.component';
import { AuthGuard } from '../shared/services/guards/auth-guard.service';
import { RoleGuard } from '../shared/services/guards/role-guard.service';
import { AircompanyDashboardComponent } from './aircompany-dashboard/aircompany-dashboard.component';
import { AircompanyProfileComponent } from './aircompany-dashboard/aircompany-profile/aircompany-profile.component';
import { FlightsComponent } from './aircompany-dashboard/flights/flights.component';
import { FlightDetailsComponent } from './aircompany-dashboard/flight-details/flight-details.component';
import { FlightNewComponent } from './aircompany-dashboard/flight-new/flight-new.component';
import { FlightRatingComponent } from './aircompany-dashboard/flight-rating/flight-rating.component';
import { UserProfileComponent } from './user-dashboard/user-profile/user-profile.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';

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
    ]
  },
  { path: 'aircompany-dashboard',
    component: AircompanyDashboardComponent,
    canActivate: [RoleGuard],
    data: {role: 'aircompany'},
    children: [
      { path: 'aircompany-profile', component: AircompanyProfileComponent },
      { path: 'flights', component: FlightsComponent },
      { path: 'flights/new', component: FlightNewComponent },
      { path: 'flight-rating', component: FlightRatingComponent },
      { path: 'flights-details/:id', component: FlightDetailsComponent },
      { path: '', redirectTo: 'aircompany-profile', pathMatch: 'full' },
      { path: '**', redirectTo: 'aircompany-profile', pathMatch: 'full' }
    ]
  },
  { path: 'user-dashboard',
  component: UserDashboardComponent,
  canActivate: [RoleGuard],
  data: {role: 'client'},
  children: [
    { path: 'user-profile', component: UserProfileComponent },
    { path: '', redirectTo: 'aircompany-profile', pathMatch: 'full' },
    { path: '**', redirectTo: 'aircompany-profile', pathMatch: 'full' }
  ]
}

];

@NgModule({
  imports: [
    RouterModule.forChild(dashboardsRoutes)
  ],
  exports: [ RouterModule ]
})
export class DashboardsRoutingModule { }

