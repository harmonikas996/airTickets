import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightNewComponent } from './flight-new.component';

describe('FlightNewComponent', () => {
  let component: FlightNewComponent;
  let fixture: ComponentFixture<FlightNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlightNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
