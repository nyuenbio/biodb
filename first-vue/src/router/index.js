import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/manage/login.vue'
import my_index from '@/components/home/my_index.vue'
import grch37_list from '@/components/data/grch37_list.vue'

import store from '@/utils/store'
import * as types from '@/utils/types'

Vue.use(Router)

export default new Router({
  routes :[{
      path: '/my_index',
      name: 'my_index',
      component: my_index,
      children:[
        {
          path: '/grch37_list',
          name: 'grch37_list',
          component: grch37_list,
          meta: {
            requireAuth: true,
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/',
      redirect: '/login',
      hidden: true
    }
  ]

})

// 页面刷新时，重新赋值token
if (window.localStorage.getItem('token')) {
  console.log('页面刷新 '+ window.localStorage.getItem('token'))
  store.commit(types.LOGIN, window.localStorage.getItem('token'))
}


