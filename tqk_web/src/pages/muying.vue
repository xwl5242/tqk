<!-- 母婴页面 -->
<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div id="dtk_mian">
    <div class="year-bg">
      <div class="ju_banner_tab w_1200">
        <h1 class="bt">
          <img src="" alt="母婴专场 每天都要来逛哦，超多优惠等着你">
        </h1>
        <div class="center">
          <h3>
            优惠榜 TOP40
          </h3>
          <div class="swiper_center">
            <mySwiper :swiperData="tops" :navigation="{useType:'pl'}" :slidesPerView="5">
              <template v-slot:default="slotProps">
                <div class="center_main">
                  <a class="pic" :href="slotProps.item.pcUrl" target="_blank">
                    <img :src="slotProps.item.picUrlForPC" alt="">
                  </a>
                  <div class="mouye">
                    <p class="fl">
                      <span>¥{{slotProps.item.actPrice}}</span>
                    </p>
                    <p class="fr">
                      差价 {{slotProps.item.dValue}}
                    </p>
                  </div>
                  <a class="text" href="">{{slotProps.item.title}}</a>
                </div>
              </template>
            </mySwiper>
          </div>
        </div>
      </div>
      <div class="ju_center w_1200">
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
    </div>
  </div>
</template>

<script>
  import * as juApi from '../../api/ju'
  import myPager from '../../components/www/pager'
  import mySwiper from '../../components/www/swiper'
  export default {
    name: "ju",
    data() {
      return {
        tops: [],
        goods: [],
        pageNo: 2,
      }
    },
    created() {
      juApi.getJuItemList(1, 40).then(res => this.tops = res.data)
      juApi.getJuItemList(this.pageNo, 40).then(res => this.goods = res.data)
    },
    methods: {
    },
    components: {
      myPager,
      mySwiper
    }
  }
</script>

<style scoped>
</style>
