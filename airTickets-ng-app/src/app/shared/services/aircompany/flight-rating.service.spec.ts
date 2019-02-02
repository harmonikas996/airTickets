import { TestBed, inject } from '@angular/core/testing';

import { FlightRatingService } from './flight-rating.service';

describe('FlightRatingService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FlightRatingService]
    });
  });

  it('should be created', inject([FlightRatingService], (service: FlightRatingService) => {
    expect(service).toBeTruthy();
  }));
});
