import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Vehicle } from '../../model/rentacar/vehicle.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private vehiclesUrl = 'http://localhost:8080/vehicles';

  constructor(
    private http: HttpClient
  ) { }

  getVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.vehiclesUrl + '/all');
  }

  getVehicleById(id: number): Observable<Vehicle> {
    return this.http.get<Vehicle>(this.vehiclesUrl + '/' + id);
  }

  // TO DO
  getVehiclesByRentACarId(id: number): Observable<Vehicle> {
    return null;
  }

  addVehicle(vehicle: Vehicle): Observable<Object> {
    return this.http.post<Vehicle>(this.vehiclesUrl + '/new', vehicle, httpOptions);
  }

  updateVehicle(vehicle: Vehicle): Observable<Object> {
    return this.http.put<Vehicle>(this.vehiclesUrl + '/' + vehicle.id + '/update', vehicle, httpOptions);
  }
}
