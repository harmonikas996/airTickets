import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './user-authentication/login/login.component';
import { RegisterComponent } from './user-authentication/register/register.component';
import { VehiclesListComponent } from './vehicles/vehicles-list/vehicles-list.component';
import { FlightsReservationComponent } from './flights/flights-reservation/flights-reservation.component';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { FlightsResListComponent } from './flights/flights-res-list/flights-res-list.component';
import { HotelsListComponent } from './hotels/hotels-list/hotels-list.component';
import { HotelDetailsComponent } from './hotels/hotel-details/hotel-details.component';

const routes: Routes = [
  { path: 'flight-reservations', component: FlightsReservationComponent },
  { path: 'flight-res-list', component: FlightsResListComponent },
  { path: 'hotels', component: HotelsListComponent },
  { path: 'login', component: LoginComponent },
  { path: 'hotel-details', component: HotelDetailsComponent },
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
