import {get, post} from './common'

export function getGoodDetailById(id, needPicts) {
  return get('/goods/detail', {'id': id, 'needPicts': needPicts})
}

export function getGoodDetail() {
  return post('/goods/tbk/detail', localStorage.getItem('search_good_obj'))
}

