package com.tarim.backend.tarimApp.core.utilities;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.tarim.backend.tarimApp.core.configure.MinioConfig;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class MinioUtil {

    private static final Logger LOGGER =LoggerFactory.getLogger(MinioUtil.class);

    private final MinioClient minioClient;
    private final MinioConfig minioConfig;


    @SneakyThrows
    public void putObject(String bucketName, MultipartFile multipartFile, String filename, String fileType){
        LOGGER.info("MinioUtil | putObject is called");

        LOGGER.info("MinioUtil | putObject | filename : " + filename);
        LOGGER.info("MinioUtil | putObject | fileType : " + fileType);

        InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes());

        minioClient.putObject(PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(filename)
                                .stream(inputStream, -1, minioConfig.getFileSize())
                                .contentType(fileType)
                                .build());       
    }

    @SneakyThrows
    public boolean bucketExists (String bucketName){
        LOGGER.info("MinioUtil | bucketExists is called");

        boolean found = 
        minioClient.bucketExists(BucketExistsArgs.builder()
                                    .bucket(bucketName)
                                    .build()) ; 
        
        LOGGER.info("MinioUtil | bucketExists | found : " + found);

        if (found) {
            LOGGER.info("MinioUtil | bucketExists | message : " + bucketName + " exists");
        } else {
            LOGGER.info("MinioUtil | bucketExists | message : " + bucketName + " does not exist");
        }
        return found;
                                    
    }

    @SneakyThrows
    public boolean makeBucket (String bucketName){

        LOGGER.info("MinioUtil | makeBucket is called");

        boolean flag = bucketExists(bucketName);

        LOGGER.info("MinioUtil | makeBucket | flag : " + flag);

        if (!flag) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build());

            return true;
        } else {
            return false;
        }

    }

    @SneakyThrows
    public List<Bucket> listBuckets() {
        LOGGER.info("MinioUtil | listBuckets is called");

        return minioClient.listBuckets();
    }


        // List all bucket names
    @SneakyThrows
    public List<String> listBucketNames() {

        LOGGER.info("MinioUtil | listBucketNames is called");

        List<Bucket> bucketList = listBuckets();

        LOGGER.info("MinioUtil | listBucketNames | bucketList size : " + bucketList.size());

        List<String> bucketListName = new ArrayList<>();
        for (Bucket bucket : bucketList) {
            bucketListName.add(bucket.name());
        }

        LOGGER.info("MinioUtil | listBucketNames | bucketListName size : " + bucketListName.size());

        return bucketListName;
    }  
    
    // List all objects from the specified bucket
    @SneakyThrows
    public Iterable<Result<Item>> listObjects(String bucketName) {

        LOGGER.info("MinioUtil | listObjects is called");

        boolean flag = bucketExists(bucketName);

        LOGGER.info("MinioUtil | listObjects | flag : " + flag);

        if (flag) {
            return minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(bucketName).build());
        }
        return null;
    }



        // Delete Bucket by its name from the specified bucket
    @SneakyThrows
    public boolean removeBucket(String bucketName) {

        LOGGER.info("MinioUtil | removeBucket is called");

        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | removeBucket | flag : " + flag);

        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);

            for (Result<Item> result : myObjects) {
                Item item = result.get();
                //  Delete failed when There are object files in bucket

                LOGGER.info("MinioUtil | removeBucket | item size : " + item.size());

                if (item.size() > 0) {
                    return false;
                }
            }

            //  Delete bucket when bucket is empty
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            flag = bucketExists(bucketName);

            LOGGER.info("MinioUtil | removeBucket | flag : " + flag);
            if (!flag) {
                return true;
            }
        }
        return false;
    }


        // List all object names from the specified bucket
    @SneakyThrows
    public List<String> listObjectNames(String bucketName) {

        LOGGER.info("MinioUtil | listObjectNames is called");

        List<String> listObjectNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);

        LOGGER.info("MinioUtil | listObjectNames | flag : " + flag);

        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                listObjectNames.add(item.objectName());
            }
        } else {
            listObjectNames.add(" Bucket does not exist ");
        }

        LOGGER.info("MinioUtil | listObjectNames | listObjectNames size : " + listObjectNames.size());

        return listObjectNames;
    }

    @SneakyThrows
    public boolean removeObject(String bucketName, String objectName) {

        LOGGER.info("MinioUtil | removeObject is called");

        boolean flag = bucketExists(bucketName);

        LOGGER.info("MinioUtil | removeObject | flag : " + flag);

        if (flag) {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return true;
        }
        return false;
    }


        // Get file path from the specified bucket
    @SneakyThrows
    public String getObjectUrl(String bucketName, String objectName) {

        LOGGER.info("MinioUtil | getObjectUrl is called");
        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | getObjectUrl | flag : " + flag);

        String url = "";

        if (flag) {
            url = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(bucketName)
                            .object(objectName)
                            .expiry(2, TimeUnit.MINUTES)
                            .build());
            LOGGER.info("MinioUtil | getObjectUrl | url : " + url);
        }
        return url;
    }


        // Get metadata of the object from the specified bucket
    @SneakyThrows
    public StatObjectResponse statObject(String bucketName, String objectName) {
        LOGGER.info("MinioUtil | statObject is called");

        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | statObject | flag : " + flag);
        if (flag) {
            StatObjectResponse stat =
                    minioClient.statObject(
                            StatObjectArgs.builder().bucket(bucketName).object(objectName).build());

            LOGGER.info("MinioUtil | statObject | stat : " + stat.toString());

            return stat;
        }
        return null;
    }


    @SneakyThrows
    public InputStream getObject (String bucketName, String objectName){
        LOGGER.info("MinioUtil | getObject is called");

        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | getObject | flag : " + flag);

        if(flag){
            StatObjectResponse statObjectResponse = statObject(bucketName, objectName);
            if(statObjectResponse != null && statObjectResponse.size()>0){
                InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
                                                                .bucket(bucketName)
                                                                .object(objectName)
                                                                .build());
                LOGGER.info("MinioUtil | getObject | stream : " + inputStream.toString());
                return inputStream;                                                
            }
                        
        }

        return null;
    }


        // Get a file object as a stream from the specified bucket （ Breakpoint download )
    @SneakyThrows
    public InputStream getObject(String bucketName, String objectName, long offset, Long length) {

        LOGGER.info("MinioUtil | getObject is called");

        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | getObject | flag : " + flag);

        if (flag) {
            StatObjectResponse statObject = statObject(bucketName, objectName);
            if (statObject != null && statObject.size() > 0) {
                InputStream stream =
                        minioClient.getObject(
                                GetObjectArgs.builder()
                                        .bucket(bucketName)
                                        .object(objectName)
                                        .offset(offset)
                                        .length(length)
                                        .build());

                LOGGER.info("MinioUtil | getObject | stream : " + stream.toString());
                return stream;
            }
        }
        return null;
    }

        // Delete multiple file objects from the specified bucket
    @SneakyThrows
    public boolean removeObject(String bucketName, List<String> objectNames) {
        LOGGER.info("MinioUtil | removeObject is called");

        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | removeObject | flag : " + flag);

        if (flag) {
            List<DeleteObject> objects = new LinkedList<>();
            for (int i = 0; i < objectNames.size(); i++) {
                objects.add(new DeleteObject(objectNames.get(i)));
            }
            Iterable<Result<DeleteError>> results =
                    minioClient.removeObjects(
                            RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());

            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();

                LOGGER.info("MinioUtil | removeObject | error : " + error.objectName() + " " + error.message());

                return false;
            }
        }
        return true;
    }

    @SneakyThrows
    public boolean putObject(String bucketName, String objectName, InputStream inputStream, String contentType) {

        LOGGER.info("MinioUtil | putObject is called");

        boolean flag = bucketExists(bucketName);
        LOGGER.info("MinioUtil | putObject | flag : " + flag);

        if (flag) {
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                            inputStream, -1, minioConfig.getFileSize())
                            .contentType(contentType)
                            .build());
            StatObjectResponse statObject = statObject(bucketName, objectName);

            LOGGER.info("MinioUtil | putObject | statObject != null : " + (statObject != null));
            LOGGER.info("MinioUtil | putObject | statObject.size() : " + statObject.size());

            if (statObject != null && statObject.size() > 0) {
                return true;
            }
        }
        return false;
    }




    
}
