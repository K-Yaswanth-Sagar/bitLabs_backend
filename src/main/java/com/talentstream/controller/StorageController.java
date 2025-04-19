package com.talentstream.controller;

import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.talentstream.service.StorageService;

@RestController
@RequestMapping("/file")
public class StorageController {

	@Autowired
	private StorageService service;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam(value = "file") MultipartFile file){
		
		return new ResponseEntity<>(service.uploadImage(file), HttpStatus.OK);
		
	}
	
	@GetMapping("/image/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable String fileName) {
	    byte[] imageData = service.getImage(fileName);

	    String contentType = URLConnection.guessContentTypeFromName(fileName);
	    ByteArrayResource resource = new ByteArrayResource(imageData);
	    return ResponseEntity
	    		.ok()
	    		.contentLength(imageData.length)
	    		 .contentType(MediaType.parseMediaType(contentType))
	    		.body(resource);
	}

	
}
