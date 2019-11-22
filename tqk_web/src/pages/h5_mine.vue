<template>
  <div>
    <div class="layout user_center" user-integral-index="">
      <div class="user_center_info">
        <div class="else">
          <a class="more" href=""><img src="https://cmsstatic.ffquan.cn//wap_new/user/images/icon/user_icon_more.svg?v=201908292038" alt=""></a>
          <a class="set" href="/index.php?r=user/setuser"><img src="https://cmsstatic.ffquan.cn//wap_new/user/images/icon/user_icon_set.svg?v=201908292038" alt=""></a>
        </div>
        <div class="info">
          <div class="user_avatar">
            <img src="https://cmsstatic.ffquan.cn//wap_new/user/images/integral/user.png?v=201908292038" alt="">
          </div>
          <div class="user_type">
            <span id="user_login" class="user_login"><a href="/index.php?r=login/login">登录</a>/<a href="/index.php?r=login/register&amp;returnUrl=http%3A%2F%2Fwww.quandidi.com%2Findex.php%3Fr%3Duser%2Findex">注册</a></span>
            <span class="user_name"></span>
          </div>
          <!-- 签到 -->
          <div class="user_sign">
            <a href="javascript:void(0)">
              <img src="https://cmsstatic.ffquan.cn//wap_new/user/images/icon/user_icon_sign.svg?v=201908292038" alt="">
              <span id="sign">签到</span>
            </a>
            <div class="leakage_sign text-center">
              已漏签<span id="not-sign-days">2</span>天
              <i class="triangle"></i>
            </div>
          </div>
        </div>
        <!-- main -->
        <div class="user_main">
          <!-- 我的积分 -->
          <div class="my_integral_box">
            <div class="my_integral">
              <p>我的积分</p><img user-layer-get-dialog="" data-dialog="integralPlay" class="integral_explain" src="https://cmsstatic.ffquan.cn//wap_new/user/images/icon/integral-explain.svg?v=201908292038">
            </div>
            <div class="integral_store">
              <div class="integral_num">
                <a href="/index.php?r=intergral/interlist">
                  <span class="count">0</span>
                </a>
              </div>
              <div class="integral_btns">
                <button class="integral_extract_btn btn" style="display: none;">积分提现</button>
                <button class="btn integral_btn">积分商城</button>
              </div>
            </div>
            <div class="estimate_integral">
              <p class="text-center">本月预估积分&nbsp;<span id="monthIntegral">0</span></p>
              <i class="long"></i>
              <p class="text-center">今日预估积分&nbsp;<span id="todayIntegral">0</span></p>
            </div>
          </div>
          <!-- 我的 -->
          <div class="user_me">
            <div class="my_favorite"><a href="javascript:void(0)"><img src="https://cmsstatic.ffquan.cn//wap_new/user/images/integral/love.png?v=201908292038"><span>我的收藏</span></a></div>
            <div class="my_footprint"><a href="/index.php?r=user/footprint"><img src="https://cmsstatic.ffquan.cn//wap_new/user/images/integral/foot.png?v=201908292038"><span>我的足迹</span></a></div>
          </div>
          <!-- 帮你淘入口 -->
          <div class="common_tools card">
            <p class="title">常用工具</p>
            <ul class="content tools row-s">
              <a href="/index.php?r=user/couponhelp"><li class="col-12-3"><img src="https://cmsstatic.ffquan.cn//wap_new/user/images/integral/tools1.png?v=201908292038"><span>领券帮助</span></li></a>
              <a rel="nofollow" href="/index.php?r=index/feedback"><li class="col-12-3"><img src="https://cmsstatic.ffquan.cn//wap_new/user/images/integral/tools4.png?v=201908292038"><span>意见反馈</span></li></a>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as util from '../api/util'
  export default {
    name: "h5Mine",
    created() {
      util.modeRem(false)
    },
    components: {
    }
  }
</script>

<style scoped>
@import "../../static/css/h5_mine.css";
</style>
