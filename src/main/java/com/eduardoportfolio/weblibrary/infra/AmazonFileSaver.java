package com.eduardoportfolio.weblibrary.infra;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

//@Component
//public class AmazonFileSaver {
//	
//	@Autowired
//	private AmazonS3Client s3;
//	
//	public String write(String baseFolder, MultipartFile multipartFile){
//		try{
//			//The AmazonS3Client type object encapsulate all the logic and the communication protocol
//			//We have only to pass the parameters, first the remote folder where we will save the
//			//file (bucket), second is the file name, third is some InputStream implementation
//			//thats really represent the file and fourth some extra informations like, expire file
//			//date and any other specific information of the application.
//			s3.putObject("weblibrary", multipartFile.getOriginalFilename(),
//										multipartFile.getInputStream(),
//										new ObjectMetadata());
//			return "http://localhost:9444/s3/weblibrary/" + multipartFile.getOriginalFilename() + 
//																				"?noAuth=true";
//		} catch (AmazonClientException|IOException e){
//			throw new RuntimeException (e);
//		}
//	}
//}
