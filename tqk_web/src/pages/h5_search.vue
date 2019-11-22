<template>
  <div>
    <div class="header_pr header_red search_header_new" style="transform: translateY(0px) translateX(0px); transition: all 0.4s ease 0s;">
      <!-- active -->
      <div class="search_term active">
        <div class="cent">
          <div class="search_hot_hostab">
            <div class="hr"></div>
            <dl class="search_hot_step">
              <dt class=" col-mar">三步轻松获得优惠券</dt>
              <dd class="row-s">
                <div class="col-12-4">
                  <i class="iconfont icon-taobaoicon"></i>
                  <p>1.进入淘宝</p>
                </div>
                <div class="col-12-4">
                  <i class="iconfont icon-fuzhi"></i>
                  <p>2.复制商品标题</p>
                </div>
                <div class="col-12-4">
                  <i class="iconfont icon-youhuiqua"></i>
                  <p>3.点击搜索查询</p>
                </div>
              </dd>
            </dl>
            <div class="hr"></div>
            <dl class="search_hot_act col-mar ">
              <dt>热门搜索</dt>
              <dd>
                <a data-key="三只松鼠" href="/index.php?r=l&amp;kw=%E4%B8%89%E5%8F%AA%E6%9D%BE%E9%BC%A0&amp;hot=1">三只松鼠</a>
                <a data-key="加绒睡衣" href="/index.php?r=l&amp;kw=%E5%8A%A0%E7%BB%92%E7%9D%A1%E8%A1%A3&amp;hot=2">加绒睡衣</a>
                <a data-key="休闲零食" href="/index.php?r=l&amp;kw=%E4%BC%91%E9%97%B2%E9%9B%B6%E9%A3%9F&amp;hot=3">休闲零食</a>
                <a data-key="不锈钢保温杯" href="/index.php?r=l&amp;kw=%E4%B8%8D%E9%94%88%E9%92%A2%E4%BF%9D%E6%B8%A9%E6%9D%AF&amp;hot=4">不锈钢保温杯</a>
                <a data-key="蓝牙耳机" href="/index.php?r=l&amp;kw=%E8%93%9D%E7%89%99%E8%80%B3%E6%9C%BA&amp;hot=5">蓝牙耳机</a>
                <a data-key="保湿润唇膏" href="/index.php?r=l&amp;kw=%E4%BF%9D%E6%B9%BF%E6%B6%A6%E5%94%87%E8%86%8F&amp;hot=6">保湿润唇膏</a>
                <a data-key="充电宝" href="/index.php?r=l&amp;kw=%E5%85%85%E7%94%B5%E5%AE%9D&amp;hot=7">充电宝</a>
                <a data-key="疯抢榜" href="/index.php?r=p&amp;hot=8">
                  疯抢榜				                         					                                <span>栏目</span>
                </a>
                <a data-key="品牌特卖" href="/index.php?r=index/brand&amp;hot=9">
                  品牌特卖				                         					                                <span>栏目</span>
                </a>
              </dd>
            </dl>
            <dl class="search_hot_act col-mar search_history"></dl>
          </div>
        </div>
      </div>
      <header class="icon_header ">
        <a class="iconfont icon-zuojiantou" @click.prevent="$router.push('/index')" style="color: #000;font-size: 2.4rem"></a>
        <div class="search">
          <!-- active -->
          <div class="icon_search result active">
            <i class="iconfont icon-search search_detail"></i>
            <div class="inputtext">
              <input type="text" name="kw" @click.prevent="searched=false" placeholder="输入商品名或粘贴宝贝标题搜索">
              <i class="iconfont icon-closecircle closekw" style="display: none;"></i>
            </div>
            <input v-show="!searched" @click.prevent="searched=!searched" class="sbumit" value="搜索">
            <a v-show="searched" class="set_tpl">
              <i class="iconfont icon-liebiao"></i>
            </a>
          </div>
        </div>
      </header>
    </div>
    <div class="header_bg search_bg"></div>
    <div class="layout search_layout">
      <div class="search_tav_main" style="transform: translateY(0px) translateX(0px); transition: all 0.4s ease 0s;">
        <div class="search_tab_nav row-s">
          <a data-dtk-satc="{desc:'人气',name:'SearchSortEvent'}" hrefs="/index.php?r=index/classify&amp;kw=&amp;px=renqi" class="col-12-3 text-center active" href="/index.php?r=index/classify&amp;kw=&amp;px=renqi&amp;row=1">人气</a>
          <a data-dtk-satc="{desc:'销量',name:'SearchSortEvent'}" hrefs="/index.php?r=index/classify&amp;kw=&amp;px=xiaoliang" class="col-12-3 text-center " href="/index.php?r=index/classify&amp;kw=&amp;px=xiaoliang&amp;row=1">销量</a>
          <a data-dtk-satc="{desc:'价格',name:'SearchSortEvent'}" hrefs="/index.php?r=index/classify&amp;kw=&amp;px=price" class="col-12-3 text-center " href="/index.php?r=index/classify&amp;kw=&amp;px=price&amp;row=1">价格
            <img class="price_s" style="display: none" src="https://cmsstatic.ffquan.cn//wap_new/search/images/asce.svg?v=201908292038">
            <img class="price_h" style="display: none" src="https://cmsstatic.ffquan.cn//wap_new/search/images/desc.svg?v=201908292038">
            <img class="price_def" style="display: inline" src="https://cmsstatic.ffquan.cn//wap_new/search/images/sort.svg?v=201908292038">
          </a>
          <div class="col-12-3 search_checkbox" style="transform: translateY(0px) translateX(0px); transition: all 0.4s ease 0s;">
            <label for="has_quan" class="checkbox text-center">
              <input id="has_quan" type="checkbox" name="has_quan" value="1">优惠券
            </label>
          </div>
        </div>
      </div>
      <div id="goods-item" class="row-s goods-two"><div class="search_defmsg"></div><div class="noproduct">没有找到相关商品</div></div>
      <input type="hidden" name="button_bnt" value="">
    </div>
  </div>
</template>

<script>
  import * as util from '../api/util'
  export default {
    name: "h5Search",
    data() {
      return {
        searched: false
      }
    },
    created() {
      util.modeRem(false)
    }
  }
</script>

<style scoped>

</style>
