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
/*        {
            path: 'bestSeller',
            name: 'bestSeller',
            component: () => import('@/views/bestSeller')
        },*/
        {
            path:'specials',
            name:'specials',
            component:()=>import('@/views/specials')
        },
/*        {
            path:'dynamic',
            name:'dynamic',
            component:()=>import('@/views/dynamic')
        },*/
        {
            path:'contact',
            name:'contact',
            component:()=>import('@/views/contact')
        },
        {
            path:'index',
            name:'index',
            component:()=>import('@/views/index')
        },
        {
            path:'cart',
            name:'cart',
            component:()=>import('@/views/cart')
        }
        ,
        {
            path:'help',
            name:'help',
            component:()=>import('@/views/help')
        },
        {
            path:'center',
            name:'center',
            component:()=>import('@/views/center')
        },
        {
            path:'order',
            name:'order',
            component:()=>import('@/views/order')
        },
        {
            path:'steps',
            name:'steps',
            component:()=>import('@/views/a-steps')
        }
        ]
    }
]

const router = new VueRouter({
    routes
})

export default router