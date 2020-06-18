import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleAdministrationComponent } from './vehicle-administration.component';

describe('VehicleAdministrationComponent', () => {
  let component: VehicleAdministrationComponent;
  let fixture: ComponentFixture<VehicleAdministrationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleAdministrationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleAdministrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
