import { TestBed, inject } from '@angular/core/testing';

import { HotelRatingService } from './hotel-rating.service';

describe('HotelRatingService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HotelRatingService]
    });
  });

  it('should be created', inject([HotelRatingService], (service: HotelRatingService) => {
    expect(service).toBeTruthy();
  }));
});
