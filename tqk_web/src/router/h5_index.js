import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/h5_index'
import mine from '@/pages/h5_mine'
import collect from '@/pages/h5_collect'
import classify from '@/pages/h5_classify'
import search from '@/pages/h5_search'
import detail from '@/pages/h5_detail'

Vue.use(Router)

export default new Router({
  mode: 'history',
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
    },{
      path: '/search',
      name: 'search',
      component: search
    },
    {
      path: '/detail',
      name: 'detail',
      component: detail
    }
  ]
})

