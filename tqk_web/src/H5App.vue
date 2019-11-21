<template>
  <div class="new_index">
    <div class="banner-bg banner-color">
      <!-- 页面顶部搜索框栏 -->
      <div class="header_pr banner-color">
        <header class="index_header banner-color">
          <a href="">
            <div class="wap_search">
              <span class="palaceholder"><i class="iconfont icon-search"></i>输入商品名或粘贴宝贝标题搜索</span>
            </div>
          </a>
          <div class="sigin" style="display: none;">
          </div>
          <div class="header-info">
            <a href="">
              <i class="iconfont icon-info"></i><span>消息</span>
              <span class="is_info"></span>
            </a>
          </div>
        </header>
      </div>
      <div class="header_bg banner-color"></div>
      <!-- 页面底部Tab菜单 -->
      <div class="ui_bot_tab">
        <div class="row-s">
          <div class="col-12-3 list">
            <a>
              <i class="iconfont"><em><img style="width:24px;height: 24px;" src="../static/images/m/index.png" /></em></i>
              <span style="color: rgb(253, 87, 92);">首页</span>
            </a>
          </div>
          <div class="col-12-3 list">
            <a>
              <i class="iconfont"><em><img style="width:24px;height: 24px ;" src="../static/images/m/type_.png" /></em></i>
              <span style="color: rgb(102, 102, 102);">分类</span>
            </a>
          </div>
          <div class="col-12-3 list">
            <a>
              <i class="iconfont"><em><img style="width:24px;height: 24px ;" src="../static/images/m/collet_.png"/></em></i>
              <span style="color: rgb(102, 102, 102);">收藏</span>
            </a>
          </div>
          <div class="col-12-3 list">
            <a>
              <i class="iconfont"><em><img style="width:24px;height: 24px ;" src="../static/images/m/mine_.png"/></em></i>
              <span style="color: rgb(102, 102, 102);">我的</span>
            </a>
          </div>
        </div>
      </div>
      <!-- 页面顶部菜单导航 -->
      <div class="layout ">
        <div class="ui_icon_tab banner-color">
          <div class="cent swiper-container-horizontal">
            <div v-if="!iconNavShow" class="ant banner-color">
              <a href="" class="banner-color active">精选</a>
            </div>
            <div v-if="!iconNavShow" class="swiper-wrapper">
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
            <div v-if="iconNavShow" class="ullit_tab" style="width:100%;">选择分类</div>
            <div v-if="iconNavShow" class="ullit" style="width:375px;left:-50px;">
              <ul class="row-s col-mar">
                <li class="col-12-3 text-center">
                  <a href=""><i class="iconfont ">
                    <img src="../static/images/m/mzh.png"></i><span>美妆</span></a>
                </li>
                <li class="col-12-3 text-center">
                  <a href=""><i class="iconfont ">
                    <img src="../static/images/m/nzh.png"></i><span>美妆</span></a>
                </li>
                <li class="col-12-3 text-center">
                  <a href=""><i class="iconfont ">
                    <img src="../static/images/m/nanzh.png"></i><span>美妆</span></a>
                </li>
                <li class="col-12-3 text-center">
                  <a href=""><i class="iconfont ">
                    <img src="../static/images/m/my.png"></i><span>美妆</span></a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <router-view />
  </div>
</template>
<script>
  export default {
    name: "H5App",
    data() {
      return {
        iconNavShow: false
      }
    },
    created() {
      // 页面上下滚动监听
      window.addEventListener("scroll", this.handleScroll)
    },
    updated() {
    },
    methods: {
      handleScroll() {
        // 页面滚动时，iconNav一定是隐藏的
        this.iconNavShow = false
      }
    }
  }
</script>
<style>
  @import "../static/css/h5_common.css";
  @import "../static/css/h5_main.css";
</style>
