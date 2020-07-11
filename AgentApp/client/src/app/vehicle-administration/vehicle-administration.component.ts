import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-vehicle-administration',
  templateUrl: './vehicle-administration.component.html',
  styleUrls: ['./vehicle-administration.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class VehicleAdministrationComponent implements OnInit {

  isAgent: boolean = false;

  constructor(
    private userService: UserService
  ) {
    this.isAgent = this.userService.getUser().company ? true : false;
   }

  ngOnInit(): void {
  }

}
