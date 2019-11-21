import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/index'
import list from '@/pages/list'
import my from '@/pages/muying'
import detail from '@/pages/detail'

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
      path: '/index',
      redirect: '/'
    },
    {
      path: '/detail/:id',
      name: 'good_detail',
      component: detail
    },
    {
      path: '/list/:type/:value',
      name: 'list',
      component: list
    },
    {
      path: '/my',
      name: 'my',
      component: my
    }
  ]
})
