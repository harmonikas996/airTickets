import { Component, OnInit } from '@angular/core';
import { Vehicle } from '../../shared/model/vehicle.model';
import { VehicleService } from '../../shared/service/vehicle/vehicle.service';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit {

  vehicles: Vehicle[];

  constructor(private vehicleService: VehicleService) { }

  ngOnInit() {
    this.getVehicles();
  }

  getVehicles(): void {
    this.vehicleService.getVehicles().subscribe(vehicles => this.vehicles = vehicles);
  }

}
