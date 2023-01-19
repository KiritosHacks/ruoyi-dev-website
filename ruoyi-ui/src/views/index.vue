<template>
  <div v-infinite-scroll="load" class="infinite-list" style="height: calc(100vh); overflow: auto">
    <el-backtop></el-backtop>
    <el-container>
      <el-header>
        <navbar />
      </el-header>
      <el-main>
        <el-row gutter="20">
          <el-col :span="4">
            <div class="grid-content bg-purple-dark"></div>
          </el-col>
          <el-col :span="16">
            <div class="grid-content bg-purple-dark"></div>
          </el-col>
          <el-col :span="4">
            <div class="grid-content bg-purple-dark"></div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <div class="grid-content bg-purple"></div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple-light"></div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <div class="grid-content bg-purple"></div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple-light"></div>
          </el-col>
          <el-col :span="8">
            <div class="grid-content bg-purple"></div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <div class="grid-content bg-purple"></div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple-light"></div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple"></div>
          </el-col>
          <el-col :span="6">
            <div class="grid-content bg-purple-light"></div>
          </el-col>
        </el-row>
        <el-row v-for="i in count" gutter="20">
          <el-col :span="6" v-for="i in rows">
            <el-card :body-style="{ padding: '0px' }">
              <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                class="image" />
              <div style="padding: 14px">
                <span>{{i.name}}</span>
                <div class="bottom clearfix">
                  <el-button type="text" class="button">查看视频详情</el-button>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <!-- <right-button :targetContainer=""/> -->

        <!-- <router-view /> -->
      </el-main>
      <!-- <el-footer><footbar/></el-footer> -->
    </el-container>
  </div>
</template>

<script>
import Navbar from "../components/stage-components/Navbar.vue";
import Footbar from "../components/stage-components/Footbar.vue";
import RightButton from "../components/stage-components/RightButton.vue";
import {listProject} from "@/api/business/project"

export default {
  components: {
    Navbar,
    Footbar,
    RightButton,
  },
  data() {
    return {
      count: 0,
      loading: true,
      currentDate: new Date(),
      rows:[],
    };
  },
  methods: {
    load() {
      this.count += 1;
      this.getProjectList({
        pageNum: 1,
        pageSize: 4
      })
    },
    getProjectList(query){
      listProject(query).then(res=>{
        this.rows = res.rows;
      })
    }
  },
};
</script>

<style>
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

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
  clear: both;
}
</style>
