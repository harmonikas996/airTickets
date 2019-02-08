import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HotelsListComponent} from './hotels-list/hotels-list.component';
import { HotelDetailsComponent } from './hotel-details/hotel-details.component';

const hotelsRoutes: Routes = [
  // { path: 'hotels',
  //   component: HotelsListComponent
  // },
  // { path: 'hotels-details/:id',
  //   component: HotelDetailsComponent,
  //   children: [
  //     // { path: ':id', component: RentacarDetailsComponent },
  //   ]
  // }

  { path: 'hotels', component: HotelsListComponent },
  { path: 'hotels-details/:id', component: HotelDetailsComponent}
];


@NgModule({
  imports: [
    RouterModule.forChild(hotelsRoutes)
  ],
  exports: [ RouterModule ]
})
export class HotelRoutingModule { }
