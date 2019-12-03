<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform" xmlns:v-clipboard="http://www.w3.org/1999/xhtml">
  <div>
    <div class="header_pr header_goods">
      <header class="icon_header">
        <a @click.prevent="$router.go(-1)" class="iconfont icon-zuojiantou" :style="showHeaderTitle?'color:#000;background:#fff;':''"></a>
        <div v-show="showHeaderTitle" class="title">
          <div class="row-s">
            <a href="#goodsInfo" :class="'col-12-4 text-center'+(selHeaderTitleIndex===0?' active':'')" @click="selHeaderTitleIndex=0">商品</a>
            <a href="#goodsImgs" :class="'col-12-4 text-center'+(selHeaderTitleIndex===1?' active':'')" @click="selHeaderTitleIndex=1">详情</a>
            <a href="#goodsRecs" :class="'col-12-4 text-center'+(selHeaderTitleIndex===2?' active':'')" @click="selHeaderTitleIndex=2">推荐</a>
          </div>
        </div>
        <a @click.prevent="showTitleMenu=!showTitleMenu" class="iconfont icon-gengduo" :style="showHeaderTitle?'color:#000;background:#fff;':''"></a>
        <div v-show="showTitleMenu" style="height:180px;">
          <h5TitleMenu :arrowStyle="'top:30px;right:30px;'" :mainStyle="'top:37px;'"/>
        </div>
      </header>
    </div>
    <!-- 页面底部分享、收藏|口令购买、领券购买 -->
    <div class="goods_shop_cart">
      <div class="cent row-s">
        <div class="col-12-2 text-center but">
          <a @click.prevent="goShare">
            <p class="img"><img src="../../static/images/m/detail_tab_share.png"></p>
            分享
          </a>
        </div>
        <div class="col-12-2 text-center but" style="position: relative; left: -.7rem;">
          <a><p class="img"><i class="iconfont icon-shoucang"></i></p>收藏</a>
        </div>
        <!-- 领券购买 淘口令 -->
        <div class="col-12-8">
          <input type="hidden" v-model="tpwd">
          <div class="btn btn-primary btn-block row-s">
            <a v-clipboard:copy="tpwd" v-clipboard:success="doCopy" class="col-12-5 active intergral_qx">口令购买</a>
            <a class="col-12-7 getGoodsLink" :href="curGood.couponClickUrl" target="_blank">领券购买</a>
          </div>
        </div>
      </div>
      <div class="goods_shop_cart_bg "></div>
    </div>

    <div class="layout row" id="goodsInfo">
      <div class="goods_swiper">
        <!-- 商品图片轮播 -->
        <mySwiper :swiperData="curGood.smallImages" :navigation="{isShow:false}">
          <template v-slot:default="slots">
            <img :src="slots.item">
          </template>
        </mySwiper>
      </div>
      <!--商品文字详情-->
      <div v-if="curGood" class="goods_info">
        <h1 class="col-mar">
<!--          <span></span>-->
          {{curGood.title}}
        </h1>
        <!-- 有券展示 -->
        <div class="info row-s col-mar">
          <div class="col-12-6 text-left col-money">
            折扣价 <span class=""><i>¥</i>
            <span v-text="curGood.zkFinalPrice"></span>
          </span>
          </div>
          <div class="col-12-6 text-right">
            已售<span class="col-red">{{curGood.volume|volumeFormat}}</span>件
          </div>
          <div class="col-12-6 text-left col-888">
            原价 ¥{{curGood.reservePrice}}
          </div>
          <div class="col-12-6 text-right auth">
            <span><i class="iconfont icon-detail_icon col-money"></i>包邮</span>
            <span><i class="iconfont icon-detail_icon col-money"></i>运费险</span>
          </div>
        </div>
        <div class="goods_quan row-s">
          <a class="row getGoodsLink">
            <div class="col-12-8 money">
              <p><span>{{curGood.couponAmount}}</span> 元优惠券</p>
              使用期限:{{curGood.couponStartTime|shortDateTime}} - {{curGood.couponEndTime|shortDateTime}}
            </div>
            <div class="col-12-4 name">
              <span>立即领券</span>
            </div>
          </a>
          <img src="../../static/images/m/goods_quan.png" alt="">
        </div>
        <div class="goods_desc col-mar col-888">
          {{curGood.itemDescription}}  {{curGood.couponInfo}}
        </div>
      </div>
      <div class="hr"></div>
      <!-- 商品店铺详情 -->
      <div v-if="curGood.shop">
        <div class="goods_shop">
          <a target="_blank" :href="curGood.shop.shopUrl">
            <div class="info col-mar">
              <img :src="curGood.shop.pictUrl" style="background: rgb(245, 245, 245); display: block;">
              <div class="text"><h3>{{curGood.shop.shopTitle}}</h3>
                <p class="col-main">{{curGood.shop.sellerNick}}</p>
                <p class="new">店铺所有优惠 <i class="iconfont icon-youjiantou"></i></p>
              </div>
            </div>
            <div class="tab row-s">
              <div v-for="evaluate in curGood.evaluates" :key="evaluate.title" class="col-12-4">
                {{evaluate.title}}:{{evaluate.score}}
                <span :class="'iconfont icon-point_'+(evaluate.level==0?'blance':(evaluate.level==1?'high':'low'))+' lv_'+(evaluate.level==0?'p':(evaluate.level==1?'g':'d'))"></span>
              </div>
            </div>
          </a>
        </div>
        <div class="hr"></div>
      </div>
      <!-- 商品相似推荐 -->
      <div v-if="curGood.recs" id="goodsRecs">
        <div class="goods_reco">
          <h3>相似推荐</h3>
          <div class="goods_reco_swiper">
            <mySwiper :swiperData="curGood.recs" :slidesPerView="3" :navigation="{isShow:false}" :autoplay="false">
              <template v-slot:default="slots">
                  <div class="swiper-cent">
                    <a class="img" rel="nofollow">
                      <img :src="slots.item.pictUrl">
                    </a>
                    <p class="name">{{slots.item.title}}</p>
                    <p class="quan"><span>{{slots.item.couponAmount}}元券</span></p>
                    <p class="money col-money">折扣价 <span>¥{{parseFloat(slots.item.zkFinalPrice).toFixed(2)}}</span></p>
                  </div>
              </template>
            </mySwiper>
          </div>
        </div>
        <div class="hr"></div>
      </div>
      <div class="ov_h" id="anchors_title"></div>
      <!-- 商品详情 -->
      <div id="goodsImgs">
        <div class="goods_reco">
          <h3>宝贝详情</h3>
          <div v-if="curGood.imgs" class="imglist">
            <img v-for="img in curGood.imgs" :key="img.split('@')[1]" :src="img.split('@')[1]" style="background: rgb(245, 245, 245); display: block;">
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as server from '../api'
  import * as util from '../api/util'
  import mySwiper from '../components/swiper'
  import h5TitleMenu from '../components/m/h5_titleMenu'
  export default {
    name: "h5Detail",
    data() {
      return {
        tpwd:'',
        curGood: {},
        showTitleMenu: false,
        showHeaderTitle: false,
        selHeaderTitleIndex: 0
      }
    },
    created() {
      util.modeRem(false)
      if(this.$route.params.id === undefined){
        server.getGoodDetail().then((res) => {
          this.prepareGood(res.data)
        })
      }else{
        let id = this.$route.params.id
        server.getGoodDetailById(id, true, true).then((res) => {
          this.prepareGood(res.data)
        })
      }
      window.addEventListener('scroll', this.handleScroll)
    },
    methods: {
      prepareGood(good) {
        server.getTpwd(good.title, 'https:'+good.couponClickUrl).then(res=>this.tpwd=res.data)
        this.curGood = good
        this.curGood.smallImages = util.str2Array(good.smallImages)
      },
      handleScroll() {
        let scrollTop = window.pageXOffset || document.documentElement.scrollTop || document.body.scrollTop
        this.showHeaderTitle = scrollTop >= 200
      },
      doCopy(e) {
        this.$layer.msg('淘口令已复制，打开【手机淘宝】即可领券购买')
      },
      goShare() {
        this.curGood.tpwd = this.tpwd
        localStorage.setItem('shareGood', JSON.stringify(this.curGood))
        this.$router.push('/share')
      }
    },
    filters: {
      volumeFormat: util.volumeFormat,
      shortDateTime: util.shortDateTime
    },
    components: {
      mySwiper: mySwiper,
      h5TitleMenu: h5TitleMenu
    }
  }
</script>

<style scoped>
@import '../../static/css/h5_detail.css';
</style>
