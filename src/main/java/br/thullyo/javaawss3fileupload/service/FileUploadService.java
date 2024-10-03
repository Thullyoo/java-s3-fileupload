package br.thullyo.javaawss3fileupload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;
import java.util.UUID;

@Service
public class FileUploadService {

    @Autowired
    private S3Client s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public void uploadFile(MultipartFile file){
        String filename = UUID.randomUUID() + file.getOriginalFilename();

        try {
            PutObjectRequest putObject = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();
            s3Client.putObject(putObject, RequestBody.fromByteBuffer(ByteBuffer.wrap(file.getBytes())));
            GetUrlRequest request = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();
            System.out.println(s3Client.utilities().getUrl(request).toString());

        }catch (Exception e){
            System.out.println("Erro durante o upload do arquivo");
            System.out.println(e.getMessage());
        }


    }

}
