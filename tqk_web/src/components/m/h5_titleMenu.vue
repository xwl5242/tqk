<template>
  <div>
    <div class="arrow" :style="arrowStyle"></div>
    <div class="detail-menu-content" :style="mainStyle">
      <ul class="main-detail">
        <li><a @click.prevent="$router.push('/')"><i class="iconfont icon-detail_home"></i>首页</a></li>
        <li><a @click.prevent="$router.push('/search')"><i class="iconfont icon-detail_search"></i>搜索</a></li>
        <li><a @click.prevent="$router.push('/mine')"><i class="iconfont icon-gerenzhongxin"></i>我的</a></li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: "h5TitleMenu",
    props: {
      arrowStyle: String,
      mainStyle: String
    }
  }
</script>

<style scoped>
@import "../../../static/css/h5_iconfont.css";
.icon-detail_home:before {
  content: "\e630"
}

.icon-detail_search:before {
  content: "\e631"
}

.icon-gerenzhongxin:before {
  content: "\e653"
}
.detail-menu-content {
  width: 120px;
  border-radius: 6px;
  overflow: hidden;
  position: absolute;
  z-index: 2;
  top: 7px;
  right: 0px;
  background: rgba(51,51,51,.9)
}

.detail-menu-content .main-detail {
  overflow: hidden
}

.detail-menu-content .main-detail li {
  border-bottom: solid 1px rgba(102,102,102,.9);
  color: #fff;
  font-size: 14px;
  margin: 0 .5rem;
  line-height: 44px;
  text-align: center  ;
}

.detail-menu-content .main-detail li .iconfontv2 {
  margin: 0 10px 0 25px
}

.detail-menu-content .main-detail li a {
  display: block;
  color: #fff;
  font-size: 14px
}

.detail-menu-content .main-detail li:nth-child(4n) {
  border-right: none
}
.arrow {
  width: 0;
  height: 0;
  border-left: 7px transparent solid;
  border-right: 7px transparent solid;
  border-bottom: 7px rgba(51,51,51,.9) solid;
  border-top: none;
  position: absolute;
  right: 20px;
  top: 0;
  z-index: 9999;
  -webkit-transform: translateY(0) translateX(100%);
  transform: translateY(0) translateX(100%)
}
i{
  margin: 0 .5rem;
}
</style>
