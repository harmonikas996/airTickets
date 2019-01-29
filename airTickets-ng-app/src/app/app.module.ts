import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    LoginWidgetComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CoreModule,
    VehiclesModule,
    UserAuthenticationModule,
    DashboardsModule,
    AppRoutingModule, // ovaj modul uvek nek ide ispod svih drugih modula. Pravilo zlatno
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
