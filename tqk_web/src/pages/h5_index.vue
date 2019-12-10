<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <div class="new_index">
    <h5BotTab ref="h5BotTab"/>
    <div class="banner-bg banner-color" >
      <h5Header/>
      <h5Banner ref="h5Banner" @goClassifyList="goClassifyList"/>
    </div>
    <div class="main">
      <div>
        <div class="main-banner-bg banner-color">
          <div class="banner-arc" style="background: #fe3a33;"></div>
        </div>
        <div class="content">
          <!-- banner -->
          <div class="main_module_layout">
            <div class="banner_swiper show_module" id="homeBanner">
              <mySwiper ref="indexSwiper" :swiperData="swipers" :autoplay="true" :openSlideChangeListener="true"
                        :pagination="{isShow:true}" :navigation="{isShow:false}" @slideChange="slideChange">
                <template v-slot:default="slotProps">
                  <img :src="slotProps.item.img">
                </template>
              </mySwiper>
            </div>
          </div>
          <!-- 图标导航 -->
          <div class="main_module_layout" style="">
            <div style="padding-top: .05rem;">
              <div class="nav">
                <ul class="nav_ul show_module" data-modelsid="12844" data-promodelid="2">
                  <li><a href="?r=p" data-dtk-satc="{desc:'疯抢排行',name:'CmsWapPartEvent'}">
                    <div><img
                      src="https://img.alicdn.com/imgextra/i1/2053469401/O1CN0151LlXA2JJhyWrrtRw_!!2053469401.gif"
                      alt=""></div>
                    <span class="text-center">疯抢排行</span></a></li>
                  <li><a href="?r=nine/wap" data-dtk-satc="{desc:'9.9包邮',name:'CmsWapPartEvent'}">
                    <div><img
                      src="https://img.alicdn.com/imgextra/i2/2053469401/O1CN018sPcWK2JJhy38xfum_!!2053469401.png"
                      alt=""></div>
                    <span class="text-center">9.9包邮</span></a></li>
                  <li><a href="?r=index/brand" data-dtk-satc="{desc:'品牌特卖',name:'CmsWapPartEvent'}">
                    <div><img
                      src="https://img.alicdn.com/imgextra/i2/2053469401/O1CN01ptnbDO2JJhybcK39o_!!2053469401.gif"
                      alt=""></div>
                    <span class="text-center">品牌特卖</span></a></li>
                  <li><a href="?r=every/e" data-dtk-satc="{desc:'每日半价',name:'CmsWapPartEvent'}">
                    <div><img
                      src="https://img.alicdn.com/imgextra/i4/2053469401/O1CN01C0wivK2JJhxxaqA66_!!2053469401.png"
                      alt=""></div>
                    <span class="text-center">每日半价</span></a></li>
                  <li><a href="?r=realtime/fold" data-dtk-satc="{desc:'折上折',name:'CmsWapPartEvent'}">
                    <div><img
                      src="https://img.alicdn.com/imgextra/i2/2053469401/O1CN011npPe82JJhy3KUj5q_!!2053469401.png"
                      alt=""></div>
                    <span class="text-center">折上折</span></a></li>
                </ul>
              </div>
            </div>
          </div>
          <div class="main_module_layout" style=" ">
            <!-- 优惠头条 -->
            <div class="top-line-group show_module" data-modelsid="12845" data-promodelid="13">
              <div class="solid"></div>
              <div class="top-line">
                <div class="top-line-logo"><img
                  src="https://img.alicdn.com/imgextra/i3/2053469401/O1CN011wdOkj2JJhy7TkPFo_!!2053469401.png" alt="">
                </div>
                <div class="swiper-container swiper-container-top-line swiper-no-swiping swiper-container-vertical">
                  <div class="swiper-wrapper"
                       style="transform: translate3d(0px, -66px, 0px); transition-duration: 0ms;">
                    <div class="swiper-slide swiper-slide-duplicate swiper-slide-next" data-swiper-slide-index="1"
                         style="height: 22px;"><a class="top-line-a" href="?r=l/d&amp;id=23424041">
                      <div class="top-line-info"><p class="text-ellipsis"><span class="text-center">暖冬佳品</span>低至8.8元！清新加厚加长格子围巾
                      </p></div>
                      <i class="iconfont icon-youjiantou"></i></a></div>
                    <div class="swiper-slide" data-swiper-slide-index="0" style="height: 22px;"><a class="top-line-a"
                                                                                                   href="?r=l/d&amp;id=23417543">
                      <div class="top-line-info"><p class="text-ellipsis"><span class="text-center">吃货攻略</span>买2份送2包！广西特产正宗柳州螺丝粉
                      </p></div>
                      <i class="iconfont icon-youjiantou"></i></a></div>
                    <div class="swiper-slide swiper-slide-prev" data-swiper-slide-index="1" style="height: 22px;"><a
                      class="top-line-a" href="?r=l/d&amp;id=23424041">
                      <div class="top-line-info"><p class="text-ellipsis"><span class="text-center">暖冬佳品</span>低至8.8元！清新加厚加长格子围巾
                      </p></div>
                      <i class="iconfont icon-youjiantou"></i></a></div>
                    <div class="swiper-slide swiper-slide-duplicate swiper-slide-active" data-swiper-slide-index="0"
                         style="height: 22px;"><a class="top-line-a" href="?r=l/d&amp;id=23417543">
                      <div class="top-line-info"><p class="text-ellipsis"><span class="text-center">吃货攻略</span>买2份送2包！广西特产正宗柳州螺丝粉
                      </p></div>
                      <i class="iconfont icon-youjiantou"></i></a></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 栏目橱窗 -->
          <div class="main_module_layout" style="">
            <div style="padding-top: .05rem; padding-bottom:.05rem">
              <div class="activity_list show_module" data-modelsid="12846" data-promodelid="20" id="cms_wap_sale_model">
                <div class="ddq">
                  <div><h3 class="title">咚咚抢</h3>
                    <p class="time"><span class="text-center">10点场</span><i class="down_time"
                                                                            isupdate="true">00:00:00</i></p></div>
                  <div class="swiper-container swiper-ddq ddq_list swiper-no-swiping swiper-container-horizontal">
                    <div class="swiper-wrapper"
                         style="transform: translate3d(-1323px, 0px, 0px); transition-duration: 0ms;">
                      <div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="7"
                           style="width: 189px;">
                        <ul class="ddq_list">
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_8',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i4/2201418239502/O1CN014ohgR32K3xyGK1CNf_!!2201418239502.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>180<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥190</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_8',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/2474367816/O1CN01iqrNwo27bmCodhnyx_!!2474367816.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>16.8<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥19.8</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_8',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i1/43574553/O1CN01TNtnfm1jVJrL4GtxG_!!43574553.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>6.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥9.9</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_8',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/2631960033/O1CN010LZlZv1C79d70WGy3_!!2631960033.png_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>69<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥79</i></p></a>
                          </li>
                        </ul>
                      </div>
                      <div class="swiper-slide" data-swiper-slide-index="0" style="width: 189px;">
                        <ul class="ddq_list">
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_1',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/i4/3791423568/O1CN01ijtzun1cEBjQfwZsv_!!3791423568.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>359<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥399</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_1',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/1652535357/O1CN016tYSOs1pRYILMSfCb_!!1652535357.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>89<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥99</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_1',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/3027584919/O1CN01sSsY9a1mCwo0tdmhG_!!3027584919.png_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>59<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥69</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_1',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i2/3230398242/O1CN01MulDJN2AksvVcCKsK_!!0-item_pic.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>24.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥34.9</i></p>
                          </a></li>
                        </ul>
                      </div>
                      <div class="swiper-slide" data-swiper-slide-index="1" style="width: 189px;">
                        <ul class="ddq_list">
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_2',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i4/1122844978/O1CN012rD1T61mdyBdfwmYS_!!1122844978.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>159<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥179</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_2',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/2918460021/O1CN01ZFu8LN1C1encMWThZ_!!2918460021.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>59<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥89</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_2',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/bao/uploaded/O1CN01du0VZs1hMxMxFf54p_!!2-item_pic.png_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>34.8<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥49.8</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_2',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i2/1803748531/O1CN01oNxVqD2CtFPS84v64_!!1803748531.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>89<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥99</i></p></a>
                          </li>
                        </ul>
                      </div>
                      <div class="swiper-slide" data-swiper-slide-index="2" style="width: 189px;">
                        <ul class="ddq_list">
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_3',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i4/822196805/O1CN01PaW4CF208jmY5m2u0_!!822196805.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>54.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥69.9</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_3',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/bao/uploaded/O1CN01egO4mo2FZxYngRv3f_!!2-item_pic.png_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>88<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥128</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_3',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i1/3792434480/O1CN019LsSw41ixssZoEEjD_!!3792434480.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>19.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥24.9</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_3',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i1/2973462812/O1CN015GrOb01WdwJDiQxza_!!2973462812.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>14.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥29.9</i></p>
                          </a></li>
                        </ul>
                      </div>
                      <div class="swiper-slide" data-swiper-slide-index="3" style="width: 189px;">
                        <ul class="ddq_list">
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_4',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="//img.alicdn.com/imgextra/i1/773562628/O1CN01phICVG1VHfMg2DVEN_!!773562628.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>19<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥39</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_4',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/bao/uploaded/TB1N08Mk.z1gK0jSZLeXXb9kVXa.png_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>49.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥59.9</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_4',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i1/2394172457/O1CN01xOneA81U1LhO26Oku_!!2394172457.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>39.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥49.9</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_4',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i1/799689845/O1CN0161wdRO2Mb3q7hIV2w_!!799689845.png_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>29.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥39.9</i></p>
                          </a></li>
                        </ul>
                      </div>
                      <div class="swiper-slide" data-swiper-slide-index="4" style="width: 189px;">
                        <ul class="ddq_list">
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_5',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/i2/3007361242/O1CN014yMwGj1L2sRnAYrTz_!!3007361242.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>108<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥128</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_5',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i1/2574873285/O1CN01uMNZyR1a8Zd3GQPnO_!!2574873285.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>15.8<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥17.8</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_5',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i2/3796585761/O1CN01rVH26u1sQaGB3spXV_!!3796585761.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>8.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥9.9</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_5',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="//img.alicdn.com/imgextra/i3/4055435040/O1CN01kHwMiR1n6Mj4Z9Whe_!!4055435040.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>14.8<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥24.8</i></p>
                          </a></li>
                        </ul>
                      </div>
                      <div class="swiper-slide swiper-slide-prev" data-swiper-slide-index="5" style="width: 189px;">
                        <ul class="ddq_list">
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_6',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/2073186249/O1CN01ZCVrGP1w25a5wAPtR_!!2073186249.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>13.8<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥18.8</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_6',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i2/2921111265/O1CN01W2YYub1LDParMqEBW_!!2921111265.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>89.7<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥99.7</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_6',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i4/2331968284/O1CN01U4PPgB2B47ZJS0vod_!!2331968284.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>98<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥118</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_6',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i4/35115354/O1CN01fzEN2B1pQB6Kz55qV_!!35115354.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>14.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥24.9</i></p>
                          </a></li>
                        </ul>
                      </div>
                      <div class="swiper-slide swiper-slide-active" data-swiper-slide-index="6" style="width: 189px;">
                        <ul class="ddq_list">
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_7',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i1/3099575147/O1CN011ntN0znL1bPIDR2_!!3099575147.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>9.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥19.9</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_7',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://gd4.alicdn.com/imgextra/i4/1767808043/O1CN01WKR6EF29Hk7ZO6wbq_!!1767808043.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>59<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥89</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_7',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i1/3087155153/O1CN01hI2bmO1nw7UTjmatr_!!3087155153.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>7.8<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥8.8</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_7',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/3027584919/O1CN01hkwvcq1mCwoJE0qp9_!!3027584919.png_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>139<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥159</i></p></a>
                          </li>
                        </ul>
                      </div>
                      <div class="swiper-slide swiper-slide-next" data-swiper-slide-index="7" style="width: 189px;">
                        <ul class="ddq_list">
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_8',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i4/2201418239502/O1CN014ohgR32K3xyGK1CNf_!!2201418239502.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>180<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥190</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_8',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/2474367816/O1CN01iqrNwo27bmCodhnyx_!!2474367816.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>16.8<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥19.8</i></p>
                          </a></li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_8',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i1/43574553/O1CN01TNtnfm1jVJrL4GtxG_!!43574553.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>6.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥9.9</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_8',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/2631960033/O1CN010LZlZv1C79d70WGy3_!!2631960033.png_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>69<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥79</i></p></a>
                          </li>
                        </ul>
                      </div>
                      <div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="0"
                           style="width: 189px;">
                        <ul class="ddq_list">
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_1',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/i4/3791423568/O1CN01ijtzun1cEBjQfwZsv_!!3791423568.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>359<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥399</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_1',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/1652535357/O1CN016tYSOs1pRYILMSfCb_!!1652535357.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>89<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥99</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_1',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i3/3027584919/O1CN01sSsY9a1mCwo0tdmhG_!!3027584919.png_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>59<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥69</i></p></a>
                          </li>
                          <li><a href="?r=ddq/wap" data-dtk-satc="{desc:'咚咚抢_1',name:'CmsWapWindowEvent'}">
                            <div><img
                              src="https://img.alicdn.com/imgextra/i2/3230398242/O1CN01MulDJN2AksvVcCKsK_!!0-item_pic.jpg_310x310.jpg_.webp"
                              alt=""></div>
                            <p>￥<span>24.9<span class="rush_bobble hide text-center">疯抢价</span></span><i>¥34.9</i></p>
                          </a></li>
                        </ul>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="long"></div>
                <div class="import">
                  <div class="import-product imports" data-dtk-satc="{desc:'咚咚抢右上',name:'CmsWapWindowEvent'}"><a
                    href="?r=a/t&amp;i=1da986d37fa67c3d&amp;today_hot=1"><img
                    src="https://img.alicdn.com/imgextra/i2/2053469401/O1CN01lSgZqQ2JJhz6ANhtO_!!2053469401.png" alt=""></a>
                  </div>
                  <div class="solid"></div>
                  <div class="import-global imports" data-dtk-satc="{desc:'咚咚抢右下',name:'CmsWapWindowEvent'}"><a
                    href="?r=a/t&amp;i=836b5897e5fc32c8&amp;today_coupon=1"><img
                    src="https://img.alicdn.com/imgextra/i3/2053469401/O1CN01HWFSOO2JJhz93sGBA_!!2053469401.png" alt=""></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="main_module_layout" style="">
            <div style="padding-top: .05rem; padding-bottom:.05rem">
              <div data-modelsid="12848" data-promodelid="21" class="brand_product show_module">
                <div class="title_group">
                  <div class="title"><h3>品牌特卖</h3>
                    <p class="text-center"><span><span class="brand_num">420</span>+品牌</span></p></div>
                  <div class="more"><a id="cms_wap_more_brand" href="?r=index/brand&amp;more_brand=0">更多品牌<i
                    class="iconfont icon-youjiantou"></i></a></div>
                </div>
                <ul class="brand_product_lists">
                  <li class="brand_product_list"><a data-dtk-satc="{desc:'29504_1',name:'BrandSaleEvent'}"
                                                    href="?r=index/branddetails&amp;brand_id=29504&amp;brand_sale=1"><img
                    src="https://img.alicdn.com/imgextra/i4/2053469401/O1CN01izjse72JJhzCO53hZ_!!2053469401.png" alt=""></a>
                  </li>
                  <li class="brand_product_list"><a data-dtk-satc="{desc:'11714362_2',name:'BrandSaleEvent'}"
                                                    href="?r=index/branddetails&amp;brand_id=11714362&amp;brand_sale=2"><img
                    src="https://img.alicdn.com/imgextra/i2/2053469401/O1CN01jD2U4t2JJhzDlvCIb_!!2053469401.jpg" alt=""></a>
                  </li>
                  <li class="brand_product_list"><a data-dtk-satc="{desc:'44602_3',name:'BrandSaleEvent'}"
                                                    href="?r=index/branddetails&amp;brand_id=44602&amp;brand_sale=3"><img
                    src="https://img.alicdn.com/imgextra/i3/2053469401/O1CN01aOGsqF2JJhzB9puCI_!!2053469401.jpg" alt=""></a>
                  </li>
                  <li class="brand_product_list"><a data-dtk-satc="{desc:'30653_4',name:'BrandSaleEvent'}"
                                                    href="?r=index/branddetails&amp;brand_id=30653&amp;brand_sale=4"><img
                    src="https://img.alicdn.com/imgextra/i3/2053469401/O1CN01jekHFm2JJhzCAPL8u_!!2053469401.jpg" alt=""></a>
                  </li>
                  <li class="brand_product_list"><a data-dtk-satc="{desc:'8404926_5',name:'BrandSaleEvent'}"
                                                    href="?r=index/branddetails&amp;brand_id=8404926&amp;brand_sale=5"><img
                    src="https://img.alicdn.com/imgextra/i3/2053469401/O1CN01LQuuOB2JJhzAvYKoe_!!2053469401.jpg" alt=""></a>
                  </li>
                  <li class="brand_product_list"><a data-dtk-satc="{desc:'104506277_6',name:'BrandSaleEvent'}"
                                                    href="?r=index/branddetails&amp;brand_id=104506277&amp;brand_sale=6"><img
                    src="https://img.alicdn.com/imgextra/i2/2053469401/O1CN01bViuAG2JJhzF7f089_!!2053469401.png" alt=""></a>
                  </li>
                  <li class="brand_product_list"><a data-dtk-satc="{desc:'1666803597_7',name:'BrandSaleEvent'}"
                                                    href="?r=index/branddetails&amp;brand_id=1666803597&amp;brand_sale=7"><img
                    src="https://img.alicdn.com/imgextra/i2/2053469401/O1CN01q9LDgg2JJhzFsUM3t_!!2053469401.jpg" alt=""></a>
                  </li>
                  <li class="brand_product_list"><a data-dtk-satc="{desc:'1546047370_8',name:'BrandSaleEvent'}"
                                                    href="?r=index/branddetails&amp;brand_id=1546047370&amp;brand_sale=8"><img
                    src="https://img.alicdn.com/imgextra/i1/2053469401/O1CN01KmlQGM2JJhzF5UqsN_!!2053469401.jpg" alt=""></a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <!-- 大家都在领 -->
          <div class="main_module_layout" style="">
            <div style="padding-top: .05rem; padding-bottom:.05rem">
              <div data-modelsid="12849" data-promodelid="16" class="get_coupon show_module">

              </div>
            </div>
          </div>
          <!-- 发现好货 -->
          <div class="find_product show_module main_module_layout">
            <p class="find_product_info text-center">发现好货</p>
            <div id="find_product_group">
              <ul v-for="goods in [leftGoods,rightGoods]">
                <li v-for="good in goods"
                    :key="good.id" class="find_product_list">
                  <a @click.prevent="showDetail(good.id)">
                    <div class="find_product_list_img ui-act-label">
                      <img :src="good.pictUrl" style="background: rgb(245, 245, 245); display: inline;">
                    </div>
                    <p class="find_product_list_title">{{good.title}}</p>
                    <div class="find-product-label-group">
                    <span id="find-product-label-0" class="find_product_label">
                      <span class="label1">{{good.couponAmount}}元券</span>
                      <span class="label3" style="float: right">券后&nbsp;¥{{parseFloat(good.zkFinalPrice-good.couponAmount).toFixed(1)}}</span>
                    </span>
                    </div>
                    <div class="find_product_more">
                      <p>一口价 ¥<span>{{good.zkFinalPrice}}</span></p>
                      <span>已售&nbsp;<span style="color: #ff3b32;">{{good.volume | volumeFormat}}</span></span>
                    </div>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <a v-show="toTopBtnShow" @click.prevent="gotoTop()" class="iconfont icon-shang scroll_top active" style="bottom: 0.5rem;"></a>
    </div>
  </div>
</template>

<script>
  import * as server from '../api'
  import * as util from '../api/util'
  import mySwiper from '../components/swiper'
  import h5Banner from '../components/m/h5_banner'
  import h5Header from '../components/m/h5_header'
  import h5BotTab from '../components/m/h5_botTab'
  export default {
    name: "H5Index",
    data() {
      return {
        leftGoods: [],
        rightGoods: [],
        loadedPageNo: 1,
        needLoadGoods: [],
        toTopBtnShow: false,
        swipers: [
          {id:1, img:'https://img.alicdn.com/imgextra/i2/2053469401/O1CN01zWvohl2JJhz8nII9X_!!2053469401.jpg'},
          {id:2, img:'https://img.alicdn.com/imgextra/i1/2053469401/O1CN011zFY822JJhzEFMiIE_!!2053469401.jpg'},
          {id:3, img:'https://img.alicdn.com/imgextra/i1/2053469401/O1CN01aC5ksE2JJhzAWiQCJ_!!2053469401.jpg'},
          {id:4, img:'https://img.alicdn.com/imgextra/i1/2053469401/O1CN01soAL0L2JJhzBuk6CO_!!2053469401.jpg'},
          {id:5, img:'https://img.alicdn.com/imgextra/i3/2053469401/O1CN01gdHnUe2JJhzGuH9Xl_!!2053469401.jpg'}
        ]
      }
    },
    created() {
      util.modeRem(true)
      server.getGoodsByMaterialId(server.HHJX_M_ID, 1, 40).then(res=>{
        this.leftGoods = res.data.filter((arr, index)=>index%2===1)
        this.rightGoods = res.data.filter((arr, index)=>index%2===0)
      })
    },
    mounted() {
      window.addEventListener("scroll", this.handleScroll)
    },
    beforeDestroy() {
      window.removeEventListener("scroll", this.handleScroll)
    },
    methods: {
      handleScroll() {
        this.$refs.h5Banner.hideIconNav()
        let scrollTop = window.pageXOffset || document.documentElement.scrollTop || document.body.scrollTop
        let scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight
        let clientHeight = document.documentElement.clientHeight || document.body.clientHeight
        if(scrollTop>4500) {
          this.toTopBtnShow = true
        }
        if(scrollTop+clientHeight >= scrollHeight-30){
          if(this.needLoadGoods.length === 0){
            this.loadedPageNo = this.loadedPageNo + 1
            server.getGoodsByMaterialId(server.HHJX_M_ID, this.loadedPageNo, 40).then(res=>{
              this.needLoadGoods = res.data
            })
          }else{
            this.leftGoods.push(this.needLoadGoods.pop())
            this.rightGoods.push(this.needLoadGoods.pop())
          }
        }
      },
      slideChange(img) {
        util.renderRGB(img, "header,.layout > div,#swiper_nav>div>div>div>div ,#swiper_nav > a, .main-banner-bg, .banner-arc")
      },
      gotoTop() {
        this.toTopBtnShow = false
        scrollTo(0,0);
      },
      goClassifyList(banner) {
        this.$parent.$router.push({name:'classifyList', params:{type: 'link', value: banner.materialId}})
      },
      showDetail(id) {
        this.$parent.$router.push({name:'detail', params:{id: id}})
      }
    },
    filters: {
      volumeFormat: util.volumeFormat
    },
    components: {
      h5Header: h5Header,
      h5Banner: h5Banner,
      mySwiper: mySwiper,
      h5BotTab: h5BotTab
    }
  }
</script>
<style scoped>
  @import "../../static/css/h5_index.css";
</style>
