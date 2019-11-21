export function range(start, stop, step=1) {
  return Array(Math.ceil((stop-start)/step)).fill(start).map((x, y) => x+y*step)
}

export const volumeFormat = function (volume) {
  if(volume!==undefined){
    volume = parseInt(volume)
    return `${(volume/10000).toFixed(2)}万`
  }
}
