import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecialOfferUpdateComponent } from './special-offer-update.component';

describe('SpecialOfferUpdateComponent', () => {
  let component: SpecialOfferUpdateComponent;
  let fixture: ComponentFixture<SpecialOfferUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpecialOfferUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpecialOfferUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
