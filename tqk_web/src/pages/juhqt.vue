<!-- 聚划算/海抢淘页面 -->
<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div id="dtk_mian">
    <div class="year-bg">
      <div class="ju_banner_tab w_1200">
        <div class="top_bg"></div>
        <div class="fix_bar">
          <div class="nav-block">
            <div class="scene_nav">
              <mySwiper :swiperData="times" :slidesPerView="8" :navigation="{isShow:false}"
                        :autoplay="false" :loop="false" :initialSlide="timeSel>12?timeSel-10:0" :containerCSS="'height:70px;'">
                <template v-slot:default="slotProps">
                  <a @click.prevent="timeSel=slotProps.item.hour">
                    <div :class="'times-slide'+(slotProps.item.hour==timeSel?' act':'')">
                      {{slotProps.item.text}}<p>{{slotProps.item.status}}</p>
                    </div>
                  </a>
                </template>
              </mySwiper>
            </div>
          </div>
        </div>
        <div class="top_header">整点限时抢</div>
        <div class="ju_center w_1200">
          <div class="sp_block"><i class="sp_block_ico left"></i> <i class="sp_block_ico right"></i>
            <div class="sp_bg"><img src="https://cmsstatic.ffquan.cn//web/images/every/typelogo.png" height="21" alt="">
            </div>
          </div>
          <div class="goods-list main-container new_comp">
            <ul class="clearfix" id="goodsItems">
              <li v-for="(good, index) in goods" :key="good.id" class="g_over" :style="(index+1)%5==0?'margin-right: 0px;':''">
                <a :href="good.pcUrl" class="img" target="_blank" data-gid="23291251">
                  <img :src="good.picUrlForPC" :alt="good.title"></a>
                <div class="goods-padding">
                  <div class="title">
                    <a target="_blank" :href="good.pcUrl">
                      {{good.title}}
                    </a>
                  </div>
                  <div class="goods-num-type">
                    <span class="old-price fl">聚享价 ¥<i>{{good.actPrice}}</i></span>
                    <span class="goods-num fr">¥<i>{{good.origPrice}}</i></span>
                  </div>
                  <div class="goods-num-type">
                    <span class="line-clamp2">{{good.uspDescList.slice(1).replace(']','')}}</span>
                  </div>
                </div>
              </li>
            </ul>
            <myPager :pagers="160"></myPager>
          </div>
        </div>
<!--          <h3>-->
<!--            优惠力度 TOP40 榜-->
<!--          </h3>-->
<!--          <div class="swiper_center">-->
<!--            <mySwiper :swiperData="tops" :navigation="{useType:'pl'}" :slidesPerView="5">-->
<!--              <template v-slot:default="slotProps">-->
<!--                <div class="center_main">-->
<!--                  <a class="pic" :href="slotProps.item.pcUrl" target="_blank">-->
<!--                    <img :src="slotProps.item.picUrlForPC" alt="">-->
<!--                  </a>-->
<!--                  <div class="mouye">-->
<!--                    <p class="fl">-->
<!--                      <span>¥{{slotProps.item.actPrice}}</span>-->
<!--                    </p>-->
<!--                    <p class="fr">-->
<!--                      差价 {{slotProps.item.dValue}}-->
<!--                    </p>-->
<!--                  </div>-->
<!--                  <a class="text" href="">{{slotProps.item.title}}</a>-->
<!--                </div>-->
<!--              </template>-->
<!--            </mySwiper>-->
<!--          </div>-->
      </div>
     </div>
  </div>
</template>

<script>
import * as juApi from '../../api/ju'
import * as util from '../../api/util'
import myPager from '../../components/www/pager'
import mySwiper from '../../components/www/swiper'
export default {
  name: "ju",
  data() {
    return {
      goods: [],
      pageNo: 1,
      times: [],
      timeSel: new Date().getHours()
    }
  },
  created() {
    juApi.getJuItemList(this.pageNo, 40).then(res => this.goods = res.data)
    this.times = this.timesCreator()
  },
  methods: {
    timesCreator() {
      let times = []
      for(let time of util.range(8, 22)){
        let date = new Date()
        let hour = date.getHours()
        let text = time.toString().padStart(2,'0')+':00场'
        let status = time<hour?'已经开抢':(time===hour?'正在抢购':'即将开始')
        times.push({'text':text,'status':status, 'hour':time})
      }
      return times
    }
  },
  components: {
    myPager,
    mySwiper
  }
}
</script>

<style scoped>
</style>
