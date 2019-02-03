import { AircompanyService } from './../../../shared/services/aircompany/aircompany.service';
import { Aircompany } from './../../../shared/model/aircompany/aircompany.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-aircompanies-list',
  templateUrl: './aircompanies-list.component.html',
  styleUrls: ['./aircompanies-list.component.css']
})
export class AircompaniesListComponent implements OnInit {

  aircompanies: Aircompany[];
  aircompany: Aircompany;

  constructor(
    private aircompanyService: AircompanyService
  ) { }

  ngOnInit() {
    this.getAircompanies();
  }

  getAircompanies(): void {
    this.aircompanyService.getAircompanies().subscribe(
      aircompanies => this.aircompanies = aircompanies,
      error => console.log('Error: ', error)
    );
  }

  onRemove(aircompany: Aircompany): void {
    this.aircompanies = this.aircompanies.filter(v => v !== aircompany);
    this.aircompanyService.removeAirCompany(aircompany.id).subscribe(aircompany => this.aircompany = aircompany);
  }
}
