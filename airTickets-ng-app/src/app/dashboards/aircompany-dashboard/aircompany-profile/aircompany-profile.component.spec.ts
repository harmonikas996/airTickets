import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AircompanyProfileComponent } from './aircompany-profile.component';

describe('AircompanyProfileComponent', () => {
  let component: AircompanyProfileComponent;
  let fixture: ComponentFixture<AircompanyProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AircompanyProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AircompanyProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
