import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomPriceDetailsComponent } from './room-price-details.component';

describe('RoomPriceDetailsComponent', () => {
  let component: RoomPriceDetailsComponent;
  let fixture: ComponentFixture<RoomPriceDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomPriceDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomPriceDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
