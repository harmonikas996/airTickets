import { RentacarService } from './../../../shared/services/rentacar/rentacar.service';
import { FormBuilder } from '@angular/forms';
import { RentACar } from './../../../shared/model/rentacar/rentacar.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-rentacars-list',
  templateUrl: './rentacars-list.component.html',
  styleUrls: ['./rentacars-list.component.css']
})
export class RentacarsListComponent implements OnInit {

  rentacars: RentACar[];
  rentacar: RentACar;

  constructor(
    private rentacarService: RentacarService
  ) { }

  ngOnInit() {
    this.getRentacars();
  }

  getRentacars(): void {
    this.rentacarService.getRentacars().subscribe(
      rentacars => this.rentacars = rentacars,
      error => console.log('Error: ', error)
    );
  }

  onRemove(rentacar: RentACar): void {
    this.rentacars = this.rentacars.filter(v => v !== rentacar);
    this.rentacarService.removeRentacar(rentacar.id).subscribe(rentacar => this.rentacar = rentacar);
  }
}
