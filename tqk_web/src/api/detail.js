import {get, post} from './common'

export function getItemDetail(id) {
  return get('/item/detail', {id: id})
}

export function getGoodDetail() {
  return post('/goods/detail', localStorage.getItem('search_good_obj'))
}

