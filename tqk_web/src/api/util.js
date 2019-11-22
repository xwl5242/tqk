export function range(start, stop, step=1) {
  return Array(Math.ceil((stop-start)/step)).fill(start).map((x, y) => x+y*step)
}

export const volumeFormat = function (volume) {
  if(volume!==undefined){
    volume = parseInt(volume)
    return `${(volume/10000).toFixed(2)}万`
  }
}

export function modeRem(isRem) {
  if(localStorage.getItem("platform") === "2") {
    // 因为前端样式单位使用的是rem,需要设置<html>标签的font-size
    document.documentElement.style.fontSize = isRem?document.documentElement.clientWidth / 3.75 + 'px':'1px'
    window.onresize = function(){
      document.documentElement.style.fontSize = isRem?document.documentElement.clientWidth / 3.75 + 'px':'1px'
    }
  }
}
