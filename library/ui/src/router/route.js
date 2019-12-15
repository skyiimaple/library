const allRouter = [{
  path: '/login',
  name: 'login',
  component: () => import('@/views/login')
},
{
  path: '/',
  name: 'Main',
  redirect: '/home',
  component: () => import('@/views/Main')
},
{
  path: '/home',
  name: 'info',
  component: () => import('@/views//home/info')
}
]
export const routes = [...allRouter]
