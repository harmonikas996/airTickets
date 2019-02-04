import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AmenityDetailsComponent } from './amenity-details.component';

describe('AmenityDetailsComponent', () => {
  let component: AmenityDetailsComponent;
  let fixture: ComponentFixture<AmenityDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AmenityDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AmenityDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
