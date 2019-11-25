<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div class="layout ">
    <div class="ui_icon_tab banner-color">
      <div class="cent swiper-container-horizontal">
        <div id="swiper_nav">
          <mySwiper :swiperData="banners" :autoplay="false" :navigation="{isShow:false}" :slidesPerView="5"
                    :containerCSS="'background:#fe3a33;'" :slideCSS="'background:#fe3a33;'" :clearDefaultSlideClass="true">
            <template v-slot:default="slotProps">
              <a @click.prevent="goClassifyList(slotProps.item)">{{slotProps.item.title}}</a>
            </template>
          </mySwiper>
          <!-- 页面顶部菜单导航右侧分类栏 -->
          <a :class="'whole iconfont banner-color '+(iconNavShow?'new_active icon-shang':'icon-more')" @click.prevent="iconNavShow=!iconNavShow"></a>
        </div>
        <div v-show="false" class="ullit_tab" style="width:375px;">选择分类</div>
        <div v-show="iconNavShow" class="ullit" style="width:375px;left:0px;">
          <ul class="row-s col-mar">
            <li v-for="banner in banners.filter(r=>r.seq!==-1)" :key="banner.id" class="col-12-3 text-center">
              <a @click.prevent="goClassifyList(banner)"><i class="iconfont ">
                <img :src="'../../../static/images/m/'+bannerIcons[banner.seq.toString()]"></i><span>{{banner.title}}</span></a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as server from '../../api'
  import mySwiper from '../swiper'
  export default {
    name: "h5Banner",
    data() {
      return {
        banners: [],
        iconNavShow: false,
        bannerIcons: {0: 'nzh.png', 1: 'msh.gif', 2: 'mzh.png', 3: 'psh.png', 4: 'nanzh.png', 5: 'ny.png', 6: 'jj.png', 7: 'shm.png', 8: 'hy.png'}
      }
    },
    created() {
      server.getSiblingsNavList(server.HHJX_M_ID).then(res=>this.banners = res.data)
    },
    methods: {
      hideIconNav() {
        this.iconNavShow = false
      },
      goClassifyList(banner) {
        this.$emit('goClassifyList', banner)
      }
    },
    components: {
      mySwiper
    }
  }
</script>

<style scoped>

</style>
