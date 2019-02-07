import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomPriceNewComponent } from './room-price-new.component';

describe('RoomPriceNewComponent', () => {
  let component: RoomPriceNewComponent;
  let fixture: ComponentFixture<RoomPriceNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomPriceNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomPriceNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
