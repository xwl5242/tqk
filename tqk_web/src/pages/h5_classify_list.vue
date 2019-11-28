<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div>
    <!-- 页面顶部title和菜单开始 -->
    <div class="main-title clearfix theme-bg-color-1">
      <a @click.prevent="$router.push('/')" class="main-back"></a>
      <div class="menu-detail">
        <span>{{selectedBanner.text}}</span>
      </div>
      <a class="mui-action-menu main-more" @click.prevent="topMenuShow = !topMenuShow"></a>
    </div>
    <nav id="detail-top-menu" :class="topMenuShow?'show':''">
      <h5TitleMenu />
    </nav>
    <!-- 页面顶部title和菜单结束 -->
    <!-- 顶部nav导航和商品详细分类开始 -->
    <div class="icon_nav_tab">
      <mySwiper ref="banner" :swiperData="banners" :navigation="{isShow:false}"
                :autoplay="false" :slidesPerView="5" :clearDefaultSlideClass="true">
        <template v-slot:default="slotProps">
          <div :class="'tab_def_list'+(slotProps.item.text===selectedBanner.text?' active':'')">
            <a @click.prevent="bannerChange(slotProps.item)">{{slotProps.item.text}}</a>
          </div>
        </template>
      </mySwiper>
    </div>
    <div class="icon_nav_tab_bg" style="height: 80px;"></div>
    <div class="cat_tab_list ">
      <ul>
        <li v-for="ni in navIcon[selectedBanner.text]" :key="ni.label" class="cat-item ">
          <a @click.prevent="">
            <img class="lazy" :src="'../../static/images/m/nav_icon/'+ni.png"><span>{{ni.label}}</span>
          </a>
        </li>
        <div class="ov_h"></div>
      </ul>
    </div>
    <div class="cat_tab_list_load" style="display: none;"></div>
    <!-- 顶部nav导航和商品详细分类结束 -->
    <!-- 页面中间筛选、排序开始 -->
    <div class="order-nav order-nav-cat" style="position: relative; top: 0; margin-top: 0;">
      <ul>
        <li class="theme-border-bottom-color-1 cur">
          <span><a class="theme-color-1">人气</a></span>
        </li>
        <li class="">
          <span><a>最新</a></span>
        </li>
        <li class="">
          <span><a>销量</a></span>
        </li>
        <li class="">
            <span><a>价格<span class="price-ico "></span></a></span>
        </li>
      </ul>
    </div>
    <div class="order-nav-bg ov_h " style="height: 0"></div>
    <!-- 页面中间筛选、排序结束 -->
    <!-- 页面商品列表开始 -->
    <div class="scrollable">
      <div v-if="goods" class="goods-two" data-page="1" style="padding-top: 0; background: #fff;">
        <div class="lazy1">
          <li v-for="(good,index) in goods" :key="good.id" class="row-s" :style="index===goods.length-1?'margin-bottom: 50px':''">
            <a>
              <div class="img ui-act-label">
                <img :src="good.pictUrl"  style="background: rgb(245, 245, 245); display: block;">
              </div>
            </a>
            <div class="cent">
              <a>
                <h3 class="product_title">
<!--                  <span class="labelTop tm">天猫</span>-->
                  <span class="title_text">{{good.title}}</span>
                </h3>
              </a>
              <div class="product_info">
                <a>
                  <div class="price"><span>折扣价&nbsp;</span> <span class="RMB">¥</span> <span class="price_num">{{good.zkFinalPrice}}</span></div>
                  <div class="label_box">
                    <span style="display:inline;" >
                      <span class="juan"><span>劵</span>{{good.couponAmount}}元</span>
                    </span>
                  </div>
                  <div class="salse"><span>已售{{good.volume|volumeFormat}}</span></div>
                </a>
              </div>
            </div>
          </li>
        </div>
      </div>
<!--      <div class="pullup-goods">-->
<!--        <div class="label">商品加载中...</div>-->
<!--      </div>-->
    </div>
    <!-- 页面商品列表结束 -->
    <div class="iconfont toTop" style="bottom: 60px;"></div>
  </div>
</template>

<script>
  import navIcon from '../../static/images/m/nav_icon/map'
  import * as server from '../api'
  import * as util from '../api/util'
  import mySwiper from '../components/swiper'
  import h5TitleMenu from '../components/m/h5_titleMenu'
  export default {
    name: "h5ClassifyList",
    data() {
      return {
        goods: [],
        navIcon: navIcon,
        banners: [],
        selectedBanner: {},
        kwOrMaterialId: '',
        topMenuShow: false,
        isLink: this.$route.params.type === 'link'
      }
    },
    created() {
      util.modeRem(false)
      this.kwOrMaterialId = this.$route.params.value
      let materialId = this.isLink ? this.kwOrMaterialId : server.HHJX_M_ID
      // 查询同等级菜单
      server.getSiblingsNavList(materialId).then(res => {
        for (let cm of res.data) {
          if (cm.seq !== -1) {
            let banner = {
              id: cm.id,
              seq: cm.seq,
              text: cm.title,
              type: this.$route.params.type,
              value: this.isLink ? cm.materialId : cm.title,
            }
            if (banner.value === this.kwOrMaterialId) {
              this.selectedBanner = banner
            }
            this.banners.push(banner)
          }
        }
      })
      server.getGoods(this.isLink, this.kwOrMaterialId, 1, 40).then(res=>this.goods=res.data)
    },
    methods: {
      bannerChange(banner) {
        this.selectedBanner = banner
      }
    },
    components: {
      mySwiper,h5TitleMenu
    },
    filters: {
      volumeFormat: util.volumeFormat
    }
  }
</script>

<style scoped>
  @import '../../static/css/h5_classify_list.css';
</style>
