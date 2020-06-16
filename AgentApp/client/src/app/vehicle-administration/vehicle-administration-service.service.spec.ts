/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { VehicleAdministrationServiceService } from './vehicle-administration-service.service';

describe('Service: VehicleAdministrationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [VehicleAdministrationServiceService]
    });
  });

  it('should ...', inject([VehicleAdministrationServiceService], (service: VehicleAdministrationServiceService) => {
    expect(service).toBeTruthy();
  }));
});
