import {NgModule} from '@angular/core';
import {ListComponent} from './list/list.component';
import {RouterModule, Routes} from '@angular/router';
import {SharedModule} from '../../shared/shared/shared.module';
import {StartComponent} from './start/start.component';

const routes: Routes = [
  {path: '', component: ListComponent},
  {path: ':id', component: StartComponent},
];

@NgModule({
  declarations: [ListComponent, StartComponent],
  imports: [
    SharedModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ProcessModule {
}
