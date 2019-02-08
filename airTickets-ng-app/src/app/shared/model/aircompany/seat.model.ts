export class Seat {
  id: number;
  clientId: number;
  passport: string;
  flightId: number;
  reservationId: number;
  price: number;
  mark: string;
  firstName: string;
  lastName: string;
  contact: string;

  public Seat() {
    this.id = 0;
    this.clientId = 0;
    this.passport = '';
    this.flightId = 0;
    this.reservationId = 0;
    this.price = 0;
    this.mark = '';
    this.firstName = '';
    this.lastName = '';
    this.contact = '';
  }
}
