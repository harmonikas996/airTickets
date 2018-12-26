import { Component, OnInit } from '@angular/core';
import { Vehicle } from 'src/app/shared/model/rentacar/vehicle.model';
import { VehicleService } from 'src/app/shared/services/rentacar/vehicle.service';


@Component({
  selector: 'vehicles-list',
  templateUrl: './vehicles-list.component.html',
  styleUrls: ['./vehicles-list.component.css']
})
export class VehiclesListComponent implements OnInit {

  vehicles: Vehicle[];
  

  constructor(private vehicleService: VehicleService) { }

  ngOnInit() {
    // srediti preuzimanje ID-a tako sto proveris kojoj kompaniji je dodeljen ulogovani admin
    this.getVehiclesByRentACarId();
    
  }

  getVehiclesByRentACarId(): void {
    // dobaviti samo ona vozila koja pripadaju rentacar servisu sa 'id' koji je prosledjen
    this.vehicleService.getVehicles().subscribe(vehicles => this.vehicles = vehicles);
  }

}
