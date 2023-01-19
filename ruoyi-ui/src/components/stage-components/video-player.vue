<template>
  <div>
    <div id="dplayer" :style="dplayerData.style"></div>
  </div>
</template>

<script>
import Hls from "hls.js";
import DPlayer from "dplayer";
export default {
  props: {
    dplayerData: Object,
  },
  data() {
    return {
      dp: {},
    };
  },
  mounted() {
  
  },
  created() {},
  methods: {
    // 切换视频源事件
    switchVideo(item) {
      //必须在初始化dplayer之前传入视频路径，否则hls加载不到资源
      this.loadVideo(item);
    },
    loadVideo(videoinfo) {
      this.dp = new DPlayer({
        container: document.getElementById("dplayer"), //容器
        theme: "#b7daff", // 风格颜色，例如播放条，音量条的颜色
        // loop: false, // 是否自动循环
        lang: "zh-cn", // 语言，'en', 'zh-cn', 'zh-tw'
        hotkey: true, // 是否支持热键，调节音量，播放，暂停等
        preload: "auto", // 自动预加载
        mutex: true, //互斥，阻止多个播放器同时播放，当前播放器播放时暂停其他播放器
        airplay: true, //	在 Safari 中开启 AirPlay
        danmaku: {
          id: "1",
          api: ["http://localhost:8080/admin/video/getDanMu/"],
          addition: ["http://localhost:8080/admin/video/getDanMu/v3/?id=1"],
        },
        contextmenu: [
          {
            text: "看什么看！QAQ",
            link: "https://github.com/DIYgod/DPlayer",
          },
        ],
        highlight: [
          {
            text: "敌军还有10秒到达战场",
            time: 10,
          },
          {
            text: "2mins 空降",
            time: 120,
          },
        ], //高能

        video: {
          //画质切换
          // quality: [{
          //     name: '480',
          //     url: "https://test-video-sl.oss-cn-hangzhou.aliyuncs.com/video/id%3A1/480/output.m3u8",
          // }, {
          //     name: '720',
          //     url: "https://test-video-sl.oss-cn-hangzhou.aliyuncs.com/video/id%3A1/480/output.m3u",
          // }, {
          //     name: '1080',
          //     url: "https://test-video-sl.oss-cn-hangzhou.aliyuncs.com/video/id%3A1/480/output.m3u",
          // }],
          url: videoinfo.video, // 播放视频的路径
          defaultQuality: 0, // 默认是HD
          pic: videoinfo.pic, // 视频封面图片
          type: "customHls", //播放类型
          customType: {
            customHls: function (video, player) {
              const hls = new Hls();
              hls.loadSource(video.src);
              hls.attachMedia(video);
            },
          }, //实例化Hls  用于解析m3u8
        },
      });
    },
    // 暂停事件
    pause() {
      this.dp.pause();
    },
  },
};
</script>

<style lang="scss" scoped></style>
