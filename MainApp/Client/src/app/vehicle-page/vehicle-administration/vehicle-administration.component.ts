import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-vehicle-administration',
  templateUrl: './vehicle-administration.component.html',
  styleUrls: ['./vehicle-administration.component.css']
})
export class VehicleAdministrationComponent implements OnInit {

  categories: Array<any> = [
    { id: 1560608769632, name: 'SUV' },
    { id: 1560608796014, name: 'Limousine' },
    { id: 1560608787815, name: 'Exotic' },
    { id: 1560608805101, name: 'Tank' }
  ];
  brands: Array<any> = [
    { id: 1560608769632, name: 'Mercedes' },
    { id: 1560608796014, name: 'BMW' },
    { id: 1560608787815, name: 'Nissan' },
    { id: 1560608805101, name: 'Toyota' }
  ];
  models: Array<any> = [
    { id: 1560608769632, name: 'R8' },
    { id: 1560608796014, name: 'M3' },
    { id: 1560608787815, name: 'Supra' },
    { id: 1560608805101, name: 'T30' }
  ];
  fuelTypes: Array<any> = [
    { id: 1560608769632, name: 'Gas' },
    { id: 1560608796014, name: 'Diesel' },
    { id: 1560608787815, name: 'Electric' },
    { id: 1560608805101, name: 'Coal' }
  ];
  transmissionTypes: Array<any> = [
    { id: 1560608769632, name: 'Automatic' },
    { id: 1560608796014, name: 'Manual 4 speed' },
    { id: 1560608787815, name: 'Manual 5 speed' },
    { id: 1560608805101, name: 'Manual 6 speed' }
  ];

  vehicle: Object = null;
  vehicleCancelBackup: Object = null;

  private sub: any;
  form: FormGroup;
  edditing: boolean = false;

  constructor(private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,) {

      this.form = this.formBuilder.group({
        brand: ['', [Validators.required]],
        model: ['', [Validators.required]],
        category: ['', [Validators.required]],
        transmission: ['', [Validators.required]],
        fuel: ['', [Validators.required]],
        mileage: ['', [Validators.required]],
        seats: ['', [Validators.required]],
      });
  }

  ngOnInit() {
    this.sub = this.activatedRoute.params.subscribe((params) => {
      //var vehicle_id = params.id;

      //change form
      // this.expenForm.setValue({
      //   person_name: this.expen.person_name,
      //   amount: this.expen.amount
      //   description: this.expen.description
      // });
    });

  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  onSubmit(data) {
    console.log(data);
  }

}
