import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

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
        path:'/findPwd',
        name:'findPwd',
        component:()=>import('@/views/findPwd/index')
    },
    {
        path:'',
        redirect:'/store/index'
    },
    {
        path: '/store',
        component: () => import('@/views/layout/Layout'),
        children: [
        {
            path: 'suggest',
            name: 'suggest',
            component: () => import('@/views/suggest')
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
            path:'index',
            name:'index',
            component:()=>import('@/views/index')
        }]
    }
]

const router = new VueRouter({
    routes
})

export default router