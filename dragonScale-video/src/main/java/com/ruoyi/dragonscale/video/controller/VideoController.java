package com.ruoyi.dragonscale.video.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.dragonscale.video.resourceHandler.NonStaticResourceHttpRequestHandler;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/video")
public class VideoController {

    private final NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler;

    public VideoController(NonStaticResourceHttpRequestHandler nonStaticResourceHttpRequestHandler) {
        this.nonStaticResourceHttpRequestHandler = nonStaticResourceHttpRequestHandler;
    }


    @Anonymous
    @GetMapping("/getVideo")
    public void videoPreview(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //假如我把视频1.mp4放在了static下的video文件夹里面
        //sourcePath 是获取resources文件夹的绝对地址
        //realPath 即是视频所在的磁盘地址
        String sourcePath = ClassUtils.getDefaultClassLoader().getResource("static/DPlayer.mp4").getPath().substring(1);

        String realPath = sourcePath+"static/DPlayer.mp4";


        Path filePath = Paths.get(sourcePath);
        if (Files.exists(filePath)) {
            String mimeType = Files.probeContentType(filePath);
            if (!StringUtils.isEmpty(mimeType)) {
                response.setContentType(mimeType);
            }
            request.setAttribute(NonStaticResourceHttpRequestHandler.ATTR_FILE, filePath);
            nonStaticResourceHttpRequestHandler.handleRequest(request, response);
            System.out.println("success");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            System.out.println("fail");
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
        System.out.println(JSONObject.toJSONString(map));
        return map;
    }
}
