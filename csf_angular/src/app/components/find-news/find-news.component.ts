import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import { Subscription } from 'rxjs';
import { NewsService } from 'src/app/service/news.service';

@Component({
  selector: 'app-find-news',
  templateUrl: './find-news.component.html',
  styleUrls: ['./find-news.component.css']
})
export class FindNewsComponent implements OnInit{
  intervalArray: number[] = [5, 15, 30, 45, 60]
  defaultInterval: number = 5
  http = inject(HttpClient)
  service = inject(NewsService)
  findNewsSub!: Subscription
  tagList : any[] = []

  ngOnInit(): void {
    this.findNewsSub = this.service.getTags(this.defaultInterval).subscribe({
      next: (data) => {
        this.tagList = data;
      },
      error: (error: HttpErrorResponse) => 
        {alert('No news hashtag within the duration within the last ' + this.defaultInterval + 'minutes.')},
      complete: () => {this.findNewsSub.unsubscribe()}
    })
  }

  updateInterval(updatedInterval : string ) {
    const newInterval = Number(updatedInterval)
    this.service.updateInterval(newInterval)
    this.defaultInterval = this.service.getInterval()
  }
}
