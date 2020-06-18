/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { TransmissionTableComponent } from './transmission-table.component';

describe('TransmissionTableComponent', () => {
  let component: TransmissionTableComponent;
  let fixture: ComponentFixture<TransmissionTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransmissionTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransmissionTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
