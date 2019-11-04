<template xmlns:v-on="http://www.w3.org/1999/xhtml">
    <div>
      <div>
        <br/><br/>
        用户名：<input type="text" v-model="user.username" placeholder="请输入用户名" />
        <br/><br/>
        密&emsp;码：<input type="password" v-model="user.userpassword" placeholder="请输入密码" />
        <br/><br/>
        <button v-on:click="login">登录</button>
        <br/><br/>
        登录验证情况：<textarea cols="30" rows="10" v-model="responseResult"></textarea>
      </div>
    </div>
</template>
<script>
    // import __ from '__' // __是需要手动引入的文件
    import my_header from '@/components/common/my_header.vue'
    import my_footer from '@/components/common/my_footer.vue'
    import * as types from '@/utils/types'
    import md5 from 'js-md5';

    export default {
        name: 'login',
        // blogHeader、blogFooter组件给申明到components里面然后在template里面使用
        components: { my_header, my_footer },
        data() {
            return {
              user:{},
              responseResult:[],
              token: ''
            }
        },
        methods: {
          login(){
            console.log(this.user)
            let pwd = md5(this.user.userpassword)
            this.user.userpassword = pwd;
            this.$axios({
                url: '/admin/login',
                method: 'post',
                data: this.user
              })
              .then(successResponse => {
                this.responseResult = JSON.stringify(successResponse.data.message)
                if(successResponse.data.code == 200){
                  this.token = successResponse.data.data;
                  this.$store.commit(types.LOGIN, this.token)
                  this.$router.replace({path: '/my_index'})
                }
              })
              .catch(failResponse =>{

              })
          }
        },
        mounted(){
          this.$store.commit(types.TITLE, 'Login');
        }
    }
</script>
<style lang="postcss" scoped>
    body {
        background-color: #ff0000;
    }
</style>
