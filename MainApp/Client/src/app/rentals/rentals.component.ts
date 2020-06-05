import { LocationService } from './location.service';
import { Component, OnInit, ViewChild, ElementRef, NgZone, ViewEncapsulation } from '@angular/core';
import {AngularYandexMapsModule} from 'angular8-yandex-maps';
import { Car } from '../models/Car.model';
import { Review } from '../models/Review.model';
import { SearchService } from '../services/search.service';
import { Category } from '../models/Category.model';
import { Brand } from '../models/Brand.model';
import { Model } from '../models/Model.model';
import { Fuel } from '../models/Fuel.model';
import { Transmission } from '../models/Transmission.model';
import { BrandService } from '../services/brand.service';
import { CategoryService } from '../services/category.service';

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
  private geoCoder;
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

  constructor(private locationService: LocationService,
              private searchService: SearchService,
              private brandService: BrandService,
              private categoryService: CategoryService) {

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

  initializeData(){
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
  }

  searchDataChanged(event){
    console.log(event);
    if (this.searchText == ''){
      return;
    }

    const promise = new Promise((resolve, reject) => {
    this.locationService.getPossiblePlaces(this.searchText).subscribe(
      (data) => {
        this.response = data;
        this.response = this.response.response.GeoObjectCollection.featureMember;
        console.log(this.response); resolve(); },
      (error) => { alert(error); reject(); }
    );
    });
    return promise;
  }

  selectionChanged(event){

  }

  getCars(pageNo: number) {
    // let searchParams;
    // searchParams.brand = this.searchParamsObjects.brands.map(x => x.name);
    // searchParams.category = this.searchParamsObjects.categories.map(x => x.name);
    // searchParams.fuel = this.searchParamsObjects.fuels.map(x => x.name);
    // searchParams.model = this.searchParamsObjects.models.map(x => x.name);
    // searchParams.transmission = this.searchParamsObjects.transmissions.map(x => x.name);

    const searchParams = {
      loc_lat: 45.2605774,
      loc_long: 19.8009594,
      start : 1592838000,
      end: 1592838300,
      brand: 'BMW,Mercedes'
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

  addSearchParamsCategory(event, category: Category){
    if (event.checked){
      this.searchParamsObjects.categories.push(category);
    } else {
      this.searchParamsObjects.categories.splice(this.categories.indexOf(category), 1);
    }
  }
}
