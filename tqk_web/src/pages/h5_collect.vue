<template>
  <div>
    <div class="header_pr  header_red">
      <header class="icon_header">
        <a class="iconfont icon-zuojiantou" ui-back="" href="/index.php?r=user/index"></a>
        <div>
          我的收藏
        </div>
        <a class="text-a user_coll_edit_switch" style="display: none;">编辑</a>
      </header>
    </div>
    <div class="header_bg"></div>
    <div class="user_coll_bottom">
      <div class="row-s">
        <div class="col-12-8">
          <label class="col-888">
            <input type="checkbox" name="all">
            <i class="iconfont"></i> <span user-num="">全选 <i></i></span>
          </label>
        </div>
        <div class="col-12-4">
          <a class="btn btn-secondary btn-block removeCllectionItem">删除</a>
        </div>
      </div>
    </div>
    <div class="layout ">
      <div class="user_coll_def"><p class="img"></p>
        <div class="text">登录后添加收藏 <p class="msg">收藏云端同步，不错过任何优惠哦</p></div>
        <div class="but">
          <a class="btn btn-secondary btn-max" href="">登录/注册</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as util from '../api/util'
  export default {
    name: "h5Collect",
    created() {
      util.modeRem(false)
    }
  }
</script>

<style scoped>
@import "../../static/css/h5_mine.css";
</style>
