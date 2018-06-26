import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {catchError, map} from 'rxjs/operators';
import 'rxjs/add/observable/of';
import * as _ from 'underscore';

@Injectable()
export class AppService {
  CURRENT_USER = 'current_user';
  USER_LIST = 'user_list';
  TASK_URL = '/api/task';
  USER_URL = '/api/user';
  PROCESS_URL = '/api/process';
  BASE_URL = '';

  constructor(
    private http: HttpClient
  ) {
  }

  login(param: any): Observable<any> {
    return this.http.post(this.BASE_URL + this.USER_URL + '/login', {userId: param['username'], password: param['password']}).pipe(
      map(res => {
        if (res === true) {
          this.setUser(param);
          this.addUser(param);
        }
        return res;
      }),
      catchError(err => {
        return Observable.throw(err);
      })
    );
  }


  public getUser(): any {
    return JSON.parse(localStorage.getItem(this.CURRENT_USER));
  }

  setUser(user: any): void {
    localStorage.setItem(this.CURRENT_USER, JSON.stringify(user));
  }

  getUserList(): any[] {
    return JSON.parse(localStorage.getItem(this.USER_LIST));
  }

  setUserList(userList: any[]): void {
    localStorage.setItem(this.USER_LIST, JSON.stringify(userList));
  }

  addUser(user: any): void {
    const userList = this.getUserList() || [];
    const oldUser = _.find(userList, res => res.username === user['username']);
    if (!oldUser) {
      userList.push(user);
    }
    this.setUserList(userList);
  }

  logout(item: any) {
    let userList = this.getUserList();
    userList = _.filter(userList, res => res['username'] !== item['username']);
    this.setUserList(userList);
    localStorage.removeItem(this.CURRENT_USER);
  }

  getTaskList(): Observable<any> {
    const user = this.getUser();
    return this.http.get(this.BASE_URL + this.TASK_URL + '/' + user['username']);
  }

  getUserGroup(): Observable<any> {
    const user = this.getUser();
    if (!user) {
      return Observable.of(null);
    }
    return this.http.get(this.BASE_URL + this.USER_URL + '/' + user['username']);
  }


  getTaskForm(taskId: string): Observable<any> {
    return this.http.get(this.BASE_URL + this.TASK_URL + '/form/' + taskId);
  }

  completeTask(taskId: string, param: any) {
    return this.http.post(this.BASE_URL + this.TASK_URL + '/' + taskId, {param: param});
  }

  processList(): Observable<any> {
    return this.http.get(this.BASE_URL + this.PROCESS_URL);
  }

  createProcess(key: string) {
    const user = this.getUser();
    return this.http.post(this.BASE_URL + this.PROCESS_URL + '/' + key + '/' + user['username'], null);
  }

  claim(id: string) {
    const user = this.getUser();
    return this.http.post(this.BASE_URL + this.TASK_URL + '/claim/' + id + '/' + user['username'], null);
  }
}
