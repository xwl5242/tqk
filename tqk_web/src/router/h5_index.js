import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/h5_index'
import mine from '@/pages/h5_mine'
import collect from '@/pages/h5_collect'
import classify from '@/pages/h5_classify'

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
    },
    {
      path: '/mine',
      name: 'mine',
      component: mine
    },
    {
      path: '/collect',
      name: 'collect',
      component: collect
    },
    {
      path: '/classify',
      name: 'classify',
      component: classify
    }
  ]
})

