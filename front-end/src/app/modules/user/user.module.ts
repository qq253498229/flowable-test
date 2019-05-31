import {NgModule} from '@angular/core';
import {ListComponent} from './list/list.component';
import {SharedModule} from '../../shared/shared/shared.module';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  {path: '', component: ListComponent}
];

@NgModule({
  declarations: [ListComponent],
  imports: [
    SharedModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class UserModule {
}
