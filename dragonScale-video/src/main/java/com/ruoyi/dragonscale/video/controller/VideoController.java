package com.ruoyi.dragonscale.video.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.dragonscale.video.resourceHandler.NonStaticResourceHttpRequestHandler;
import com.ruoyi.dragonscale.video.service.M3U8Service;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/video")
public class VideoController extends BaseController {

    @Resource
    M3U8Service m3U8Service;
    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    public VideoController(NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler) {
        this.nonStaticResourceHttpRequestHandler = nonStaticResourceHttpRequestHandler;
    }

    @Deprecated
    @Anonymous
    @GetMapping("/getVideo")
    public void videoPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //假如我把视频1.mp4放在了static下的video文件夹里面
        //sourcePath 是获取resources文件夹的绝对地址
        //realPath 即是视频所在的磁盘地址
        String sourcePath = ClassUtils.getDefaultClassLoader().getResource("static/Dplayer.mp4").getPath().substring(1);

//        String realPath = sourcePath+"static/DPlayer.mp4";
        logger.info("视频路径为:"+sourcePath);

        Path filePath = Paths.get(sourcePath);
        if (Files.exists(filePath)) {
            //获取文件类型
            String mimeType = Files.probeContentType(filePath);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }


    @Anonymous
    @GetMapping("/getDanMu/v3/")
    public Object danMuPreview(){
//        System.out.println(id);
        System.out.println("进入方法");
//        参数字段顺序分别是：时间、类型(0、1、2)、颜色#xxx、作者、内容
        ArrayList<Object[]> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Object[] danmu = new Object[]{i,1,"#fff","618c713c","妹子真好看！！！！！"+i};
            list.add(danmu);
        }


        Map<String, Object> map = new HashMap() {{
            put("code", 0);
            put("data", list);
        }};
        return map;
    }


    @Anonymous
    @GetMapping("/getVideoUrl")
    public Object getVideoUrl(@RequestParam String videoId,@RequestParam String definition){        //@RequestParam String userId
        //ToDo:权限认证 根据userId和videoId判断是否能够访问此video
        logger.info("视频id："+videoId+"/n用户id：");
        String url = "https://"+m3U8Service.getM3U8(videoId, definition);
        return AjaxResult.success("获取视频链接成功！",url);
    }


}
