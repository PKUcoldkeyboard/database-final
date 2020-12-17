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
        path:'',
        redirect:'/index/head'
    },
    {
        path: '/index',
        component: () => import('@/views/layout/Layout2'),
        children: [
        {
            path: 'suggest',
            name: 'suggest',
            component: () => import('@/views/suggest/index')
        },
        {
            path:'reserve',
            name:'reserve',
            component:()=>import('@/views/reserve')
        },
        {
            path:'map',
            name:'map',
            component:()=>import('@/views/map')
        },
        {
            path:'contact',
            name:'contact',
            component:()=>import('@/views/contact')
        },
        {
            path:'head',
            name:'head',
            component:()=>import('@/views/head')
        }]
    }
]

const router = new VueRouter({
    routes
})

export default router