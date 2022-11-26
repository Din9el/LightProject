package com.dingel.server.controller;

import com.dingel.server.service.UploadService;
import com.dingel.server.pojo.dto.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/policy")
    public ResponseBean uploadImg(MultipartFile file) throws IOException {
        return uploadService.uploadImg(file);
    }


}
