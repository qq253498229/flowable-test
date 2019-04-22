import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {NavigatorComponent} from './shared/navigator/navigator.component';

const routes: Routes = [
  {
    path: '', component: NavigatorComponent, children: [
      {path: '', redirectTo: '/user', pathMatch: 'full'},
      {path: 'user', loadChildren: './modules/user/user.module#UserModule'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
