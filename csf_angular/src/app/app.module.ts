import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ShareNewsComponent } from './components/share-news/share-news.component';
import { NewsDetailsComponent } from './components/news-details/news-details.component';
import { FindNewsComponent } from './components/find-news/find-news.component';

@NgModule({
  declarations: [
    AppComponent,
    ShareNewsComponent,
    NewsDetailsComponent,
    FindNewsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
