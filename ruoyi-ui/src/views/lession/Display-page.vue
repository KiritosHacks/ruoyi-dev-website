<template>
  <VideoPlayer ref="dplayer" :dplayerData="dplayerData"></VideoPlayer>
</template>

<script>
import VideoPlayer from '@/components/stage-components/video-player.vue';
import {getVideoUrl} from '@/api/video/video'
export default {
  components:{
    VideoPlayer
  },
  data() {
    return {
      currentDate: new Date(),
      dplayerData: {
				style:
					'width:100%;height:672px;position: absolute;bottom: 0;right: 0;',
			},
			info: { video: '', pic: 'https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png' },
      query:{
        videoId: "id:1",
        definition: "480",
      }
    };
  },
  methods: {
		switchVideo() { 
			this.$refs.dplayer.switchVideo(this.info)
		},
    getUrl(){
      getVideoUrl(this.query).then(response=>{
      this.info.video = response.data;
      this.switchVideo();
    });
    }
	},
  mounted(){
    this.getUrl();
  }
    
}
</script>

<style scoped>

.time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
</style>