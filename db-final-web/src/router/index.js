import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

/*Layout */
import Layout from '@/views/layout/Layout'

const routes = [{
        path: '/login',
        name: 'login',
        component: () => import('@/views/login/index')
    },
    {
        path: '/register',
        name: 'register',
        component: () => import('@/views/register/index')
    },
    {
        path: '',
        component: Layout,
        redirect: '/home',
        children: [{
            path: 'home',
            name: 'home',
            component: () => import('@/views/home/index'),
            meta: { title: '首页', icon: 'home' }
        }]
    },
    {
        path: '/index',
        component: () => import('@/views/layout/Layout2'),
        children: [{
            path: 'suggest',
            name: 'suggest',
            component: () => import('@/views/suggest/index')
        }]
    }
]

const router = new VueRouter({
    routes
})

export default router