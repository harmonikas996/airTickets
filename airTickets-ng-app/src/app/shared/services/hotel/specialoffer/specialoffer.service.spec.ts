import { TestBed, inject } from '@angular/core/testing';

import { SpecialofferService } from './specialoffer.service';

describe('SpecialofferService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SpecialofferService]
    });
  });

  it('should be created', inject([SpecialofferService], (service: SpecialofferService) => {
    expect(service).toBeTruthy();
  }));
});
