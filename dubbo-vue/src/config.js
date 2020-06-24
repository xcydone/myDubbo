// 应用具体配置
const APP_CONFIG = {
  /*sysName: 'dubbo-web',
  httpHeaders: {
    'Content-Type': 'application/json'
  },
  // session有效时间 ms
  sessionDuration: 30 * 60 * 1000,
  // 首页路由名称, 用于处理遭遇各种异常路由时的最终跳转路由
  indexPageName: 'Dashboard',
  // 登录页路由名称
  loginPageName: 'login',
  // 百度地图ak
  ak: 'L4Sgd9YzuudEdv0FqukvZRr0TLavQ5cg',
  setting: {
    fixedHeader: true,
    needTagsView: false,
    showLogo: true
  },
  locale: 'zh',
  */
  formSize: 'medium', // element-ui form 的大小 medium / small / mini
  apiPath: process.env.VUE_APP_apipath ? process.env.VUE_APP_apipath : '', // 接口服务器路径
  /*authPath: process.env.VUE_APP_authpath,
  accessToken: 'token',
  // 渲染错误处理
  errorHandler (e) {
    console.error('捕获到了错误：' + e)
  }*/
}

module.exports = APP_CONFIG
