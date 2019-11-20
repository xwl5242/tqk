import {get} from './common'

function getItemListByKeyword(keyword, pageNo, pageSize){
  return get('/goods/search/pc', {keyword: keyword, pageNo: pageNo, pageSize:pageSize})
}

function getItemListByMaterial(material, pageNo, pageSize, order){
  return get('/goods/'+material, {pageNo: pageNo, pageSize: pageSize, order})
}

export function getItemList(isLink, kwOrMaterial, pageNo = 0, pageSize = 23, order = {}){
  if(isLink) {
    return getItemListByMaterial(kwOrMaterial,pageNo,pageSize,order)
  }else{
    return getItemListByKeyword(kwOrMaterial,pageNo,pageSize)
  }
}

export function getSibingsNavList(materialId) {
  return get('/coupon/material/sibings?materialId='+materialId)
}
