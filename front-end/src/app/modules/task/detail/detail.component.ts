import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {SharedService} from '../../../shared/shared.service';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  result: {
    name: string,
    createTime: Date,
    formModel: any,
    startFormModel: any
  } = {
    name: null,
    createTime: null,
    formModel: {},
    startFormModel: {}
  };

  param = {};
  id;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private service: SharedService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.http.get<any>('/api/task/info/' + this.id).subscribe(res => {
      console.log('taskInfo', res);
      this.result = res;
    });
  }

  submit() {
    console.log(this.param);
    this.http.post('/api/task/' + this.id, this.param).subscribe(res => {
      alert('提交成功');
      this.router.navigate(['/task']);
    });
  }
}
