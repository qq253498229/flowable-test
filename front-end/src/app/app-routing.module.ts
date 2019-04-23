import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {NavigatorComponent} from './shared/navigator/navigator.component';

const routes: Routes = [
  {
    path: '', component: NavigatorComponent, children: [
      {path: '', redirectTo: '/user', pathMatch: 'full'},
      {path: 'user', loadChildren: './modules/user/user.module#UserModule'},
      {path: 'group', loadChildren: './modules/group/group.module#GroupModule'},
      {path: 'task', loadChildren: './modules/task/task.module#TaskModule'},
      {path: 'process', loadChildren: './modules/process/process.module#ProcessModule'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
