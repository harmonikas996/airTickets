import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Vehicle } from '../../model/rentacar/vehicle.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private vehiclesUrl = 'http://localhost:8080/vehicles';
  private rentacarsUrl = 'http://localhost:8080/rentacars';

  constructor(
    private http: HttpClient
  ) { }

  getVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.vehiclesUrl + '/all');
  }

  getVehicleById(id: number): Observable<Vehicle> {
    return this.http.get<Vehicle>(this.vehiclesUrl + '/' + id);
  }

  // TODO
  getVehiclesByRentACarId(id: number): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.rentacarsUrl + '/carsFromRentacar?id=' + id);
  }

  addVehicle(vehicle: Vehicle): Observable<Object> {
    return this.http.post<Vehicle>(this.vehiclesUrl + '/new', vehicle, httpOptions);
  }

  updateVehicle(vehicle: Vehicle): Observable<Object> {
    return this.http.put<Vehicle>(this.vehiclesUrl + '/' + vehicle.id + '/update', vehicle, httpOptions);
  }

  removeVehicle(vehicle: Vehicle | number): Observable<Vehicle> {
    const id = typeof vehicle === 'number' ? vehicle : vehicle.id;
    return this.http.delete<Vehicle>(this.vehiclesUrl + '/' + id + '/delete', httpOptions).pipe(
      tap(_ => console.log(`deleted vehicle id=${id}`))
    );
  }

  searchVehicles(
    priceFrom: number,
    priceTo: number,
    pickupDateTime: string,
    dropoffDateTime: string,
    pickupLocation: string,
    dropoffLocation: string,
    passengersSalji: number,
    rentacarId: number,
    vehicleType: number
    ): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(this.vehiclesUrl + 
      '/search?rentacarId=' + rentacarId + 
      '&priceFrom=' + priceFrom +
      '&priceTo=' + priceTo +
      '&pickupDateTime=' + pickupDateTime + 
      '&dropoffDateTime=' + dropoffDateTime + 
      '&pickupLocation=' + pickupLocation +
      '&dropoffLocation=' + dropoffLocation + 
      '&passengersSalji=' + passengersSalji +
      '&vehicleType=' + vehicleType
      );
  }
}
