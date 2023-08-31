package vttp2023.batch3.csf.assessment.cnserver.repositories;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Repository
public class ImageRepository {

	@Autowired
	private AmazonS3 s3;

	
	
	// TODO: Task 1
	public String postImage(MultipartFile file) throws IOException {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(file.getContentType());
		metadata.setContentLength(file.getSize());
		String imgId = UUID.randomUUID().toString();
		PutObjectRequest putReq = new PutObjectRequest("inibencana", imgId, file.getInputStream(), metadata);
		putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);
		s3.putObject(putReq);
		return imgId;
	}

}
