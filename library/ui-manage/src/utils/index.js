import axios from 'axios'
import QS from 'qs'
import { router } from '@/router/index'
import Message from 'ant-design-vue/lib/message'
const myUtils = {}

// http response(响应拦截)
axios.interceptors.response.use(
  response => {
    // 可以在这里做全部统一处理
    if (response.data.success) {
      return Promise.resolve(response)
    } else {
      if (response.data.code) {
        switch (response.data.code) {
          case '9999':
            Message.error(response.data.message)
            return Promise.reject(response)
          case '1001':
            Message.error(response.data.message, 3, () => {
            })
            return Promise.reject(response)
          default:
            Message.error(response.data.message, 3, () => {
              router.replace({
                path: '/login'
              })
            })
            return Promise.reject(response)
        }
      }
    }
  },
  error => {
    // 对响应错误做点什么
    return Promise.reject(error.response)
  }
)
// http request(请求拦截)
axios.interceptors.request.use(
  config => {
    return config
  },
  error => {
    return Promise.reject(error)
  }
)
axios.defaults.withCredentials = true

myUtils.install = (Vue, options) => {
  let host = process.env.NODE_ENV === 'development' ? options.domaintest : window.location.origin
  console.log(host)
  // axios.defaults.baseURL = host
  // post请求(From Data)
  Vue.$post_from = (url, params) => {
    return new Promise((resolve, reject) => {
      axios.post(url, QS.stringify(params), { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } })
        .then(response => {
          resolve(response.data)
        }).catch((error) => {
          reject(error)
        })
    })
  }
  // post请求(Request Payload)
  Vue.$my_post = (url, params) => {
    return new Promise((resolve, reject) => {
      axios.post(url, params)
        .then(response => {
          resolve(response.data)
        })
        .catch(err => {
          reject(err.data)
        })
    })
  }
  // get请求(Request Payload)
  Vue.$my_get = (url, params = {}) => {
    return new Promise((resolve, reject) => {
      axios.get(url, {
        params: params
      })
        .then(response => {
          resolve(response.data)
        })
        .catch(err => {
          reject(err)
        })
    })
  }
}
export default myUtils
