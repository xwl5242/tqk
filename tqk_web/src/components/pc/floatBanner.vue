<!--
页面顶部浮动导航菜单组件，页面滑动到一定程度，该菜单导航显示
参数配置：
  1.isShow：是否显示，根据父组件的scroll监听事件来动态设置该值，实现该组件的显示和隐藏，默认为隐藏
使用示例：
  <floatBanner :isShow="floatNavShow" @search="search"/>
-->
<template>
  <div v-if="isShow" class="floatNav">
    <div class="w_1200">
      <ul class="fn-left">
        <li v-for="nav in navs" :key="nav.id" :class="nav.materialId===curFloatNav?'active':''">
          <a @click.prevent="goNav(nav.materialId)">{{nav.title}}</a>
        </li>
      </ul>
      <div class="fn-block">
        <input type="text" class="fn-src my-src-input" name="kw" v-model="keywords" placeholder="暖宝宝">
        <span class="src-close-btn"></span>
        <button type="button" class="srcBtn" @click="search"></button>
      </div>
    </div>
  </div>
</template>

<script>
  import * as server from '../../api'
  export default {
    name: "floatBanner",
    data() {
      return {
        navs: [],
        keywords: '',
        curFloatNav: ''
      }
    },
    props: {
      isShow: {
        type: Boolean,
        default: false
      }
    },
    created() {
      server.getCouponMaterialByPid(2).then(res => this.navs = res.data)
    },
    methods: {
      goNav(materialId) {
        localStorage.setItem('curFloatNav', materialId)
        this.$router.push({name: 'list', params: {type: 'link', value: materialId}})
      },
      search() {
        this.$emit('search', this.keywords)
      }
    },
    updated() {
      let cur = localStorage.getItem('curFloatNav')
      this.curFloatNav = cur? cur: '13366'
    }
  }
</script>

<style scoped>
  .floatNav {
    width: 100%;
    height: 50px;
    position: fixed;
    top: 0;
    z-index: 9999;
    min-width: 1200px;
    background-color: #fff;
    box-shadow: 0 2px 4px 0 rgba(170,170,170,.2);
  }
  .floatNav .w_1200 {
    max-width: 1200px;
    position: relative;
    z-index: 0;
    margin: auto;
  }
  .floatNav .w_1200 .fn-left, .floatNav .w_1200 .fn-left li {
    float: left;
  }
  .floatNav .w_1200 .fn-left li.active a {
    border-color: #FF435E;
    color: #FF435E;
  }
  .floatNav .w_1200 .fn-left li a {
    display: block;
    height: 48px;
    line-height: 50px;
    border-bottom: solid 2px #fff;
    color: #666;
    font-size: 14px;
    text-align: center;
    padding: 0 5px;
    margin-right: 5px;
  }
  .fn-block {
    float: right;
    width: 368px;
    height: 30px;
    border: 1px solid #FE2E54;
    top: 9px;
    position: relative;
    border-radius: 20px;
  }
  .floatNav .w_1200 .fn-src {
    border-radius: 20px;
  }
  .fn-src {
    width: 268px;
    height: 30px;
    border: none;
    display: block;
    font-size: 12px;
    font-family: 'microsoft yahei';
    color: #aaa;
    padding-left: 15px;
  }
  .floatNav .w_1200 .srcBtn {
    border-radius: 0 20px 20px 0;
  }
  .srcBtn {
    width: 50px;
    height: 32px;
    background-color: #FE2E54;
    background-repeat: no-repeat;
    border: none;
    right: -1px;
    top: -1px;
    color: #fff;
    position: absolute;
    background-image: url(../../../static/images/p/srcicos.png);
    background-size: auto 60%;
    background-position: center;
  }
  .src-close-btn {
    display: none;
    right: 55px;
    top: 3px;
    /*background-image: url(../../static/images/cms-img.png);*/
    background-position: -107px 7px;
    background-size: 287px 800px;
    width: 28px;
    height: 28px;
    z-index: 9999;
    cursor: pointer;
  }
</style>
