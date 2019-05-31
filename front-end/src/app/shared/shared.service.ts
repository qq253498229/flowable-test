import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SharedService {
  key = 'userId';

  constructor() {
  }

  get userId() {
    return localStorage.getItem(this.key);
  }

  set userId(userId: string) {
    localStorage.setItem(this.key, userId);
  }

  authCheck() {
    return !!this.userId;
  }
}
