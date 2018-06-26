import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {IndexComponent} from './index/index.component';
import {RouterModule, Routes} from '@angular/router';
import {ProcessComponent} from './process/process.component';
import {TaskComponent} from './task/task.component';
import {UserComponent} from './user/user.component';
import {LoginComponent} from './login/login.component';
import {FormsModule} from '@angular/forms';
import {AppService} from './app.service';
import {HttpClientModule} from '@angular/common/http';
import {TaskDetailComponent} from './task-detail/task-detail.component';

const routes: Routes = [
  {path: 'index', component: IndexComponent},
  {path: 'process', component: ProcessComponent},
  {path: 'task', component: TaskComponent},
  {path: 'task/:id', component: TaskDetailComponent},
  {path: 'user', component: UserComponent},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: 'index', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent,
    ProcessComponent,
    TaskComponent,
    UserComponent,
    LoginComponent,
    TaskDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [RouterModule, AppService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
