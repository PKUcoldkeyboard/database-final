package com.cuterwrite.dbfinal.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cuterwrite.dbfinal.common.ResponseResult;
import com.cuterwrite.dbfinal.dto.MinioUploadDto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;

/**  
 * Minio对象存储管理
 * @author Pang S.Z.
 * @create 2020-10-07 23:18:09 
 */
@RestController
@RequestMapping("/minio")
public class MinioController {
	private static final Logger LOGGER=LoggerFactory.getLogger(MinioController.class);
	@Value("${minio.endpoint}")
	private String ENDPOINT;
	@Value("${minio.bucketName}")
	private String BUCKET_NAME;
	@Value("${minio.accessKey}")
	private String ACCESS_KEY;
	@Value("${minio.secretKey}")
	private String SECRET_KEY;
	
	//文件上传
	@PostMapping("/upload")
	public ResponseResult upload(@RequestParam("file")MultipartFile file) throws Exception {
		MinioClient minioClient=new MinioClient(ENDPOINT,ACCESS_KEY,SECRET_KEY);
		boolean isExist=minioClient.bucketExists(BUCKET_NAME);
		if(isExist) {
			LOGGER.info("存储桶已经存在");
		}else {
			//创建存储桶并设置readonly
			minioClient.makeBucket(BUCKET_NAME);
			minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
		}
		String filename=file.getOriginalFilename();
		//设置存储对象名称
		String objectName=DateUtil.format(new Date(), "yyyyMMdd")+"/"+filename;
		//上传一个文件到存储桶中
		minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(),file.getContentType());
		LOGGER.info("文件上传成功");
		MinioUploadDto minioUploadDto=new MinioUploadDto();
		minioUploadDto.setName(filename);
		minioUploadDto.setUrl(ENDPOINT+"/"+BUCKET_NAME+"/"+objectName);
		return ResponseResult.ok().data(BeanUtil.beanToMap(minioUploadDto));
	}
	
	/*
	 * 文件删除
	 * objectName格式e.g.(20201007/4vm95m.jpg)
	 */
	@PostMapping("/delete")
	public ResponseResult delete(@RequestParam("objectName")String objectName) throws Exception{
		MinioClient minioClient=new MinioClient(ENDPOINT,ACCESS_KEY,SECRET_KEY);
		minioClient.removeObject(BUCKET_NAME, objectName);
		return ResponseResult.ok();
	}
}
