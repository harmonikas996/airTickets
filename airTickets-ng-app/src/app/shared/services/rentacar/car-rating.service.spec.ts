import { TestBed, inject } from '@angular/core/testing';

import { CarRatingService } from './car-rating.service';

describe('CarRatingService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CarRatingService]
    });
  });

  it('should be created', inject([CarRatingService], (service: CarRatingService) => {
    expect(service).toBeTruthy();
  }));
});
