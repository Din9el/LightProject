package com.dingel.server.service.impl;


import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.pojo.util.PathUtils;
import com.dingel.server.service.UploadService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;

import org.omg.CORBA.SystemException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Data
@ConfigurationProperties(prefix = "oss")
public class OssUploadService implements UploadService {

    private String accessKey;
    private String secretKey;
    private String bucket;
    @Override
    public ResponseBean uploadImg(MultipartFile img) throws IOException {

            //判断文件类型
            //获取原始文件名
            String originalFilename = img.getOriginalFilename();
            //对原始文件名进行判断
            if(!originalFilename.endsWith(".png")){
                return null;
            }

            //如果判断通过上传文件到OSS
            String filePath = PathUtils.generateFilePath(originalFilename);
            String url = uploadOss(img,filePath);//  2099/2/3/wqeqeqe.png
            return ResponseBean.success(url);
    }


    public String uploadOss(MultipartFile imgFile, String filePath){
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = filePath;
        try {
            InputStream inputStream = imgFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                Map<String, String> respMap = new LinkedHashMap<String, String>();
                respMap.put("upToken", upToken); //获取的token

                return "http://dingel.xyz/"+key;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return "www";
    }


}
