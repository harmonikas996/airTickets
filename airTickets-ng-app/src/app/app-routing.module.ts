import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './user-authentication/login/login.component';
import { RegisterComponent } from './user-authentication/register/register.component';
import { VehiclesListComponent } from './vehicles/vehicles-list/vehicles-list.component';
import { FlightsReservationComponent } from './flights/flights-reservation/flights-reservation.component';
import { VehiclesComponent } from './vehicles/vehicles.component';

const routes: Routes = [
  { path: 'flightreservations', component: FlightsReservationComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // uvek na kraju nek stoje
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
