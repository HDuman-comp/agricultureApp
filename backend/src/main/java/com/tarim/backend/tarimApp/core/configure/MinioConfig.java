package com.tarim.backend.tarimApp.core.configure;
import io.minio.MinioClient;
import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    /** *  It's a URL, domain name ,IPv4 perhaps IPv6 Address ") */
    @Value("${minio.endPoint}")
    private String endpoint;

    /** * //"TCP/IP Port number " */
    @Value("${minio.port}")
    private Integer port;

    /** * //"accessKey Similar to user ID, Used to uniquely identify your account " */
    @Value("${minio.access.name}")
    private String accessKey;

    /** * //"secretKey It's the password for your account " */
    @Value("${minio.access.secret}")
    private String secretKey;

    /** * //" If it is true, It uses https instead of http, The default value is true" */
    @Value("${minio.secure}")
    private boolean secure;

    /** * //" Default bucket " */
    @Value("${minio.bucket.name}")
    private String bucketName;

    /** *  The maximum size of the picture  */
    @Value("${minio.imageSize}")
    private long imageSize;

    /** *  Maximum size of other files  */
    @Value("${minio.fileSize}")
    private long fileSize;

    @Bean
    public MinioClient minioClient() {
        MinioClient minioClient =
                MinioClient.builder()
                        .credentials(accessKey, secretKey)
                        .endpoint(endpoint,port,secure)
                        .build();
        return minioClient;
    }
    
}
