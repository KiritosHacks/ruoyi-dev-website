package com.dragonscale.oos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OssService {

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private  String ENDPOINT;
    @Value("${spring.cloud.alicloud.oss.bucket}")
    private  String BUCKET;

    @Value("${spring.cloud.alicloud.access-key}")
    private  String ACCESS_KEY_ID;
    @Value("${spring.cloud.alicloud.secret-key}")
    private  String ACCESS_KEY_SECRET;

    //获取oss 仓库地址信息
    public String getOssUrlInfo(){
        return BUCKET+"."+ENDPOINT;
    }

}
