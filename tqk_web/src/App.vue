<template>
  <div id="app">
    <div class="new_home">
      <tqkHeader @search="search" />
      <floatBanner :isShow="floatNavShow" @search="search"/>
      <tqkBanner :menus="menus"/>
      <router-view />
      <tqkFooter :menus="menus" />
    </div>
  </div>
</template>

<script>
import * as server from './api'
import tqkHeader from './components/pc/header'
import tqkBanner from './components/pc/banner'
import tqkFooter from './components/pc/footer'
import floatBanner from './components/pc/floatBanner'
export default {
  name: 'App',
  data() {
    return {
      menus: [],
      floatNavShow: false
    }
  },
  created() {
    server.getMenus(server.PLATFORM_PC).then(res => this.menus = res.data);
    window.addEventListener("scroll", this.handleScroll)
  },
  methods: {
    handleScroll() {
      let scrollTop = window.pageXOffset || document.documentElement.scrollTop || document.body.scrollTop;
      let offsetTop = document.querySelector('#baner').offsetTop;
      this.floatNavShow = scrollTop > offsetTop
    },
    search(keywords) {
      this.$router.push({name: 'list', params: {type: 'kw', value: keywords}})
    }
  },
  components: {
    tqkHeader: tqkHeader,
    tqkBanner: tqkBanner,
    tqkFooter: tqkFooter,
    floatBanner: floatBanner
  }
}
</script>

<style>
  blockquote,body,button,caption,dd,div,dl,dt,fieldset,figure,form,h1,h2,h3,h4,h5,h6,hr,html,input,legend,li,menu,ol,p,pre,table,td,textarea,th,ul {
    margin: 0;
    padding: 0
  }

  address,article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section {
    display: block
  }

  table {
    border-collapse: collapse;
    border-spacing: 0
  }

  caption,th {
    text-align: left;
    font-weight: 400
  }

  abbr,body,fieldset,html,iframe,img {
    border: 0
  }

  address,cite,dfn,em,i,var {
    font-style: normal
  }

  [hidefocus],summary {
    outline: 0
  }

  li {
    list-style: none
  }

  h1,h2,h3,h4,h5,h6,small {
    font-size: 100%
  }

  sub,sup {
    font-size: 83%
  }

  code,kbd,pre,samp {
    font-family: inherit
  }

  q:after,q:before {
    content: none
  }

  textarea {
    overflow: auto;
    resize: none
  }

  label,summary {
    cursor: default
  }

  a,button {
    cursor: pointer
  }

  b,em,h1,h2,h3,h4,h5,h6,strong {
    font-weight: 700
  }

  a,a:hover,del,ins,s,u {
    text-decoration: none
  }

  body,button,input,keygen,legend,select,textarea {
    font: 12px/1.14 arial,\5b8b\4f53;
    color: #333;
    outline: 0
  }

  body {
    background: #F6F6F6;
    min-width: 1200px;
    font: 14px/1.5 Helvetica,Hiragino Sans GB,Microsoft Yahei,sans-serif
  }

  a,a:hover {
    color: #333
  }
</style>
