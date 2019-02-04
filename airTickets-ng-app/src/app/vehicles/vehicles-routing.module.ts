import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { VehiclesComponent } from './vehicles.component';
import { VehiclesListComponent } from './vehicles-list/vehicles-list.component';
import { RoleGuard } from '../shared/services/guards/role-guard.service';
import { RentacarsListComponent } from './rentacars-list/rentacars-list.component';

const vehiclesRoutes: Routes = [
  { path: 'rentacars',
    component: RentacarsListComponent,
    children: [
      // { path: 'company-profile', component: CompanyProfileComponent },
      // { path: 'list', component: VehiclesListComponent },
      // { path: 'vehicles/new', component: VehicleNewComponent },
      // { path: 'vehicle-details/:id', component: VehicleDetailsComponent },
      // { path: 'branches', component: BranchesListComponent },
      // { path: 'branches/new', component: BranchNewComponent },
      // { path: 'branch-details/:id', component: BranchDetailsComponent },
      // { path: 'user-profile', component: UserProfileComponent },
      // { path: '', redirectTo: 'list', pathMatch: 'full' },
      // { path: '**', redirectTo: 'list', pathMatch: 'full' }
    ]
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(vehiclesRoutes)
  ],
  exports: [ RouterModule ]
})
export class VehiclesRoutingModule { }
