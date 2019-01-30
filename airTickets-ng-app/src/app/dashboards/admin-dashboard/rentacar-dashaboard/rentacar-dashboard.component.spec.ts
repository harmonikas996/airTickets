import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentacarDashboardComponent } from './rentacar-dashboard.component';

describe('RentacarDashboardComponent', () => {
  let component: RentacarDashboardComponent;
  let fixture: ComponentFixture<RentacarDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentacarDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentacarDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
