package org.example.his.api.common;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.example.his.api.exception.HisException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Component
@Slf4j
public class MinioUtil {
    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucket;

    private MinioClient client;

    @PostConstruct
    public void init() {
        this.client = new MinioClient.Builder().endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    public void uploadImage(String path, MultipartFile file) {
        try {
            //在Minio中保存图片（文件不能超过5M）
            this.client.putObject(PutObjectArgs.builder().bucket(bucket).object(path)
                    .stream(file.getInputStream(), -1, 5 * 1024 * 1024)
                    .contentType("image/jpeg").build());
            log.debug("向" + path + "保存了文件");
        } catch (Exception e) {
            log.error("保存文件失败", e);
            throw new HisException("保存文件失败");
        }
    }

    public void uploadExcel(String path, MultipartFile file) {
        try {
            //Excel文件的MIME类型
            String mime = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            //Excel文件不能超过20M
            this.client.putObject(PutObjectArgs.builder()
                    .bucket(bucket).object(path)
                    .stream(file.getInputStream(), -1, 20 * 1024 * 1024)
                    .contentType(mime).build());
            log.debug("向" + path + "保存了文件");
        } catch (Exception e) {
            log.error("保存文件失败", e);
            throw new HisException("保存文件失败");
        }
    }

    public InputStream downloadFile(String path) {
        try {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .build();
            return client.getObject(args);
        } catch (Exception e) {
            log.error("文件下载失败", e);
            throw new HisException("文件下载失败");
        }
    }

    public void deleteFile(String path) {
        try {
            this.client.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .build());
            log.debug("删除了" + path + "路径下的文件");
        } catch (Exception e) {
            log.error("文件删除失败", e);
            throw new HisException("文件删除失败");
        }
    }
}

