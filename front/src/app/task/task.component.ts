import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  taskList: any[] = [];
  groupId: string;


  constructor(
    private service: AppService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.service.getUserGroup().subscribe(res => {
      this.groupId = res[0]['groupId'];
    });
    this.service.getTaskList().subscribe(res => {
      console.log(res);
      this.taskList = res;
    });
  }

  detail(id: string) {
    this.router.navigate(['/task', id]);
  }

  claim(id: string) {
    console.log(id);
    this.service.claim(id).subscribe(res => {
      this.ngOnInit();
    });
  }

}
