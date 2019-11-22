<template>
  <div>
    <!-- 页面顶部搜索框栏 -->
    <div class="header_pr banner-color">
      <header class="index_header banner-color">
        <a href="">
          <div class="wap_search" @click.prevent="$router.push('/search')">
            <span class="palaceholder">
              <i class="iconfont icon-search"></i>
              输入商品名或粘贴宝贝标题搜索
            </span>
          </div>
        </a>
        <div class="sigin" style="display: none;">
        </div>
        <div class="header-info">
          <a href="">
            <i class="iconfont icon-info"></i><span>消息</span>
            <span class="is_info"></span>
          </a>
        </div>
      </header>
    </div>
    <div class="header_bg banner-color"></div>
  </div>
</template>

<script>
  export default {
    name: "h5Header"
  }
</script>

<style scoped>
  .banner-color {
    background: #fe3a33;
    transition: background .5s
  }
  .header_pr {
    border-bottom: 0!important;
    background: rgba(0,0,0,0)
  }
  .index_header {
    background: rgba(0,0,0,0);
    height: .44rem;
    overflow: hidden;
    display: flex;
    margin: auto;
    align-items: center;
    box-sizing: border-box;
    padding-right: .1rem
  }

  .index_header>a {
    width: 100%
  }

  .index_header .wap_search {
    flex: 1;
    height: .32rem;
    background: #fff;
    border-radius: .36rem;
    margin-left: .09rem;
    display: flex;
    align-items: center
  }

  .index_header .wap_search span {
    height: .32rem;
    font-size: .13rem;
    font-family: PingFangSC-Regular;
    font-weight: 400;
    color: #999;
    line-height: .29rem;
    display: inline-block;
    padding-left: .12rem
  }

  .index_header .wap_search span i {
    font-size: .18rem;
    margin-right: .08rem;
    top: .02rem
  }

  .index_header .header-info a,.index_header .sigin {
    box-sizing: border-box;
    display: none;
    flex-direction: column;
    justify-content: center;
    margin-left: .1rem
  }

  .index_header .header-info a .sigin-gif,.index_header .sigin .sigin-gif {
    width: .32rem;
    height: .32rem;
    margin-top: -.05rem
  }

  .index_header .header-info a .iconfont,.index_header .sigin .iconfont {
    font-size: .2rem;
    color: #fff;
    text-align: center;
    position: relative;
    z-index: 1;
    zoom:1}

  .index_header .header-info a span,.index_header .sigin span {
    font-size: .1rem;
    font-family: PingFangSC-Regular;
    font-weight: 400;
    color: #fff;
    text-align: center;
    position: relative;
    z-index: 1;
    zoom:1;top: -3px
  }

  .index_header .header-info a {
    display: flex;
    width: .36rem;
    position: relative;
    left: .05rem;
    margin-left: 0
  }

  .index_header .header-info .is_info {
    width: .06rem;
    height: .06rem;
    background: #fe3a33;
    border: .01rem solid #fff;
    border-radius: 50%;
    position: absolute;
    zoom:1;left: .2rem;
    top: .01rem;
    z-index: 99;
    display: none
  }
  .new_index .header_bg {
    height: .44rem;
    width: 100%;
    margin: auto
  }
</style>
