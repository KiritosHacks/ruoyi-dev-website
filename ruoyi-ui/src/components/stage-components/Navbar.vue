<template>
    <div style="display:flex;justify-content:center; ">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" router="true">
            <el-menu-item index="/">首页</el-menu-item>
            <el-submenu index="2">
                <template slot="title">我的工作台</template>
                <el-menu-item index="2-1">选项1</el-menu-item>
                <el-menu-item index="2-2">选项2</el-menu-item>
                <el-menu-item index="2-3">选项3</el-menu-item>
                <el-submenu index="2-4">
                    <template slot="title">选项4</template>
                    <el-menu-item index="2-4-1">选项1</el-menu-item>
                    <el-menu-item index="2-4-2">选项2</el-menu-item>
                    <el-menu-item index="2-4-3">选项3</el-menu-item>
                </el-submenu>
            </el-submenu>
            <el-menu-item index="" disabled>消息中心</el-menu-item> 
            <el-menu-item><search :input="inputData"></search></el-menu-item>
            <el-menu-item index=""><a href="https://www.ele.me" target="_bzlank">订单管理</a></el-menu-item>
            <el-menu-item index="lession">课程中心</el-menu-item>
            <el-menu-item>课程中心</el-menu-item>
            <el-menu-item>课程中心</el-menu-item>
            <el-menu-item>课程中心</el-menu-item>
            <el-menu-item index="/system/index"><el-button circle class="el-icon-user-solid"></el-button></el-menu-item>
            
        </el-menu>
    </div>
    
    
</template>
   
<script>
import router from '../../router';
import search from '@/components/stage-components/Search.vue'
export default {
    components:{
        search
    },
    data() {
        return {
            activeIndex: "1",
            activeIndex2: "1",
            inputData: "",
        };
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
    },
};
</script>
   
<style scoped>
