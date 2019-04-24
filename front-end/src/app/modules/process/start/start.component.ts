import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
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
    private service: SharedService
  ) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    SharedService.log(this.id);
    this.http.get<any>('/api/process/' + this.id).subscribe(res => {
      SharedService.log(res.form.fields[0]);
      SharedService.log(res.form.fields[1]);
      this.result = res;
    });
  }

  submit() {
    SharedService.log(this.param);
    this.http.post('/api/process/start/' + this.id + '/' + this.service.userId, this.param).subscribe(res => {
      SharedService.log(res);
    });
  }

}
