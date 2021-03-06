import { Component, OnInit } from '@angular/core';
import { Vehicle } from 'src/app/shared/model/rentacar/vehicle.model';
import { VehicleService } from 'src/app/shared/services/rentacar/vehicle.service';
import { RentacarService } from 'src/app/shared/services/rentacar/rentacar.service';
import { RentACar } from 'src/app/shared/model/rentacar/rentacar.model';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';


@Component({
  selector: 'vehicles-list',
  templateUrl: './vehicles-list.component.html',
  styleUrls: ['./vehicles-list.component.css']
})
export class VehiclesListComponent implements OnInit {

  vehicles: Vehicle[];
  vehicle: Vehicle;
  rentacar: RentACar;
  
  constructor(private vehicleService: VehicleService, private rentacarService: RentacarService, private token: TokenStorageService) { }

  ngOnInit() {
    // srediti preuzimanje ID-a tako sto proveris kojoj kompaniji je dodeljen ulogovani admin
    this.getCompanyId();

  }

  getCompanyId() {

    this.rentacarService.getRentacarByAdminUsername(this.token.getUsername()).subscribe(
      data => this.rentacar = data,
      error => console.log('Error: ', error),
      () => this.getVehiclesByRentACarId()
    );
  }

  getVehiclesByRentACarId(): void {
    // dobaviti samo ona vozila koja pripadaju rentacar servisu sa 'id' koji je prosledjen
    this.vehicleService.getVehiclesByRentACarId(this.rentacar.id).subscribe(
      vehicles => this.vehicles = vehicles,
      error => console.log('Error: ', error),
      () => this.getCompanyName()
    );
  }

  getCompanyName() : void {
    for(let v of this.vehicles) {

      let r: RentACar;
      this.rentacarService.getRentacarById(v.rentACarId).subscribe(
        rentacar => r = rentacar,
        error => console.log('Error: ', error),
      () => v.rentACarId = r.name
      );
    }
  }

  onRemove(vehicle: Vehicle): void {
    this.vehicles = this.vehicles.filter(v => v !== vehicle);
    this.vehicleService.removeVehicle(vehicle.id).subscribe(vehicle => this.vehicle = vehicle);
  }

}
