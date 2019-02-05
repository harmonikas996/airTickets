import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentacarsListComponent } from './rentacars-list.component';

describe('RentacarsListComponent', () => {
  let component: RentacarsListComponent;
  let fixture: ComponentFixture<RentacarsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentacarsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentacarsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
