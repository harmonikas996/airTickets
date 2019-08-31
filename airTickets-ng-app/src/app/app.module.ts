import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';

import { AppRoutingModule } from './/app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { DashboardsModule } from './dashboards/dashboards.module';
import { LoginWidgetComponent } from './shared/navigation/login-widget/login-widget.component';
import { NavigationComponent } from './shared/navigation/navigation.component';
import { LoginComponent } from './user-authentication/login/login.component';
import { RegisterComponent } from './user-authentication/register/register.component';
import { UserAuthenticationModule } from './user-authentication/user-authentication.module';
import { VehiclesModule } from './vehicles/vehicles.module';
import { ProfileComponent } from './profile/profile.component';
import { AircompanyDashboardComponent } from './dashboards/aircompany-dashboard/aircompany-dashboard.component';
import { AuthInterceptor, httpInterceptorProviders } from './user-authentication/service/auth-interceptor';
import { UserDashboardComponent } from './dashboards/user-dashboard/user-dashboard.component';
import { FlightsModule } from './flights/flights.module';
import { MyDatePickerModule } from 'mydatepicker';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { HotelsModule } from './hotels/hotels.module';
import { OwlDateTimeModule, OWL_DATE_TIME_FORMATS } from 'ng-pick-datetime';
import { ChartsModule } from 'ng2-charts';
import { ReportsComponent } from './shared/reports/reports.component';
import { ReservationsReportComponent } from './shared/reports/reservations-report/reservations-report.component';
import { HotelsComponent } from './hotels/hotels.component';
import { FlightsComponent } from './flights/flights.component';
import { FlightsReservationComponent } from './flights/flights-reservation/flights-reservation.component';
import { HotelRoutingModule } from './hotels/hotel-routing.module';

// export const MY_MOMENT_FORMATS = {
//   // parseInput: 'l LT',
//   parseInput: 'dd.MM.YYYY LT',
//   fullPickerInput: 'dd.MM.YYYY LT',
//   datePickerInput: 'dd.MM.YYYY',
//   timePickerInput: 'LT',
//   monthYearLabel: 'MMM YYYY',
//   dateA11yLabel: 'LL',
//   monthYearA11yLabel: 'MMMM YYYY',
// };

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    ReportsComponent,
    LoginWidgetComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    AircompanyDashboardComponent,
    UserDashboardComponent,
    VehiclesComponent,
    ReportsComponent,
    ReservationsReportComponent,
    HotelsComponent,
    FlightsComponent
  ],
  imports: [
  BrowserModule,
    HttpClientModule,
    CoreModule,
    FormsModule,
    ReactiveFormsModule,
    NgSelectModule,
    OwlDateTimeModule,
    // OwlMomentDateTimeModule,
    ChartsModule,
    MyDatePickerModule,
    VehiclesModule,
    HotelsModule,
    FlightsModule,
    UserAuthenticationModule,
    DashboardsModule,
    HotelRoutingModule,
    AppRoutingModule, // ovaj modul uvek nek ide ispod svih drugih modula. Pravilo zlatno
  ],
  providers: [
    httpInterceptorProviders,
    // { provide: OWL_DATE_TIME_FORMATS, useValue: MY_MOMENT_FORMATS }
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
