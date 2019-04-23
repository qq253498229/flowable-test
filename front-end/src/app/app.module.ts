import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavigatorComponent} from './shared/navigator/navigator.component';
import {NgZorroAntdModule, NZ_I18N, zh_CN} from 'ng-zorro-antd';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {registerLocaleData} from '@angular/common';
import zh from '@angular/common/locales/zh';

registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent,
    NavigatorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    // 导入 ng-zorro-antd 模块
    NgZorroAntdModule,
  ],
  // 配置 ng-zorro-antd 国际化（文案 及 日期）
  providers: [{provide: NZ_I18N, useValue: zh_CN}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
