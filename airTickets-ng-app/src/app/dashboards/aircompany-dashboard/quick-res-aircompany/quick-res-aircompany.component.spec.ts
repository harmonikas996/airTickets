import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuickResAircompanyComponent } from './quick-res-aircompany.component';

describe('QuickResAircompanyComponent', () => {
  let component: QuickResAircompanyComponent;
  let fixture: ComponentFixture<QuickResAircompanyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuickResAircompanyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuickResAircompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
