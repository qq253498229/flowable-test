import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {SharedService} from '../shared.service';

@Component({
  selector: 'app-navigator',
  templateUrl: './navigator.component.html',
  styleUrls: ['./navigator.component.scss']
})
export class NavigatorComponent implements OnInit {

  constructor(
    private router: Router,
    private service: SharedService
  ) {
  }

  ngOnInit() {
  }

}
