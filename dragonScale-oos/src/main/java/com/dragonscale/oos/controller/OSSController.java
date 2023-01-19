package com.dragonscale.oos.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.BusinessStatus;
import com.ruoyi.common.exception.file.FileException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.UUID;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

@RestController
@RequestMapping("/OSS")
public class OSSController extends BaseController {

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private  String ENDPOINT;
    @Value("${spring.cloud.alicloud.oss.bucket}")
    private  String BUCKET;

    @Value("${spring.cloud.alicloud.access-key}")
    private  String ACCESS_KEY_ID;
    @Value("${spring.cloud.alicloud.secret-key}")
    private  String ACCESS_KEY_SECRET;


    //TODO: 入参为用户ID 视频ID
    @GetMapping("/OSSObject/upload")
    public Object uploadOSSObject(MultipartFile file){


        //创建OSS实例
        OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
//        随机生成uuid TODO:使用用户id+视频id保证唯一性
        String UUid = UUID.randomUUID().toString().replace("-","");

        String filename = file.getOriginalFilename();


        try(InputStream inputStream = file.getInputStream()){

            String datePath = DateUtils.getDate();
            filename = datePath+"/"+UUid+filename;
            ossClient.putObject(BUCKET,filename,inputStream);
            ossClient.shutdown();
            logger.info("上传地址为:https://"+BUCKET+"."+ENDPOINT+"/"+filename);
            return R.ok();
        }catch (Exception e){
            logger.error("文件读取失败");
            throw new FileException("文件读取失败",null);
        }

    }


}
