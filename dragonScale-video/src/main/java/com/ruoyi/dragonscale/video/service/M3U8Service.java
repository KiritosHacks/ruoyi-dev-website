package com.ruoyi.dragonscale.video.service;

import com.dragonscale.oos.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class M3U8Service {

    @Resource
    OssService ossService;

    //获取oss地址+文件目录
    private String getOSSUrl(String videoId){
        String urlInfo = ossService.getOssUrlInfo();//获取oss桶的名称和服务器地址
        return urlInfo+"/video/"+videoId+"/";
    }

    //获取M3U8文件地址，入参：清晰度 用户名
    public String getM3U8(String videoId,String definition){
        return getOSSUrl(videoId)+definition+"/output.m3u8";
    }

}
