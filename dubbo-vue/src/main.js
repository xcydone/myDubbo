/*
// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

//引入element-ui的全部组件
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'//element-ui的css
Vue.use(ElementUI) //使用elementUI

// 引入IVIEW组件
import iView from 'iview'
import 'iview/dist/styles/iview.css'
Vue.use(iView)

// 引入vue-easytable
import 'vue-easytable/libs/themes-base/index.css'
import {VTable, VPagination} from 'vue-easytable'
Vue.component(VTable.name, VTable)
Vue.component(VPagination.name, VPagination)

//引入axios
/!*import axios from 'axios'
Vue.prototype.$ajax = axios*!/


Vue.config.productionTip = false

/!* eslint-disable no-new *!/
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
*/


import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import router from '@/router'
import store from '@/store'
import api from './api'
import GeminiScrollbar from 'vue-gemini-scrollbar'
import echarts from 'echarts'

import Vant from 'vant'
import 'vant/lib/index.css'

import '@/assets/style/index.scss'

Vue.use(ElementUI)
Vue.use(GeminiScrollbar)
Vue.use(Vant);

Vue.prototype.api = api
Vue.prototype.$echarts = echarts
Vue.prototype.baseUrl = 'http://127.0.0.1:8092/'

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
