<template>
    <a-config-provider :locale="locale">
        <div class="index">
            <a-layout>
                <a-affix :offset-top="0">
                    <div class="isLogin">
                        <div class="isLoginContent">
                            <div class="content-item">
                                欢迎您！庞上智
                            </div>
                            <div class="content-item" id="vip-center">
                                <router-link to="/home">会员中心</router-link>
                            </div>
                            <a-button class="exit_button" type="link" @click.native.prevent="sayHello">
                                退出
                            </a-button>
                        </div>
                    </div>
                    <div class="header">
                        <div class="headerLogo">
                            <div class="headerLogoImg">
                                <img src="http://139.224.22.9:9090/db-final/20201205/pku.png" alt />
                            </div>
                            <div class="headerLogoName">
                                智慧场馆
                            </div>
                        </div>
                        <div class="tabWrap">
                            <a-menu mode="horizontal" :default-selected-keys="['1']">
                                <a-menu-item key="1">
                                    <router-link to="/index">首页</router-link>
                                </a-menu-item>
                                <a-menu-item key="2">
                                    <router-link to="/index">预约租借</router-link>
                                </a-menu-item>
                                <a-menu-item key="3">
                                    <router-link to="/index/suggest">预约须知</router-link>
                                </a-menu-item>
                                <a-menu-item key="4">
                                    <router-link to="/index">场馆地图</router-link>
                                </a-menu-item>
                                <a-menu-item key="5">
                                    <router-link to="/index">联系我们</router-link>
                                </a-menu-item>
                            </a-menu>
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
        }
    },
    methods: {
        sayHello() {
            alert("已退出")
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

.isLogin {
    width: 100%;
    height: 48px;
    background: #990007;
}

.isLoginContent {
    margin-left: 1100px;
    height: 100%;
    display: flex;
    align-items: center;
    color: #ffffff;
}

.content-item {
    margin: 0px 10px;
}

#vip-center a,
#vip-center a:hover {
    color: #f0b953 !important;
}

.ant-btn-link {
    color: #ffffff !important;
}

.header {
    width: 100%;
    height: 80px;
    background: #ffffff;
    box-shadow: 0 3px 6px rgba(0, 0, 0, .08)
}

.headerLogo {
    margin-left: 160px;
    width: 300px;
    height: 100%;
    float: left;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.headerLogoImg {
    float: left;
    height: 100%;
    display: flex;
    align-items: center;
}

.headerLogoName {
    margin-left: 24px;
    height: 100%;
    align-items: center;
    display: flex;
    font-size: 24px;
    font-weight: 400;
    color: rgb(50, 57, 62);
}

.tabWrap {
    margin-left: 340px;
    width: 640px;
    height: 100%;
    float: left;
}

.ant-menu-item {
    font-size: 18px;
    font-weight: 400;
    line-height: 76px;
    margin: 0 5px;
    border-bottom: 4px solid #ffffff !important;
}

.ant-menu-item-selected {
    color: rgb(50, 57, 62) !important;
    border-bottom: 4px solid #eb6a1b !important;
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