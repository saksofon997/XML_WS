/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { PricelistTableComponent } from './pricelist-table.component';

describe('PricelistTableComponent', () => {
  let component: PricelistTableComponent;
  let fixture: ComponentFixture<PricelistTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PricelistTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PricelistTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
