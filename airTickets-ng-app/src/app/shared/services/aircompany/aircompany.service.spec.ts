import { TestBed, inject } from '@angular/core/testing';

import { AircompanyService } from './aircompany.service';

describe('AircompanyService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AircompanyService]
    });
  });

  it('should be created', inject([AircompanyService], (service: AircompanyService) => {
    expect(service).toBeTruthy();
  }));
});
