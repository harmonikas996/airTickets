import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentacarNewComponent } from './rentacar-new.component';

describe('RentacarNewComponent', () => {
  let component: RentacarNewComponent;
  let fixture: ComponentFixture<RentacarNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentacarNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentacarNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
