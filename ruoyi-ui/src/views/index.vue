<template>
  <div>
    <el-container>
      <el-header><navbar /></el-header>
      <el-main>
        <router-view />
      </el-main>

      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>

<script>
import Navbar from "../components/stage-components/Navbar.vue";



export default {
  components: { Navbar },
};
</script>

<style>
</style>