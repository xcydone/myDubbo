package com.crossyf.dubbo.common.utils;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.InputStream;

/**
 * <p>
 * MinioTemplate
 * </p>
 *
 * @author caif
 * @since 2020-03-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MinioTemplate implements InitializingBean {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

    private MinioClient client;

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.hasText(endpoint, "Minio url 为空");
        Assert.hasText(accessKey, "Minio accessKey为空");
        Assert.hasText(secretKey, "Minio secretKey为空");
        this.client = new MinioClient(endpoint, accessKey, secretKey);
        this.createBucket(this.bucketName);
    }

    /**
     * 创建bucket
     *
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void createBucket(String bucketName) {
        if (!client.bucketExists(bucketName)) {
            client.makeBucket(bucketName);
        }
    }

    /**
     * 根据bucketName删除信息
     * @param bucketName bucket名称
     */
    @SneakyThrows
    public void removeBucket(String bucketName) {
        client.removeBucket(bucketName);
    }

    /**
     * 获取文件外链
     *
     * @param objectName 文件名称
     * @param expires    过期时间 <=7
     * @return url
     */
    @SneakyThrows
    public String getObjectURL(String objectName, Integer expires) {
        return client.presignedGetObject(this.bucketName, objectName, expires);
    }

    /**
     * 获取文件
     *
     * @param objectName 文件名称
     * @return 二进制流
     */
    @SneakyThrows
    public InputStream getObject(String objectName) {
        return client.getObject(this.bucketName, objectName);
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String objectName, InputStream stream) throws Exception {
        client.putObject(this.bucketName, objectName, stream, stream.available(), "application/octet-stream");
    }

    /**
     * 上传文件
     *
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws Exception {
        this.createBucket(bucketName);
        client.putObject(bucketName, objectName, stream, stream.available(), "application/octet-stream");
    }

    /**
     * 上传文件
     *
     * @param objectName  文件名称
     * @param stream      文件流
     * @param size        大小
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     */
    public void putObject(String objectName, InputStream stream, long size, String contextType) throws Exception {
        client.putObject(this.bucketName, objectName, stream, size, contextType);
    }

    /**
     * 删除文件
     *
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     */
    public void removeObject(String objectName) throws Exception {
        client.removeObject(this.bucketName, objectName);
    }

    /**
     * 获取文件信息
     *
     * @param objectName 文件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     */
    public ObjectStat getObjectInfo(String objectName) throws Exception {
        return client.statObject(this.bucketName, objectName);
    }

}
