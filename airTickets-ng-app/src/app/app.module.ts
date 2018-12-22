import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule }    from '@angular/common/http';

import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { AppRoutingModule } from './/app-routing.module';
import { VehicleComponent } from './rentAcar/vehicle/vehicle.component';
import { LoginWidgetComponent } from './navigation/login-widget/login-widget.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    VehicleComponent,
    LoginWidgetComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
