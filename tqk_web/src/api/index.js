import {get, post} from './common'

export const PLATFORM_PC = '0' // PC端
export const PLATFORM_WIFI = '1' // 无线端
export const HHJX_M_ID = '13366' // 好货精选
export const DEYH_M_ID = '9660' // 大额优惠
export const RMYX_M_ID = '3756' // 热门精选
export const JRDP_M_ID = '3786' // 今日大牌

/**
 * 根据物料id获取商品(来源数据库)
 * @param materialId 物料id
 * @param pageNo 页码
 * @param pageSize 页大小
 * @returns {AxiosPromise}
 */
export function getGoodsByMaterialId(materialId, pageNo, pageSize) {
  return get('/goods/'+materialId, {pageNo: pageNo, pageSize: pageSize})
}

/**
 * 根据关键词搜索商品
 * @param keyword 关键词
 * @param pageNo 页码
 * @param pageSize 页大小
 * @returns {AxiosPromise}
 */
export function getGoodsByKeyword(keyword, pageNo, pageSize){
  return get('/goods/tbk/search/pc', {keyword: keyword, pageNo: pageNo, pageSize:pageSize})
}

/**
 * 根据浏览器导航栏地址判断请求是link还是kw
 * @param isLink 请求类型是link
 * @param kwOrMaterialId 关键词或物料id
 * @param pageNo 页码
 * @param pageSize 页大小
 * @returns {AxiosPromise}
 */
export function getGoods(isLink, kwOrMaterialId, pageNo, pageSize){
  if(isLink) {
    return getGoodsByMaterialId(kwOrMaterialId,pageNo,pageSize)
  }else{
    return getGoodsByKeyword(kwOrMaterialId,pageNo,pageSize)
  }
}

/**
 * 获取官方活动
 * @param platform 平台
 * @returns {AxiosPromise}
 */
export function getOA(platform) {
  return get('/official/activity/' + platform)
}

/**
 * 获取搜索推荐
 * @returns {AxiosPromise}
 */
export function getSR() {
  return get('/search/recommend/list')
}

/**
 * 获取导航nav
 * @param platform 平台
 * @returns {AxiosPromise}
 */
export function getNav(platform) {
  return get('/nav/list/' + platform)
}

/**
 * 获取菜单
 * @param platform 平台
 * @returns {AxiosPromise}
 */
export function getMenus(platform) {
  return get('/menu/list/' + platform)
}

/**
 * 获取优惠券和物料关系
 * @param pid 父id
 * @returns {AxiosPromise}
 */
export function getCouponMaterialByPid(pid) {
  return get('/coupon/material/' + pid)
}

/**
 * 获取同等级的物料id
 * @param materialId 物料id
 * @returns {AxiosPromise}
 */
export function getSiblingsNavList(materialId) {
  return get('/coupon/material/siblings?materialId='+materialId)
}

/**
 * 获取商品详情通过商品id(商品id来源数据库)
 * @param id 商品id
 * @param needPicts 是否需要图片和店铺详情
 * @returns {AxiosPromise}
 */
export function getGoodDetailById(id, needImgs, needRecs) {
  return get('/goods/detail', {'id': id, 'needImgs': needImgs, 'needRecs': needRecs})
}

/**
 * 获取商品详情(商品来源为 淘宝客搜索api 的返回结果)
 * @returns {AxiosPromise}
 */
export function getGoodDetail() {
  return post('/goods/tbk/detail', localStorage.getItem('search_good_obj'))
}

/**
 * 获取相关商品推荐
 * @param cat 商品类目根id
 * @param materialId 物料id
 * @returns {AxiosPromise}
 */
export function getGoodRecs(cat, materialId) {
  return get('/goods/tbk/search/recs', {'cat': cat, 'materialId': materialId})
}

/**
 * 获取淘口令
 * @param text
 * @param url 淘口令跳转的地址
 * @returns {AxiosPromise}
 */
export function getTpwd(text, url) {
  return get('/goods/tbk/tpwd', {text: text, url: url})
}

/**
 * 获取短连接
 * @param url
 * @returns {AxiosPromise}
 */
export function getShortUrl(url) {
  return get('/goods/tbk/shortUrl', {url: url})
}
