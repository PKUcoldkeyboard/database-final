<template>
    <a-config-provider :locale="locale">
        <div class="index">
            <a-layout>
                <a-affix :offset-top="0">
                    <div class="topBar">
                        <div class="flex-spacer"></div>
                        <div style="width:33%"><a target="_blank" href="https://github.com/PKUcoldkeyboard/database-final">This project is open source, you can learn more about our project in Github.</a>
                        </div>
                        <div class="webTool">
                            <router-link to="/store/cart">
                                <a-icon type="shopping-cart" />
                                购物车
                            </router-link>
                            <router-link to="/store/order">我的订单</router-link>
                            <router-link to="/store/center">个人中心</router-link>
                            <router-link to="/store/help">帮助中心</router-link>
                        </div>
                    </div>
                    <div class="header">
                        <div class="headerLogo">
                            <router-link to="/store/index">
                                <span>Qsinghua University</span>
                            </router-link>
                        </div>
                        <div class="tabWrap">
                            <a-menu mode="horizontal" :selectedKeys="[this.$route.path]">
                                <a-menu-item key="/store/index">
                                    <router-link to="/store/index">首页</router-link>
                                </a-menu-item>
                                <a-menu-item key="/store/specials">
                                    <router-link to="/store/specials">每日特价</router-link>
                                </a-menu-item>
                                <a-menu-item key="/store/bestSeller">
                                    <router-link to="/store/bestSeller">畅销榜</router-link>
                                </a-menu-item>
                                <a-menu-item key="/store/dynamic">
                                    <router-link to="/store/dynamic">动态</router-link>
                                </a-menu-item>
                                <a-menu-item key="/store/contact">
                                    <router-link to="/store/contact">联系我们</router-link>
                                </a-menu-item>
                            </a-menu>
                        </div>
                        <div class="login-register">
                            <a-button v-if="login_status===false" type="link" class="login-btn" @click.native.prevent="handleLogin">登录</a-button>
                            <a-button v-else type="link" class="login-btn" @click.native.prevent="handleLogout">登出</a-button>
                            <a-button type="primary" class="register-btn" @click.native.prevent="handleRegister">注册</a-button>
                        </div>
                    </div>
                </a-affix>
                <a-layout-content class="content">
                    <app-main></app-main>
                </a-layout-content>
                <a-layout-footer class="footer">
                    版权所有 北京大学数据库小组
                </a-layout-footer>
            </a-layout>
        </div>
    </a-config-provider>
</template>
<script>
import { AppMain } from './components'
import zhCN from 'ant-design-vue/lib/locale-provider/zh_CN'
export default {
    name: 'layout2',
    components: {
        AppMain
    },
    data() {
        return {
            locale: zhCN,
            login_status: false,
        }
    },
    created() {
        if (this.$store.state.user.token) {
            this.login_status = true;
        }
    },
    methods: {
        handleLogin() {
            this.$router.push({ path: '/login' })
        },
        handleRegister() {
            this.$router.push({ path: '/register' })
        },
        handleLogout() {
            this.$store.dispatch('Logout').then(() => {
                alert('登出成功!')
            });
        }
    },
    computed: {
        token() {
            return this.$store.state.user.token;
        }
    },
    watch: {
        token(val) {
            if (this.login_status) {
                this.login_status = false;
            } else {
                this.login_status = true;
            }
        }
    }
}
</script>
<style scope>
html {
    line-height: 1.15;
    /* Correct the line height in all browsers */
    -webkit-text-size-adjust: 100%;
    /* Prevent adjustments of font size after orientation changes in iOS. */
}

body {
    margin: 0;
}

a {
    background-color: transparent;
    /* Remove the gray background on active links in IE 10. */
}

img {
    border-style: none;
    /*  Remove the border on images inside links in IE 10. */
}

.index .ant-layout {
    background: #ffffff;
}

.topBar {
    width: 100%;
    height: 40px;
    background: #564695;
    display: flex;
    align-items: center;
    justify-content: center;
}

.webTool {
    display: flex;
    justify-content: center;
    width: 33%;
}

.webTool a {
    padding: 10px;
}

.topBar .flex-spacer {
    width: 33%;
}

.topBar a {
    color: #fff !important;
}

.ant-btn-link {
    color: #ffffff !important;
}

.header {
    width: 100%;
    height: 64px;
    background: #ffffff;
    box-shadow: 0 3px 6px rgba(0, 0, 0, .08);
    display: flex;
}

.login-register {
    display: flex;
    width: 33%;
    align-items: center;
    justify-content: center;
}

.tabWrap {
    width: 50%;
    display: flex;
    justify-content: flex-start;
}

.login-btn {
    margin-right: 2%;
    color: #564695 !important;
}

.headerLogo {
    width: 25%;
    font-size: 1.5rem !important;
    font-weight: 600 !important;
    text-decoration: none !important;
    align-items: center;
    justify-content: flex-end;
    padding: 0 1.25rem 0 1.875rem;
    display: flex;
}

.ant-menu-item {
    font-size: 16px !important;
    font-weight: 500;
    line-height: 60px;
    margin: 0 5px;
    border-bottom: 4px solid #ffffff !important;
}

.ant-menu-item-selected {
    color: rgb(50, 57, 62) !important;
    border-bottom: 4px solid #564695 !important;
}

.ant-menu-item-active {
    background-color: #fff !important;
    color: rgb(50, 57, 62) !important;
}

.ant-menu-item a {
    color: rgb(50, 57, 62) !important;
}

.content {
    width: 1200px;
    margin-top: 64px;
    margin-bottom: 16px;
    margin-left: auto;
    margin-right: auto;
    min-height: 440px;
    color: black;
}

.footer {
    background: #fff;
    width: 100%;
    bottom: 0;
    height: 80px;
    color: #484843;
    text-align: center;
    border-top: 1px solid #dcdcdc;
}
</style>