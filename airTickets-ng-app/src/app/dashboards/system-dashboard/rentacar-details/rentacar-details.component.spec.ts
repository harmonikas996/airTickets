import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentacarDetailsComponent } from './rentacar-details.component';

describe('RentacarDetailsComponent', () => {
  let component: RentacarDetailsComponent;
  let fixture: ComponentFixture<RentacarDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentacarDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentacarDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
