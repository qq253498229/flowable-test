import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AppService} from '../app.service';

@Component({
  selector: 'app-task-detail',
  templateUrl: './task-detail.component.html',
  styleUrls: ['./task-detail.component.css']
})
export class TaskDetailComponent implements OnInit {
  taskId: string;

  properties = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: AppService
  ) {
  }

  ngOnInit() {
    this.taskId = this.route.snapshot.paramMap.get('id');
    this.service.getTaskForm(this.taskId).subscribe(res => {
      this.properties = res;
      console.log(this.properties);
    });
  }

  complete() {
    this.service.completeTask(this.taskId, this.properties).subscribe(() => {
      alert('成功');
      this.router.navigate(['/task']);
    });
  }

  cancel() {
    this.router.navigate(['/task']);
  }

}
