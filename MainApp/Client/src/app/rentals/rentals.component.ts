import { LocationService } from './location.service';
import { Component, OnInit, ViewChild, ElementRef, NgZone, ViewEncapsulation } from '@angular/core';
import {AngularYandexMapsModule} from 'angular8-yandex-maps';
import { Car } from '../models/Car.model';
import { Review } from '../models/Review.model';
@Component({
  selector: 'app-rentals',
  templateUrl: './rentals.component.html',
  styleUrls: ['./rentals.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RentalsComponent implements OnInit {

  constructor(private locationService: LocationService) { 
    //var review = new Review(4,null,{id:null,name:""},
    //{id:null,name: ""},
    //"");
    //this.cars.push(new Car(["https://article.images.consumerreports.org/f_auto/prod/content/dam/CRO%20Images%202018/Cars/November/CR-Cars-InlineHero-2019-Honda-Insight-driving-trees-11-18"],
    //"Jaguar","I dont know","Diesel","Automatic", "A",2,"Unlimited",1000,2,review,1));
    //this.cars.push(new Car(["https://www.testoviautomobila.rs/wp-content/uploads/2015/05/fica-prelepa-slika-840x420.jpg"],
    //"Zastava","500","Gasoline","Manual", "A",15,"Unlimited",5000,5,review,2));
    
  }
  latitude: number;
  longitude: number;
  zoom: number;
  address: string;
  searchText: string;
  private geoCoder;
  response: any;
  rating: Number;
  cars = new Array<Car>();

  @ViewChild('search')
  public searchElementRef: ElementRef;
  ngOnInit() {
    this.response = [];
    this.rating = 4.2;
    
  }
  searchDataChanged(event){
    console.log(event);
    if (this.searchText == ''){
      return;
    }
    
    let promise = new Promise((resolve, reject) => {
    this.locationService.getPossiblePlaces(this.searchText).subscribe(
      (data) => {
        this.response = data;
        this.response = this.response.response['GeoObjectCollection']['featureMember'];
       console.log(this.response); resolve();},
      (error) => { alert(error); reject(); }
    );
    });
    return promise;
  }
  
  selectionChanged(event){

  }

}
