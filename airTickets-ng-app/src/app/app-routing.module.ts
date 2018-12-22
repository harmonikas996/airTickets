import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VehicleComponent } from './rentAcar/vehicle/vehicle.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  { path: 'vehicles', component: VehicleComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [ 
    RouterModule.forRoot(routes) 
  ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
