import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AircompanyNewComponent } from './aircompany-new.component';

describe('AircompanyNewComponent', () => {
  let component: AircompanyNewComponent;
  let fixture: ComponentFixture<AircompanyNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AircompanyNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AircompanyNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
