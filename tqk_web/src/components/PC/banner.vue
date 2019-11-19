<!--
页面顶部导航功能组件
参数配置：
  1.menus：导航菜单的数据来源
使用示例：
  <tqkBanner :menus="menus"/>
-->
<template>
  <div id="baner" class="theme-bg-color-1">
    <div id="nav">
      <a @click.prevent="changeMenu(item.menuUrl, index)" v-for="(item,index) in menus" :key="item.id"
         :class="index===menuSel?'active':''">
        {{item.menuName}}
      </a>
    </div>
  </div>
</template>

<script>
  export default {
    name: "tqkBanner",
    data() {
      return {
        menuSel: 0
      }
    },
    props: {
      menus: Array
    },
    methods: {
      changeMenu(url, index) {
        this.menuSel = index
        this.$router.push(url)
      }
    }
  }
</script>

<style scoped>
  #baner {
    width: 100%;
    height: 35px;
    background-color: #FE2E54;
  }
  #baner #nav {
    width: 1200px;
    margin: 0 auto;
    position: relative;
  }
  #baner #nav a {
    color: #fff;
    height: 35px;
    line-height: 35px;
    display: block;
    float: left;
    width: auto;
    padding: 0 30px;
    position: relative;
  }
  #baner #nav a.iconM {
    text-align: center;
  }
  #baner #nav a.active {
    background-color: #E41E43;
  }
  #baner #nav a:hover {
    background-color: #E41E43;
  }
</style>
