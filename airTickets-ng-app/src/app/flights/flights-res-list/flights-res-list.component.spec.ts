import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightsResListComponent } from './flights-res-list.component';

describe('FlightsResListComponent', () => {
  let component: FlightsResListComponent;
  let fixture: ComponentFixture<FlightsResListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlightsResListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightsResListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
