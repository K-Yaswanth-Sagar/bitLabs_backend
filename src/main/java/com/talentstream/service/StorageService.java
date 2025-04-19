package com.talentstream.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class StorageService {

	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Autowired
	private AmazonS3 s3Client;
	
	private File convertMultipartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try(FileOutputStream fos = new FileOutputStream(convertedFile)){
			fos.write(file.getBytes());
		}catch (IOException e) {
			System.out.println(e);
		}
		return convertedFile;
	}
	
	
	public String uploadImage(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		File fileObj = convertMultipartFileToFile(file);
		s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
		fileObj.delete();
		return fileName;
	}
	
	
	
	public byte[] getImage(String fileName) {
		S3Object s3Object = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		
		try {
			byte[] content = IOUtils.toByteArray(inputStream);
			return content;
		}catch(IOException e) {
			System.out.println(e);
		}
		return null;
	}
	
	
}
