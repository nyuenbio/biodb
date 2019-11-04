// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './components/data/toast.css';
import Toast from './components/data/toast.js';
import router from './router'
import store from './utils/store'
import * as types from './utils/types'

Vue.use(ElementUI);
Vue.use(Toast);

// 引用axios，并设置基础URL为后端服务api地址
var axios = require('axios')
//开发环境的全局URL配置
//axios.defaults.baseURL = 'http://localhost:8443/dx'
//测试环境的全局URL配置
//axios.defaults.baseURL = 'http://47.101.129.229:8443/dx'
//胖节点的全局URL配置
axios.defaults.baseURL = 'http://192.168.99.10:8443/dx'

// 将API方法绑定到全局
Vue.prototype.$axios = axios
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  axios,
  store,
  router,
  components: { App },
  template: '<App/>',
  render: h => h(App)
})


// http request 拦截器
axios.interceptors.request.use(
  config => {
    if (store.state.token) {
      config.headers.Authorization = `${store.state.token}`
    }
    return config
  },
  err => {
    return Promise.reject(err)
  },
)

// http response 拦截器
axios.interceptors.response.use(
  response => {
    return response
  },
  error => {
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // 401 清除token信息并跳转到登录页面
          store.commit(types.LOGOUT)

          // 只有在当前路由不是登录页面才跳转
          router.currentRoute.path !== 'login' &&
          router.replace({
            path: 'login',
            query: { redirect: router.currentRoute.path },
          })
      }
    }
    // console.log(JSON.stringify(error));//console : Error: Request failed with status code 402
    return Promise.reject(error.response.data)
  },
)

router.beforeEach((to, from, next) => {
  console.log("before each ----------")
  console.log("store.sytate.token => "+store.state.token)
  console.log("local token => "+localStorage.getItem("token"))
  console.log(from)
  console.log(to)
  console.log(next)
  console.log(to.matched.some(r => r.meta.requireAuth))
  if (to.matched.some(r => r.meta.requireAuth)) {
    if (store.state.token) {
      next();
    }
    else {
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      })
    }
  }
  else {
    next();
  }
})

