// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import H5App from './H5App'
import router from './router/index'
import H5Router from './router/h5_index'
import {PLATFORM} from './api/common'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import 'swiper/dist/css/swiper.css'
import layer from 'vue-layer'
import 'vue-layer/lib/vue-layer.css'
import VueClipboard from 'vue-clipboard2'

Vue.config.productionTip = false
Vue.use(VueAwesomeSwiper)
Vue.use(VueClipboard)
Vue.prototype.$layer = layer(Vue)

let CurApp = PLATFORM? (PLATFORM==="2"? H5App: App): App
let CurRouter = PLATFORM? (PLATFORM==="2"? H5Router: router): router
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: CurRouter,
  components: { App: CurApp },
  template: '<App/>'
})
