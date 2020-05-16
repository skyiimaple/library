import 'babel-polyfill'
import Vue from 'vue'
import App from './App.vue'
import {
  router
} from './router/index'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import fetch from '@/utils/index.js'
import api from '@/utils/api.js'
import store from './store'

Vue.config.productionTip = false
Vue.use(Antd)
Vue.use(fetch, {
  domaintest: 'http://127.0.0.1:8888'
})
Vue.prototype.$api = api
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
