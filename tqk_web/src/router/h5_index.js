import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/h5_index'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '*',
      redirect: '/'
    },
    {
      path: '/',
      name: 'index',
      component: index
    }
  ]
})

