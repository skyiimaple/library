import Vue from 'vue'
export default {
  // 后台登录接口
  login (p) {
    return Vue.$post('/api/auth/userlogin', p)
  },
  // 获取图书管理
  getBookManage (p) {
    return Vue.$get('/api/cms/book/list/' + p.page + '/' + p.size, { bid: p.bid })
    // return Vue.$get('/api/portal/list/book/' + p.page + '/' + p.size, { bid: p.bid })
  },
  // 获取图书分类管理
  getCatgoryManage (p) {
    return Vue.$get('/api/cms/cat/list/', p)
  },
  // 获取分类标签
  getTags (p) {
    return Vue.$get('/api/cms/cat/list/' + p.page + '/' + p.size)
    // return Vue.$get('/api/portal/list/cat/' + p.page + '/' + p.size)
  },

  // 搜索
  getSearch (p) {
    return Vue.$get('/search/get', p)
  },
  // 获取详情
  // getInfoById (p) {
  //   return Vue.$get('/api/cms/book/get/desc/' + p)
  // },
  // getInfoById2 (p) {
  //   return Vue.$get('/api/cms/book/get/' + p)
  // },
  // 获取详情
  getInfoByName (p) {
    return Vue.$get2('/api/portal/get/book/' + p)
  },
  // 获取评论
  getCommon (p) {
    return Vue.$get('/api/cms/comment/list/' + p)
  },
  // 热门推荐
  getRecommendList (p) {
    return Vue.$get('/api/portal/recommend/hot')
  }
}
