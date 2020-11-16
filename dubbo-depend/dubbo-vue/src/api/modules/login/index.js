import fetch from '@/utils/fetch'

export default {

  onReady() {
    return fetch(`/login/onReady`,{},{method: 'get'})
  },

  getCaptcha(randnum) {
    return fetch(`/login/getCaptcha?randnum=`+randnum, {},{method: 'get'})
  },

  //登录
  login(loginDto) {
    return fetch(`/login/loginIn`, loginDto, {method: 'post'})
  },

  /*// 根据名字查询菜单
  getShopList(params = {}) {
    return fetch(`/agent/qryShopByPage`, params, {method: 'post'})
  },

  // 查询全部菜单
  getShopDetail(shopId) {
    return fetch(`/agent/qryShopDetail?shopId=` + shopId, {}, {method: 'get'})
  },*/
}
