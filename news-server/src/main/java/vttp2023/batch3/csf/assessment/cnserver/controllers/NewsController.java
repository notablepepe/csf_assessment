package vttp2023.batch3.csf.assessment.cnserver.controllers;

import java.io.IOException;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;
import vttp2023.batch3.csf.assessment.cnserver.services.NewsService;

@CrossOrigin()
@RequestMapping(path = "/api")
@RestController
public class NewsController {

	@Autowired
	private NewsService service;
	// TODO: Task 1
	   @PostMapping(path = "/uploadnews", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	   public ResponseEntity<String> postNews(@RequestPart MultipartFile file, 
	   			@RequestPart String title, @RequestPart String description
				,@RequestPart String tags) throws IOException{
				
			return ResponseEntity.ok(this.service.postNews(file, title, description, tags));

	   }

	// TODO: Task 2
	@GetMapping(path = "/tags")
	public ResponseEntity<List<TagCount>> getTags(@RequestParam Integer interval) {


		return ResponseEntity.ok(this.service.getTags(interval));
	}


	// TODO: Task 3
	@GetMapping(path="/tags/${hashtag}")
	public ResponseEntity<List<Document>> getNewsByHashtags(@PathVariable String hashtag, @RequestParam Integer interval) {

		
		return ResponseEntity.ok(service.getNewsByHashTags(hashtag, interval));
	}
}
