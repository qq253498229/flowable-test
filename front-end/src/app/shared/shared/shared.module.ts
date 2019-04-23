import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {NgZorroAntdModule} from 'ng-zorro-antd';

const THIRD_MODULES = [
  NgZorroAntdModule
];
const COMPONENTS = [];
const DIRECTIVES = [];

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
