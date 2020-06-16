import { FuelService } from './../services/fuel.service';
import { LocationService } from './location.service';
import { Component, OnInit, ViewChild, ElementRef, ViewEncapsulation } from '@angular/core';
import { Car } from '../models/Car.model';
import { SearchService } from '../services/search.service';
import { Category } from '../models/Category.model';
import { Brand } from '../models/Brand.model';
import { Model } from '../models/Model.model';
import { Fuel } from '../models/Fuel.model';
import { Transmission } from '../models/Transmission.model';
import { BrandService } from '../services/brand.service';
import { CategoryService } from '../services/category.service';
import { TransmissionService } from '../services/transmission.service';

export class SearchParams {
  loc_lat: number;
  loc_long: number;
  start: number;
  end: number;

  categories: Category[];
  brands: Brand[];
  models: Model[];
  fuels: Fuel[];
  transmissions: Transmission[];
}

@Component({
  selector: 'app-rentals',
  templateUrl: './rentals.component.html',
  styleUrls: ['./rentals.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RentalsComponent implements OnInit {

  latitude: number;
  longitude: number;
  zoom: number;
  address: string;
  searchText: string;
  response: any;
  rating: Number;

  categories: Category[];
  brands: Brand[];
  models: Model[];
  fuels: Fuel[];
  transmissions: Transmission[];

  searchParamsObjects: SearchParams;

  cars: Car[];
  pageNo: number;
  totalPages: number;
  tripStartDate: Date;
  tripEndDate: Date;
  tripStartTime: number;
  tripEndTime: number;

  constructor(private locationService: LocationService,
    private searchService: SearchService,
    private brandService: BrandService,
    private categoryService: CategoryService,
    private fuelService: FuelService,
    private trannsmissionService: TransmissionService) {

    this.initializeData();
    this.pageNo = 0;
    this.getCars(this.pageNo);
  }


  @ViewChild('search')
  public searchElementRef: ElementRef;
  ngOnInit() {
    this.response = [];
    this.rating = 4.2;

  }

  initializeData() {
    this.searchParamsObjects = new SearchParams();
    this.searchParamsObjects.categories = new Array();
    this.searchParamsObjects.brands = new Array();
    this.searchParamsObjects.models = new Array();
    this.searchParamsObjects.fuels = new Array();
    this.searchParamsObjects.transmissions = new Array();

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
  }

  searchDataChanged(event) {
    if (this.searchText == '') {
      return;
    }

    const promise = new Promise((resolve, reject) => {
      this.locationService.getPossiblePlaces(this.searchText).subscribe(
        (data) => {
          this.response = data;
          this.response = this.response.response.GeoObjectCollection.featureMember;
          resolve();
        },
        (error) => { alert(error); reject(); }
      );
    });
    return promise;
  }

  selectionChanged() {

  }

  startTimeChange(value) {
    this.tripStartTime = value;
  }

  endTimeChange(value) {
    this.tripEndTime = value;
  }

  from() {
    return Number(this.tripStartDate?.getTime()) / 1000 + this.tripStartTime * 60;
  }

  to() {
    return Number(this.tripEndDate?.getTime()) / 1000 + this.tripEndTime * 60;
  }

  getCars(pageNo: number) {
    const searchParams = {
      loc_lat: 45.2605774,
      loc_long: 19.8009594,
      start: 1592838000,
      end: 1592838300,
      brand: this.searchParamsObjects.brands.length > 0 ? this.searchParamsObjects.brands.map(x => x.name) : null,
      model: this.searchParamsObjects.models.length > 0 ? this.searchParamsObjects.models.map(x => x.name) : null,
      category: this.searchParamsObjects.categories.length > 0 ? this.searchParamsObjects.categories.map(x => x.name) : null,
      fuel: this.searchParamsObjects.fuels.length > 0 ? this.searchParamsObjects.fuels.map(x => x.name) : null,
      transmission: this.searchParamsObjects.transmissions.length > 0 ? this.searchParamsObjects.transmissions.map(x => x.name) : null,
    };
    this.searchService.search(pageNo, searchParams).subscribe(
      (data: any) => {
        this.cars = data.content;
        this.pageNo = data.pageNo;
        this.totalPages = data.totalPages;
      },
      (error) => {
        alert(error);
      }
    );
  }

  addSearchParamsCategory(event, category: Category) {
    if (event.checked) {
      this.searchParamsObjects.categories.push(category);
    } else {
      this.searchParamsObjects.categories.splice(this.categories.indexOf(category), 1);
    }
  }
  addSearchParamsFuels(event, fuel: Fuel) {
    if (event.checked) {
      this.searchParamsObjects.fuels.push(fuel);
    } else {
      this.searchParamsObjects.fuels.splice(this.categories.indexOf(fuel), 1);
    }
  }
  addSearchParamsTransmissions(event, transmission: Transmission) {
    if (event.checked) {
      this.searchParamsObjects.transmissions.push(transmission);
    } else {
      this.searchParamsObjects.transmissions.splice(this.categories.indexOf(transmission), 1);
    }
  }

  showVehicle(car: Car) {
    let params = "?vehicleID=" + car.id + "&startDate=" + this.tripStartDate
      + "&endDate=" + this.tripEndDate
      + "&startTime=" + this.tripStartTime
      + "&endTime=" + this.tripEndTime;
    window.open("/vehicle" + params, "_blank");
    // todo in F-S-2
  }
  loadMore(pageNo: number) {
    this.pageNo = pageNo;
    this.getCars(pageNo);
  }
  brandSelectionChanged(selectedBrands) {
    this.searchParamsObjects.brands = selectedBrands.value;
    this.models = [];
    selectedBrands.value.forEach(brand => {
      this.models.push(...brand.models);
    });
  }
  modelSelectionChanged(selectedModels) {
    this.searchParamsObjects.models = selectedModels.value;
  }
  moveAdvancedSearch(destination: string) {
    var dom = window.document;
    dom.getElementById(destination).prepend(document.getElementById("tempAdvancedSearch"));
  }
}