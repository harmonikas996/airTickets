import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AircompanyDashboardComponent } from './aircompany-dashboard.component';

describe('AircompanyDashboardComponent', () => {
  let component: AircompanyDashboardComponent;
  let fixture: ComponentFixture<AircompanyDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AircompanyDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AircompanyDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
