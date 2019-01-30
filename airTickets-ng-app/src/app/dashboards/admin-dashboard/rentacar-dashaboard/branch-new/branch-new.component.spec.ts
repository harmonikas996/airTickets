import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BranchNewComponent } from './branch-new.component';

describe('BranchNewComponent', () => {
  let component: BranchNewComponent;
  let fixture: ComponentFixture<BranchNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BranchNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BranchNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
