import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';

@Component({
  selector: 'app-process',
  templateUrl: './process.component.html',
  styleUrls: ['./process.component.css']
})
export class ProcessComponent implements OnInit {

  list = [];

  constructor(
    private service: AppService
  ) {
  }

  ngOnInit() {
    this.service.processList().subscribe(res => {
      this.service.log(res);
      this.list = res;
    });
  }

  create(key: string) {
    this.service.log(key);
    this.service.createProcess(key).subscribe(res => {
      alert('开启实例成功');
    });
  }

}
