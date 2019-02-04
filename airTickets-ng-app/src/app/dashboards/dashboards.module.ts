import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';

import { DashboardsRoutingModule } from './/dashboards-routing.module';
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
import { AircompanyProfileComponent } from './aircompany-dashboard/aircompany-profile/aircompany-profile.component';
import { FlightsComponent } from './aircompany-dashboard/flights/flights.component';
import { FlightDetailsComponent } from './aircompany-dashboard/flight-details/flight-details.component';
import { FlightNewComponent } from './aircompany-dashboard/flight-new/flight-new.component';
import { FlightRatingComponent } from './aircompany-dashboard/flight-rating/flight-rating.component';
import { HotelDashboardComponent } from './hotel-dashboard/hotel-dashboard.component';
import { RoomDetailsComponent } from './hotel-dashboard/room-details/room-details.component';
import { RoomNewComponent } from './hotel-dashboard/room-new/room-new.component';
import { RoomListComponent } from './hotel-dashboard/room-list/room-list.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { SystemDashboardComponent } from './system-dashboard/system-dashboard.component';
import { RentacarsListComponent } from './system-dashboard/rentacars-list/rentacars-list.component';
import { RentacarNewComponent } from './system-dashboard/rentacar-new/rentacar-new.component';
import { RentacarDetailsComponent } from './system-dashboard/rentacar-details/rentacar-details.component';
import { HotelDetailsComponent } from './system-dashboard/hotel-details/hotel-details.component';
import { HotelNewComponent } from './system-dashboard/hotel-new/hotel-new.component';
import { HotelsListComponent } from './system-dashboard/hotels-list/hotels-list.component';
import { AircompanyDetailsComponent } from './system-dashboard/aircompany-details/aircompany-details.component';
import { AircompanyNewComponent } from './system-dashboard/aircompany-new/aircompany-new.component';
import { AircompaniesListComponent } from './system-dashboard/aircompanies-list/aircompanies-list.component';
import { MyDatePickerModule } from 'mydatepicker';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AmenityNewComponent } from './hotel-dashboard/amenity-new/amenity-new.component';
import { AmenityListComponent } from './hotel-dashboard/amenity-list/amenity-list.component';
import { AmenityDetailsComponent } from './hotel-dashboard/amenity-details/amenity-details.component';
import { HotelProfileComponent } from './hotel-dashboard/hotel-profile/hotel-profile.component';



@NgModule({
  imports: [
    CommonModule,
    DashboardsRoutingModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ReactiveFormsModule,
    NgSelectModule,
    MyDatePickerModule,
    BrowserAnimationsModule
  ],
  declarations: [
    RentacarDashboardComponent,
    CompanyProfileComponent,
    VehiclesListComponent,
    VehicleDetailsComponent,
    VehicleNewComponent,
    BranchesListComponent,
    BranchNewComponent,
    BranchDetailsComponent,
    AircompanyProfileComponent,
    FlightsComponent,
    FlightDetailsComponent,
    FlightNewComponent,
    FlightRatingComponent,
    HotelDashboardComponent,
    RoomDetailsComponent,
    RoomNewComponent,
    RoomListComponent,
    UserProfileComponent,
    SystemDashboardComponent,
    RentacarsListComponent,
    RentacarNewComponent,
    RentacarDetailsComponent,
    HotelDetailsComponent,
    HotelNewComponent,
    HotelsListComponent,
    AircompanyDetailsComponent,
    AircompanyNewComponent,
    AircompaniesListComponent,
    AmenityNewComponent,
    AmenityListComponent,
    AmenityDetailsComponent,
    HotelProfileComponent,
  ],
  providers: [
    AuthGuard,
    RoleGuard
  ]

})
export class DashboardsModule { }
