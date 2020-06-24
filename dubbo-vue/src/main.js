// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import Print from 'vue-print-nb'
import api from './api'

Vue.prototype.baseUrl = 'http://localhost:8100/'
Vue.prototype.api = api

Vue.use(ElementUI)
Vue.use(Print)

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
