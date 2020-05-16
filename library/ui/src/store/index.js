import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'
const vuexLocal = new VuexPersistence({
  storage: window.localStorage
})
Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    currMenu: { name: '首页', path: 'home' },
    userInfo: ''
  },
  getters: {
    getCurrMenu (state) {
      return state.currMenu
    }
  },
  mutations: {
    setCurrMenu (state, currMenu) {
      state.currMenu = currMenu
    },
    setUserInfo (state, userInfo) {
      state.userInfo = userInfo
    }
  },
  actions: {
  },
  plugins: [vuexLocal.plugin]
})
