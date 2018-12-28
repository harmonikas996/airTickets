import { TestBed, inject } from '@angular/core/testing';

import { RentacarService } from './rentacar.service';

describe('RentacarService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RentacarService]
    });
  });

  it('should be created', inject([RentacarService], (service: RentacarService) => {
    expect(service).toBeTruthy();
  }));
});
