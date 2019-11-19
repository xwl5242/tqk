<!--
轮播图组件Swiper(vue-awesome-swiper)
一、组件名称：mySwiper

二、配置参数:
  0.option: Swiper官方支持的所有属性
  1.swiperData: 轮播图展示的内容数据来源,type:Array
  2.containerCSS: 轮播图最外层容器(swiper-container)的自定义样式,type:String
  3.slideCSS: 轮播图每一个slide(swiper-slide)的自定义样式,type:String
  4.pagination: 轮播图分页器配置,Swiper官方所有的配置均可用,默认不使用分页器
    组件默认参数:
      a.isShow: 是否启用分页器,type:Boolean
      b.el: 分页器容器的css选择器或HTML标签,type:String,default:'.swiper-pagination'
      c.dynamicBullets: 动态分页器，当你的slide很多时，开启后，分页器小点的数量会部分隐藏,type:String
  5.navigation: 轮播图前进、后退按钮配置,Swiper官方所有的配置均可用,默认显示前进、后退按钮
    组件默认参数:
      a.isShow: 是否启用分页器,type:Boolean
      b.nextEl: 设置前进按钮的css选择器或HTML元素,type:String,default:'.swiper-button-next'
      c.prevEl: 设置后退按钮的css选择器或HTML元素,type:String,default:'.swiper-button-prev'
      d.myCSS: 前进、后退按钮自定义样式,type:String
      e.useType: 风格,目前只有两种:main和pl,默认使用main,后期可以扩展增加更多的样式,其实和myCSS功能一样,只是为了使用方便。
                 扩展更多的风格样式时,只需在<style></style>标签内部写具体的样式即可。
                 例如,新增的风格名称为 new,那只需添加.prev-new{...} | .next-new{...}一组样式即可使用。
  6.autoplay: 是否自动播放,默认true,间隔时间默认800ms
  7.loop: 是否循环,默认true
  8.slidesPerView: 设置slider容器能够同时显示的slides数量,默认0,就是只显示一个slide
  9.spaceBetween: 当slidesPerView大于1时,此参数有效slide之间设置距离(单位px),默认30px

三、轮播组件使用：
1.轮播内部元素使用插槽来实现,Swiper的每一个Slide中的具体HTML元素是在父组件中定义排版的(样式也在父组件中).
2.在使用该组件的父组件对应的<mySwiper></mySwiper>中定义HTML元素
3.如要使用swiperData中的数据,需要在父组件中使用<template v-slot:default="slotProps"></template>标签包裹步骤2中自定义的HTML标签
4.父组件使用子组件的数据,子组件默认绑定 item 到插槽上,在父组件中只需 slotProps.item.属性 即可

四、使用示例：
<mySwiper :swiperData="..." :containerCSS="'height:300px;'" :slideCSS="'width:720px;'" :pagination="{isShow:true,}" :navigation="{useType:'pl'}" >
  <template v-slot:default="slotProps">
    <img :src="slotProps.item.img" :alt="slotProps.item.title">
  </template>
</mySwiper>
-->
<template>
  <div>
    <swiper :options="options" :style="containerCSS">
      <swiper-slide v-for="item in swiperData" :key="item.id" :style="slideCSS">
        <slot :item="item"></slot>
      </swiper-slide>
      <div v-if="pagination_.isShow" class="swiper-pagination" slot="pagination"></div>
      <div v-if="navigation_.isShow" :class="'swiper-button-prev prev-'+navigation_.useType" :style="navigation_.myCSS" slot="button-prev"></div>
      <div v-if="navigation_.isShow" :class="'swiper-button-next next-'+navigation_.useType" :style="navigation_.myCSS" slot="button-next"></div>
    </swiper>
  </div>
</template>
<script>
    export default {
      name: "mySwiper",
      data() {
        return {
          options: {},
          pagination_: {
            isShow: false,
            el: '.swiper-pagination',
            dynamicBullets: true
          },
          navigation_: {
            isShow: true,
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
            myCSS: '',
            useType: 'main'
          }
        }
      },
      props: {
        option: {
          type: Object,
          default: function(){
            return {}
          }
        },
        swiperData: Array,
        initialSlide: {
          type: Number,
          default: 0
        },
        autoplay: {
          type: Boolean,
          default: true
        },
        loop: {
          type: Boolean,
          default: true,
        },
        containerCSS: {
          type: String,
          default: ''
        },
        slideCSS: {
          type: String,
          default: ''
        },
        slidesPerView: {
          type: Number,
          default: 1
        },
        spaceBetween: {
          type: Number,
          default: 30
        },
        pagination: {
          type: Object,
          default: function (){
            return {}
          }
        },
        navigation: {
          type: Object,
          default: function (){
            return {}
          }
        }
      },
      created() {
        Object.assign(this.pagination_, this.pagination)
        Object.assign(this.navigation_, this.navigation)
        if(this.pagination_.isShow){
          this.options['pagination'] = {
            el: this.pagination_.el,
            dynamicBullets: this.pagination_.dynamicBullets
          }
        }
        if(this.navigation_.isShow){
          this.options['navigation'] = {
            nextEl: this.navigation_.nextEl,
            prevEl: this.navigation_.prevEl
          }
        }
        if(this.slidesPerView >= 1) {
          this.options['slidesPerView'] = this.slidesPerView
          this.options['spaceBetween'] = this.spaceBetween
        }
        if(this.autoplay){
          this.options['autoplay'] = {autoplay: {delay: 800, stopOnLastSlide: false}}
        }
        this.options['loop'] = this.loop
        this.options['initialSlide'] = this.initialSlide
        Object.assign(this.options, this.option)
      },
      methods: {
      }
    }
</script>
<style scoped>
  .next-main:hover, .prev-main:hover {
    opacity: .5;
  }
  .prev-main {
    left: 0;
    background: url(../../static/images/p/pre.png) center;
    background-size: 100% 100%;
  }
  .next-main {
    right: 0;
    background: url(../../static/images/p/next.png) center;
    background-size: 100% 100%;
  }
  .prev-pl {
    left: 0;
    width: 50px;
    height: 50px;
    background: url(../../static/images/p/pl_prev.png) center;
    background-size: 100% 100%;
  }
  .next-pl {
    right: 0;
    width: 50px;
    height: 50px;
    background: url(../../static/images/p/pl_next.png) center;
    background-size: 100% 100%;
  }
  .pre-ju {
    opacity: 0;
    display: block;
    background-size: 27px 44px;
    width: 40px;
    height: 40px;
    background: rgba(255,255,255,.3);
    border-radius: 0 100px 100px 0;
    outline: 0;
    left: -28px;
    font-size: 22px;
    z-index: 9;
    text-align: center;
    line-height: 40px;
  }
  .next-ju {
    opacity: 0;
    display: block;
    background-size: 27px 44px;
    width: 40px;
    height: 40px;
    background: rgba(255,255,255,.3);
    border-radius: 100px 0 0 100px;
    outline: 0;
    font-size: 22px;
    text-align: center;
    line-height: 40px;
    left: auto;
    right: -28px;
    z-index: 99999;
  }
</style>
