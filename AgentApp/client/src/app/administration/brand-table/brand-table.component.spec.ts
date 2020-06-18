/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { BrandTableComponent } from './brand-table.component';

describe('BrandTableComponent', () => {
  let component: BrandTableComponent;
  let fixture: ComponentFixture<BrandTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BrandTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BrandTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
