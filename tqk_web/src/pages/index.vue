<!-- 首页 -->
<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div id="dtk_mian">
    <div class="year-bg">
      <div class="home-page">
        <div class="hp-head banner_con">
          <!-- 页面顶部左侧快速导航NAV -->
          <ul class="hph_1">
            <li class="attr_list">
              <dl v-for="item in navRootList" :key="item.id">
                <dt class="tit">
                  <img :class="'ico ' + item.icon"  />{{item.title}}
                </dt>
                <dd>
                  <a v-for="nav in sub_nav(item.id)" :key="nav.id"
                     :style="nav.hot=='1'?'color:#FE2E54':''" @click.prevent="quickSearch(nav)">{{nav.title}}</a>
                </dd>
              </dl>
            </li>
          </ul>
          <!-- 页面顶部中间轮播图 -->
          <div class="hph_2">
            <mySwiper :swiperData="officialActivity" :containerCSS="'height:300px;'" :slideCSS="'width:720px;'" >
              <template v-slot:default="slotProps">
                <img :src="slotProps.item.officialActivityImg" :alt="slotProps.item.officialActivityTitle">
              </template>
            </mySwiper>
            <!--            中下三banner-->
            <div class="cb" id="cms_pc_swiper_down">
              <div class="swiper-container com_banner cb1" banner-id="cb1">
                <div class="swiper-wrapper">
                  <div class="swiper-slide">
                    <a href="/index.php?r=a/t&amp;i=b26a9a7b86dca90a" target="_blank"> <img style="border: 0" src="https://img.alicdn.com/imgextra/i3/2053469401/O1CN01f9BE162JJhwq9kyyZ_!!2053469401.jpg" alt="品牌优惠券,找优惠券,优惠券领取,购物" /> </a>
                  </div>
                </div>
              </div>
              <div class="swiper-container com_banner cb2" banner-id="cb2">
                <div class="swiper-wrapper">
                  <div class="swiper-slide">
                    <a href="/index.php?r=a/t&amp;i=7fb9739b66b566b1" target="_blank"> <img style="border: 0" src="https://img.alicdn.com/imgextra/i3/2053469401/O1CN01fs3Afm2JJhwmilLQy_!!2053469401.jpg" alt="限时秒杀,折扣,特卖,品牌特卖" /> </a>
                  </div>
                </div>
              </div>
              <div class="swiper-container com_banner cb3" banner-id="cb3">
                <div class="swiper-wrapper">
                  <div class="swiper-slide">
                    <a href="/index.php?r=a/t&amp;i=84833da712f9a7d3" target="_blank"> <img style="border: 0" src="https://img.alicdn.com/imgextra/i2/2053469401/O1CN01gbFecl2JJhuZA1ZjK-2053469401.png" alt="网购省钱,抢购,秒杀" /> </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 页面顶部右侧广告位 -->
          <div class="hph_3">
            <div class="banner-r">
              <div class="login">
                <a href="">
                  <img src="" class="round">
                </a>
                <p>欢迎，<a href="" class="c-main">游客</a></p>
                <a href="" @click.prevent="" class="btn btn-pink">立即登录</a>
              </div>
              <div class="jifen flexbox">
                <div class="txt">
                  <span class="title">直播数量</span>
                  <span>当前优惠<em>300</em>款</span>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="hp-topic">
          <span class="lb">通知<img src="../../static/images/p/lb.png" alt="通知" /></span> 我们的宗旨是方便网友们快速找到物美价廉的优惠券，为网友们提供更多优质的折扣商品。 网站的优惠券商品信息，每天更新。
        </div>
        <!-- 页面中间部分 -->
        <div class="hp_content">
          <!-- 大额优惠 -->
          <div class="hpc_l fl">
            <div class="cent ddq">
              <div class="tit">
                <p class="fl"><i></i> <span style="">大额优惠</span> <span style=""></span> </p>
                <span class="fl time">面额大  券折扣力度高</span>
                <a target="_blank" class="fr more" @click.prevent="showMore('9660')">查看更多</a>
                <span class="fr change" @click="deChange">换一换</span>
              </div>
              <div class="list_block">
                <a class="list" target="_blank" v-for="gg in deShowGoods" :key="gg.itemId" @click.prevent="showDetail(gg.id)">
                  <img class="img" :src="gg.pictUrl" :alt="gg.title" />
                  <div class="content fr">
                    <p class="co_tit">{{gg.title}}</p>
                    <div class="icon">
                      <span>巨力优惠，{{gg.couponAmount}}元券额</span>
                    </div>
                    <div class="price cf">
                      ￥<span>{{gg.zkFinalPrice}}</span><b>￥{{gg.reservePrice}}</b>
                    </div>
                  </div>
                </a>
              </div>
            </div>
            <!-- 热门优选 -->
            <div class="cent fq">
              <div class="tit">
                <p class="fl"><i></i> <span style="">热门优选</span> <span class="dec" style=""></span> </p>
                <div class="fl">
                  <span>优质商品 好券直播</span>
                </div>
                <div class="fr more" target="_blank" @click="showMore('3756')"> 查看更多 </div>
              </div>
              <div class="list_top">
                <a v-for="(rmyx, index) in rmyxGoods.slice(0,2)" :key="rmyx.id" @click.prevent="showDetail(rmyx.id)" class="list" target="_blank">
                  <img class="rank_img" :class="'top__' + (index+1)" />
                  <span class="center_img">
                    <img :src="rmyx.pictUrl" />
                  </span>
                  <div class="content">
                    <p>{{rmyx.title}}</p>
                    <span class="sale_num">近30天销量 <span class="num" style="color: #FF852F;">{{rmyx.volume|volumeFormat}}</span> 件</span>
                    <div>
                      <span class="price">￥<i>{{rmyx.zkFinalPrice}}</i></span>
                      <span class="quan fr"><i>{{rmyx.couponAmount}}</i>元券</span>
                    </div>
                  </div>
                </a>
              </div>
              <div class="list_bt">
                <a v-for="(rmyx, index) in rmyxGoods.slice(3)" :key="rmyx.id" @click.prevent="showDetail(rmyx.id)" class="list" target="_blank">
                  <div class="goods_img">
                    <span class="rank_num"><i>{{index+3}}</i></span>
                    <img :src="rmyx.pictUrl" />
                  </div> <p>{{rmyx.title}}</p> <span class="dec">近30天销量 <span class="num">{{rmyx.volume|volumeFormat}}</span> 件</span>
                  <div>
                    <span class="price">￥<i>{{rmyx.zkFinalPrice}}</i></span>
                    <span class="quan fr"><i>{{rmyx.couponAmount}}</i>元券</span>
                  </div>
                </a>
              </div>
            </div>
          </div>
          <!-- 今日大牌 -->
          <div class="hpc_r fr">
            <img class="top_img" src="../../static/images/p/jrdp_bg.jpg" />
            <img class="top_logo" :src="jrdpTop1Shop.pictUrl" />
            <p class="tit">
              <i></i>
              <span style="">今日大牌</span>
              <a class="dec" style="width: 70%;text-align: right;color: #777;" @click.prevent="showMore('3786')">查看更多</a>
            </p>
            <div class="desc">
              <p style="color: rgb(0, 0, 0);">{{jrdpTop1Shop.shopTitle}}</p>
              <span style="color: rgb(0, 0, 0);">爆款销量
                <a style="color: red;font-weight: 800;">{{jrdpTop1Shop.volume|volumeFormat}}</a>
              </span>
            </div>
            <!--           循环开始-->
            <div v-for="jrdp in jrdpGoods" :key="jrdp.id" class="list" >
              <a class="" target="_blank" @click.prevent="showDetail(jrdp.id)">
                <img :src="jrdp.pictUrl" class="img fl" />
                <div class="content fr">
                  <p class="co_tit">{{jrdp.title}}</p>
                  <div class="price">
                    <span class="fl"> ￥<span>{{jrdp.zkFinalPrice}}</span></span>
                    <b class="fl">￥{{jrdp.reservePrice}}</b>
                  </div>
                </div> </a>
            </div>
          </div>
        </div>
        <!-- 好货精选 -->
        <div class="hp_qlist">
          <div class="tit">
            <p class="fl"><i></i> <span style="">好货精选</span> <span class="dec" style="">实时更新 独享优惠券</span> </p>
            <!--            <span class="fr reduce">今日已为用户省钱<b>4,238,953</b> 元</span>-->
            <a target="_blank" class="fr more" style="color: #777;" @click.prevent="showMore('13366')">查看更多</a>
          </div>
          <div class="list_cent">
            <a target="_blank" v-for="good in jxGoods" :key="good.itemId" class="goods_list" @click.prevent="showDetail(good.id)">
              <div class="img">
                <img :src="good.pictUrl" :alt="good.title" />
              </div>
              <div class="content">
                <p class="co_tit"><span><i :class="'tag '+ (good.userType==0?'tag-tao':'tag-tmall') +' fl'"
                                           :title="good.userType==0?'淘宝':'天猫'"></i>{{good.title}}</span> </p>
                <div class="sale_num">
                  <span class="fl">原价 <i>{{good.reservePrice}}</i> </span>
                  <span class="fr">销量 <i>{{good.volume|volumeFormat}}</i></span>
                </div>
                <div class="price cf">
                  <i class="p">￥</i>
                  <span>{{good.zkFinalPrice}}</span>
                  <b class="fr"><i>{{good.couponAmount}}</i>元券</b>
                </div>
              </div>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import * as indexApi from '../api'
  import * as detailApi from '../api/detail'
  import mySwiper from '../components/swiper'
  export default {
    data() {
      return {
        // 官方活动(页面顶部中间轮播图)
        officialActivity: [],
        // 页面顶部左侧快速导航菜单的根菜单(大分类)
        navRootList: [],
        // 页面顶部左侧快速导航菜单
        navList: [],
        // 好货精选页签 默认查询40条记录
        jxGoods: [],
        // 大额优惠页签 "换一换" 的点击次数
        deChangeTimes: 0,
        // 大额优惠页签 默认查询的40条记录
        deGoods: [],
        // 大额优惠页签 一次只展示4条记录，点击 "换一换" 会从deGoods中获取4个新的记录
        deShowGoods: [],
        // 热门优选页签 默认查询8条记录，并且按销量(volume)倒序
        rmyxGoods: [],
        // 今日打牌页签
        jrdpGoods: [],
        jrdpTop1Shop: {}
      }
    },
    created() {
      // 查询官方活动
      indexApi.getOfficialActivity().then((res) => {
        this.officialActivity = res.data
      })
      // 查询页面顶部左侧的快速导航菜单,默认查询的为高佣的
      indexApi.getNavList().then((res) => {
        this.navList = res.data
        this.navRootList = res.data.filter((n) => {
          return n.parentId === -1
        })
      })
      // 查询 好货精选
      indexApi.getTBGoods(indexApi.HHJX_M_ID, 1, 40).then((res) => {
        this.jxGoods = res.data
      })
      // 查询 大额优惠
      indexApi.getTBGoods(indexApi.DEYH_M_ID, 1, 40).then((res) => {
        this.deGoods = res.data
        this.deShowGoods = this.deGoods.slice(0, 4)
      })
      // 查询 热门精选
      indexApi.getTBGoods(indexApi.RMYX_M_ID, 1, 8).then((res) => {
        this.rmyxGoods = res.data
      })
      // 查询 今日大牌
      indexApi.getTBGoods(indexApi.JRDP_M_ID, 1, 4).then((res) => {
        this.jrdpGoods = res.data
        detailApi.getGoodDetailById(res.data[0].id, false).then((res) => {
          this.jrdpTop1Shop = res.data.extraMap['shop']
          this.jrdpTop1Shop['volume'] = res.data.volume
        })
      })
    },
    methods: {
      sub_nav(pid) {
        return this.navList.filter((n) => {
          return n.parentId === pid
        })
      },
      quickSearch(nav) {
        // 页面顶部左侧快速导航菜单点击事件
        if (nav.type === 'link') {
          // link类型，直接跳转到 根据物料id查询
          this.$router.push({name: 'list', params: {type: 'link', value: nav.materialId}})
        } else {
          // kw类型，直接跳转到 根据关键词查询
          this.$router.push({name: 'list', params: {type: 'kw', value: nav.title}})
        }
      },
      deChange() {
        // 大额优惠页签 "换一换" 功能
        this.deChangeTimes = this.deChangeTimes < (this.deGoods.length / 4 - 1) ? this.deChangeTimes + 1 : 0
        this.deShowGoods = this.deGoods.slice(this.deChangeTimes * 4, (this.deChangeTimes + 1) * 4)
      },
      showDetail(id) {
        // 跳转到商品详情页
        this.$router.push({name: 'good_detail', params: {id: id}})
      },
      showMore(materialId) {
        this.$router.push({name: 'list', params: {type: 'link', value: materialId}})
      }
    },
    components: {
      mySwiper
    },
    filters: {
      volumeFormat: function(volume) {
        if(volume!==undefined){
          volume = parseInt(volume)
          return `${(volume/10000).toFixed(2)}万`
        }
      }
    }
  }
</script>
<style>
  @import "../../static/css/index.css";
</style>
