import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'
const vuexLocal = new VuexPersistence({
  storage: window.localStorage
})
Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    jwt: ''
  },
  getters: {
  },
  mutations: {
    setJwt (state, data) {
      state.jwt = data
    }
  },
  actions: {
  },
  plugins: [vuexLocal.plugin]
})
