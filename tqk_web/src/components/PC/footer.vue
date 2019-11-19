<!--
页面底部footer功能组件
参数配置：
  1.menus：导航菜单的数据来源
使用示例：
  <tqkFooter :menus="menus" />
-->
<template>
  <div id="footer" class="com-footer" style="height: auto">
    <div class="footer-wrapper" style="padding: 45px 0 10px;">
      <ul>
        <li>网站导航</li>
        <li v-for="item in menus" :key="item.id">
          <a :href="item.menuUrl">{{item.menuName}}</a>
        </li>
      </ul>
      <p>券宠优惠券网是一家中立的导购网站，网站中的商品信息均来自于互联网。如商品信息不同，可能是商品信息未及时更新，所有商品信息请以天猫/淘宝店铺内为准。</p>
      <div class="text">
        <span>版权所有</span>
        <span class="ml">© CopyRight&nbsp;2019</span>
        <a href="" target="_blank" rel="nofollow">
          券宠优惠券网 http://www.quanchong.com </a>
        <a class="miitbeian ml" href="http://www.beian.miit.gov.cn" target="_blank" rel="nofollow">
          京ICP备11111111号 </a>
        <p class="ml" style="text-algin:center;margin: 15px auto;"></p>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "tqkFooter",
    props: {
      menus: Array
    }
  }
</script>

<style scoped>
  .com-footer {
    width: 100%;
    font-size: 12px;
    background: rgba(30,30,30,1);
    color: rgba(255,255,255,.6);
  }
  .com-footer a {
    color: rgba(255,255,255,.6);
  }
  .com-footer .footer-wrapper {
    width: 1200px;
    margin: 0 auto;
  }
  .com-footer .footer-wrapper ul {
    display: flex;
    width: 525px;
    margin: 0 auto;
  }
  .com-footer .footer-wrapper ul li {
    margin-right: 26px;
    list-style: none;
  }
  .com-footer .footer-wrapper ul li a {
    padding-right: 18px;
    border-right: 1px solid rgba(255,255,255,.6);
    color: rgba(255,255,255,.6);
  }
  .com-footer .footer-wrapper p {
    text-align: center;
    margin: 20px 0 15px;
  }
  .com-footer .footer-wrapper .text {
    text-align: center;
  }
  .com-footer .footer-wrapper .text .ml {
    margin-left: 10px;
  }
</style>
