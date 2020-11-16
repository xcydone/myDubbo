import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);
const store = new Vuex.Store({
  state: {
    user: {},
    noPrivComp:[],
    translate: ''
  },
  mutations: {
    setUser: (state, user) => {
      user = user || {};
      state.user = user;
    },
    setNoPrivComp: (state,compId) => {
      if(compId && state.noPrivComp.indexOf(compId) == -1){
        state.noPrivComp.push(compId)
      }
    },
    setTranslate: (state,translate)=> {
      state.translate = translate
    }
  }
});

export default store
