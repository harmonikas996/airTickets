import { TestBed, inject } from '@angular/core/testing';

import { CarReservationService } from './car-reservation.service';

describe('CarReservationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CarReservationService]
    });
  });

  it('should be created', inject([CarReservationService], (service: CarReservationService) => {
    expect(service).toBeTruthy();
  }));
});
