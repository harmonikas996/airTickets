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
import { RoomPriceListComponent } from './hotel-dashboard/room-price-list/room-price-list.component';
import { RoomPriceNewComponent } from './hotel-dashboard/room-price-new/room-price-new.component';
import { RoomPriceDetailsComponent } from './hotel-dashboard/room-price-details/room-price-details.component';
import { SpecialofferDetailsComponent } from './hotel-dashboard/specialoffer-details/specialoffer-details.component';
import { SpecialofferNewComponent } from './hotel-dashboard/specialoffer-new/specialoffer-new.component';
import { SpecialOfferUpdateComponent } from './hotel-dashboard/special-offer-update/special-offer-update.component';
import { AirportNewComponent } from './system-dashboard/airport-new/airport-new.component';
import { AirportListComponent } from './system-dashboard/airport-list/airport-list.component';
import { AirportsDetailsComponent } from './system-dashboard/airports-details/airports-details.component';
import { QuickReservationsComponent } from './rentacar-dashaboard/quick-reservations/quick-reservations.component';
import { QuickResHotelComponent } from './hotel-dashboard/quick-res-hotel/quick-res-hotel.component';
import { SharedModule } from '../shared/shared.module';
import { FriendsListComponent } from './user-dashboard/friends-list/friends-list.component';
import { QuickResAircompanyComponent } from './aircompany-dashboard/quick-res-aircompany/quick-res-aircompany.component';


@NgModule({
  imports: [
    CommonModule,
    DashboardsRoutingModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    ReactiveFormsModule,
    NgSelectModule,
    MyDatePickerModule,
    BrowserAnimationsModule,
    SharedModule,
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
    AirportNewComponent,
    AirportListComponent,
    AirportsDetailsComponent,
    HotelNewComponent,
    HotelsListComponent,
    AircompanyDetailsComponent,
    AircompanyNewComponent,
    AircompaniesListComponent,
    AmenityNewComponent,
    AmenityListComponent,
    AmenityDetailsComponent,
    HotelProfileComponent,
    RoomPriceListComponent,
    RoomPriceNewComponent,
    RoomPriceDetailsComponent,
    SpecialofferDetailsComponent,
    SpecialofferNewComponent,
    SpecialOfferUpdateComponent,
    QuickReservationsComponent,
    QuickResHotelComponent,
    FriendsListComponent,
    QuickResAircompanyComponent
  ],
  providers: [
    AuthGuard,
    RoleGuard
  ]

})
export class DashboardsModule { }
