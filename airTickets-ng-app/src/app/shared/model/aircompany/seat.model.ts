export class Seat {
  id: number;
  clientId: number;
  passport: string;
  flightId: number;
  reservationId: number;
  flightResId: number;
  price: number;
  mark: string;
  firstName: string;
  lastName: string;
  contact: string;

  constructor() {
    this.id = 0;
    this.clientId = null;
    this.passport = '';
    this.flightId = null;
    this.reservationId = null;
    this.price = 0;
    this.mark = '';
    this.firstName = '';
    this.lastName = '';
    this.contact = '';
    this.flightResId = null;
  }

  // constructor(id, clientId, passport, flightId, reservationId, price, mark, firstName, lastName, contact) {
  //   this.id = id;
  //   this.clientId = clientId;
  //   this.passport = passport;
  //   this.flightId = flightId;
  //   this.reservationId = reservationId;
  //   this.price = price;
  //   this.mark = mark;
  //   this.firstName = firstName;
  //   this.lastName = lastName;
  //   this.contact = contact;
  // }
}
