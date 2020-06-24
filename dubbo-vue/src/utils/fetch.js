/**
 * 封装请求
 */
import axios from 'axios'
import config from '@/config'
import { deepClone } from './index'
import store from '@/store'
import router from '@/router'

let instance = axios.create({
  baseURL: '/',
  withCredentials: false,
  timeout: 20000,
  headers: {
    'Accept': '*',
    'Content-Type': 'application/json;charset=UTF-8'
  }
})

// 拦截请求
instance.interceptors.request.use(
  config =>{
    config.data = JSON.stringify(config.data);
    config.headers['token'] = window.sessionStorage.getItem("token")
    return config
  },
  error => {
    Message({ message: error, type: 'error' })
    return Promise.reject(error)
  }
)

// 拦截返回
instance.interceptors.response.use(
  response => {
    const headers = response.headers

    // 情况一：上传文件拦截放开
    if (response.headers[`content-disposition`]!==undefined){
      let fileNameEncodeURI = response.headers[`content-disposition`].split(';')[1].substring(9)
      let fileName = decodeURI(fileNameEncodeURI)
      localStorage.setItem("fileName",fileName)
    }

    // 情况二：下载excel文件放开
    if(headers['content-type'] === "application/vnd.ms-excel;charset=utf-8"){
      return response.data

    } else if (response.data.code === 200) {
      // 情况三： 正常数据
      store.commit('refreshToken', response.headers.token)
      return Promise.resolve(response)

    } else if (response.data.code === 1001) {
      // 情况四： 登录出去 清除token
      store.commit('logout');
      let redirectPath = router.currentRoute.fullPath
      if (redirectPath.indexOf('/login') >= 0) {
        router.push({path: redirectPath}) // 防止Token过期后重复跳转
      } else {
        router.push({
          path:"/login",
          query:{redirect: redirectPath}//从哪个页面跳转
        })
      }
      return Promise.reject(response)
    } else {
      // 以上三种情况都不是，不处理直接返回错误信息
      Message({
        message: response.data.msg,
        type: 'error',
        duration: 3 * 1000
      })
      return Promise.reject(response)
    }
  },
  error => {
    if (error.response) {
      // 判断返回状态码
      switch (error.response.status) {
        case 401: // 返回 401 清除token信息并跳转到登录页面
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
          return Promise.reject(error)
        default:
          Message({ message: '系统异常', type: 'error' })
          return Promise.reject(error)
      }
    } else {
      Message({ message: error, type: 'error' })
      return Promise.reject(error)
    }
  }
)

export default async (url = '', query = {}, option = {}) => {
  let params = deepClone(query)
  if (!url) {
    return Promise.reject(`params 'url' not exist！`)
  }
  let method = option.method || 'post'  // 没有设置method时默认给post方式
  if (url.indexOf('http') !== 0) {    // url没有http开头时，补上默认地址
    let prefix = config.apiPath
    if (typeof prefix === 'string') {
      url = prefix + url
    }
  }
  switch (method) {
    case 'get':
      return instance.get(url, { params: params })
    case 'upload':
      const formData = new FormData()
      for (const key in params) {
        formData.append(key, params[key])
      }
      return instance.post(url, formData, { headers: { 'Content-Type': 'multipart/form-data' }})
    case 'post':
      return instance.post(url, params, option)
    case 'put':
      return instance.put(url, params, option)
    case 'patch':
      return instance.patch(url, params, option)
    case 'delete':
      return instance.delete(url, { params: params })
    default:
      return Promise.reject(`unknown request method '${method}'`)
  }
}
