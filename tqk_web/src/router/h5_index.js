import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/h5_index'
import mine from '@/pages/h5_mine'
import login from '@/pages/h5_login'
import collect from '@/pages/h5_collect'
import classify from '@/pages/h5_classify'
import search from '@/pages/h5_search'
import detail from '@/pages/h5_detail'
import share from '@/pages/h5_share'
import classifyList from '@/pages/h5_classify_list'

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
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/share',
      name: 'share',
      component: share
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
      path: '/detail/:id',
      name: 'detail',
      component: detail
    },
    {
      path: '/classify/:type/:value',
      name: 'classifyList',
      component: classifyList
    }
  ]
})

