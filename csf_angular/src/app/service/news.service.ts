import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  http = inject(HttpClient)
  defaultInterval = 5

  postNews(formData: FormData) : Observable<any>{
    return this.http.post(`http://localhost:8080/api/uploadnews`, formData)
  } 

  getTags(interval: number) : Observable<any>{
    return this.http.get(`htttp://localhost:8080/api/tags`, {params: {
      'interval' : interval
    }})
  }

  getNewsByHashtagInterval(hashtag:string, interval: number) : Observable<any>{
    return this.http.get(`htttp:localhost:8080/api/tags/${hashtag}`, {params: 
      {
        'interval': interval
      }})
  }

  getInterval() {
    return this.defaultInterval
  }

  updateInterval(interval: any) {
    this.defaultInterval = interval
  }
}
