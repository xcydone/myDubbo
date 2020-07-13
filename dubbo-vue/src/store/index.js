import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state:sessionStorage.getItem('state') ? JSON.parse(sessionStorage.getItem('state')): {
        token: window.sessionStorage.getItem('token') == null ? '' : window.sessionStorage.getItem('token'),
        oper: {
            operId:window.sessionStorage.getItem('oper') == null ? '' : JSON.parse(window.sessionStorage.getItem('oper')).operId,
            operName:window.sessionStorage.getItem('oper') == null ? '' : JSON.parse(window.sessionStorage.getItem('oper')).operName,
            isNeedModifyPwd:window.sessionStorage.getItem('oper') == null ? false : JSON.parse(window.sessionStorage.getItem('oper')).isNeedModifyPwd,
        },
        userId:''
    },
    mutations: {
        login (state, oper, userId) {
            state.oper = oper
            state.userId = userId
            window.sessionStorage.setItem('oper', JSON.stringify(oper))
        },
        refreshToken(state, token) {
            state.token = token
            window.sessionStorage.setItem('token', JSON.stringify(token))
        },
        logout (state) {
            // 注意不能用 null 清除，否则将无法判断 oper 里具体的内容
            state.oper = []
            state.userId = ''
            state.token = ''
            window.sessionStorage.clear();
        }
    }
})
