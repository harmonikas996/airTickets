import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AmenityNewComponent } from './amenity-new.component';

describe('AmenityNewComponent', () => {
  let component: AmenityNewComponent;
  let fixture: ComponentFixture<AmenityNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AmenityNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AmenityNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
