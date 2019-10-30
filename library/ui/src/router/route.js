const allRouter = [{
  path: '/login',
  name: 'login',
  component: () => import('@/views/login')
},
{
  path: '/',
  name: 'I',
  redirect: '/index',
  component: () => import('@/views/Main'),
  children: [{
    path: '/index',
    name: 'info',
    component: () => import('@/views/info')
  }]
}
]
export const routes = [
  ...allRouter
]
