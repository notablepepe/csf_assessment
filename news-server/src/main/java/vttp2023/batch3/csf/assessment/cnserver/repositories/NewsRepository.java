package vttp2023.batch3.csf.assessment.cnserver.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import vttp2023.batch3.csf.assessment.cnserver.models.News;

@Repository
public class NewsRepository {

	@Autowired
	private MongoTemplate template;

	// TODO: Task 1 
	// Write the native Mongo query in the comment above the method
	public String postNews(Document doc) {
		
		Document newsObj = template.insert(doc,"news");
		JsonObject payload = Json.createObjectBuilder()
							.add("newsId", newsObj.getObjectId("_id").toString())
							.build();
		return payload.toString();
	}
	

	// TODO: Task 2 
	// Write the native Mongo query in the comment above the method
	public JsonArray getTags(Integer interval){
		Integer intervalInMs = interval * 60 * 1000;
		tagsResult = template.query(null);
		JsonArray tagArray = Json.createArrayBuilder().build();
		return tagArray;
	}


	// TODO: Task 3
	//news.find({"tags": hashtag});
	// Write the native Mongo query in the comment above the method
	public List<Document> getNewsByHashTags(String hashtag, Integer interval) {
		List<Document> newsWithHashtag = template.find({"tags" : hashtag});
		return newsWithHashTag;
	}


}
