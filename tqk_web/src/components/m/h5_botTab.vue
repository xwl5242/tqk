<template>
  <div class="ui_bot_tab">
    <div class="row-s">
      <div v-for="menu in menus" :key="menu.id" class="col-12-3 list">
        <a @click.prevent="changeTab(menu)">
          <i class="iconfont">
            <em><img :class="menu.menuIconClass+(menuSel===menu.seq?' active':'')"/></em>
          </i>
          <span :style="menuSel===menu.seq?'color: #FD575C':''">{{menu.menuName}}</span>
        </a>
      </div>
    </div>
  </div>
</template>

<script>
  import {get} from '../../api/common'
  export default {
    name: "h5BotTab",
    data() {
      return {
        menus: [],
        menuSel: 0
      }
    },
    created() {
      get('/menu/list/1').then(res => this.menus = res.data)
    },
    methods: {
      changeTab(menu) {
        this.menuSel=menu.seq
        this.$router.push(menu.menuUrl)
      }
    }
  }
</script>

<style scoped>
  .ui_bot_tab~.layout {
    padding-bottom: 44px;
    display: block
  }

  .ui_bot_tab {
    border-top: solid 1px #eee;
    position: fixed;
    zoom:1;z-index: 110;
    left: 0;
    bottom: 0;
    width: 100%;
    background: #fff;
    height: 50px
  }

  .ui_bot_tab .row-s>.list {
    color: #666;
    text-align: center;
    font-size: 12px;
    padding: 3px 0 2px;
    position: relative;
    z-index: 1;
    zoom:1;line-height: 20px
  }

  .ui_bot_tab .row-s>.list .iconfont {
    display: block;
    color: #666;
    font-size: 24px;
    height: 24px
  }

  .ui_bot_tab .row-s>.list .iconfont em {
    position: absolute;
    bottom: 0;
    text-align: center;
    left: 0;
    width: 100%
  }

  .ui_bot_tab .row-s>.list .iconfont em img {
    width: auto;
    height: 24px;
    display: block;
    margin: auto
  }

  .ui_bot_tab .row-s>.list em.num {
    position: absolute;
    zoom:1;left: 45%;
    top: 3px;
    z-index: 10;
    font-style: normal;
    background: #FC3F78;
    color: #fff;
    overflow: hidden;
    font-size: 11px;
    border-radius: 20px;
    -moz-border-radius: 20px;
    -webkit-border-radius: 20px;
    -o-border-radius: 20px;
    -ms-border-radius: 20px;
    line-height: 15px;
    text-align: center;
    margin-left: 10px;
    max-width: 2rem;
    padding: 0 7px;
    text-overflow: ellipsis;
    white-space: nowrap
  }

  .ui_bot_tab .row-s>.list.active,.ui_bot_tab .row-s>.list.active .iconfont {
    color: #FC3F78
  }

  .ui_bot_tab .row-s div:nth-last-child(1) .bnt_info_red {
    position: absolute;
    z-index: 99;
    top: 3px;
    right: 25px;
    display: none
  }
  .icon_h5_index.active {
    width:24px;height: 24px;
    content: url("../../../static/images/m/index.png");
  }
  .icon_h5_classify.active {
    width:24px;height: 24px;
    content: url("../../../static/images/m/type.png");
  }
  .icon_h5_collect.active {
    width:24px;height: 24px;
    content: url("../../../static/images/m/collet.png");
  }
  .icon_h5_mine.active {
    width:24px;height: 24px;
    content: url("../../../static/images/m/mine.png");
  }

  .icon_h5_index {
    width:24px;height: 24px;
    content: url("../../../static/images/m/index_.png");
  }
  .icon_h5_classify {
    width:24px;height: 24px;
    content: url("../../../static/images/m/type_.png");
  }
  .icon_h5_collect {
    width:24px;height: 24px;
    content: url("../../../static/images/m/collet_.png");
  }
  .icon_h5_mine {
    width:24px;height: 24px;
    content: url("../../../static/images/m/mine_.png");
  }
</style>
