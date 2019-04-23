import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  dataSet = [];

  constructor(
    private http: HttpClient
  ) {
  }

  ngOnInit() {
    this.http.get<any[]>('/api/user').subscribe(res => {
      console.log(res);
      this.dataSet = res;
    });
  }

}
