import 'babel-polyfill'
import Vue from 'vue'
import App from './App.vue'
import {
  router
} from './router/index'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import myUtils from '@/utils/index.js'
import api from '@/utils/api.js'

Vue.config.productionTip = false
Vue.use(Antd)
Vue.use(myUtils, {
  domaintest: 'http://127.0.0.1:8080'
})
Vue.prototype.$api = api
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
