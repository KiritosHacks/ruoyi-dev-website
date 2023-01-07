<template>
  <div>
    <div id="dplayer" :style="dplayerData.style"></div>
  </div>
</template>
 
<script>
import DPlayer from "dplayer";
export default {
  name: "dplayer",
  props: {
    dplayerData: Object,
  },
  data() {
    return {};
  },
  mounted() {
    this.dp = new DPlayer({
      container: document.getElementById("dplayer"),
      theme: "#b7daff", // 风格颜色，例如播放条，音量条的颜色
      // loop: false, // 是否自动循环
      lang: "zh-cn", // 语言，'en', 'zh-cn', 'zh-tw'
      hotkey: true, // 是否支持热键，调节音量，播放，暂停等
      preload: "auto", // 自动预加载
      mutex: true, //互斥，阻止多个播放器同时播放，当前播放器播放时暂停其他播放器

      airplay: true, //	在 Safari 中开启 AirPlay

      danmaku: {
        id: "1",
          api: ['http://localhost:8080/admin/video/getDanMu/'],
          addition: ['http://localhost:8080/admin/video/getDanMu/v3/?id=1']
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
      ],

      video: {
        url: "http://localhost:8080/admin/video/getVideo", // 播放视频的路径
        defaultQuality: 0, // 默认是HD
        pic: "https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png", // 视频封面图片
      },
    });
  },
  created() {},
  methods: {
    // 切换视频源事件
    switchVideo(item) {
      this.dp.switchVideo({
        url: item.video,
        pic: item.imgUrl,
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