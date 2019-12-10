<template xmlns:v-clipboard="http://www.w3.org/1999/xhtml">
  <div>
    <div class="header_pr  header_red">
      <header class="icon_header">
        <a @click.prevent="goBack" class="iconfont icon-zuojiantou"></a>
        <div class="title">
          分享商品
        </div>
      </header>
    </div>
    <div class="header_bg"></div>
    <div class="goods_btn_share">
      <div class="row-s col-mar">
        <div class="col-12-6">
          <a class="btn btn-secondary btn-block code btn-max">复制口令</a></div>
        <div class="col-12-6"><a class="btn btn-secondary btn-block img btn-max">保存图片</a>
        </div>
      </div>
    </div>
    <div v-if="shareGood" class="layout">
      <div class="goods_share_hreader">
        <pre class="col-mar" style="margin-top: 1rem;">
{{shareGood.title}}

【原价】{{shareGood.reversePrice}}元
【折扣价】{{shareGood.zkFinalPrice}}元
【优惠券】{{shareGood.couponAmount}}元
【淘口令】{{shareGood.tpwd}}

<span style="font-size: 12px;color: #acb0b5;">复制此信息打开手机淘宝即可查看并下单</span>
        </pre>
      </div>
      <div class="item-con" id="html2Img">
        <div class="item-info-con">
          <a>
            <div class="shop-info">
              <img class="shop-logo" :src="shareGood.shop.pictUrl">
              <span class="shop-name">{{shareGood.shop.shopTitle}}</span>
            </div>
            <img class="item-img" :src="shareGood.pictUrl">
            <div class="good-info">
              <div class="item-info">
                <span class="title">
                  {{shareGood.title}}
                </span>
                <div class="price-info">
                  <div>
                    <span class="after-price-title">用券后</span>
                    <span class="after-price-icon">¥</span>
                    <span class="after-price-num">99.00</span>
                  </div>
                  <div style="margin: 5px 0;">
                    <span class="price">现价¥119</span>
                    <span class="sell-num">{{shareGood.volume}}笔成交</span>
                  </div>
                </div>
              </div>
              <div class="qrcode">
                <vue-qr :size="100" :text="couponShortUrl" :margin="4"></vue-qr>
              </div>
            </div>
          </a>
        </div>
      </div>
      <div style="margin: 20px;">
        <share :imageUrl="'http:'+shareGood.pictUrl"></share>
      </div>
    </div>
  </div>
</template>
<script>
  import * as server from '../api'
  import vueQr from 'vue-qr'
  import html2canvas from 'html2canvas'
  import share from '../components/share/share'
  export default {
    name: "h5Share",
    data() {
      return {
        shareImgUrl:'',
        shareGood: {},
        couponShortUrl: ''
      }
    },
    created() {
      this.shareGood = JSON.parse(localStorage.getItem('shareGood'))
      server.getShortUrl('http:'+this.shareGood.couponClickUrl).then(res=>this.couponShortUrl = res.data)
    },
    methods: {
      goBack() {
        localStorage.removeItem('shareGood')
        this.$router.back()
      },
      doCopy(e) {
        this.$layer.msg('淘口令已复制，打开【手机淘宝】即可领券购买')
      },
      imgShare() {
        let html2Img = document.getElementById('html2Img')
        html2canvas(html2Img, {
          scale: 1,
          width: 375,
          height: 580,
          x: 0,
          y: window.pageYOffset,
          useCORS: true,
          taintTest: true,
          timeout: 500,
          backgroundColor: null
        }).then((canvas) => {
          let url = canvas.toDataURL('image/png')
          let a = document.createElement('a')
          let event = new MouseEvent('click')
          a.download = 'goodImg.png'
          a.href = url
          a.dispatchEvent(event)
        })

      }
    },
    components: {
      vueQr: vueQr,
      share: share
    }
  }
</script>

<style scoped>
  .layout {
    height: 775px;
  }
  .qrcode {
    float: right;
    width: 30%;
    text-align: right;
    padding: 9px;
    border-left: 1px dashed #fbaa58;
  }
  .good-info {
    margin: 7px 0;
    width: 99%;
    border: 1px solid #fbaa58;
    height: 120px;
    border-radius: 12px;
  }
  .item-con {
    width: 100%;
    height: 480px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }
  .item-info-con {
    /*height: 462px;*/
    width: 340px;
    margin: 10px auto;
    border-radius: 16px;
    background: #fff;
    position: relative;
    overflow: hidden;
  }
  .item-con .shop-info {
    position: absolute;
    left: 0;
    top: 0;
    height: 40px;
    width: 100%;
    background: rgba(255, 255, 255, 0.8);
  }
  .item-con .shop-info .shop-logo {
    height: 30px;
    margin: 5px 10px;
  }
  .item-con .item-img {
    border-radius: 16px;
    width: 340px;
    height: 340px;
  }
  .item-con .item-info {
    float: left;
    width: 60%;
    padding: 7px;
  }
  .item-con .item-info .title {
    color: #4a4a4a;
    font-size: 14px;
    line-height: 20px;
    /*overflow: hidden;*/
    text-overflow: ellipsis;
    display: block;
    /*-webkit-line-clamp: 2;*/
    /*-webkit-box-orient: vertical;*/
    margin: 10px 0 5px 0;
  }
  .item-con .item-info .after-price-title {
    font-size: 16px;
    height: 20px;
    line-height: 21px;
    -webkit-transform: scale(0.72);
    transform: scale(0.72);
    -webkit-transform-origin: left 80%;
    transform-origin: left 80%;
    color: #fff;
    background: #f44;
    padding: 0 5px;
    display: inline-block;
    border-radius: 3px;
  }
  .item-con .item-info .after-price-icon {
    font-size: 14px;
    -webkit-transform: scale(0.72);
    transform: scale(0.72);
    color: #f44;
    -webkit-transform-origin: left bottom;
    transform-origin: left bottom;
    display: inline-block;
    margin-left: -10px;
    line-height: 1;
  }
  .item-con .item-info .after-price-num {
    font-size: 18px;
    color: #f44;
    vertical-align: middle;
    font-weight: 600;
    line-height: 1;
    margin-right: 8px;
  }
  .item-con .item-info .price {
    font-size: 14px;
    color: #9b9b9b;
    vertical-align: middle;
    line-height: 1.2;
    margin-top: 1px;
    display: inline-block;
  }
  .item-con .item-info .sell-num {
    float: right;
    color: #9b9b9b;
    font-size: 14px;
    line-height: 1.2;
    position: relative;
    top: 4px;
  }

  .goods_btn_share {
    position: fixed;
    zoom:1;left: 0;
    bottom: 0;
    background-color: #fff;
    z-index: 500;
    width: 100%;
    padding: 5px 0
  }
  .shop-name {
    color: #FC3F78;
    font-weight: 600;
  }
  .goods_btn_share .col-mar {
    margin: 0 10px
  }

  .goods_btn_share .code {
    background: linear-gradient(90deg,#fbaa58 0,#fbaa58 100%);
    margin-right: 3px;
    width: auto;
    font-size: 15px
  }

  .goods_btn_share .img {
    margin-left: 3px;
    width: auto;
    font-size: 15px
  }

  button {
    background: none;
    border: 1px solid;
    color: #FC3F78;
    font-size: 0.9rem;
    border-radius: 5px;
    height: 3rem;
    line-height: 3rem;
    width: 80%;
    margin: 0.5rem 0;
    position: relative;
    cursor: pointer;
  }

</style>
