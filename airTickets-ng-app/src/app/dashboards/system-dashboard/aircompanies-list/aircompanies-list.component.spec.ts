import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AircompaniesListComponent } from './aircompanies-list.component';

describe('AircompaniesListComponent', () => {
  let component: AircompaniesListComponent;
  let fixture: ComponentFixture<AircompaniesListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AircompaniesListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AircompaniesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
