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
 *
 * @author Pang S.Z.
 * @create 2020-10-07 23:18:09
 */
@RestController
@RequestMapping("/minio")
public class MinioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);
    @Value("${minio.endpoint}")
    private String endPoint;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws Exception
     * @Description
     */
    @PostMapping("/upload")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) throws Exception {
        MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
        boolean isExist = minioClient.bucketExists(bucketName);
        if (isExist) {
            LOGGER.info("存储桶已经存在");
        } else {
            //创建存储桶并设置readonly
            minioClient.makeBucket(bucketName);
            minioClient.setBucketPolicy(bucketName, "*.*", PolicyType.READ_ONLY);
        }
        String filename = file.getOriginalFilename();
        //设置存储对象名称
        String objectName = DateUtil.format(new Date(), "yyyyMMdd") + "/" + filename;
        //上传一个文件到存储桶中
        minioClient.putObject(bucketName, objectName, file.getInputStream(), file.getContentType());
        LOGGER.info("文件上传成功");
        MinioUploadDto minioUploadDto = new MinioUploadDto();
        minioUploadDto.setName(filename);
        minioUploadDto.setUrl(endPoint + "/" + bucketName + "/" + objectName);
        return ResponseResult.ok().data(BeanUtil.beanToMap(minioUploadDto));
    }

    /**
     * @param objectName
     * @return
     * @throws Exception
     * @Description 文件删除，
     */
    @PostMapping("/delete")
    public ResponseResult delete(@RequestParam("objectName") String objectName) throws Exception {
        MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
        minioClient.removeObject(bucketName, objectName);
        return ResponseResult.ok();
    }
}
