import { TestBed, inject } from '@angular/core/testing';

import { RoomPriceService } from './room-price.service';

describe('RoomPriceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RoomPriceService]
    });
  });

  it('should be created', inject([RoomPriceService], (service: RoomPriceService) => {
    expect(service).toBeTruthy();
  }));
});
