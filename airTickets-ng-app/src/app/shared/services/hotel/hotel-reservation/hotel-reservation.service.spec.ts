import { TestBed, inject } from '@angular/core/testing';

import { HotelReservationService } from './hotel-reservation.service';

describe('HotelReservationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HotelReservationService]
    });
  });

  it('should be created', inject([HotelReservationService], (service: HotelReservationService) => {
    expect(service).toBeTruthy();
  }));
});
