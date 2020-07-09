import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public userService: UserService,
    private router: Router) { }

  ngOnInit() {
  }

  administrator() {
    if (!this.userService.checkLoggedIn()){
      return false;
    }
    if (this.userService.getUser().roles.some( role => role.name ==="ROLE_ADMIN")) {
      return true;
    }
    return false;
  }

  owner() {
    if (!this.userService.checkLoggedIn()){
      return false;
    }
    if (this.userService.getUser().roles.some( role => role.name ==="ROLE_VEHICLE_OWNER")) {
      return true;
    }
    return false;
  }

  logout() {
    this.userService.logout();
  }
}
