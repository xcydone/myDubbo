/**
 * 封装请求
 */
import axios from 'axios'
import config from '@/config'
import { deepClone } from './index'

let instance = axios.create({
  baseURL: '/',
  withCredentials: false,
  timeout: 20000,
  headers: {
    'Accept': '*',
    'Content-Type': 'application/json'
  }
})

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
    let prefix = config.apiPath
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
