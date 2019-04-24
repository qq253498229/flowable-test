import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor() {
  }

  get userId() {
    return localStorage.getItem('userId');
  }

  set userId(userId: string) {
    localStorage.setItem('userId', userId);
  }

  log(obj: any) {
    if (!environment.production) {
      console.log(obj);
    }
  }
}
