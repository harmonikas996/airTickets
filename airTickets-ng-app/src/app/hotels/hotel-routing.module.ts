import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HotelsListComponent} from './hotels-list/hotels-list.component';

const hotelsRoutes: Routes = [
  { path: 'hotels',
    component: HotelsListComponent
  }
]


@NgModule({
  imports: [
    RouterModule.forChild(hotelsRoutes)
  ],
  exports: [ RouterModule ]
})
export class HotelRoutingModule { }
