<template>
  <div class="layout ">
    <div class="ui_icon_tab banner-color">
      <div class="cent swiper-container-horizontal">
        <div id="swiper_nav">
          <div v-show="!iconNavShow" class="ant banner-color">
            <a href="" class="banner-color active">精选</a>
          </div>
          <div v-show="!iconNavShow" class="swiper-wrapper">
            <div class="swiper-slide banner-color swiper-slide-active">
              <a class="banner-color" href="">美妆</a>
            </div>
            <div class="swiper-slide banner-color swiper-slide-active">
              <a class="banner-color" href="">美妆</a>
            </div>
            <div class="swiper-slide banner-color swiper-slide-active">
              <a class="banner-color" href="" >美妆</a>
            </div>
            <div class="swiper-slide banner-color swiper-slide-active">
              <a class="banner-color" href="" >美妆</a>
            </div>
            <div class="swiper-slide banner-color swiper-slide-active">
              <a class="banner-color" href="" >美妆</a>
            </div>
            <div class="swiper-slide banner-color swiper-slide-active">
              <a class="banner-color" href="" >美妆</a>
            </div>
            <div class="swiper-slide banner-color swiper-slide-active">
              <a class="banner-color" href="" >美妆</a>
            </div>
          </div>
          <!-- 页面顶部菜单导航右侧分类栏 -->
          <a :class="'whole iconfont banner-color '+(iconNavShow?'new_active icon-shang':'icon-more')" @click.prevent="iconNavShow=!iconNavShow"></a>
        </div>
        <div v-show="false" class="ullit_tab" style="width:375px;">选择分类</div>
        <div v-show="iconNavShow" class="ullit" style="width:375px;left:-50px;">
          <ul class="row-s col-mar">
            <li class="col-12-3 text-center">
              <a href=""><i class="iconfont ">
                <img src="../../../static/images/m/mzh.png"></i><span>美妆</span></a>
            </li>
            <li class="col-12-3 text-center">
              <a href=""><i class="iconfont ">
                <img src="../../../static/images/m/nzh.png"></i><span>美妆</span></a>
            </li>
            <li class="col-12-3 text-center">
              <a href=""><i class="iconfont ">
                <img src="../../../static/images/m/nanzh.png"></i><span>美妆</span></a>
            </li>
            <li class="col-12-3 text-center">
              <a href=""><i class="iconfont ">
                <img src="../../../static/images/m/my.png"></i><span>美妆</span></a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "h5Banner",
    data() {
      return {
        iconNavShow: false
      }
    },
    methods: {
      hideIconNav() {
        this.iconNavShow = false
      }
    }
  }
</script>

<style scoped>

</style>
