import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-passengers-details',
  templateUrl: './passengers-details.component.html',
  styleUrls: ['./passengers-details.component.css']
})
export class PassengersDetailsComponent implements OnInit {

  @Input() selectedSeatsDep: number[] = []; 
  @Input() selectedSeatsRet: number[] = [];

  constructor() { }

  ngOnInit() {
  }

}
