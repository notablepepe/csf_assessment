import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { NewsService } from 'src/app/service/news.service';

@Component({
  selector: 'app-news-details',
  templateUrl: './news-details.component.html',
  styleUrls: ['./news-details.component.css']
})
export class NewsDetailsComponent implements OnInit{
  interval!: number
  activatedRoute = inject(ActivatedRoute)
  hashtag! : string
  service = inject(NewsService)
  hashtagSub! : Subscription
  hashtagList: any[] = []
  tagsArray: any[] = []

  ngOnInit(): void {
    this.hashtag = this.activatedRoute.snapshot.params['hashtag'];
    this.interval = this.service.getInterval();
    this.hashtagSub = this.service.getNewsByHashtagInterval(this.hashtag,this.interval).subscribe({
      next: (data) => {
              this.hashtagList = data
              
            },
      error: (error: HttpErrorResponse) => {alert(error)},
      complete: () => {this.hashtagSub.unsubscribe()}
    })
  }

}
