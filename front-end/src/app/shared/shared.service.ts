import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  constructor() {
  }

  get userId() {
    return localStorage.getItem(this.key);
  }

  set userId(userId: string) {
    localStorage.setItem(this.key, userId);
  }

  key = 'userId';

  static log(obj: any) {
    if (!environment.production) {
      console.log(obj);
    }
  }

  authCheck() {
    return !!this.userId;
  }
}
