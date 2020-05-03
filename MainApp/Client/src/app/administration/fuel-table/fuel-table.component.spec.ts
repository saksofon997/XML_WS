/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { FuelTableComponent } from './fuel-table.component';

describe('FuelTableComponent', () => {
  let component: FuelTableComponent;
  let fixture: ComponentFixture<FuelTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FuelTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FuelTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
