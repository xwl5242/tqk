import {get} from './common'

export const HHJX_M_ID = '13366'
export const DEYH_M_ID = '9660'
export const RMYX_M_ID = '3756'
export const JRDP_M_ID = '3786'

export function getOfficialActivity() {
  return get('/official/activity/pc')
}

export function getNavList() {
  return get('/nav/list')
}

export function getTBGoods(materialId, pageNo = 1, pageSize = 20) {
  return get('/goods/'+materialId, {
    pageNo: pageNo,
    pageSize: pageSize
  })
}
