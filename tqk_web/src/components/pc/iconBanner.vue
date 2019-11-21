<!--
list页面内容中"图标导航"组件
参数配置：
  1.type: 点击图标后请求list页面的类型，两种：link和kw
  2.value: 点击图标后请求list页面携带的参数，请求类型为link时，value为物料id;请求类型为kw时，value为kw(关键字)
  3.initSelIndex: 初始化选中的图标

使用示例：
  <iconBanner :type="link" :value="13367"/>
-->
<template>
  <div class="top_link">
    <a @click.prevent="reBanner(banner)" v-for="banner in banners"
       :key="banner.id" :class="'link_list'+(bannerSel===banner.seq?' act':'')">
      <img :class="banner.iconClass" height="25" alt="">
      <p>{{banner.text}}</p>
    </a>
  </div>
</template>

<script>
  import {get} from '../../api/common'
  export default {
    name: "iconBanner",
    data() {
      return {
        banners: [{
          id: -100,
          seq: -100,
          type: 'kw',
          text: '母婴',
          objValue: '母婴',
          iconClass: 'sb_ico__my'
        }],
        bannerSel: 0
      }
    },
    props: {
      type: String,
      value: String
    },
    created() {
      get('/coupon/material/siblings?materialId='+this.value).then(res => {
        for(let cm of res.data){
          if(cm.seq !== -1){
            console.log(cm)
            this.banners.unshift({
              id: cm.id,
              seq: cm.seq,
              text: cm.title,
              type: this.type,
              objValue: this.type==='kw'? cm.title: this.value,
              iconClass: 'sb_ico__'+cm.seq
            })
            if(this.type === 'link' && this.value === cm.materialId) {
              this.bannerSel = cm.seq
            }
          }
        }
        this.banners.reverse()
        this.banners.push(this.banners.shift())
      })
    },
    methods: {
      reBanner(banner) {
        this.bannerSel = banner.seq
        this.$emit('reIconBanner', banner)
      }
    }
  }
</script>

<style scoped>
  .top_link {
    height: 80px;
    margin: 0 0 15px;
    padding-top: 30px;
    text-align: center;
  }
  .link_list.act, .link_list.act p {
    opacity: 1;
  }
  .link_list, .link_list p {
    opacity: .7;
  }
  .link_list {
    display: inline-block;
    position: relative;
    text-align: center;
    width: 70px;
  }
  a {
    position: relative;
    z-index: 9;
    color: #fff;
  }
  .link_list img {
    max-width: 26px;
  }
  .link_list.act p {
    opacity: 1;
  }
  .link_list.act:after {
    content: '';
    position: absolute;
    border: 2px solid #FFFD46;
    border-radius: 2px;
    width: 26px;
    left: 21px;
    bottom: -15px;
  }
  .sb_ico__0 {
    content:url(../../../static/images/p/topico__0.png);
  }
  .sb_ico__1 {
    content:url(../../../static/images/p/topico__1.png);
  }
  .sb_ico__2 {
    content:url(../../../static/images/p/topico__2.png);
  }
  .sb_ico__3 {
    content:url(../../../static/images/p/topico__3.png);
  }
  .sb_ico__4 {
    content:url(../../../static/images/p/topico__4.png);
  }
  .sb_ico__5 {
    content:url(../../../static/images/p/topico__5.png);
  }
  .sb_ico__6 {
    content:url(../../../static/images/p/topico__6.png);
  }
  .sb_ico__7 {
    content:url(../../../static/images/p/topico__7.png);
  }
  .sb_ico__8 {
    content:url(../../../static/images/p/topico__8.png);
  }
  .sb_ico__my {
    content:url(../../../static/images/p/topico__my.png);
  }
</style>
