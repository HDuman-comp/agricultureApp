package com.tarim.backend.tarimApp.business.minio;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tarim.backend.tarimApp.core.configure.MinioConfig;
import com.tarim.backend.tarimApp.core.payload.FileResponse;
import com.tarim.backend.tarimApp.core.utilities.MinioUtil;

import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MinioService implements IMinioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioService.class);

    private final MinioUtil minioUtil;
    private final MinioConfig minioConfig;

    int portNumber = 8080;



    @Override
    public boolean bucketExists(String bucketName) {

        LOGGER.info("MinioServiceImpl | bucketExists is called");

        return minioUtil.bucketExists(bucketName);

    }


    @Override
    public void makeBucket(String bucketName) {
        LOGGER.info("MinioServiceImpl | makeBucket is called");

        LOGGER.info("MinioServiceImpl | makeBucket | bucketName : " + bucketName);

        minioUtil.makeBucket(bucketName);

    }


    @Override
    public List<String> listBucketName() {

        LOGGER.info("MinioServiceImpl | listBucketName is called");
        return minioUtil.listBucketNames();

    }
    @Override
    public List<Bucket> listBuckets() {
        LOGGER.info("MinioServiceImpl | listBuckets is called");
        return minioUtil.listBuckets();

    }
    @Override
    public boolean removeBucket(String bucketName) {

        LOGGER.info("MinioServiceImpl | removeBucket is called");

        LOGGER.info("MinioServiceImpl | removeBucket | bucketName : " + bucketName);

        return minioUtil.removeBucket(bucketName);

    }


    @Override
    public List<String> listObjectNames(String bucketName) {

        LOGGER.info("MinioServiceImpl | listObjectNames is called");

        LOGGER.info("MinioServiceImpl | listObjectNames | bucketName : " + bucketName);

        return minioUtil.listObjectNames(bucketName);

    }


    @Override
    public FileResponse putObject(MultipartFile multipartFile, String bucketName, String fileType) {

        LOGGER.info("MinioServiceImpl | putObject is called");

        try{
            bucketName = StringUtils.isNotBlank(bucketName) ? bucketName:minioConfig.getBucketName();

            LOGGER.info("MinioServiceImpl | putObject | bucketName : " + bucketName);

            if(!this.bucketExists(bucketName)){
                this.makeBucket(bucketName);
                LOGGER.info("MinioServiceImpl | putObject | bucketName : " + bucketName + " created");
            }

            String fileName = multipartFile.getOriginalFilename();
            LOGGER.info("MinioServiceImpl | getFileType | fileName : " + fileName);

            String objectName = UUID.randomUUID().toString().replaceAll("-", "")
            + fileName.substring(fileName.lastIndexOf("."));
            LOGGER.info("MinioServiceImpl | getFileType | objectName : " + objectName);

            Long fileSize = multipartFile.getSize();
            LOGGER.info("MinioServiceImpl | getFileType | fileSize : " + fileSize);

            LocalDateTime createdTime = LocalDateTime.now();
            LOGGER.info("MinioServiceImpl | getFileType | createdTime : " + createdTime);

            minioUtil.putObject(bucketName, multipartFile, objectName,fileType);

            LOGGER.info("MinioServiceImpl | getFileType | url : " + minioConfig.getEndpoint()+"/"+bucketName+"/"+objectName);

            return FileResponse.builder()
                    .filename(objectName)
                    .fileSize(fileSize)
                    .url("localhost:" + portNumber + "/minio/download/" + bucketName + "/" + objectName)
                    .contentType(fileType)
                    .createdTime(createdTime)
                    .build();


        }catch(Exception e){

            LOGGER.info("MinioServiceImpl | getFileType | Exception : " + e.getMessage());
            return null;

        }

    }



    @Override
    public InputStream downloadObject(String bucketName, String objectName) {
        LOGGER.info("MinioServiceImpl | downloadObject is called");

        LOGGER.info("MinioServiceImpl | downloadObject | bucketName : " + bucketName);
        LOGGER.info("MinioServiceImpl | downloadObject | objectName : " + objectName);

        return minioUtil.getObject(bucketName,objectName);
    }




    @Override
    public boolean removeObject(String bucketName, String objectName) {
        LOGGER.info("MinioServiceImpl | removeObject is called");

        LOGGER.info("MinioServiceImpl | removeObject | bucketName : " + bucketName);
        LOGGER.info("MinioServiceImpl | removeObject | objectName : " + objectName);

        return minioUtil.removeObject(bucketName, objectName);

    }


    @Override
    public boolean removeListObject(String bucketName, List<String> objectNameList) {

        LOGGER.info("MinioServiceImpl | removeListObject is called");

        LOGGER.info("MinioServiceImpl | removeObject | bucketName : " + bucketName);
        LOGGER.info("MinioServiceImpl | removeObject | objectNameList size : " + objectNameList.size());

        return minioUtil.removeObject(bucketName,objectNameList);

    }


    @Override
    public String getObjectUrl(String bucketName, String objectName) {
        LOGGER.info("MinioServiceImpl | getObjectUrl is called");

        LOGGER.info("MinioServiceImpl | getObjectUrl | bucketName : " + bucketName);
        LOGGER.info("MinioServiceImpl | getObjectUrl | objectName : " + objectName);

        return minioUtil.getObjectUrl(bucketName, objectName);

    }

    


    
}
