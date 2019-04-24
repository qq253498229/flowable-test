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

  result = {
    process: {
      name: ''
    },
    form: {
      fields: []
    }
  };

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private service: SharedService
  ) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.http.get<any>('/api/process/' + id).subscribe(res => {
      this.service.log(res.form.fields[0]);
      this.service.log(res.form.fields[1]);
      this.result = res;
    });
  }

  submit() {
    this.service.log(this.param);
  }

}
