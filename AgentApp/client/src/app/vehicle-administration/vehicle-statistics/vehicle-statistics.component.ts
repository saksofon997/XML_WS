import { Component, OnInit } from '@angular/core';
import { VehicleService } from 'src/app/services/vehicle.service';
import { Car } from 'src/app/models/Car.model';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';

@Component({
  selector: 'app-vehicle-statistics',
  templateUrl: './vehicle-statistics.component.html',
  styleUrls: ['./vehicle-statistics.component.css']
})
export class VehicleStatisticsComponent implements OnInit {
  dataSource: Car[];
  status = 'MILEAGE'
  VehicleOptions: ChartOptions = {
    responsive: true,
  };
  VehicleLabels: Label[] = [];
  VehicleType: ChartType = 'horizontalBar';
  VehicleLegend = true;
  VehiclePlugins = [];
  VehicleColors: Color[]=[{
    backgroundColor: [
      'rgba(255, 99, 132, 0.2)',
      'rgba(54, 162, 235, 0.2)',
      'rgba(255, 206, 86, 0.2)',
      'rgba(0, 255, 0, 0.2)',
      'rgba(102, 0, 204, 0.2)',
      'rgba(255, 128, 0, 0.2)'
    ],
    hoverBackgroundColor:"#EEEEEE",
    borderColor:"#585858",
    hoverBorderColor:"#585858"
}];
VehicleData: ChartDataSets[] = [
    { data: [], label: 'Locations' }
  ];
  constructor( private vehicleService: VehicleService) { }

  ngOnInit() {
    this.vehicleService.getByOwner(1).subscribe(
      (data: any) => {
        this.dataSource = data;
        this.sortVehicles('mileage');
      },
      (error) => {
        alert(error);
      });
  }

  sortVehicles(sortType: string){
    this.VehicleLabels = [];
    this.VehicleData[0].data= [];
    if(sortType == 'mileage'){
      this.status = 'MILEAGE'
      this.dataSource.sort((a,b) => (a.mileage > b.mileage) ? 1 : ((b.mileage > a.mileage) ? -1 : 0));
      this.dataSource.forEach((car) => {
        this.VehicleLabels.push(car.brand.name + ' ' + car.model.name);
        this.VehicleData[0].data.push(car.mileage);
        this.VehicleData[0].label = 'Mileage';
      })
    }else if(sortType == 'reviews'){
      this.status = 'REVIEWS'
      this.dataSource.sort((a,b) => (a.numberOfReviews > b.numberOfReviews) ? 1 : ((b.numberOfReviews > a.numberOfReviews) ? -1 : 0));
      this.dataSource.forEach((car) => {
        this.VehicleLabels.push(car.brand.name + ' ' + car.model.name);
        this.VehicleData[0].data.push(car.numberOfReviews);
        console.log(car.numberOfReviews);
        this.VehicleData[0].label = 'Number of reviews';
      })
    } else if(sortType == 'stars'){
      this.status = 'STARS'
      this.dataSource.sort((a,b) => (a.numberOfStars > b.numberOfStars) ? 1 : ((b.numberOfStars > a.numberOfStars) ? -1 : 0));
      this.dataSource.forEach((car) => {
        this.VehicleLabels.push(car.brand.name + ' ' + car.model.name);
        this.VehicleData[0].data.push(car.numberOfStars);
        console.log(car.numberOfStars);
        this.VehicleData[0].label = 'Rating';
      })
    }
     
  }
}
