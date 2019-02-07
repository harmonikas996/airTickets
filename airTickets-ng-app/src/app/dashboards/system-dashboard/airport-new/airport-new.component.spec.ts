import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AirportNewComponent } from './airport-new.component';

describe('AirportNewComponent', () => {
  let component: AirportNewComponent;
  let fixture: ComponentFixture<AirportNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AirportNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AirportNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
