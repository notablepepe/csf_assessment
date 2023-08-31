package vttp2023.batch3.csf.assessment.cnserver.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import vttp2023.batch3.csf.assessment.cnserver.models.News;
import vttp2023.batch3.csf.assessment.cnserver.models.TagCount;
import vttp2023.batch3.csf.assessment.cnserver.repositories.ImageRepository;
import vttp2023.batch3.csf.assessment.cnserver.repositories.NewsRepository;

@Service
public class NewsService {
	
	@Autowired
	private NewsRepository newsRepo;

	@Autowired
	private ImageRepository s3Repo;
	// TODO: Task 1
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns the news id
	public String postNews(MultipartFile file, String title, String description, String tags) throws IOException {
		

		//news model require tags to be in List
		
		List<String> tagsList = new ArrayList<>();
		String[] tagsArr = tags.split(",");
		for(String tag : tagsArr) {
			tagsList.add(tag);
		}
		
		String imgUrl = "https://inibencana.sgp1.digitaloceanspaces.com/" + s3Repo.postImage(file);
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		Document newsDoc = new Document();
		newsDoc.append("postDate", System.currentTimeMillis());
		newsDoc.append("title", title);
		newsDoc.append("description", description);
		newsDoc.append("image", imgUrl);
		newsDoc.append("tags", tagsList);
		

		
		return newsRepo.postNews(newsDoc);
	}
	 
	// TODO: Task 2
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of tags and their associated count
	public List<TagCount> getTags(Integer interval) {
		JsonArray tagsArr = newsRepo.getTags(interval);
		List<TagCount> tagList = new ArrayList<>();
		for(JsonValue tag: tagsArr) {
			JsonObject tagObj = tag.asJsonObject();
			String tagName = tagObj.getString("tag");
			Integer count = tagObj.getInt("count");
			TagCount result = new TagCount(tagName, count);
			tagList.add(result);
		}
		return tagList;
	}

	// TODO: Task 3
	// Do not change the method name and the return type
	// You may add any number of parameters
	// Returns a list of news
	public List<News> getNewsByTag(String hashtag, Integer interval) {
		List<Document> newsDoc = newsRepo.getNewsByTag(hashtag,interval);
		List<News> newsList = new ArrayList<>();
		for(Document doc : newsDoc) {
			String id = doc.getObjectId("_id").toString();
			Long postdate = doc.getLong("postdate");
			String title = doc.getString("title");
			String description = doc.getString("description");
			String imgUrl = doc.getString("image");
			List<String> tags = doc.getList("tags", String.class);
			News newsObj = new News(id, postdate, title, description, imgUrl, tags);
			newsList.add(newsObj);
		}
		return newsList;
	}
	// rivate String id;
	// private long postDate;
	// private String title;
	// private String description;
	// private String image;
	// private List<String> tags;
	
}
