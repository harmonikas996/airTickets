import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './user-authentication/login/login.component';
import { RegisterComponent } from './user-authentication/register/register.component';
import { VehiclesListComponent } from './vehicles/vehicles-list/vehicles-list.component';
import { FlightsReservationComponent } from './flights/flights-reservation/flights-reservation.component';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { FlightsResListComponent } from './flights/flights-res-list/flights-res-list.component';
import { HotelsListComponent } from './hotels/hotels-list/hotels-list.component';
import { FlightsComponent } from './flights/flights.component';
import { RoleGuard } from './shared/services/guards/role-guard.service';
import { HotelDetailsComponent } from './hotels/hotel-details/hotel-details.component';
import { ProfileComponent } from './profile/profile.component';
import { AuthGuard } from './shared/services/guards/auth-guard.service';

const routes: Routes = [
  // { path: 'flights', component: FlightsComponent,
  //   // children: [
  //   //   { path: 'flights-reservation', component: FlightsReservationComponent },
  //   //   { path: 'flights-res-list', component: FlightsResListComponent },
  //   //   // { path: '', redirectTo: 'flights-reservation', pathMatch: 'full' },
  //   //   // { path: '**', redirectTo: 'flights-reservation', pathMatch: 'full' }
  //   // ]
  // },
  // { path: 'flights', component: FlightsComponent },
  { 
    path: '',
    pathMatch: 'full', 
    component: ProfileComponent, 
    canActivate: [AuthGuard]
  },
  { path: 'flight-reservations', component: FlightsReservationComponent },
  // { path: 'flight-res-list', component: FlightsResListComponent },

  { path: 'login', component: LoginComponent },
  // { path: 'hotel-details', component: HotelDetailsComponent },
  { path: 'register', component: RegisterComponent },
  // uvek na kraju nek stoje
  // { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: '**', redirectTo: '', pathMatch: 'full' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
