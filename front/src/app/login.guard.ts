import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {AppService} from './app.service';

@Injectable()
export class LoginGuard implements CanActivate {
  constructor(
    public service: AppService,
    public router: Router
  ) {
  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (!this.service.getUser()) {
      this.router.navigate(['login']);
    }
    return true;
  }
}
