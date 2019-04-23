import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {NgZorroAntdModule} from 'ng-zorro-antd';
import {TrueFalsePipe} from '../pipe/true-false.pipe';

const THIRD_MODULES = [
  NgZorroAntdModule
];
const COMPONENTS = [];
const DIRECTIVES = [
  TrueFalsePipe
];

@NgModule({
  declarations: [
    ...COMPONENTS,
    ...DIRECTIVES,
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ...THIRD_MODULES
  ],
  exports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    ...THIRD_MODULES,
    ...COMPONENTS,
    ...DIRECTIVES,
  ]
})
export class SharedModule {
}
