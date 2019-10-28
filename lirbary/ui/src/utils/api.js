import Vue from 'vue'
export default {
  // 后台登录接口
  login (p) {
    return Vue.$my_get('/home/admin_login', p)
  }
}
