import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BetweenStepsComponent } from './between-steps.component';

describe('BetweenStepsComponent', () => {
  let component: BetweenStepsComponent;
  let fixture: ComponentFixture<BetweenStepsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BetweenStepsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BetweenStepsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
