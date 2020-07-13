// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import Print from 'vue-print-nb'
import api from './api'
import store from "./store";

Vue.prototype.baseUrl = 'http://127.0.0.1:8092/'
Vue.prototype.api = api

Vue.use(ElementUI)
Vue.use(Print)

Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  // debugger
  if (store.state.oper.operId && to.path.startsWith("/login")) {
    router.go(-1);
    next(false);
  }
  if (to.meta.requireAuth) {
    if (store.state.oper.operId) {
      // this.api.getMenu(store.state.oper.operId).then(resp => {
      next();
      // })
    } else {
      next({
        path: "login",
        query: { redirect: to.fullPath },
      });
    }
  } else {
    next();
  }
});

new Vue({
  el: '#app',
  router,
  store: store,
  components: { App },
  template: '<App/>'
})
