import { TestBed, inject } from '@angular/core/testing';

import { FlightReservationService } from './flight-reservation.service';

describe('FlightReservationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FlightReservationService]
    });
  });

  it('should be created', inject([FlightReservationService], (service: FlightReservationService) => {
    expect(service).toBeTruthy();
  }));
});
