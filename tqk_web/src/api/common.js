import axios from 'axios'

export const TIME_OUT = 5000
export const HOST = '/tb'
export const PLATFORM = localStorage.getItem("platform")

axios.defaults.timeout = TIME_OUT

axios.interceptors.request.use(
  config => {
    let token = localStorage.getItem('token')
    config.headers['Content-Type'] = 'application/json;charset=UTF-8'
    config.headers['Authorization'] = ''
    if(token!=null){
      config.headers['Authorization'] = token
    }
    return config
  }
)

axios.interceptors.response.use(
  resp => {
    return Promise.resolve(resp)
  },
  error => {
    return Promise.reject(error)
  }
)

export function get(url, params = {}) {
  return axios({
    url: HOST + url,
    method: 'GET',
    params: params
  })
}

export function post(url, data) {
  return axios({
    url: HOST + url,
    method: 'POST',
    data: data
  })
}
