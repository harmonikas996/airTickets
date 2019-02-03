import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelNewComponent } from './hotel-new.component';

describe('HotelNewComponent', () => {
  let component: HotelNewComponent;
  let fixture: ComponentFixture<HotelNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
