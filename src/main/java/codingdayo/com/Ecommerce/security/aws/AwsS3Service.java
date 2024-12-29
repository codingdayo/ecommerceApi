package codingdayo.com.Ecommerce.security.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.persistence.Basic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class AwsS3Service {


    private final String bucketName = "dayo-ecommerce";

    //@Value("${aws.s3.access}")
    //private String awsS3AccessKey;
    //
    //@Value("${aws.s3.secrete}")
    //private String awsS3SecreteKey;

    @Value("${aws.s3.access}")
    private String awsS3AccessKey;
    @Value("${aws.s3.secrete}")
    private String awsS3SecreteKey;

//    method to upload images
    public String saveImageToS3(MultipartFile photo){
        try{
            String s3FileName = photo.getOriginalFilename();
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3AccessKey, awsS3AccessKey);

            //Create s3 client with config credentials and region
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .withRegion(Regions.US_EAST_1)
                    .build();

        //    get input Stream from pHOTO
            InputStream inputStream = photo.getInputStream();

        //    set metadata for the object
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpeg");

        //create a put request to upload the image to s3

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3FileName, inputStream, metadata);
            s3Client.putObject(putObjectRequest);

            return "https://" + bucketName + ".s3.us-east-1.amazonaws.com/" + s3FileName;


        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Unable to upload image to S3 bucket: " + e.getMessage());
        }
    }
}
