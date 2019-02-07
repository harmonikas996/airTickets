import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AirportsDetailsComponent } from './airports-details.component';

describe('AirportsDetailsComponent', () => {
  let component: AirportsDetailsComponent;
  let fixture: ComponentFixture<AirportsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AirportsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AirportsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
