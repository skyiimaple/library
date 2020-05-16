const myUtils = {}
let num = 0
myUtils.install = Vue => {
  // 动态获取表格宽度
  Vue.prototype.$getTableWidth = (arr, type = 0) => {
    if (type === 0) num = 0
    arr.forEach(res => {
      if (res.width) {
        num += res.width
      }
      if (res.children && res.children.length) {
        Vue.prototype.$getTableWidth(res.children, 1)
      }
    })
    return num
  }
  // 获取字符串时间
  Vue.prototype.$getTime = (date, offset = 0, type = false) => {
    var tdate = new Date(date)
    tdate.setDate(tdate.getDate() + offset)
    var y = tdate.getFullYear()
    var m = tdate.getMonth() + 1
    m = m < 10 ? '0' + m : m
    var d = tdate.getDate()
    d = d < 10 ? '0' + d : d
    if (type) {
      var H = tdate.getHours()
      H = H < 10 ? '0' + H : H
      var M = tdate.getMinutes()
      M = M < 10 ? '0' + M : M
      var S = tdate.getSeconds()
      S = S < 10 ? '0' + S : S
      if (H === '00' && M === '00' && S === '00') {
        return y + '-' + m + '-' + d + ' ' + '12:00:00'
      } else {
        return y + '-' + m + '-' + d + ' ' + H + ':' + M + ':' + S
      }
    } else {
      return y + '-' + m + '-' + d
    }
  }
  // 动态获取高度
  Vue.prototype.$getComputeHeight = sub => {
    var pageHeight = window.innerHeight
    return pageHeight - sub
  }
  // 设置cookie
  Vue.prototype.$setCookie = (name, value, expire) => {
    var date = new Date()
    date.setSeconds(date.getSeconds() + expire)
    document.cookie =
      name +
      '=' +
      escape(value) +
      '; expires=' +
      date.toGMTString() +
      '; path=/;'
  }
  // 获取cookie
  Vue.prototype.$getCookie = name => {
    if (document.cookie.length > 0) {
      let start = document.cookie.indexOf(name + '=')
      if (start !== -1) {
        start = start + name.length + 1
        let end = document.cookie.indexOf(';', start)
        if (end === -1) end = document.cookie.length
        return unescape(document.cookie.substring(start, end))
      }
    }
    return ''
  }
  // 删除cookie
  Vue.prototype.$delCookie = name => {
    var date = new Date()
    date.setSeconds(date.getSeconds() - 1)
    document.cookie =
      name + '=' + escape('') + '; expires=' + date.toGMTString()
  }
  Vue.prototype.$index = (arr, val) => {
    for (var i = 0; i < arr.length; i++) {
      if (arr[i] === val) {
        return i
      }
    }
    return -1
  }
  // 根据数组的下标，删除该下标的元素
  Vue.prototype.$remove = (arr, val) => {
    let index = Vue.prototype.$index(arr, val)
    if (index > -1) {
      arr.splice(index, 1)
    }
    return arr
  }
  // 自定义toFixed
  Vue.prototype.$Fixed = (num, d = 2) => {
    num *= Math.pow(10, d)
    num = Math.round(num)
    return num / Math.pow(10, d)
  }
  // 根据id格式化成树数据结构，data是原始数据，t1是一级名字列名，t2是二级名字列名，t3是children列名
  Vue.prototype.$handleTreeData = (data, t1, t2, t3) => {
    let dataTree = []
    data.forEach((res) => {
      let myObj = { title: '', value: '', key: '', children: [] }
      myObj.title = res[t1]
      myObj.value = myObj.key = res['id']
      res[t3].forEach((item) => {
        if (item) {
          let myChildren = { title: '', value: '', key: '' }
          myChildren.title = item[t2]
          myChildren.value = myChildren.key = myObj.value + '-' + item['id']
          myObj.children.push(myChildren)
        }
      })
      dataTree.push(myObj)
    })
    return dataTree
  }
  // 根据字符数组转成数组
  Vue.prototype.$parseArr = (data) => {
    let list = []
    let arr = data.replace(/"/g, '').replace(/\[/g, '').replace(/\]/g, '').split(', ')
    arr.forEach(item => {
      list.push(item)
    })
    return list
  }
}
export default myUtils
