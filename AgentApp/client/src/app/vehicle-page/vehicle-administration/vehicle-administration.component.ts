import { VehicleService } from './../../services/vehicle.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { trigger, transition, style, animate } from '@angular/animations';
import { SearchService } from 'src/app/services/search.service';
import { BrandService } from 'src/app/services/brand.service';
import { CategoryService } from 'src/app/services/category.service';
import { FuelService } from 'src/app/services/fuel.service';
import { TransmissionService } from 'src/app/services/transmission.service';
import { PricelistService } from 'src/app/services/pricelist.service';
import { OccupancyService } from 'src/app/services/occupancy.service';
import { VehicleOccupancy } from 'src/app/models/VehicleOccupancy.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-vehicle-administration',
  templateUrl: './vehicle-administration.component.html',
  styleUrls: ['./vehicle-administration.component.css'],
  animations: [
    trigger(
      'inOutAnimation',
      [
        transition(
          ':enter',
          [
            style({ opacity: 0 }),
            animate('1s ease-out',
              style({ opacity: 1 }))
          ]
        ),
        transition(
          ':leave',
          [
            style({ opacity: 1 }),
            animate('1s ease-in',
              style({ opacity: 0 }))
          ]
        )
      ]
    )
  ]
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
  fuels: Array<any> = [
    { id: 1560608769632, name: 'Gas' },
    { id: 1560608796014, name: 'Diesel' },
    { id: 1560608787815, name: 'Electric' },
    { id: 1560608805101, name: 'Coal' }
  ];
  transmissions: Array<any> = [
    { id: 1560608769632, name: 'Automatic' },
    { id: 1560608796014, name: 'Manual 4 speed' },
    { id: 1560608787815, name: 'Manual 5 speed' },
    { id: 1560608805101, name: 'Manual 6 speed' }
  ];
  pricelists: Array<any> = [
    { id: 1560608769632, name: 'Empty' }
  ];

  vehicle: Object = null;
  vehicleCancelBackup: Object = null;

  private sub: any;
  form: FormGroup;
  edditing: boolean = false;
  now: Date = new Date();
  images: any;

  constructor(private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private searchService: SearchService,
    private brandService: BrandService,
    private categoryService: CategoryService,
    private fuelService: FuelService,
    private trannsmissionService: TransmissionService,
    private vehicleService: VehicleService,
    private pricelistService: PricelistService,
    private occupancyService: OccupancyService,
    private userService: UserService) {

  }

  ngOnInit() {
    this.sub = this.activatedRoute.params.subscribe((params) => {

      //var vehicle_id = params.id;

      //change form with vehicle
      // this.form.setValue({
      //   brand:  1560608796014,
      //   model: 1560608769632,
      //   category: 1560608805101,
      //   transmission: 1560608805101,
      //   fuel: 1560608805101,
      //   mileage: 30000,
      //   seats: 3,
      //   alwaysAvailable: true,
      //   availableFrom: [''],
      //   availableUntil: [''],
      // });
    });
    this.form = this.formBuilder.group({
      brand: ['', [Validators.required]],
      model: ['', [Validators.required]],
      category: ['', [Validators.required]],
      transmission: ['', [Validators.required]],
      fuel: ['', [Validators.required]],
      mileage: ['', [Validators.required]],
      seats: ['', [Validators.required]],
      childSeats: ['', [Validators.required]],

      hasAndroid: [false],

      alwaysAvailable: [false],
      occupiedFrom: [''],
      occupiedTo: [''],

      unlimitedMileage: [false],
      availableMileage: [''],

      pricelist: ['', [Validators.required]],

      cdw: [false],
    });
    this.brandService.getAll().subscribe(
      (data: any) => {
        this.brands = data;
      },
      (error) => {
        alert(error);
      }
    );
    this.categoryService.getAll().subscribe(
      (data: any) => {
        this.categories = data;
      },
      (error) => {
        alert(error);
      }
    );
    this.fuelService.getAll().subscribe(
      (data: any) => {
        this.fuels = data;
      },
      (error) => {
        alert(error);
      }
    );
    this.trannsmissionService.getAll().subscribe(
      (data: any) => {
        this.transmissions = data;
      },
      (error) => {
        alert(error);
      }
    );

    let userId = this.userService.getUser().id;

    this.pricelistService.getByOwner(userId).subscribe(
      (data: any) => {
        this.pricelists = data;
      },
      (error) => {
        alert("Error getting pricelists, maybe you need to create a pricelist first?");
      }
    );

  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  brandSelectionChanged(selectedBrands) {
    this.models = selectedBrands.value.models;
  }

  addVehicle(vehicle, images) {
    this.vehicleService.addVehicle(vehicle, images).subscribe(
      (data: any) => {
        alert("Vehicle created");

        if(this.form.controls.hasAndroid.value) {
          let rand = Math.random();
          let message = `This is a token for your vehicles Android device: ${data.id} ${rand}`;
          alert(message);
        }

        if (!this.form.controls.alwaysAvailable.value) {
          let occupancy = new VehicleOccupancy(null, Number(this.form.controls.occupiedFrom.value) / 1000, Number(this.form.controls.occupiedTo.value) / 1000, "MANUAL", null);
          this.addOccupancy(data.id, occupancy)
        }
        else {
          this.ngOnInit();
        }
      },
      (error) => { alert(error); }
    );
  }

  addOccupancy(vehicleId, occupancy) {
    let promise = new Promise((resolve, reject) => {
      this.occupancyService.add(vehicleId, occupancy).subscribe(
        (data: any) => {
          alert("Occupancy added");
          this.ngOnInit();
        },
        (error) => { alert(error); }
      );
    });
    return promise;
  }

  onSubmit(data) {
    var vehicle = {
      brand: {
        id: this.form.controls.brand.value.id,
        name: this.form.controls.brand.value.name
      },
      model: {
        id: this.form.controls.model.value.id,
        name: this.form.controls.model.value.name
      },
      category: {
        id: this.form.controls.category.value.id,
        name: this.form.controls.category.value.name
      },
      seats: this.form.controls.seats.value,
      transmission: {
        id: this.form.controls.transmission.value.id,
        name: this.form.controls.transmission.value.name
      },
      fuel: {
        id: this.form.controls.fuel.value.id,
        name: this.form.controls.fuel.value.name
      },
      pricelist: {
        id: this.form.controls.pricelist.value.id,
        ownerId: this.form.controls.pricelist.value.ownerId,
        name: this.form.controls.pricelist.value.name,
        pricePerDay: this.form.controls.pricelist.value.pricePerDay,
        pricePerKm: this.form.controls.pricelist.value.pricePerKm,
        cdw: this.form.controls.pricelist.value.cdw,
        description: this.form.controls.pricelist.value.description
      },
      childSeats: this.form.controls.childSeats.value,
      mileage: this.form.controls.mileage.value,
      availableMileage: this.form.controls.unlimitedMileage.value ? -1 : this.form.controls.availableMileage.value,
      cdw: this.form.controls.cdw.value,
      numberOfStars: 0,
      numberOfReviews: 0,
      locationLongitude: 19.8317609,
      locationLatitude: 45.2553823,

      ownerId: this.userService.getUser().id
    }

    this.addVehicle(vehicle, this.images.files);

  }
  doAction() {
    // this.dialogRef.close({event:this.action,data:this.local_data});
  }
}