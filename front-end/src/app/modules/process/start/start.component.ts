import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {SharedService} from '../../../shared/shared.service';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.scss']
})
export class StartComponent implements OnInit {
  param = {};
  id = '';

  result = {
    process: {
      name: ''
    },
    form: {
      fields: []
    },
  };

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private service: SharedService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.http.get<any>('/api/process/' + this.id).subscribe(res => {
      this.result = res;
    });
  }

  submit() {
    console.log(this.param);
    this.http.post('/api/process/start/' + this.id + '/' + this.service.userId, this.param).subscribe(() => {
      alert('开启成功');
      this.router.navigate(['/process']);
    });
  }

}
