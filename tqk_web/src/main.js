// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import H5App from './H5App'
import router from './router'
import {PLATFORM} from './api/common'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import 'swiper/dist/css/swiper.css'

Vue.config.productionTip = false
Vue.use(VueAwesomeSwiper)

let CurApp = PLATFORM? (PLATFORM==="2"? H5App: App): App
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App: CurApp },
  template: '<App/>'
})
