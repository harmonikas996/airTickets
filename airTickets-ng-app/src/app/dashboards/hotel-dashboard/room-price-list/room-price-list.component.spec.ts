import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomPriceListComponent } from './room-price-list.component';

describe('RoomPriceListComponent', () => {
  let component: RoomPriceListComponent;
  let fixture: ComponentFixture<RoomPriceListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomPriceListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomPriceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
