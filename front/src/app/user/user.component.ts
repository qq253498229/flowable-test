import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  userList: any[];
  user: any;

  constructor(
    private service: AppService
  ) {
  }

  ngOnInit() {
    this.userList = this.service.getUserList();
    this.user = this.service.getUser();
    this.service.log(this.userList);
  }

  logout(item: any) {
    this.service.logout(item);
    this.ngOnInit();
  }

  change(item: any) {
    this.service.setUser(item);
    this.ngOnInit();
  }
}
