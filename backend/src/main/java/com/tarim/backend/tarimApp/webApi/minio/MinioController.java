package com.tarim.backend.tarimApp.webApi.minio;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tarim.backend.tarimApp.business.exception.FileResponseException;
import com.tarim.backend.tarimApp.business.minio.MinioService;
import com.tarim.backend.tarimApp.core.payload.FileResponse;
import com.tarim.backend.tarimApp.core.utilities.FileTypeUtils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/minio")
public class MinioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);

    private final MinioService minioService;

    
    private int portNumber = 8080;

    @PostMapping(path = "/upload/{bucketName}", consumes = {"multipart/form-data"})
    public FileResponse uploadFile(@RequestPart("file") MultipartFile multipartFile, @PathVariable("bucketName") String bucketName){

        LOGGER.info("MinioController | uploadFile is called");

        LOGGER.info("MinioController | uploadFile | bucketName : " + bucketName);

        String fileType = FileTypeUtils.getFileType(multipartFile);
        
        LOGGER.info("MinioController | uploadFile | fileType : " + fileType);

        if (fileType != null) {
            FileResponse deneme = minioService.putObject(multipartFile, bucketName, fileType);
            return deneme;
        }

        throw new FileResponseException("File cannot be upload.");

    }


    @PostMapping("/addBucket/{bucketName}")
    public String addBucket(@PathVariable String bucketName) {

        LOGGER.info("MinioController | addBucket is called");

        LOGGER.info("MinioController | addBucket | bucketName : " + bucketName);

        minioService.makeBucket(bucketName);
        return "Bucket name "+ bucketName +" created";
    }
    

    @GetMapping("/show/{bucketName}")
    public List<String> show(@PathVariable String bucketName) {

        LOGGER.info("MinioController | show is called");

        LOGGER.info("MinioController | show | bucketName : " + bucketName);

        return minioService.listObjectNames(bucketName);
    }

    @GetMapping("/showBucketName")
    public List<String> showBucketName() {

        LOGGER.info("MinioController | showBucketName is called");

        return minioService.listBucketName();
    }

    @DeleteMapping("/removeBucket/{bucketName}")
    public String delBucketName(@PathVariable String bucketName) {

        LOGGER.info("MinioController | delBucketName is called");

        LOGGER.info("MinioController | delBucketName | bucketName : " + bucketName);

        boolean state =  minioService.removeBucket(bucketName);

        LOGGER.info("MinioController | delBucketName | state : " + state);

        if(state){
            return " Delete Bucket Name successfully ";
        }else{
            return " Delete failed ";
        }
    }

    @DeleteMapping("/removeObject/{bucketName}/{objectName}")
    public String delObject(@PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName) {

        LOGGER.info("MinioController | delObject is called");

        LOGGER.info("MinioController | delObject | bucketName : " + bucketName);
        LOGGER.info("MinioController | delObject | objectName : " + objectName);

        boolean state =  minioService.removeObject(bucketName, objectName);

        LOGGER.info("MinioController | delBucketName | state : " + state);

        if(state){
            return " Delete Object successfully ";
        }else {
            return " Delete failed ";
        }
    }


    @DeleteMapping("/removeListObject/{bucketName}")
    public String delListObject(@PathVariable("bucketName") String bucketName, @RequestBody List<String> objectNameList) {

        LOGGER.info("MinioController | delListObject is called");

        LOGGER.info("MinioController | delListObject | bucketName : " + bucketName);
        LOGGER.info("MinioController | delListObject | objectNameList size : " + objectNameList.size());

        boolean state =  minioService.removeListObject(bucketName, objectNameList) ;

        LOGGER.info("MinioController | delBucketName | state : " + state);

        if(state){
            return " Delete List Object successfully ";
        }else {
            return " Delete failed ";
        }
    }

    @GetMapping("/showListObjectNameAndDownloadUrl/{bucketName}")
    public Map<String, String> showListObjectNameAndDownloadUrl(@PathVariable String bucketName) {

        LOGGER.info("MinioController | showListObjectNameAndDownloadUrl is called");

        LOGGER.info("MinioController | showListObjectNameAndDownloadUrl | bucketName : " + bucketName);

        Map<String, String> map = new HashMap<>();
        List<String> listObjectNames = minioService.listObjectNames(bucketName);

        LOGGER.info("MinioController | showListObjectNameAndDownloadUrl | listObjectNames size : " + listObjectNames.size());

        String url = "localhost:" + portNumber + "/minio/download/" + bucketName + "/";
        LOGGER.info("MinioController | showListObjectNameAndDownloadUrl | url : " + url);

        for (int i = 0; i <listObjectNames.size() ; i++) {
            map.put(listObjectNames.get(i),url+listObjectNames.get(i));
        }

        LOGGER.info("MinioController | showListObjectNameAndDownloadUrl | map : " + map.toString());

        return map;
    }

    @GetMapping("showDowloadUrl/{bucketName}/{objectName}")
    public String showDowloadUrl (@PathVariable("bucketName") String bucketName, @PathVariable("objectName") String objectName ){
        
        String url = "localhost:" + portNumber + "/minio/download/" + bucketName + "/" + objectName;

        return url;
    }


    @GetMapping("/download/{bucketName}/{objectName}")
    public void download(HttpServletResponse response, @PathVariable("bucketName") String bucketName,
                         @PathVariable("objectName") String objectName) {

        LOGGER.info("MinioController | download is called");
        LOGGER.info("MinioController | download | bucketName : " + bucketName);
        LOGGER.info("MinioController | download | objectName : " + objectName);

        InputStream in = null;
        try {
            in = minioService.downloadObject(bucketName, objectName);

            if(in == null){
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            String fileExtension = FileNameUtils.getExtension(objectName);
            MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;

            if(fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg")){
                mediaType = MediaType.IMAGE_JPEG;
            } 
            else if(fileExtension.equalsIgnoreCase("png")){
                mediaType = MediaType.IMAGE_PNG;
            }

            response.setContentType(mediaType.toString());
            response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncoder.encode(objectName, StandardCharsets.UTF_8) + "\"");

            /* 
            response.setHeader("Content-Disposition", "attachment;filename="
                    + URLEncoder.encode(objectName, "UTF-8"));
            response.setCharacterEncoding("UTF-8");
            */
            // Remove bytes from InputStream Copied to the OutputStream .
            IOUtils.copy(in, response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            LOGGER.info("MinioController | download | UnsupportedEncodingException : " + e.getMessage());
        } catch (IOException e) {
            LOGGER.info("MinioController | download | IOException : " + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    LOGGER.info("MinioController | download | IOException : " + e.getMessage());
                }
            }
        }

    }




    
  
}
