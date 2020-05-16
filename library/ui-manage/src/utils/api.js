import Vue from 'vue'
export default {
  // 后台登录接口
//   login (p) {
//     return Vue.$post('', p)
//   },
// 获取图书管理
  getBookManage (p) {
    return Vue.$get('/cms/book/list/' + p.page + '/' + p.size)
  },
  // 获取图书分类管理
  getCatgoryManage (p) {
    return Vue.$get('/cms/cat/list/', p)
  }
}
