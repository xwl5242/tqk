<!-- 商品详情页面 -->
<template>
  <div id="dtk_mian">
    <div class="year-bg">
      <div class="detail_new">
        <div class="top-block">
          <!--当前位置导航end-->
          <div class="detail-wrap main-container">
            <!--大图显示start-->
            <div class="detail-row clearfix">
              <div class="img-block">
                <a rel="nofollow" target="_blank" class="img  shop_link">
                  <img class="lg-img" :src="pictUrlTemp" :alt="curGood.title">
                  <ul class="sm-img">
                    <li><img :src="pictUrl"></li>
                    <li v-for="smallImage in curGood.smallImages" :key="smallImage"
                        @mouseover="autoShowImg(smallImage)" @mouseout="autoShowImg(pictUrl)">
                      <img :src="smallImage">
                    </li>
                  </ul>
                </a>
              </div>
              <!--大图显示end-->
              <div class="detail-col">
                <a class="title clearfix shop_link" rel="nofollow" :href="curGood.clickUrl" target="_blank">
                  <span :class="curGood.userType==0?'taobao':'tmall'"></span>
                  <span class="title">{{curGood.title}}</span>
                </a>
                <div class="active-type">
                  <div class="act-bg">
                    <!--淘抢购-->
                    <span class="tit fl"></span>
                    <span class="des"></span>
                    <!--疯抢榜文案-->
                    <div class="rank fr"><span class="rank-num">近24小时， <i></i>人已抢</span> <span class="rank-list">疯抢排名 <i>No.<b></b></i></span></div>
                    <!--淘抢购 聚划算 咚咚呛文案-->
                    <div class="last-time tjd fr"><span>距离结束仅剩</span> <i class="h">00</i> : <i class="m">00</i> : <i class="s">00</i></div>
                    <!--3.8节文案-->
                    <div class="last-time women fr"><span class="soe"></span> <i class="h">00</i> : <i class="m">00</i> : <i class="s">00</i></div>
                    <!--618-->
                    <div class="last-time activity_3 fr"></div>
                    <div class="last-time activity_4 fr"></div>
                    <!--99大促预告-->
                    <div class="promotion last-time">
                      <span class="dj_text" style="display:none">定金<b class="dj"></b>元<span style="display:none">立减<b class="lj">0</b>元</span></span>
                      <span class="fr lq-text">领券再减</span>
                      <span class="fr time-text">距离结束仅剩 <i class="h">00</i> : <i class="m">00</i> : <i class="s">00</i></span>
                    </div>
                    <!--99大促正场-->
                    <div class="promotion_start last-time">
                      <span class="fr time-text-start">距离结束仅剩 <i class="h">00</i> : <i class="m">00</i> : <i class="s">00</i></span>
                    </div>
                  </div>
                </div>
                <!--上新时间  累计销量-->
                <div class="time-num">
                  <span class="time">优惠开始时间：{{curGood.couponStartTime}}</span>
                </div>
                <div class="time-num">
                  <span class="time">优惠结束时间：{{curGood.couponEndTime}}</span>
                </div>
                <!--独家优惠-->
                <div class="time-num">
                  <span class="time">30天累计销量：<i style="color:#FF2E54;font-weight: 800;">{{curGood.volume}}</i> 件</span>
                </div>

                <!--商品标签-->
                <div class="goods-label">
                  独家巨献优惠：
                  <span class="coupon"><span class="quan">{{curGood.couponAmount}}元券</span></span>
<!--                  <span class="label">包邮</span><span class="label">运费险</span>-->
                </div>
                <div class="price">
                  ¥ <i>{{curGood.zkFinalPrice}}</i> <b>¥<i>{{curGood.reservePrice}}</i></b>
                  <div class="my-buy-btn">
                    <a class="buy shop_link" :href="curGood.couponShareUrl" target="_blank">领券购买</a>
                  </div>
                </div>
                <div class="buy-share">
<!--                  <button class="share fr"><i class="iconfont icon-fenxiang"></i> <span>分享</span>-->
                    <!--分享链接s-->
                    <!-- <div class="text2">
                        <div class="bdshare">
                            <div class="share-platform">
                                <div class="share-platform-r">
                                     <div class="bshare-custom bdsharebuttonbox bdshare-button-style1-32">
                                        <a title="分享到微信" class="bshare-weixin bds_weixin"></a>
                                        <a title="分享到新浪微博" class="bshare-sinaminiblog bds_tsina"></a>
                                        <a title="分享到QQ好友" class="bshare-qqim bds_sqq"></a>
                                        <a title="分享到QQ空间" class="bshare-qzone bds_qzone"></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> -->
                    <!--分享链接e-->
<!--                  </button>-->
                </div>
              </div>
              <div class="my-ercode">
                <img src="../../static/images/p/ercode.png">
                <div><p>关注公众号，不迷路</p></div>
              </div>
              <div class="goods-desc cf">
                <div class="tit">推荐理由</div>
                <div class="rec-text">
                  <span>{{curGood.itemDescription}}</span>
                </div>
                <div class="content">
                  <p>此款商品正在进行限时活动，原来{{curGood.userType==0?'淘宝':'天猫'}}售价{{curGood.zkFinalPrice}}元，
                    现有{{curGood.couponAmount}}元优惠券，到手仅需{{parseFloat(curGood.zkFinalPrice-curGood.couponAmount).toFixed(2)}}元，绝对超值，有需要可速度下单哦！</p>
                </div>
              </div>
            </div>

            <div class="shop-msg" v-if="curGood.shop">
              <div class="tit"><span>店铺信息</span></div>
              <div class="shop-block">
                <img class="shop-img fl" :src="curGood.shop.pictUrl" alt="" width="60" height="60">
                <div class="shop-name fl">
                  <p>{{curGood.shop.shopTitle}}</p>
                  <p class="iconfont icon-detail_tmall">{{curGood.shop.sellerNick}}</p>
                </div>
                <ul class="shop-score fr">
                  <li v-for="evaluate in curGood.evaluates" :key="evaluate.title">
                    <p>{{evaluate.title}}</p>
                    <span>{{evaluate.score}}
                    <i :class="'ico ' + (evaluate.level==0?'flat':(evaluate.level==1?'high':'low')) ">
                      {{evaluate.levelText}}
                    </i>
                    </span>
                  </li>
                </ul>
              </div>
            </div>
            <div class="goods-detail">
              <div class="tit"><span>商品详情</span></div>
              <img class="lazy img check_img_show" v-for="iimg in curGood.imgs" :key="iimg.split('@')[0]" :src="iimg.split('@')[1]"/>
            </div>
          </div>
          <div class="cf"></div>
        </div>
        <div style="font-family: SimSun;width:1200px;margin: 45px auto 30px;font-size: 12px;line-height: 26px;color: #aaa">
          <p style="color: #666;font-weight: bold;font-size: 14px;margin-bottom: 10px;font-family: '宋体';">内容声明：</p>
          <p class="void_border">
            本网站是一家中立的导购网站，网站中的商品信息均来自于互联网。如商品信息不同，可能是商品信息未及时更新引起，所有商品信息请以淘宝/天猫店铺内为准。网站提醒用户，购买商品前注意谨慎核实相关信息。如用户对商品/服务的标题 、价格、详情等任何信息有任何疑问的，请在购买前与商品所属店铺经营者沟通确认。网站存在海量商品信息，如用户发现商品中有任何违法/违规信息，请<a rel="nofollow" href="/index.php?r=index/feedbackpc&amp;id=23143588" style="color: #5384ff">立即反馈</a></p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import * as server from '../api'
import * as util from '../api/util'
export default {
  data() {
    return {
      curGood: {},
      pictUrl: '',
      pictUrlTemp: '',
      recommendGoods: [],
      showRecommendGoods: []
    }
  },
  created() {
    if(this.$route.params.id == undefined){
      server.getGoodDetail().then((res) => {
        this.prepareGood(res.data)
      })
    }else{
      let id = this.$route.params.id
      server.getGoodDetailById(id, true, false).then((res) => {
        this.prepareGood(res.data)
      })
    }
  },
  methods: {
    prepareGood(good) {
      this.curGood = good
      this.curGood.smallImages = util.str2Array(good.smallImages)
      this.pictUrl = this.pictUrlTemp = this.curGood.pictUrl
    },
    autoShowImg(imgUrl) {
      this.pictUrlTemp = imgUrl
    }
  }
}
</script>
<style>
  @import "../../static/css/detail.css";
  html body {
    background-color: #fff;
  }
  .goods-list ul li:hover {
    border: 1px solid #de366e;
  }
</style>
