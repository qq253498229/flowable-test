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
      console.log(res);
      this.list = res;
    });
  }

  create(key: string) {
    console.log(key);
    this.service.createProcess(key).subscribe(res => {
      alert('开启实例成功');
    });
  }

}
