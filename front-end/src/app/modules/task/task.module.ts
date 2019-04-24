import {NgModule} from '@angular/core';
import {ListComponent} from './list/list.component';
import {RouterModule, Routes} from '@angular/router';
import {SharedModule} from '../../shared/shared/shared.module';
import {DetailComponent} from './detail/detail.component';

const routes: Routes = [
  {path: '', component: ListComponent},
  {path: ':id', component: DetailComponent}
];

@NgModule({
  declarations: [ListComponent, DetailComponent],
  imports: [
    SharedModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class TaskModule {
}
