package com.jenson.studying.config;

import java.net.URI;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;

@Configuration
@EnableConfigurationProperties(OssProperties.class)
public class OssConfig {

    @Bean
    public S3Client s3Client(OssProperties properties) {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
            StringUtils.hasText(properties.getAccessKey()) ? properties.getAccessKey() : "",
            StringUtils.hasText(properties.getSecretKey()) ? properties.getSecretKey() : ""
        );

        S3Client.Builder builder = S3Client.builder()
            .region(Region.of(properties.getRegion()))
            .credentialsProvider(StaticCredentialsProvider.create(credentials))
            .serviceConfiguration(S3Configuration.builder()
                .pathStyleAccessEnabled(properties.isPathStyleAccessEnabled())
                .build());

        if (StringUtils.hasText(properties.getEndpoint())) {
            builder = builder.endpointOverride(URI.create(properties.getEndpoint()));
        }

        return builder.build();
    }
}
