package br.thullyo.javaawss3fileupload.config;

import com.amazonaws.services.macie2.model.S3Bucket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

@Configuration
public class S3Config {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.accessKey}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    public S3Client s3Client(){
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKeyId, secretKey);

        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .build();
    }

}
