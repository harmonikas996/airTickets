import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecialofferNewComponent } from './specialoffer-new.component';

describe('SpecialofferNewComponent', () => {
  let component: SpecialofferNewComponent;
  let fixture: ComponentFixture<SpecialofferNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpecialofferNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpecialofferNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
