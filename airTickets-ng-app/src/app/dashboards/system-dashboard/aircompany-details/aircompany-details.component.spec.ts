import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AircompanyDetailsComponent } from './aircompany-details.component';

describe('AircompanyDetailsComponent', () => {
  let component: AircompanyDetailsComponent;
  let fixture: ComponentFixture<AircompanyDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AircompanyDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AircompanyDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
