import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FindNewsComponent } from './components/find-news/find-news.component';
import { ShareNewsComponent } from './components/share-news/share-news.component';
import { NewsDetailsComponent } from './components/news-details/news-details.component';

const routes: Routes = [
  {path: '' , component: FindNewsComponent},
  {path: 'news', component: ShareNewsComponent},
  {path: 'news/:hashtag' , component: NewsDetailsComponent},
  {path: "**", redirectTo:'/', pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
