import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightRatingComponent } from './flight-rating.component';

describe('FlightRatingComponent', () => {
  let component: FlightRatingComponent;
  let fixture: ComponentFixture<FlightRatingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlightRatingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
