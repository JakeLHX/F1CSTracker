package com.jake.f1SeasonTracker;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
public class F1SeasonTrackerS3IntegrationTests {

    final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion("eu-west-2").build();

    @Test
    void helloWorld() {
        String helloWorld = "Hello World";
        assert(helloWorld == "Hello World");
    }

    @Test
    void listBuckets() {
        List<Bucket> buckets = s3.listBuckets();
        System.out.println("Your {S3} buckets are:");
        for (Bucket b : buckets) {
            System.out.println("* " + b.getName());
        }
    }

    @Test
    void addFileToBucket() {
        String imagePath = "src/test/resources/test_driver_logo.png";

        try {
            s3.putObject("jake-f1cs-bucket1", "Thingy", new File(imagePath));
            System.out.println("Successfully placed file into bucket "+"jake-f1cs-bucket1");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }


}
