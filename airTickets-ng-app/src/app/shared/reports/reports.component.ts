import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Location } from '@angular/common';
import { RentacarService } from '../services/rentacar/rentacar.service';
import { TokenStorageService } from 'src/app/user-authentication/service/token-storage.service';
import { isFormattedError } from '@angular/compiler';
import { HotelService } from '../services/hotel/hotel.service';
import { AircompanyService } from '../services/aircompany/aircompany.service';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})

export class ReportsComponent implements OnInit {
  // lineChart
  public lineChartData:Array<any> = [
    {data: [], label: 'Income in EUR(€)'}
  ];
  public lineChartLabels:Array<any> = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEP', 'OCT', 'NOV', 'DEC'];
  public lineChartLabelsWeeks:Array<any> = [];
  public lineChartOptions:any = {
    responsive: true
  };
  public lineChartColors:Array<any> = [
    { // grey
      backgroundColor: 'rgba(0,123,255, 0.2)',
      borderColor: '#007BFF',
      pointBackgroundColor: '#007BFF',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];

  public lineChartLegend:boolean = true;
  public lineChartType:string = 'line';

  reportForm: FormGroup;
  types: String[];
  years: String[];
  id: number;
  yearlyIncome: number;
  dataSet: Number[];
  adminType: String;

  constructor(
    private rentacarService: RentacarService,
    private hotelService: HotelService,
    private aircompanyService: AircompanyService,
    private location: Location,
    private formBuilder: FormBuilder,
    private token: TokenStorageService
  ) { }

  ngOnInit() {

    this.reportForm = this.formBuilder.group({
      reportType: [null, Validators.required],
      reportYear: ['', Validators.required],
      reportGraphType: [null]
    });

    this.types = ['Monthly', 'Weekly'];
    this.years = ['2019', '2018', '2017', '2016', '2015', '2014', '2013', '2012', '2011', '2010', '2009'];

    for(let i=1; i<=53; i++) {
      this.lineChartLabelsWeeks.push(i);
    }

    this.getCompanyByAdminUsername();
  }

  getCompanyByAdminUsername() {
    if(this.token.getAuthorities().includes('rentacar')) {

      this.rentacarService.getRentacarByAdminUsername(this.token.getUsername()).subscribe(data => this.id = data.id);
      this.adminType = 'rentacar';
    } else if(this.token.getAuthorities().includes('hotel')) {

      this.hotelService.getHotelByAdminUsername(this.token.getUsername()).subscribe(data => this.id = data.id);
      this.adminType = 'hotel';
    } else if(this.token.getAuthorities().includes('aircompany')) {

      this.aircompanyService.getAircompanyByAdminUsername(this.token.getUsername()).subscribe(data => this.id = data.id);
      this.adminType = 'aircompany';
    }
  }

  onSubmit() {

        if(this.reportForm.controls['reportType'].value == 'Monthly') {
          this.rentacarService.getMonthlyReport(this.id, this.reportForm.controls['reportYear'].value, this.adminType).subscribe(
            data => this.dataSet = data,
            (error) => console.error('An error occurred, ', error),
            () => {
              let _lineChartData:Array<any> = new Array(this.lineChartData.length);
                _lineChartData[0] = {data: new Array(this.dataSet.length), label: 'Income in EUR(€)'};
                for (let j = 0; j < this.dataSet.length; j++) {
                  _lineChartData[0].data[j] = this.dataSet[j];
                }
              this.lineChartData = _lineChartData;
            }

          );
        } else if(this.reportForm.controls['reportType'].value == 'Weekly') {
          this.rentacarService.getWeeklyReport(this.id, this.reportForm.controls['reportYear'].value, this.adminType).subscribe(
            data => this.dataSet = data,
            (error) => console.error('An error occurred, ', error),
            () => {
              let _lineChartData:Array<any> = new Array(this.lineChartData.length);
                _lineChartData[0] = {data: new Array(this.dataSet.length), label: 'Income in EUR(€)'};
                for (let j = 0; j < this.dataSet.length; j++) {
                  _lineChartData[0].data[j] = this.dataSet[j];
                }
              this.lineChartData = _lineChartData;
            }

          );
        }
  }

  fetchData(way: number): void {
    if (way == 1) {
      console.log("Daj mi Monthly");
      if(this.reportForm.controls['reportYear'].value != '') {
        this.onSubmit();
      }
    }
    else {
      if(this.reportForm.controls['reportType'].value != null) {
        console.log("ODABRANO");
        this.onSubmit();
      }
      console.log("Daj mi Yearly");
      this.rentacarService.getYearlyReport(this.id, this.reportForm.controls['reportYear'].value, this.adminType).subscribe(
        data => this.yearlyIncome = data
      );
    }
  }

  switch() {
    this.lineChartType = !(this.reportForm.controls['reportGraphType'].value) ? 'line' : 'bar'
  }

  // events
  public chartClicked(e:any):void {
    console.log(e);
  }

  public chartHovered(e:any):void {
    console.log(e);
  }
}
