<template>
    <div>
      <myHeader></myHeader>
      <hr/>
      <div style="margin-top:5px">
        <el-row :gutter="10">
          <el-col :xs="4" :sm="4" :md="4" :lg="4">
            <div>
              <el-radio-group v-model="isCollapse" style="margin-bottom: 20px;">
                <el-radio-button :label="false">展开</el-radio-button>
                <el-radio-button :label="true">收起</el-radio-button>
              </el-radio-group>
              <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse" @select="handleSelect">
                  <el-submenu index="1">
                      <template slot="title">
                        <i class="el-icon-document"></i>
                        <span slot="title">数据列表</span>
                      </template>
                      <el-menu-item index="1-1">grch_37</el-menu-item>
                      <el-menu-item index="1-2">clingen</el-menu-item>
                  </el-submenu>
                  <el-menu-item index="2">
                    <i class="el-icon-menu"></i>
                    <span slot="title">导航二</span>
                  </el-menu-item>
                  <el-menu-item index="3">
                    <i class="el-icon-document"></i>
                    <span slot="title">导航三</span>
                  </el-menu-item>
                  <el-menu-item index="4">
                    <i class="el-icon-setting"></i>
                    <span slot="title">导航四</span>
                  </el-menu-item>
              </el-menu>
            </div>
          </el-col>

          <el-col :xs="20" :sm="20" :md="20" :lg="20">
            <div>
              <div style="border: 1px solid #A6A6A6; border-radius:6px; padding:5px; margin:2px; background-color: white">
                <el-breadcrumb separator="/">
                  <el-breadcrumb-item v-for="item in breadcrumbItems" :key="item.index">{{item}}</el-breadcrumb-item>
                </el-breadcrumb>
              </div>
            </div>

            <div style="margin-top:10px">
              <router-view></router-view>
            </div>
          </el-col>
        </el-row>
      </div>
      <hr/>
      <myFooter></myFooter>
    </div>
</template>
<style>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
</style>
<script>
    // import __ from '__' // __是需要手动引入的文件
    import myHeader from '@/components/common/my_header.vue'
    import myFooter from '@/components/common/my_footer.vue'

    export default {
        name: 'my_index',
        components: {
          myHeader,
          myFooter
        },
        data() {
          return {
            isCollapse: false,
            breadcrumbItems: []
          };
        },
        methods: {
          handleOpen(key, keyPath) {
            //console.log(key, keyPath);
          },
          handleClose(key, keyPath) {
            //console.log(key, keyPath);
          },
          handleSelect(key, keyPath){
            switch(key){
              case '1-1':
                this.$router.push('/grch37_list');
                this.breadcrumbItems  = ['grch37_list']
                break;
              case '1-2':
                this.breadcrumbItems  = ['cligen']
                break;
              case '2':
                this.breadcrumbItems  = ['login']
                break;
            }
          }
        },
        watch: {

        }
    }
</script>

