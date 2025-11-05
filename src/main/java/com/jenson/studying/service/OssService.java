package com.jenson.studying.service;

import java.net.URI;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jenson.studying.config.OssProperties;

import software.amazon.awssdk.services.s3.S3Client;

@Service
public class OssService {

    private final S3Client s3Client;
    private final OssProperties ossProperties;

    public OssService(S3Client s3Client, OssProperties ossProperties) {
        this.s3Client = s3Client;
        this.ossProperties = ossProperties;
    }

    public S3Client getClient() {
        return s3Client;
    }

    public String getBucket() {
        return ossProperties.getBucket();
    }

    public URI buildObjectUrl(String objectKey) {
        Objects.requireNonNull(objectKey, "objectKey must not be null");
        String normalizedKey = objectKey.startsWith("/") ? objectKey.substring(1) : objectKey;
        String bucket = Objects.requireNonNull(ossProperties.getBucket(), "OSS bucket must be configured");
        if (StringUtils.hasText(ossProperties.getEndpoint())) {
            String base = ossProperties.getEndpoint();
            if (base.endsWith("/")) {
                base = base.substring(0, base.length() - 1);
            }
            return URI.create(String.format("%s/%s/%s", base, bucket, normalizedKey));
        }
        return URI.create(String.format("https://%s.s3.%s.amazonaws.com/%s", bucket,
            ossProperties.getRegion(), normalizedKey));
    }
}
