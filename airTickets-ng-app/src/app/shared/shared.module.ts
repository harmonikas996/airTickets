import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GoogleMapsComponent } from './google-maps/google-maps.component';
import { AgmCoreModule } from '@agm/core';
import { HttpClientJsonpModule, HttpClientModule } from '@angular/common/http';
import { LocationMapComponent } from './location-map/location-map.component';

@NgModule({
  imports: [
    CommonModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBIK-S8ZY9dSsBzn-1mqG3caWE092pNSps',
      libraries: ['places']
    }),
    HttpClientModule,
    HttpClientJsonpModule
  ],
  declarations: [
    GoogleMapsComponent,
    LocationMapComponent,
  ],
  exports: [
    GoogleMapsComponent,
    LocationMapComponent,
  ]
})
export class SharedModule { }
