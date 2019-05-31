import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  dataSet = [];

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.http.get<any[]>('/api/process').subscribe(res => {
      console.log('processList', res);
      this.dataSet = res;
    });
  }

  open(id: any) {
    console.log('openId', id);
    this.router.navigate(['/process', id]);
  }
}
