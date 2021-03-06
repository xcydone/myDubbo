import Vue from 'vue'
import Router from 'vue-router'
import MainLayout from '@/components/mainLayout/MainLayout'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/systable/userList'
    },
    {
      path: '/systable',
      name: 'systable',
      component: MainLayout,
      redirect: '/systable',
      meta: {title: '系统管理', disabledDashboard: true},
      children: [{
        path: 'userList',
        component: () => import('@/pages/systable/user/userList'),
        meta: {title: '用户管理'}
      },
      {
        path: 'userList/detail-edit',
        name: 'detail-edit',
        component: () => import('@/pages/systable/user/detail-edit'),
        meta: {title: '用户编辑'}
      },
      {
        path: 'entityList',
        component: () => import('@/pages/systable/entity/entityList'),
        meta: {title: '企业管理'}
      },
      {
        path: 'dictList',
        component: () => import('@/pages/systable/dict/dictList'),
        meta: {title: '字典管理'}
      },
      {
        path: 'dictDetail',
        name: 'dictDetail',
        component: () => import('@/pages/systable/dict/dictDetail'),
        meta: {title: '字典详情'}
      }]
    },
    {
      path: '/test/tableTitle',
      component: () => import('@/pages/test/testTitle/tableTitle'),
    },
    {
      path: '/test/tableTitle2',
      component: () => import('@/pages/test/testTitle/sheet'),
    },
    {
      path: '/test/testRender',
      component: () => import('@/pages/test/testRender'),
    },
    {
      path: '/test/testColor',
      component: () => import('@/pages/test/testColor/testColorChange'),
    },
    {
      path: '/test/testRowMerge',
      component: () => import('@/pages/test/testRowMerge/table'),
    },
    {
      path: '/test/testClock',
      component: () => import('@/pages/test/testClock/clock'),
    },
    {
      path: '/test/testSummery',
      component: () => import('@/pages/test/testSummery/summ'),
    },
    {
      path: '/test/testVant',
      component: () => import('@/pages/test/testVant/testVant'),
    }]
})
