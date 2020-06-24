import fetch from '@/utils/fetch'

export default {
  sayFirst() {
    return fetch(`/first/sayFirst`, {}, {method: 'get'})
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
