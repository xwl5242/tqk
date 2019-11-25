import ColorThief from 'colorthief/dist/color-thief.mjs'

/**
 * 获取指定范围内的数字数组
 * @param start 开始数字
 * @param stop 结束数字，不包含
 * @param step 步长
 * @returns {*[]}
 */
export function range(start, stop, step=1) {
  return Array(Math.ceil((stop-start)/step)).fill(start).map((x, y) => x+y*step)
}

/**
 * 商品销量格式化
 * @param volume 商品销量
 * @returns {string} xx万,如:8.9万
 */
export const volumeFormat = function (volume) {
  if(volume!==undefined){
    volume = parseInt(volume)
    return `${(volume/10000).toFixed(2)}万`
  }
}

/**
 * 页面字体rem模式
 * @param isRem 是否开启rem模式
 */
export function modeRem(isRem) {
  if(localStorage.getItem("platform") === "2") {
    // 因为前端样式单位使用的是rem,需要设置<html>标签的font-size
    document.documentElement.style.fontSize = isRem?document.documentElement.clientWidth / 3.75 + 'px':'10px'
    window.onresize = function(){
      document.documentElement.style.fontSize = isRem?document.documentElement.clientWidth / 3.75 + 'px':'10px'
    }
  }
}

/**
 * 利用指定图片的主色调颜色去渲染其他元素的背景色
 * @param imgElement 主色调来源的元素
 * @param setRGBElementSelector 需要被渲染的元素的css选择器表达式
 */
export function renderRGB(imgElement, setRGBElementSelector) {
  let img = new Image()
  img = imgElement
  img.crossOrigin = 'Anonymous'
  img.src = imgElement.src

  img.addEventListener('load', function() {
    const  colorThief = new ColorThief();
    let color = colorThief.getColor(img)
    color = rgbToHex(color[0], color[1], color[2])
    document.querySelectorAll(setRGBElementSelector).forEach(
      value => value.setAttribute('style', 'background:'+color+';border-color:'+color+';')
    )
  })
}

const rgbToHex = (r, g, b) => '#' + [r, g, b].map(x => {
  const hex = x.toString(16)
  return hex.length === 1 ? '0' + hex : hex
}).join('')
