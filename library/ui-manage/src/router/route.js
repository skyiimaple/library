const allRouter = [
  {
    path: '/',
    name: 'login',
    redirect: '/login',
    component: () => import('@/views/login'),
    children: [
      {
        path: '/login',
        name: 'login',
        component: () => import('@/views/login')
      }
    ]
  },
  {
    path: '/home',
    name: 'Main',
    // redirect: '/info',
    component: () => import('@/views/Main'),
    children: [
      {
        path: '/info',
        name: 'info',
        component: () => import('@/views/home/info')
      },
      {
        path: '/user',
        name: 'user',
        component: () => import('@/views/home/user')
      },
      {
        path: '/category',
        name: 'category',
        component: () => import('@/views/home/category')
      },
      {
        path: '/comment',
        name: 'comment',
        component: () => import('@/views/home/comment')
      }
    ]
  }
]
export const routes = [...allRouter]
