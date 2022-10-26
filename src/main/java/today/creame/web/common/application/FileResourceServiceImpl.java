package today.creame.web.common.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import today.creame.web.common.domain.FileResource;
import today.creame.web.common.domain.FileResourceJpaRepository;
import today.creame.web.common.exception.IllegalOriginalFileNameException;
import today.creame.web.common.exception.UploadFailureException;
import today.creame.web.common.support.ObjectKeySequence;

@RequiredArgsConstructor
@Service
public class FileResourceServiceImpl implements FileResourceService {
    private final Logger log = LoggerFactory.getLogger(FileResourceServiceImpl.class);
    private final AmazonS3 amazonS3;
    private final FileResourceJpaRepository fileResourceJpaRepository;

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final static DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private final static String CONTEXT = "https://s3.ap-northeast-2.amazonaws.com/";
    private final static String BUCKET_NAME = "today.creame.file";


    @Override
    public Long temp(MultipartFile file) {
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DATE_FORMATTER);
        String timestamp = now.format(TIMESTAMP_FORMATTER);
        String sequence = String.valueOf(ObjectKeySequence.getInstance().next());
        log.debug("timestamp: {}",timestamp);
        log.debug("sequence: {}", sequence);

        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        long fileSize = file.getSize();
        log.debug("original filename: {}, content-type: {}, file size: {}", originalFileName, contentType, fileSize);

        String objectKey = new StringBuilder()
            .append("temp/")
            .append(date)
            .append("/")
            .append(timestamp)
            .append("-")
            .append(sequence)
            .append(splitFileExtension(originalFileName))
            .toString();

        log.debug("object key:{}", objectKey);

        try (InputStream is = file.getInputStream()) {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);
            objectMetadata.setContentLength(fileSize);

            amazonS3.putObject(new PutObjectRequest(BUCKET_NAME, objectKey, is, objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            log.error(" 파일 업로드에 실패. 파일명 : {}, reason: {}", originalFileName, e);
            throw new UploadFailureException();
        }

        FileResource fileResource =
            new FileResource(CONTEXT, BUCKET_NAME, objectKey, originalFileName, contentType, fileSize);
        fileResourceJpaRepository.save(fileResource);

        log.debug("file resource:{}", fileResource);
        return fileResource.getId();
    }

    private String splitFileExtension(String originalFileName) {
        if (originalFileName == null || originalFileName.isBlank()) {
            log.info("파일 형식에 맞지 않습니다. filename:{}", originalFileName);
            throw new IllegalOriginalFileNameException();
        }

        int lastIndexOf = originalFileName.lastIndexOf('.');
        if (lastIndexOf < 0) {
            log.info("파일 형식에 맞지 않습니다. filename:{}", originalFileName);
            throw new IllegalOriginalFileNameException();
        }

        return originalFileName.substring(lastIndexOf);
    }

    private String buildFileName(String prefix, String originalFileName) {
        String key = String.valueOf(System.currentTimeMillis());

        if (originalFileName == null) {
            return key;
        }

        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        log.debug("file extension:{}", extension);

        String fileName = prefix.concat(key).concat(extension);
        log.debug("file name: {}", fileName);

        return fileName;
    }
}