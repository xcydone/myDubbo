import lscache from 'lscache'
import config from '../config'

const TOKEN = config.accessToken || 'accessToken'
const LOGIN_TIME = 'UserLoginTime'
const USER_INFO = 'UserInfo'

/**
 * 空对象判断
 * @param obj
 * @returns {boolean}
 */
export const isEmpty = (obj) => {
  if (obj) {
    for (const key in obj) return false
    return true
  } else {
    return true
  }
}

/**
 * 获取登录剩余时间秒数
 * @returns {number}
 */
export const getLoginRemainingTime = () => {
  let loginTime = Math.ceil(+lscache.get(LOGIN_TIME) / 1000)
  return (config.sessionDuration || 30 * 60 * 1000) / 1000 - (Math.ceil(Date.now() / 1000) - loginTime)
}

/**
 * 从缓存获取用户信息
 */
export const getUserInfoFromCache = () => {
  if (typeof lscache.get(USER_INFO) === 'object') {
    return lscache.get(USER_INFO)
  } else {
    return JSON.parse(lscache.get(USER_INFO) || '""')
  }
}

export const getAccessToken = () => {
  return lscache.get(TOKEN)
}

export const clearAccessToken = () => {
  lscache.remove(TOKEN)
}

/**
 * 保存用户信息到缓存
 * @param userInfo
 */
export const setUserInfoToCache = (userInfo) => {
  lscache.set(USER_INFO, JSON.stringify(userInfo))
  lscache.set(LOGIN_TIME, Date.now())
  lscache.set(TOKEN, userInfo[TOKEN])
}

/**
 * 移除缓存中的用户信息
 */
export const removeUserInfoFromCache = () => {
  lscache.remove(USER_INFO)
  lscache.remove(LOGIN_TIME)
  lscache.remove(TOKEN)
}

/**
 * 参数对象转换成请求参数字符串
 * @param params
 * @returns {*}
 */
export const params2query = (params) => {
  if (typeof params !== 'object') return ''
  var queries = []
  for (var i in params) {
    if (params.hasOwnProperty(i)) {
      params[i] && queries.push(i + '=' + params[i])
    }
  }
  return queries.join('&')
}
export const query2params = (query) => {
  if (typeof query !== 'string') return {}
  let param = {}
  let params
  let kv
  params = query.split('&')
  for (let i = 0, len = params.length; i < len; i++) {
    if (!params[i]) continue
    kv = params[i].split('=')
    if (kv[0] && kv[1]) param[kv[0]] = kv[1]
  }
  return param
}
/**
 * 判断是否是链接
 * @param path
 * @returns {boolean}
 */
export const isExternal = (path) => {
  return /^(https?:|mailto:|tel:)/.test(path)
}
/**
 * 判断是否存在class
 * @param ele
 * @param cls
 * @returns {boolean}
 */
export const hasClass = (ele, cls) => {
  return !!ele.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'))
}
/**
 * 添加class
 * @param ele
 * @param cls
 */
export const addClass = (ele, cls) => {
  if (!hasClass(ele, cls)) ele.className += ' ' + cls
}
/**
 * 删除class
 * @param ele
 * @param cls
 */
export const removeClass = (ele, cls) => {
  if (hasClass(ele, cls)) {
    const reg = new RegExp('(\\s|^)' + cls + '(\\s|$)')
    ele.className = ele.className.replace(reg, ' ')
  }
}

/**
 * 深拷贝
 * @param source
 * @returns {*}
 */
export const deepClone = (source) => {
  if (!source && typeof source !== 'object') {
    throw new Error('error arguments', 'deepClone')
  }
  const targetObj = source.constructor === Array ? [] : {}
  Object.keys(source).forEach(keys => {
    if (source[keys] && typeof source[keys] === 'object') {
      targetObj[keys] = deepClone(source[keys])
    } else {
      targetObj[keys] = source[keys]
    }
  })
  return targetObj
}
/**
 * 判断非负整数
 * @param num
 * @returns {boolean}
 */
export const isInteger = (num) => {
  if ((num + '').indexOf('.') > -1) {
    return false
  } else {
    return /^[1-9]\d*|0$/.test(num)
  }
}
/**
 * 判断b的值是否和a相等
 * @param a
 * @param b
 * @returns {boolean}
 */
export const valueEquals = (a, b) => {
  if (a === b) return true
  if (!(a instanceof Array)) return false
  if (!(b instanceof Array)) return false
  if (a.length !== b.length) return false
  for (let i = 0; i !== a.length; ++i) {
    if (a[i] !== b[i]) return false
  }
  return true
}
