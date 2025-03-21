import Vue from 'vue'
export default {
  // 后台登录接口
  login (p) {
    return Vue.$post('/api/auth/userlogin', p)
  },
  // 获取图书管理
  getBookManage (p) {
    return Vue.$get('/api/cms/book/list/' + p.page + '/' + p.size)
  },
  // 获取图书分类管理
  getCatgoryManage (p) {
    return Vue.$get('/api/cms/cat/list/' + p.page + '/' + p.size)
  },
  // 新增分类
  addCategory (p) {
    return Vue.$mypost('/api/cms/cat/add', p)
  },
  // 删除分类
  deleteCategory (p) {
    return Vue.$mypost('/api/cms/cat/delete/' + p)
  },
  // 修改分类
  editCatgory (p) {
    return Vue.$mypost('/api/cms/cat/edit/' + p.id, p)
  },
  // 新增图书
  addBook (p) {
    return Vue.$mypost('/api/cms/book/add', p)
  },
  // 新增图书详情
  addingBook (p) {
    return Vue.$mypost('/api/cms/book/add/desc/' + p)
  },
  // 删除图书
  deleteBook (p) {
    return Vue.$mypost('/api/cms/cat/delete/' + p)
  },
  // 修改图书
  editBook (p) {
    return Vue.$put('/api/cms/cat/edit/' + p)
  },
  // 修改图书详情
  editingBook (p) {
    return Vue.$put('/api/cms/cat/edit/desc/' + p)
  },
  // 新增评论
  addCommon (p) {
    return Vue.$mypost('/api/cms/comment/add/' + p.uid + '/' + p.bid, { content: p.text })
  },
  // 获取评论
  getCommon (p) {
    return Vue.$get('/api/cms/comment/list/' + p.page + '/' + p.size)
  },
  // 删除评论
  deleteComment (p) {
    return Vue.$mypost('/api/cms/comment/delete/' + p)
  },
  // 获取令牌
  getJwt (p) {
    return Vue.$get('/api/auth/userjwt', p)
  }
}
