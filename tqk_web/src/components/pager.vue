<!--
分页组件(myPager)
参数配置：
  1.pageNum：分页组件要处理的所有数据量，pageNum/pageSize = pageCount
  2.pageSize：分页组件每页显示的数量，页大小，默认为20
  3.showTotal：分页组件是否显示 当前页/总页码 标签，默认为false(不显示)
  4.showPrevNextBtn：分页组件是否显示 前一页/后一页 按钮，默认为true(显示)

使用示例：
  <myPager :pageNum="200" :pageSize="20" :showTotal="true" :showPrevNextBtn="true" />
-->
<template>
  <div class="pager main-container" style="margin: 20px auto;">
    <div id="yw0" class="pager">
      <a v-if="showPrevNextBtn" :class="'pre-page'+(preBtnAble?'':' disabled')" @click.prevent="pnClick('p')">上一页</a>
      <a v-if="prePageMore" class="item more" @click.prevent="mpClick('p')">...</a>
      <a v-for="pIndex in showPages" :key="pIndex" :class="'item'+(pIndex==pageNo?' cur':'')" @click.prevent="pClick(pIndex)">{{pIndex}}</a>
      <a v-if="nextPageMore" class="item more" @click.prevent="mpClick('n')">...</a>
      <a v-if="nextPageMore" :class="'item' + (lastClicked?' cur':'')" @click.prevent="pClick()">{{pageCount}}</a>
      <a v-if="showPrevNextBtn" :class="'next-page' + (nextBtnAble?'':' disabled')" @click.prevent="pnClick('n')">下一页</a>
      <span v-if="showTotal" class="item">{{pageNo}} / {{pageCount}}</span>
    </div>
  </div>
</template>

<script>
import * as util from '../api/util'
export default {
  name: "myPager",
  data() {
    return {
      pageNo: 1,
      lastClicked: false,
      pageCount: 0,
      prePageMore: true,
      nextPageMore: false,
      preBtnAble: false,
      nextBtnAble: true,
      showPages: []
    }
  },
  props: {
    pageNum: {
      type: Number,
      default: 200
    },
    pageSize: {
      type: Number,
      default: 20
    },
    showTotal: {
      type: Boolean,
      default: false
    },
    showPrevNextBtn: {
      type: Boolean,
      default: true
    }
  },
  created() {
    this.pageCount = parseInt(this.pageNum/this.pageSize),
    this.prePageMore = this.pageNo > 5,
    this.nextPageMore = this.pageCount > 5,
    this.preBtnAble = this.pageNo >= 2,
    this.nextBtnAble = this.pageCount > 1,
    this.showPages = util.range(this.pageNo,this.pageNo+5)
  },
  methods: {
    pnClick(isPre) {
      /**
       * 上一页 下一页按钮点击事件
       */
      isPre = isPre === 'p'
      if(isPre && this.preBtnAble){
        // 上一页
        this.pageNo = this.pageNo >=2? this.pageNo - 1: this.pageNo
        this.nextBtnAble = this.pageCount > 1
      }else if(!isPre && this.nextBtnAble){
        // 下一页
        this.pageNo = this.pageNo < this.pageCount? this.pageNo + 1: this.pageNo
        this.nextBtnAble = this.pageNo < this.pageCount
      }
      this.preBtnAble = this.pageNo >= 2
      this.$emit('goPage', this.pageNo)
    },
    pClick(index) {
      /**
       * 页码按钮点击事件
       * index 页码
       */
      // 是否点击最后一个按钮，默认先置为否
      this.lastClicked = false
      // index为 undefined 时，点击的最后一个按钮
      index = index === undefined? this.pageCount: index
      this.pageNo = index
      this.preBtnAble = (index !== 1)
      this.nextBtnAble = (index !== this.pageCount)
      this.prePageMore = index >= 6
      this.nextPageMore = index < this.pageCount - 4
      if(index === this.pageCount){
        // 点击的最后一个按钮，设置状态为ture和新的页码数组
        this.lastClicked = true
        this.showPages = util.range(index - 4, index + 1)
      }
      this.$emit('goPage', index)

    },
    mpClick(isPre) {
      /**
       * "更多"按钮点击事件
       * preMorePageBtn 是否为 前置"更多"按钮
       * @type {any[]}
       */
      // 前一组页码按钮 开始位置
      isPre = isPre === 'p'
      let startIndex = isPre? (this.showPages[0] - 5): this.showPages[this.showPages.length-1] + 1
      // 前一组页码
      this.showPages = util.range(startIndex < 1? 1: startIndex, (startIndex + 5) >= this.pageCount? this.pageCount: (startIndex + 5))
      // 设置前置或后置"更多"按钮是否显示
      this.prePageMore = isPre? (startIndex !== 1): (startIndex >= 6)
      this.nextPageMore = isPre? (this.pageCount >= 6): (startIndex !== this.pageCount - 4)
    }
  }
}
</script>

<style scoped>
@import "../../static/css/pager.css";
</style>
