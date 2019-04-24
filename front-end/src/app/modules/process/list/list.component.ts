import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
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
    private router: Router
  ) {
  }

  ngOnInit() {
    this.http.get<any[]>('/api/process').subscribe(res => {
      SharedService.log(res);
      this.dataSet = res;
    });
  }

  open(id: any) {
    this.router.navigate(['/process', id]);
  }
}
