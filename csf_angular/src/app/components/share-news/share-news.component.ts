import { HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild, inject } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { NewsService } from 'src/app/service/news.service';

@Component({
  selector: 'app-share-news',
  templateUrl: './share-news.component.html',
  styleUrls: ['./share-news.component.css']
})
export class ShareNewsComponent implements OnInit{
  @ViewChild('file') imageFile!: ElementRef

  form! : FormGroup
  fb = inject(FormBuilder)
  imageSub! : Subscription
  textSub! : Subscription
  tags: string[] = []
  service = inject(NewsService)
  payload! : any
  router = inject(Router)


  ngOnInit(): void {
    this.form = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(5)]],
      photo: ['', Validators.required],
      description: ['', [Validators.required, Validators.minLength(5)]],
      tags: [this.tags]
    })
  }

  postNews(){
    const formData = new FormData();
    formData.set('file', this.imageFile.nativeElement.files[0]);
    formData.set('title', this.form.value['title']);
    formData.set('description', this.form.value['description']);
    formData.set('tags', this.tags.toString())
    console.log(this.tags.toString());
    
    this.imageSub = this.service.postNews(formData).subscribe({
      next: (data) => {
        this.payload = data
        if(this.payload) {
          alert(this.payload.newsId)
          this.router.navigate(['/'])
        } else {
          alert('post news failed')
        }
        
      },
      error: (error: HttpErrorResponse) => {alert(error)},
      complete: () => {this.imageSub.unsubscribe()}
    })
  }

  addTag() {
      const tags = this.form.value['tags']
      const tagsArr = tags.split(" ")
      
      
      for(var tag of tagsArr) {
        this.tags.push(tag)
      }
      
      
      console.log(this.tags);
      
      
  }

  removeTag(tag: string) {
    let index = this.tags.indexOf(tag)
    console.log(index);
    
    this.tags.splice(index, 1)
    console.log(this.tags);
    
    
  }
}
