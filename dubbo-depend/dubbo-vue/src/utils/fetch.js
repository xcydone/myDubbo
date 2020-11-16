/**
 * 封装请求
 */
import axios from 'axios'
import { deepClone } from './index'
import { Message,MessageBox } from 'element-ui'
/* eslint-disable prefer-promise-reject-errors */
let instance = axios.create({
  baseURL: '/',
  withCredentials: false,
  timeout: 20000,
  headers: {
    'Accept': '*',
    'Content-Type': 'application/json'
  }
})
/*// 拦截请求
instance.interceptors.request.use(function (req) {
  const token = uas.getToken()
  if (token) {
    req.headers['uas_tt'] = token
  }
  return req
}, function (err) {
  Message({ message: err, type: 'error' })
  return Promise.reject(err)
})
// 拦截返回
instance.interceptors.response.use(function (res) {
  if (res.data.code === 0) {
    return res.data.data
  } else {
    Message({ message: res.data.message, type: 'error' })
    return Promise.reject(res.data.message)
  }
}, function (err) {
  if (err.response) {
    switch (err.response.status) {
      case 401: // 返回 401 清除token信息并跳转到登录页面
        // case 403:
        MessageBox.alert('会话已过期, 请重新登录!', '提示', {
          confirmButtonText: '确定',
          type: 'warning',
          showClose: false,
          callback: _ => {
            window.location.href = '/login';
          }
        })
        break
      case 500:
      case 504:
        Message({ message: '服务器异常', type: 'error' })
        return Promise.reject(err)
      default:
        Message({ message: '系统异常', type: 'error' })
        return Promise.reject(err)
      // break
    }
  } else {
    Message({ message: err, type: 'error' })
    return Promise.reject(err)
  }
})*/

export default async (url = '', query = {}, option = {}) => {
  let params = deepClone(query)
  for (const key in params) {
    if (params[key] !== 0 && !params[key]) {
      delete params[key]
    }
  }
  if (!url) {
    return Promise.reject(`params 'url' not exist！`)
  }
  let method = option.method || 'post'
  if (url.indexOf('http') !== 0) {
    let prefix = 'http://127.0.0.1:8092/'
    if (typeof prefix === 'string') {
      url = prefix + url
    }
  }
  switch (method) {
    case 'get':
      return instance.get(url, {
        params: params
      })
    case 'upload':
      const formData = new FormData()
      for (const key in params) {
        formData.append(key, params[key])
      }
      return instance.post(url, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
    case 'post':
      return instance.post(url, params, option)
    case 'put':
      return instance.put(url, params, option)
    case 'patch':
      return instance.patch(url, params, option)
    case 'delete':
      return instance.delete(url, {
        params: params
      })
    default:
      return Promise.reject(`unknown request method '${method}'`)
  }
}
