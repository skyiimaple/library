const allRouter = [
  {
    path: '/',
    name: 'Main',
    redirect: '/home',
    component: () => import('@/views/Main'),
    children: [
      {
        path: '/home',
        name: 'info',
        component: () => import('@/views/home/info')
      },
      {
        path: '/person',
        name: 'person',
        component: () => import('@/views/person')
      },
      {
        path: '/library',
        name: 'library',
        component: () => import('@/views/library')
      },
      {
        path: '/bookShop',
        name: 'bookShop',
        component: () => import('@/views/bookShop')
      },
      {
        path: '/annualList',
        name: 'annualList',
        component: () => import('@/views/annualList')
      }
    ]
  }
]
export const routes = [...allRouter]
