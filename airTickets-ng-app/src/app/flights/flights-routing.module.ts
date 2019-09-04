import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FlightsReservationComponent } from './flights-reservation/flights-reservation.component';
import { AircompanyDetailsComponent } from './aircompany-details/aircompany-details.component';

const flightsRoutes: Routes = [
  {
    path: 'flightreservations',
    component: FlightsReservationComponent
  },
  {
    path: 'aircompanies-details/:id',
    component: AircompanyDetailsComponent
  }
];


@NgModule({
  imports: [
    RouterModule.forChild(flightsRoutes)
  ],
  exports: [ RouterModule ]
})
export class FlightsRoutingModule { }
