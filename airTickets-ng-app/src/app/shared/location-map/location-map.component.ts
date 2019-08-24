import { Component, OnInit, ViewChild, ElementRef, AfterViewInit, Output, Input,
          EventEmitter, OnChanges, SimpleChange } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-location-map',
  templateUrl: './location-map.component.html',
  styleUrls: ['./location-map.component.css']
})
export class LocationMapComponent implements AfterViewInit, OnChanges {

  @Input() defaultCoords: string;
  @Input() addressTyped: string;
  @Output() selectedAddress = new EventEmitter<string>();
  @Output() selectedCity = new EventEmitter<string>();
  @Output() selectedCoords = new EventEmitter<string>();
  @ViewChild('locationMap') locationMapViewChild: ElementRef;
  map: Microsoft.Maps.Map;

  constructor() { }

  ngAfterViewInit() {
    this.loadMapScenario(this.defaultCoords);
  }

  ngOnChanges(changes: { [property: string]: SimpleChange }) {
    const change: SimpleChange = changes['addressTyped'];
    if (!change.isFirstChange()) {
      this.geoCoding(change.currentValue);
    }
  }

  loadMapScenario(defaultCoords: string): void {
    this.map = new Microsoft.Maps.Map(this.locationMapViewChild.nativeElement, {
      credentials: environment.bingMapCredentials,
      supportedMapTypes: [Microsoft.Maps.MapTypeId.road]
    });

    if (defaultCoords !== null) {
      const coords = defaultCoords.split(',');
      this.addPin(coords[0], coords[1]);
      this.reverseGeoCoding(coords[0], coords[1], true);
    }

    Microsoft.Maps.Events.addHandler(this.map, 'click', (e) => { this.changePinLocation(<Microsoft.Maps.IMouseEventArgs>e); });
  }

  changePinLocation(e: Microsoft.Maps.IMouseEventArgs) {
    this.addPin(e.location.latitude, e.location.longitude);

    this.reverseGeoCoding(e.location.latitude, e.location.longitude);
    this.selectedCoords.emit(e.location.latitude + ',' + e.location.longitude);
  }

  geoCoding(address: string) {
    Microsoft.Maps.loadModule('Microsoft.Maps.Search', () => {
      const searchManager = new Microsoft.Maps.Search.SearchManager(this.map);
      const requestOptions = {
          bounds: this.map.getBounds(),
          where: address,
          callback: (answer, userData) => {
            this.selectedAddress.emit(answer.results[0].location.name);
            this.selectedCoords.emit(answer.results[0].location.latitude + ',' + answer.results[0].location.longitude);
            this.map.setView({
              center: new Microsoft.Maps.Location(answer.results[0].location.latitude, answer.results[0].location.longitude),
              zoom: 17
            });
            this.addPin(answer.results[0].location.latitude, answer.results[0].location.longitude);
          }
        };
      searchManager.geocode(requestOptions);
    });
  }

  reverseGeoCoding(latitude: any, longitude: any, initialLoad?: boolean) {
    Microsoft.Maps.loadModule('Microsoft.Maps.Search', () => {
      const searchManager = new Microsoft.Maps.Search.SearchManager(this.map);
      const reverseGeocodeRequestOptions = {
          location: new Microsoft.Maps.Location(latitude, longitude),
          callback: (answer, userData) => {
            this.selectedAddress.emit(answer.address.formattedAddress);
            this.selectedCity.emit(answer.address.locality);
            if (initialLoad) {
              this.map.setView({
                center: new Microsoft.Maps.Location(latitude, longitude),
                zoom: 17
              });
            }
          }
        };
      searchManager.reverseGeocode(reverseGeocodeRequestOptions);
    });
  }

  addPin(latitude: any, longitude: any) {
    this.map.entities.clear();
    this.map.entities.push(new Microsoft.Maps.Pushpin(new Microsoft.Maps.Location(latitude, longitude, ),
    { icon: '../../../assets/img/misc/icons8-marker-50.png',
    anchor: new Microsoft.Maps.Point(25, 50) }));
  }
}
