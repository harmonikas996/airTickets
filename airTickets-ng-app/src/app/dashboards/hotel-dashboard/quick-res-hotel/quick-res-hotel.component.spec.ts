import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuickResHotelComponent } from './quick-res-hotel.component';

describe('QuickResHotelComponent', () => {
  let component: QuickResHotelComponent;
  let fixture: ComponentFixture<QuickResHotelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuickResHotelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuickResHotelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
