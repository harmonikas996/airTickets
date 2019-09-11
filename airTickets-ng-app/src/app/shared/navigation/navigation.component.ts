import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user/user.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  constructor(
    private userService: UserService
  ) { }

  hotelReservationDone = false;
  carReservationDone = false;
  hotelBtnTxt = 'Book a room?';
  carBtnTxt = 'Book a car?';

  ngOnInit() {

  }

  checkReservation() : boolean {
    if(window.sessionStorage.getItem('reservationId') != null) {
      if (sessionStorage.getItem('carReservationId') !== null) {
        this.carBtnTxt = 'Car booked!';
        this.carReservationDone = true;
      }
  
      if (sessionStorage.getItem('hotelReservationId') !== null) {
        this.hotelBtnTxt = 'Room booked!';
        this.hotelReservationDone = true;
      }
      return true;
    } else {
      return false;
    }
  }

  finishReservation() {

    this.userService.finishReservation(sessionStorage.getItem('reservationId'), sessionStorage.getItem('AuthUsername')).subscribe();

    sessionStorage.removeItem('carReservationId');
    sessionStorage.removeItem('hotelReservationId');
    sessionStorage.removeItem('reservationId');
    sessionStorage.removeItem('flightStart');
    location.assign('./user-dashboard/history');
  }

}
