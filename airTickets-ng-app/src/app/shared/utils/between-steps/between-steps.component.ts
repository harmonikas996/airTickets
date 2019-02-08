import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-between-steps',
  templateUrl: './between-steps.component.html',
  styleUrls: ['./between-steps.component.css']
})
export class BetweenStepsComponent implements OnInit {

  @Input() flightReservationSuccess: boolean;

  step: number;
  message: string = '';

  constructor() { }

  ngOnInit() {
    this.step = 0;
    this.message = 'Do you want to book a hotel room?';
  }


  clickNo(step: number) {
    console.log("USAO U CLICKNO");
    if(step == 0) {

      this.message = 'Would you like to rent a car?'
      this.step = 1;
      console.log("uvecao step");
    } else if(step == 1) {
      console.log("stepujem sad kao jedinica");
      window.sessionStorage.removeItem('reservationId');
      window.location.href = '../';
    }
  }

  clickYes(step: number) {
    if(step==0) {
      window.location.href = './hotels';
    } else {
      window.location.href = './rentacars';
    }
  }
}
