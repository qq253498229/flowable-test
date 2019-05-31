import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SharedService} from '../../../shared/shared.service';
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
    private service: SharedService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.http.get<any[]>('/api/task/' + this.service.userId).subscribe(res => {
      console.log('taskList', res);
      this.dataSet = res;
    });
  }

  operation(data: any) {
    console.log('operation', data);
    this.router.navigate(['/task', data.id]);
  }

  delete(data: any) {
    console.log('delete', data);
    this.http.delete('/api/task/' + data.id).subscribe(() => {
      alert('删除成功');
      this.ngOnInit();
    });
  }
}
