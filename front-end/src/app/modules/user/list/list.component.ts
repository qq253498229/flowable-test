import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SharedService} from '../../../shared/shared.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  dataSet = [];

  constructor(
    private http: HttpClient,
    private service: SharedService
  ) {
  }

  ngOnInit() {
    this.http.get<any[]>('/api/user').subscribe(res => {
      SharedService.log(res);
      this.dataSet = res;
    });
  }

  choose(id: any) {
    this.service.userId = id;
  }
}
