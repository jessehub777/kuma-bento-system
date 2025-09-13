package jp.co.kuma.controller.admin;

import jp.co.kuma.result.Result;
import jp.co.kuma.utils.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@RequiredArgsConstructor
public class CommonController {
    
    private final S3Util s3Util;
    
    /**
     * upload to minio
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        
        s3Util.uploadFile(fileName, file.getInputStream(), file.getContentType());
        return Result.success(s3Util.getFileUrl(fileName));
    }
}
