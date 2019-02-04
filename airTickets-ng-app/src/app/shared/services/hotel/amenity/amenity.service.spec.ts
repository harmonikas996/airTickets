import { TestBed, inject } from '@angular/core/testing';

import { AmenityService } from './amenity.service';

describe('AmenityService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AmenityService]
    });
  });

  it('should be created', inject([AmenityService], (service: AmenityService) => {
    expect(service).toBeTruthy();
  }));
});
