<template>
  <div>
    <div class="header_pr header_goods">
      <header class="icon_header">
        <a class="iconfont icon-zuojiantou" style="background: rgba(0, 0, 0, 0.14); color: rgb(119, 119, 119);"></a>
        <div class="title" style="opacity: 0.533333;">
          <div class="row-s">
            <a class="col-12-4 text-center active">商品</a>
            <a class="col-12-4 text-center">详情</a>
            <a class="col-12-4 text-center">推荐</a>
          </div>
        </div>
        <a class="iconfont icon-gengduo" style="background: rgba(0, 0, 0, 0.14); color: rgb(119, 119, 119);"></a>
        <div class="h_newlit">
          <div>
            <a href="/index.php?r=index/wap"><i class="iconfont icon-detail_home"></i>首页</a>
            <a href="/index.php?r=index/classify"><i class="iconfont icon-detail_search"></i>搜索</a>
            <a href="/index.php?r=index/customService"><i class="iconfont icon-detail_servicer"></i>客服</a>
            <a rel="nofollow" href="/index.php?r=index/feedback&amp;id=23491751"><i
              class="iconfont icon-datail_feedback"></i>反馈</a>
            <a href="/index.php?r=user/index"><i class="iconfont icon-gerenzhongxin"></i>我的</a>
          </div>
        </div>
      </header>
    </div>
    <!-- 购物车快捷入口 必须加在layout上面 -->
    <div class="goods_shop_cart">
      <div class="cent row-s">
        <div class="col-12-2 text-center but">
          <a href="">
            <p class="img">
              <img src="https://cmsstatic.ffquan.cn//wap_new/main/images/detail_tab_share.svg?v=201908292038" alt="">
            </p>
            分享
          </a>
        </div>
        <div class="col-12-2 text-center but" style=" position: relative; left: -.7rem;">
          <a>
            <p class="img">
              <i class="iconfont icon-shoucang"></i>
            </p>
            收藏
          </a>
        </div>
        <!-- 领券购买 淘口令 -->
        <div class="col-12-8">
          <div class="btn btn-primary btn-block row-s">
            <a href="javascript:;" class="col-12-5 active intergral_qx">口令购买</a>
            <a ui-open-taobao="" class="col-12-7 getGoodsLink">领券购买</a>
          </div>
        </div>
        <div class="buy-wrapper-box">
          <div class="cent">
            <a href="javascript:;">
              <i class="iconfont icon-key"></i>通过口令分享</a>
            <a href="javascript:;">
              <i class="iconfont icon-pic"></i>通过图片分享</a>
            <textarea style="display: none;" id="codeText">李子柒柳州螺蛳粉广西特产正宗螺丝粉*3包
【原价】34.7元
【券后】29.7元
【复制此信息打开[手-机-淘-宝]即可查看并下单】 $kouling$
【必买理由】【地道柳州味】原价77，聚划算34.7，优惠券5元，到手29.7。地道柳州螺蛳粉，8小时精炖汤底，征服挑剔味蕾，10分钟舌尖美食，酸辣鲜爽，一个人的豪华盛宴，丰盛满足！老人小孩都还爱吃呢~</textarea>
            <em></em>
          </div>
        </div>
      </div>
      <div class="goods_shop_cart_bg "></div>
    </div>
    <div class="layout row" id="anchors_info">
      <div class="goods_swiper">
        <div class="swiper-container swiper-container-horizontal opacity1" style="height: 375px; overflow: hidden;">
          <div class="swiper-wrapper" style="transition-duration: 0ms; transform: translate3d(-1875px, 0px, 0px);">
            <div class="swiper-slide" style="width: 375px;">
              <a class="intergral_qx" href="javascript:void(0);">
                <img class="swiper-lazy swiper-lazy-loaded" alt=""
                     src="https://img.alicdn.com/imgextra/i4/1969263872/O1CN01auvtJj1eTQ7lVTRKT_!!1969263872.jpg_480x480.jpg">
              </a>
            </div>
            <div class="swiper-slide" style="width: 375px;">
              <a class="intergral_qx" href="javascript:void(0);">
                <img class="swiper-lazy swiper-lazy-loaded" alt=""
                     src="https://img.alicdn.com/imgextra/i4/3965833216/O1CN01r0bEWx1ZcyJr3GLtS_!!3965833216.jpg_480x480.jpg">
              </a>
            </div>
            <div class="swiper-slide" style="width: 375px;">
              <a class="intergral_qx" href="javascript:void(0);">
                <img class="swiper-lazy swiper-lazy-loaded" alt=""
                     src="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01i9GaH21ZcyINu6lIs_!!3965833216.jpg_480x480.jpg">
              </a>
            </div>
            <div class="swiper-slide" style="width: 375px;">
              <a class="intergral_qx" ui-open-taobao="" href="javascript:void(0);">
                <img class="swiper-lazy swiper-lazy-loaded" alt=""
                     src="https://img.alicdn.com/imgextra/i4/3965833216/O1CN01w3DZg11ZcyIULNXyo_!!3965833216.jpg_480x480.jpg">
              </a>
            </div>
            <div class="swiper-slide swiper-slide-prev" style="width: 375px;">
              <a class="intergral_qx" ui-open-taobao="" href="javascript:void(0);">
                <img class="swiper-lazy swiper-lazy-loaded" alt=""
                     src="https://img.alicdn.com/imgextra/i1/3965833216/O1CN016X2Mdj1ZcyITp1xc5_!!3965833216.jpg_480x480.jpg">
              </a>
            </div>
            <div class="swiper-slide swiper-slide-active" style="width: 375px;">
              <a class="intergral_qx" ui-open-taobao="" href="javascript:void(0);">
                <img class="swiper-lazy swiper-lazy-loaded" alt=""
                     src="https://img.alicdn.com/imgextra/i1/3965833216/O1CN01c8ySUh1ZcyIJIZYf9_!!3965833216.jpg_480x480.jpg">
              </a>
            </div>
          </div>
          <div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets"><span
            class="swiper-pagination-bullet"></span><span class="swiper-pagination-bullet"></span><span
            class="swiper-pagination-bullet"></span><span class="swiper-pagination-bullet"></span><span
            class="swiper-pagination-bullet"></span><span
            class="swiper-pagination-bullet swiper-pagination-bullet-active"></span></div>
        </div>
      </div>
      <!--banner-->
      <div class="goods_activity hsj" style="background: #FFFFFF">
        <img src="https://img.alicdn.com/imgextra/i1/2053469401/TB2PmMEwLiSBuNkSnhJXXbDcpXa-2053469401.png" alt="">
        <p class="text" style="font-size:12px; color:#999999;">限时限量 疯狂抢购中</p>
        <div class="outTime">
          <span style="font-size:14px; color:#e61514;"> 距结束仅剩</span>
          <p>
            <b>16</b>:<b>50</b>:<b>57</b>.<b>7</b>
          </p>
        </div>
      </div>
      <!--banner-->
      <div class="goods_info">
        <h1 class="col-mar"><span>天猫</span>李子柒柳州螺蛳粉广西特产正宗螺丝粉*3包</h1>
        <!-- 有券展示 -->
        <div class="info row-s col-mar">
          <div class="col-12-6 text-left col-money">
            券后价 <span class=""><i>¥</i>29.7</span>
          </div>
          <div class="col-12-6 text-right">
            已售<span class="col-red">38.8万</span>件
          </div>
          <div class="col-12-6 text-left col-888">
            天猫价 ¥34.7
          </div>
          <div class="col-12-6 text-right auth">
            <span><i class="iconfont icon-detail_icon col-money"></i>包邮</span>
            <span><i class="iconfont icon-detail_icon col-money"></i>运费险</span>
          </div>
        </div>
        <div class="goods_quan row-s">
          <a class="row getGoodsLink" data-dtk-satc="{gid:'23491751',desc:'立即领券-大',name:'DetailGoodsEvent'}"
             ui-open-taobao="" data-money="5" data-id="23491751">
            <div class="col-12-8 money">
              <p><span>5</span> 元优惠券</p>
              使用期限:2019.11.21-2019.11.22
            </div>
            <div class="col-12-4 name">
              <span>立即领券</span>
            </div>
          </a>
          <img src="https://cmsstatic.ffquan.cn//wap_new/main/images/goods_quan.png?v=201908292038" alt="">
        </div>
        <div class="goods_desc col-mar col-888">
          【地道柳州味】原价77，聚划算34.7，优惠券5元，到手29.7。地道柳州螺蛳粉，8小时精炖汤底，征服挑剔味蕾，10分钟舌尖美食，酸辣鲜爽，一个人的豪华盛宴，丰盛满足！老人小孩都还爱吃呢~
        </div>
      </div>
      <div class="hr"></div>
      <div id="goodsShopShow">
        <div class="goods_shop"><a href="/index.php?r=p/shop&amp;seller_id=3965833216">
          <div class="info col-mar"><img ui-lazyload=""
                                         data-original="https://img.alicdn.com/imgextra//i3/3965833216/TB2Nrf6a56guuRkSnb4XXbu4XXa_!!3965833216.jpg_310x310.jpg"
                                         src="https://img.alicdn.com/imgextra//i3/3965833216/TB2Nrf6a56guuRkSnb4XXbu4XXa_!!3965833216.jpg_310x310.jpg"
                                         alt="" isload="true" style="background: rgb(245, 245, 245); display: block;">
            <div class="text"><h3>李子柒旗舰店</h3>
              <p class="col-main"><i class="iconfont icon-detail_tmall "></i></p>
              <p class="new">店铺所有优惠 <i class="iconfont icon-youjiantou"></i></p></div>
          </div>
          <div class="tab row-s">
            <div class="col-12-4">宝贝描述:4.9<span class="iconfont  icon-point_high lv_g"></span></div>
            <div class="col-12-4">卖家服务:4.9<span class="iconfont icon-point_high lv_g"></span></div>
            <div class="col-12-4">物流服务:4.8<span class="iconfont icon-point_high lv_g"></span></div>
          </div>
        </a></div>
        <div class="hr"></div>
      </div>
      <div id="goodsRecoShow">
        <div class="goods_reco">
          <h3>相似推荐</h3>
          <div class="goods_reco_swiper">
            <div class="swiper-container swiper-container-horizontal opacity1" ui-swiper=""
                 data-config="{slidesPerView : 'auto',initialSlide :0}">
              <div class="swiper-wrapper">
                <div class="swiper-slide swiper-slide-active">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23491751" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i4/1969263872/O1CN01auvtJj1eTQ7lVTRKT_!!1969263872.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">李子柒柳州螺蛳粉广西特产正宗螺丝粉*3包</p>
                    <p class="quan"><span>5元券</span></p>
                    <p class="money col-money">券后价 <span>¥29.7</span></p></div>
                </div>
                <div class="swiper-slide swiper-slide-next">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23506201" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i4/3718824608/O1CN01dF4on71juVddNNqY4_!!3718824608.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">正宗广西柳州螺蛳粉300g*5包</p>
                    <p class="quan"><span>3元券</span></p>
                    <p class="money col-money">券后价 <span>¥19.9</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23505954" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i3/2507301529/O1CN011pxVRa1NAK9hfB7p2_!!2507301529.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">白家陈记贵州特色花溪牛肉粉方便速食</p>
                    <p class="quan"><span>5元券</span></p>
                    <p class="money col-money">券后价 <span>¥29.9</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23504157" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i3/4084823779/O1CN01xhWVdC1dmpIME1QEy_!!0-item_pic.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">【老牌柳柳】广西正宗螺蛳粉300g*2袋</p>
                    <p class="quan"><span>5元券</span></p>
                    <p class="money col-money">券后价 <span>¥9.9</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23509521" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i2/1642693104/O1CN01vLSdOY1YnfxbVEL4v_!!1642693104.png_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">【好欢螺】广西螺蛳粉10袋礼盒装</p>
                    <p class="quan"><span>10元券</span></p>
                    <p class="money col-money">券后价 <span>¥106.1</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23505173" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i1/2709954877/O1CN01NUhn9Z1lti9YjrE0X_!!2709954877.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">【寻螺记】正宗柳州螺蛳粉300g*5包</p>
                    <p class="quan"><span>10元券</span></p>
                    <p class="money col-money">券后价 <span>¥24.9</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23482823" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/i4/2200674480175/O1CN01xtkOML1DABo9TtDKq_!!2200674480175.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">【好欢螺】柳州特产螺狮粉300g*3袋</p>
                    <p class="quan"><span>3元券</span></p>
                    <p class="money col-money">券后价 <span>¥31.83</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23492230" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i1/3243282961/O1CN01PhUXHQ1XkBJae3MiM_!!3243282961.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">土芒佳品柳州螺蛳粉正宗螺狮粉280*5包</p>
                    <p class="quan"><span>10元券</span></p>
                    <p class="money col-money">券后价 <span>¥24.9</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23509500" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i4/2982641050/O1CN01dHEboZ1JcwRPzfZsr_!!2982641050.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">嘻螺会柳州螺蛳粉广西特产4大桶装</p>
                    <p class="quan"><span>20元券</span></p>
                    <p class="money col-money">券后价 <span>¥23.8</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23509183" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i2/2200807433132/O1CN01J3e7Di1Z0V34rzRPK_!!2200807433132.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">柳州花甲香菇好欢螺螺蛳粉370g*3</p>
                    <p class="quan"><span>3元券</span></p>
                    <p class="money col-money">券后价 <span>¥47.7</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23498560" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i2/1860866819/O1CN01JbcsWr20F9LTGlmRn_!!1860866819.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">螺状元柳州螺蛳粉310g*5袋</p>
                    <p class="quan"><span>25元券</span></p>
                    <p class="money col-money">券后价 <span>¥39</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23493544" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i3/2201231503867/O1CN01EVsOTO1eR87HzAHZ1_!!2201231503867.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">【螺满天下】柳州螺狮粉330g*3包</p>
                    <p class="quan"><span>10元券</span></p>
                    <p class="money col-money">券后价 <span>¥16.8</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23504853" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/i2/4070411707/O1CN01ct0Hev1OTqdp3PaT3_!!4070411707.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">小园香红薯酸辣粉丝6桶嗨吃家整箱</p>
                    <p class="quan"><span>5元券</span></p>
                    <p class="money col-money">券后价 <span>¥24.9</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23487674" class="img" rel="nofollow"><img
                    src="//img.alicdn.com/imgextra/i1/2200674480175/O1CN01zHw4HR1DABrTeXWXh_!!2200674480175.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">好欢螺小龙虾味螺蛳粉320gx3</p>
                    <p class="quan"><span>3元券</span></p>
                    <p class="money col-money">券后价 <span>¥41.7</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23480638" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/i2/3847935596/O1CN01v9mIKh1rD0utKcMdJ_!!3847935596.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">好欢螺柳州特产螺蛳粉400g*3</p>
                    <p class="quan"><span>3元券</span></p>
                    <p class="money col-money">券后价 <span>¥37.23</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23506851" class="img" rel="nofollow"><img
                    src="//img.alicdn.com/imgextra/i3/2200806433596/O1CN01auOWlS1cR0lrwmgW9_!!2200806433596.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">3袋好欢螺柳州特产螺蛳粉</p>
                    <p class="quan"><span>3元券</span></p>
                    <p class="money col-money">券后价 <span>¥31.83</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23498199" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i4/2982641050/O1CN014DvPrh1JcwRLx6qRH_!!2982641050.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">嘻螺会螺蛳粉广西特产320g3包</p>
                    <p class="quan"><span>10元券</span></p>
                    <p class="money col-money">券后价 <span>¥19.5</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23487815" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/i1/2200674480175/O1CN01TgVE3C1DABoB1HMul_!!2200674480175.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">【好欢螺】螺蛳粉300gx5包</p>
                    <p class="quan"><span>5元券</span></p>
                    <p class="money col-money">券后价 <span>¥59.5</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23473542" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i2/3895401452/O1CN01a84AVU1Mb3e9HRE5P_!!3895401452.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">【嗨吃家】重庆正宗酸辣粉6桶</p>
                    <p class="quan"><span>5元券</span></p>
                    <p class="money col-money">券后价 <span>¥12.9</span></p></div>
                </div>
                <div class="swiper-slide">
                  <div class="swiper-cent"><a href="/index.php?r=p/d&amp;id=23487807" class="img" rel="nofollow"><img
                    src="https://img.alicdn.com/imgextra/i3/2200674480175/O1CN01M6oR9x1DABrUYgutK_!!2200674480175.jpg_310x310.jpg_.webp"
                    alt=""></a>
                    <p class="name">好欢螺螺蛳粉加辣加臭三包组合</p>
                    <p class="quan"><span>3元券</span></p>
                    <p class="money col-money">券后价 <span>¥39.7</span></p></div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="hr"></div>
      </div>
      <div class="ov_h" id="anchors_title"></div>
      <div id="goodsReco">
        <div class="goods_reco"><h3>宝贝详情</h3>
          <div class="imglist"><img
            data-original="https://img.alicdn.com/imgextra/i1/3965833216/O1CN01UzpvLo1ZcyJdqMslc_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i1/3965833216/O1CN01UzpvLo1ZcyJdqMslc_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i1/3965833216/O1CN01evpxZ71ZcyJfqagcm_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i1/3965833216/O1CN01evpxZ71ZcyJfqagcm_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i2/3965833216/O1CN0114d3qQ1ZcyIQCAL2w_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i2/3965833216/O1CN0114d3qQ1ZcyIQCAL2w_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i3/3965833216/O1CN01gltggt1ZcyIzU1G55_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i3/3965833216/O1CN01gltggt1ZcyIzU1G55_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i4/3965833216/O1CN01nntQFT1ZcyIQC943A_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i4/3965833216/O1CN01nntQFT1ZcyIQC943A_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i4/3965833216/O1CN01AlkAH61ZcyIRenANh_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i4/3965833216/O1CN01AlkAH61ZcyIRenANh_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i4/3965833216/O1CN01iro0b01ZcyIQC9jbV_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i4/3965833216/O1CN01iro0b01ZcyIQC9jbV_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01xvzxz91ZcyIQC9nky_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01xvzxz91ZcyIQC9nky_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i1/3965833216/O1CN011Haz9o1ZcyIQqa3lp_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i1/3965833216/O1CN011Haz9o1ZcyIQqa3lp_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01YRzlmR1ZcyIQxiM9f_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01YRzlmR1ZcyIQxiM9f_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01v6OFZ91ZcyIQqck54_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01v6OFZ91ZcyIQqck54_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i1/3965833216/O1CN01mzNlVz1ZcyIP5pY5t_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i1/3965833216/O1CN01mzNlVz1ZcyIP5pY5t_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01Mm8c5L1ZcyISwsuOv_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01Mm8c5L1ZcyISwsuOv_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i1/3965833216/O1CN015vwvxZ1ZcyIP5pkXd_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i1/3965833216/O1CN015vwvxZ1ZcyIP5pkXd_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01U75X6U1ZcyJpn4op1_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01U75X6U1ZcyJpn4op1_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i3/3965833216/O1CN01alCi9I1ZcyIQC9CKW_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i3/3965833216/O1CN01alCi9I1ZcyIQC9CKW_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i3/3965833216/O1CN01EwrqCj1ZcyINuJzLd_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i3/3965833216/O1CN01EwrqCj1ZcyINuJzLd_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01eQvCrv1ZcyIQZVh8V_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01eQvCrv1ZcyIQZVh8V_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i4/3965833216/O1CN01eCgg9e1ZcyIJIldwc_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i4/3965833216/O1CN01eCgg9e1ZcyIJIldwc_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01KBP6el1ZcyITuD0JX_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i2/3965833216/O1CN01KBP6el1ZcyITuD0JX_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"><img
            data-original="https://img.alicdn.com/imgextra/i1/3965833216/O1CN01j85F171ZcyG5TOuze_!!3965833216.jpg"
            src="https://img.alicdn.com/imgextra/i1/3965833216/O1CN01j85F171ZcyG5TOuze_!!3965833216.jpg" ui-lazyload=""
            alt="" isload="true" style="background: rgb(245, 245, 245); display: block;"></div>
        </div>
      </div>
      <div class="hr"></div>
      <div id="anchors_ant">

        <div class="goods_reco" data-open="/index.php?r=p/d">
          <h3>今日热销</h3>
          <ul class="goods-new row-s" id="RecommendGoodsShow">
            <li class="col-12-6" id="cms-goods-items_23487882">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23487882}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23487882"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="https://img.alicdn.com/imgextra/i1/2450547955/O1CN01KHL4ch28dRH8rfeBp_!!2450547955.jpg_310x310.jpg_.webp"
                                                                 data-original="https://img.alicdn.com/imgextra/i1/2450547955/O1CN01KHL4ch28dRH8rfeBp_!!2450547955.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"
                                                                 style="background: rgb(245, 245, 245); display: block;"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23487882}"
                                    href="/index.php?r=p/d&amp;id=23487882">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">杨幂同款【挑战一秒变瘦】保暖加绒魔术裤</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">29</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23487882" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>30元</span>        </span>
                    </div>
                    <div class="salse"><span>已售1.2万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23490220">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23490220}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23490220"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="https://img.alicdn.com/imgextra/i3/1613406809/O1CN012uea1j20AZNuOFNpy_!!1613406809.jpg_310x310.jpg_.webp"
                                                                 data-original="https://img.alicdn.com/imgextra/i3/1613406809/O1CN012uea1j20AZNuOFNpy_!!1613406809.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"
                                                                 style="background: rgb(245, 245, 245); display: block;"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23490220}"
                                    href="/index.php?r=p/d&amp;id=23490220">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">【过冬神器】拍2件 泰国3.0牛奶保暖衣</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">69</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23490220" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>100元</span>        </span>
                    </div>
                    <div class="salse"><span>已售9801</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23490983">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23490983}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23490983"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="https://img.alicdn.com/imgextra/i3/2206781406914/O1CN01mqCJhC20weyCvRG3f_!!2206781406914.jpg_310x310.jpg_.webp"
                                                                 data-original="https://img.alicdn.com/imgextra/i3/2206781406914/O1CN01mqCJhC20weyCvRG3f_!!2206781406914.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"
                                                                 style="background: rgb(245, 245, 245); display: block;"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23490983}"
                                    href="/index.php?r=p/d&amp;id=23490983">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">【官方直营】德绒阳离子火焰衫内衣打底衫</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">29.9</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23490983" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>110元</span>        </span>
                    </div>
                    <div class="salse"><span>已售3982</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23494729">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23494729}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23494729"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="https://img.alicdn.com/imgextra/i2/2251015092/O1CN01y4wd7r1nUBKvrcn1U_!!2251015092.jpg_310x310.jpg_.webp"
                                                                 data-original="https://img.alicdn.com/imgextra/i2/2251015092/O1CN01y4wd7r1nUBKvrcn1U_!!2251015092.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"
                                                                 style="background: rgb(245, 245, 245); display: block;"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23494729}"
                                    href="/index.php?r=p/d&amp;id=23494729">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">【爆款返场】梦芭蕾阳离子德绒自发热打底衣</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">29.8</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23494729" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>80元</span>        </span>
                    </div>
                    <div class="salse"><span>已售24.8万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23495129">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23495129}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23495129"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="https://img.alicdn.com/imgextra/i1/2450547955/O1CN01L9cSdn28dRH2QYTSv_!!2450547955.jpg_310x310.jpg_.webp"
                                                                 data-original="https://img.alicdn.com/imgextra/i1/2450547955/O1CN01L9cSdn28dRH2QYTSv_!!2450547955.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"
                                                                 style="background: rgb(245, 245, 245); display: block;"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23495129}"
                                    href="/index.php?r=p/d&amp;id=23495129">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">品质！仙女珊瑚绒暖暖裤套装</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">43.8</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23495129" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>50元</span>        </span>
                    </div>
                    <div class="salse"><span>已售4万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23497497">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23497497}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23497497"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="https://img.alicdn.com/imgextra/i1/3497088514/O1CN01cH3cFq2ClSfFNSycr_!!3497088514.jpg_310x310.jpg_.webp"
                                                                 data-original="https://img.alicdn.com/imgextra/i1/3497088514/O1CN01cH3cFq2ClSfFNSycr_!!3497088514.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"
                                                                 style="background: rgb(245, 245, 245); display: block;"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23497497}"
                                    href="/index.php?r=p/d&amp;id=23497497">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">【三色可选】秋冬百搭金丝绒小脚裤</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">19.9</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23497497" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>20元</span>        </span>
                    </div>
                    <div class="salse"><span>已售663</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23497635">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23497635}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23497635"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="//cmsstatic.ffquan.cn//web/images/rolling.gif"
                                                                 data-original="https://img.alicdn.com/imgextra/i4/2276865762/O1CN01rpEEgf1sR2haTI1Js_!!2276865762.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23497635}"
                                    href="/index.php?r=p/d&amp;id=23497635">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">【高品质】魔术裤加绒打底裤女【超显瘦】</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">48</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23497635" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>20元</span>        </span>
                    </div>
                    <div class="salse"><span>已售10.5万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23504297">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23504297}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23504297"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="//cmsstatic.ffquan.cn//web/images/rolling.gif"
                                                                 data-original="https://img.alicdn.com/imgextra/i1/3434959896/O1CN01koWCLH2MyQ2Wcnmvo_!!0-item_pic.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23504297}"
                                    href="/index.php?r=p/d&amp;id=23504297">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">【婉觅】网红大毛领羽绒服女韩版</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">269</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23504297" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>130元</span>        </span>
                    </div>
                    <div class="salse"><span>已售1682</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23459969">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23459969}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23459969"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="//cmsstatic.ffquan.cn//web/images/rolling.gif"
                                                                 data-original="https://img.alicdn.com/imgextra/i4/2201296327637/O1CN018zTJd926HnKAPawiC_!!2201296327637.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23459969}"
                                    href="/index.php?r=p/d&amp;id=23459969">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">【拍4件】四合院儿原生木浆抽纸40包整箱</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">24.99</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23459969" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>20元</span>        </span>
                    </div>
                    <div class="salse"><span>已售6.1万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23468013">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23468013}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23468013"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="//cmsstatic.ffquan.cn//web/images/rolling.gif"
                                                                 data-original="https://img.alicdn.com/imgextra/i2/2204175008243/O1CN01OTr6Hk2AlLL6RgOtR_!!2204175008243.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23468013}"
                                    href="/index.php?r=p/d&amp;id=23468013">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">拍四件！香将军奶香小熊曲奇饼干400g</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">9.9</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23468013" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>10元</span>        </span>
                    </div>
                    <div class="salse"><span>已售77.7万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23483544">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23483544}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23483544"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="//cmsstatic.ffquan.cn//web/images/rolling.gif"
                                                                 data-original="https://img.alicdn.com/imgextra/i3/2130096995/O1CN01XJbQT921Xl1LZBF3J_!!2130096995.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23483544}"
                                    href="/index.php?r=p/d&amp;id=23483544">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span
                    class="title_text">大牌【仁和】草本珍珠牙粉</span></h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">9.9</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23483544" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>60元</span>        </span>
                    </div>
                    <div class="salse"><span>已售10.1万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23491140">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23491140}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23491140"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="//cmsstatic.ffquan.cn//web/images/rolling.gif"
                                                                 data-original="//img.alicdn.com/imgextra/i1/920986391/TB1x6kUbTnI8KJjSszgXXc8ApXa_!!0-item_pic.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23491140}"
                                    href="/index.php?r=p/d&amp;id=23491140">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">氨基酸泡沫洗面奶带刷头深层清洁</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">19.9</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23491140" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>10元</span>        </span>
                    </div>
                    <div class="salse"><span>已售5万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23496852">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23496852}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23496852"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="//cmsstatic.ffquan.cn//web/images/rolling.gif"
                                                                 data-original="//img.alicdn.com/imgextra/i4/656236861/O1CN01Emfsvs20YNvkbDmJy_!!656236861.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23496852}"
                                    href="/index.php?r=p/d&amp;id=23496852">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">3个刷头！【超声波美白】电动牙刷</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">9.9</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23496852" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>60元</span>        </span>
                    </div>
                    <div class="salse"><span>已售58.9万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23501350">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23501350}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23501350"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="//cmsstatic.ffquan.cn//web/images/rolling.gif"
                                                                 data-original="https://img.alicdn.com/imgextra/i4/879244925/O1CN01GGMfm11mFhCDN5EKj_!!879244925.jpg_310x310.jpg_.webp"
                                                                 alt="" isload="true"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23501350}"
                                    href="/index.php?r=p/d&amp;id=23501350">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">【2套装】冬季品牌直营澳洲热纱3秒即热</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">39.9</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23501350" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>60元</span>        </span>
                    </div>
                    <div class="salse"><span>已售4.3万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23505965">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23505965}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23505965"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="//cmsstatic.ffquan.cn//web/images/rolling.gif"
                                                                 data-original="https://img.alicdn.com/imgextra/i1/721690846/O1CN015yhn5S1I7Ve22nhB4_!!721690846.png_310x310.jpg_.webp"
                                                                 alt="" isload="true"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23505965}"
                                    href="/index.php?r=p/d&amp;id=23505965">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">零熙ins日系可爱发圈头绳8个装</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">8.99</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23505965" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>3元</span>        </span>
                    </div>
                    <div class="salse"><span>已售2.2万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
            <li class="col-12-6" id="cms-goods-items_23506730">
              <div class="cent"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23506730}"
                                   style="height:180px;" href="/index.php?r=p/d&amp;id=23506730"
                                   class="img ui-act-label"><img ui-lazyload=""
                                                                 src="//cmsstatic.ffquan.cn//web/images/rolling.gif"
                                                                 data-original="https://img.alicdn.com/bao/uploaded/O1CN015Lrxg81dU349KYvw4_!!2-item_pic.png_310x310.jpg_.webp"
                                                                 alt="" isload="true"></a>
                <div class="mar"><a ui-jsbridge="" data-params="{ jump_type: 1,jump_value:23506730}"
                                    href="/index.php?r=p/d&amp;id=23506730">
                  <div><h3 class="product_title"><span class="labelTop tm"></span> <span class="title_text">【拍3份15元】碗装八宝速食饭350g</span>
                  </h3></div>
                  <div class="product_info">
                    <div class="price"><span>券后&nbsp;</span> <span class="RMB">¥</span> <span
                      class="price_num">15</span><span class="red_bubble">历史低价<span class="triangle"></span></span>
                    </div>
                    <div class="label_box"><span id="label_box_23506730" ui-delete-overflow="" data-len="1"
                                                 data-center="center" isload="true"><span class="juan"><span>劵</span>10元</span>        </span>
                    </div>
                    <div class="salse"><span>已售4.3万</span><span>评论0</span></div>
                  </div>
                </a></div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="ov_h" data-top=""><a ui-weixin-open="" id="weiboOpen"></a></div>
  </div>
</template>

<script>
  import * as util from '../api/util'
  export default {
    name: "h5Detail",
    created() {
      util.modeRem(false)
    }
  }
</script>

<style scoped>

</style>
