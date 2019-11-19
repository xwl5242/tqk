<!--
页面顶部搜索功能组件
参数配置：
  无
使用示例：
  <tqkHeader @search="search" />
-->
<template>
  <div class="headNav">
    <div id="header">
      <a class="header_logo">券宠优惠券网</a>
      <div id="showList">
        <div id="search">
          <input type="text" v-model="keywords" placeholder="棉拖鞋">
          <span class="src-close-btn"></span>
          <button @click="search">搜 索</button>
          <div class="rec_word">
            <a v-for="item in searchRecommends" @click="quickSearch(item.keyWord)"
               :key="item.id" :style="item.hot=='1'?'color:red;':''">
              {{item.keyWord}}
            </a>
          </div>
        </div>
      </div>
      <img class="head_group fr" src="https://cmsstatic.ffquan.cn//images/home/headgrup.png" alt="">
    </div>
  </div>
</template>

<script>
import {get} from '../../api/common'
export default {
  name: "tqkHeader",
  data() {
    return {
      keywords: '',
      searchRecommends: []
    }
  },
  created() {
    get('/search/recommend/list').then(res => this.searchRecommends = res.data)
  },
  methods: {
    quickSearch(kw) {
      this.$router.push({name: 'list', params: {type: 'kw', value: kw}})
    },
    search() {
      this.$emit('search', this.keywords)
    }
  }
}
</script>

<style scoped>
  .headNav {
    width: 100%;
    background-color: #fff;
  }
  .headNav #header {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    width: 1200px;
    height: 105px;
    margin: 0 auto;
    padding: 6px 0;
  }
  .headNav #header #showList {
    float: none;
    width: auto;
    display: inline-block;
  }
  .headNav #header #showList #search {
    width: 570px;
    height: 40px;
    position: relative;
    float: left;
    margin-top: 15px;
    margin-right: 40px;
  }
  .headNav #header #showList #search input {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    padding: 0 40px 0 15px;
    width: 470px;
    height: 40px;
    float: left;
    font-size: 14px;
    border: 2px solid #FE2E54;
  }
  .headNav #header #showList #search button {
    width: 100px;
    height: 40px;
    overflow: hidden;
    padding: 7px 23px;
    border-radius: 0 2px 2px 0;
    float: right;
    display: inline-block;
    border: 0;
    color: #fff;
    letter-spacing: 2px;
    font-size: 16px;
    line-height: 21px;
    cursor: pointer;
    background-color: #FE2E54;
  }
  .src-close-btn {
    top: 5px;
    right: 110px;
    z-index: 9999;
    display: none;
    width: 28px;
    height: 28px;
    /*background-image: url(/web/images/cms-img.png);*/
    background-position: -107px 7px;
    background-size: 287px 800px;
    cursor: pointer;
  }
  .header_logo {
    float: left;
    color: #ff0060;
    display: inline-block;
    margin: 4px 10px 0 0;
    width: 320px;
    height: 90px;
    text-align: left;
    font-size: 36px;
    line-height: 80px;
  }
  .head_group {
    position: relative;
    top: 14px;
    margin-right: 20px;
    height: 60px;
  }
  .fr {
    float: right;
  }
  .rec_word {
    position: relative;
    top: 4px;
    overflow: hidden;
    width: 500px;
    height: 20px;
  }
  .rec_word a {
    font-size: 12px;
    margin: 0 20px 0 0;
    color: #929292;
  }
</style>
