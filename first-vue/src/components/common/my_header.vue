<template>
    <div align="right">
      {{date}} <el-button icon="el-icon-s-custom" type="primary" plain  @click="logout()">退出登录</el-button>
    </div>
</template>
<script>
    // import __ from '__' // __是需要手动引入的文件
    import {formatDate} from "../../utils/dateUtil.js";
    import * as types from '../../utils/types'
    import { mapState } from 'vuex'

    export default {
        name: 'my_header',
        components: {},
        data() {
            return {
              date: this.formatDate(new Date())
            }
        },
        methods: {
            formatDate(time) {
              return formatDate(time,"yyyy"+"年"+"MM"+"月"+"dd"+"日"+" hh:mm:ss");
            },
          logout() {
            this.$store.commit(types.LOGOUT)
            this.$router.push({
              path: '/',
            })
          }
        },
      computed: mapState({
        title: state => state.title,
        token: state => state.token,
      })
    }
</script>
<style lang="postcss" scoped>
    body {
        background-color: #ff0000;
    }
</style>
