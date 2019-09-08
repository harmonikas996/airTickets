import { TestBed, inject } from '@angular/core/testing';

import { RoomRatingService } from './room-rating.service';

describe('RoomRatingService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RoomRatingService]
    });
  });

  it('should be created', inject([RoomRatingService], (service: RoomRatingService) => {
    expect(service).toBeTruthy();
  }));
});
