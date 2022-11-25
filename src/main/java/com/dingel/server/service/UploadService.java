package com.dingel.server.service;

import com.dingel.server.pojo.dto.ResponseBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    ResponseBean uploadImg(MultipartFile img) throws IOException;
}
