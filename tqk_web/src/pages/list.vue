<!-- 商品list页面 -->
<template>
  <div id="dtk_mian">
    <div class="year-bg">
      <img src="../../static/images/p/list_banner.png" width="526" height="84" alt="" class="list_banner ">
      <div class="list-content">
        <span class="list_top_bg"></span>
        <div class="list_top_block">
          <div class="link_block_fixed">
            <iconBanner @reIconBanner="reIconBanner" :type="reqType" :value="isKW?'13366':$route.params.value"/>
          </div>
          <div class="list_top_block">
            <p class="tit">
              <i class="iconfont icon-hot"></i>
              <img src="../../static/images/p/rqbk.png" width="135" height="28" alt="">
            </p>
            <div class="goods-list">
              <ul>
                <li v-for="(good, index) in tops" :class="index!=2?'top top_pre_2':'top'" :key="good.id">
                  <div class="rank"></div>
                  <a @click.pervent="showDetail(good)">
                    <div class="img-block">
                      <img class="img" :src="good.pictUrl" width="100%" alt="">
                    </div>
                    <div class="cont">
                      <p class="tit">{{good.title}}</p>
                      <span class="num">近30天疯抢 <span>{{good.volume}}</span> 件</span>
                      <div class="line-block" title="">
                        <div class="line"><em style="width: 22.0959%;"></em></div>
                      </div>
                      <div class="wrap">
                        <div class="price fl">
                          <b>券后</b> ¥ <span>{{(good.zkFinalPrice-good.couponAmount).toFixed(2)}}</span>
                          <i><b>¥</b> {{good.zkFinalPrice}}</i>
                        </div>
                        <div class="ico fr">
                          <i class="ju tag_tit"></i>
                          <i :class="good.userType==1?'tmall tag_tit':'tao tag_tit'"></i>
                        </div>
                      </div>
                    </div>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="goods-list clearfix">
          <ul class="">
            <!-- 商品list -->
            <li v-for="(good, index) in goods" :key="good.itemId" :class="(index+1)%5!=0?'goods-list-pres':''">
              <div class="rank"></div>
              <a @click.prevent="showDetail(good)" target="_blank">
                <div class="img-block">
                  <img class="img" :src="good.pictUrl" width="100%" alt="">
                </div>
                <div class="cont">
                  <p class="tit">{{good.title}}</p>
                  <span class="num">近30天疯抢 <span>{{good.volume}}</span> 件</span>
                  <div class="line-block">
                    <div class="line"><em :style="'width: ' + good.couponRemainCount/good.couponTotalCount*100 + '%;'"
                                          :title="good.couponRemainCount/good.couponTotalCount*100 + '%'"></em></div>
                  </div>
                  <div class="wrap">
                    <div class="price fl">
                      <b>券后</b> ¥ <span>{{(good.zkFinalPrice-good.couponAmount).toFixed(2)}}</span>
                      <i><b>¥</b> {{good.zkFinalPrice}}</i>
                    </div>
                    <div class="ico fr">
                      <i :class="good.userType==1?'tmall tag_tit':'tao tag_tit'"></i>
                    </div>
                  </div>
                </div>
              </a>
            </li>
          </ul>
          <myPager :pageNum="300" :showTotal="true" @goPage="showPage"></myPager>
        </div>
      </div>
   </div>
  </div>
</template>

<script>
import * as listApi from '../api/list'
import myPager from '../components/pager'
import iconBanner from '../components/pc/iconBanner'
export default {
  name: "list",
  data() {
    return {
      tops: [],
      goods: [],
      curSubBanner:undefined,
      reqType: this.$route.params.type,
      isKW: this.$route.params.type === 'kw'
    }
  },
  created() {
    // 页面首次加载
    this.getItemList(1, this.curSubBanner, true)
  },
  methods: {
    getItemList(pageNo, subBanner, isFirst) {
      /**
       * 获取商品列表
       * pageNo 页码； first 第一次操作(设置top榜)
       */
      // 获取请求中的信息，关键字搜索时获取关键字，链接请求时获取物料id
      let isLink = false
      let kwOrMaterial = ''
      if(subBanner!==undefined){
        isLink = subBanner.type === 'link'
        kwOrMaterial = subBanner.objValue
      }else{
        isLink = !this.isKW
        kwOrMaterial = this.$route.params.value
      }
      // 调用服务，获取商品信息
      listApi.getItemList(isLink, kwOrMaterial, pageNo, 23, {'volume':'desc'}).then(res => {
        if(isFirst){
          this.tops = res.data.slice(0,3)
        }
        this.goods = res.data.slice(3)
      })
    },
    showDetail(good) {
      /**
       * 跳转到商品详情页
       * good 商品信息
       */
      // 需要发送post请求，requestBody为具体商品信息
      localStorage.setItem('search_good_obj', JSON.stringify(good))
      this.$router.push({name: 'good_detail'})
    },
    showPage(pn) {
      /**
       * 页面底部分页页码的点击事件
       * pageNo 页码
       */
      this.getItemList(pn, this.curSubBanner, false)
    },
    reIconBanner(clickedBanner) {
      this.getItemList(1, clickedBanner, true)
    }
  },
  components: {
    myPager: myPager,
    iconBanner: iconBanner
  }
}
</script>

<style scoped>
  @import "../../static/css/list.css";
</style>
