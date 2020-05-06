import { LocationService } from './location.service';
import { Component, OnInit, ViewChild, ElementRef, NgZone, ViewEncapsulation } from '@angular/core';
import { StarRatingComponent } from 'ng-starrating';
import {AngularYandexMapsModule} from 'angular8-yandex-maps';

@Component({
  selector: 'app-rentals',
  templateUrl: './rentals.component.html',
  styleUrls: ['./rentals.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RentalsComponent implements OnInit {

  constructor(private locationService: LocationService) { }
  latitude: number;
  longitude: number;
  zoom: number;
  address: string;
  searchText: string;
  private geoCoder;
  response: any;
  @ViewChild('search')
  public searchElementRef: ElementRef;
  ngOnInit() {
    this.response = [];
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
