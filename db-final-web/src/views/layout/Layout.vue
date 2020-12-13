<template>
	<a-config-provider :locale="locale">
	<div class="app-wrapper">
		<a-layout>
			<a-layout-header class="header">
				<div style="width: 1136px;margin-left: auto;margin-right: auto;">
					<a-row :gutter="[16,16]">
						<a-col :span="8">
						</a-col>
						<a-col :span="8">
							<a-input-search></a-input-search>
						</a-col>
						<a-col :span="6">
						</a-col>
						<a-col :span="2">
							<a-dropdown>
								<a-menu slot="overlay">
									<a-menu-item key="1">
										<router-link to="/login">登录
										</router-link>
									</a-menu-item>
									<a-menu-item key="2">
										<router-link to="/register">注册
										</router-link>
									</a-menu-item>
								</a-menu>
								<a-button style="margin-top:16px;">
									<a-icon type="user"/>
									<span>登录/注册</span>
								</a-button>
							</a-dropdown>
						</a-col>
					</a-row>
				</div>
			</a-layout-header>
			<a-layout-header class="navbar">
				<a-menu
					mode="horizontal"
					:style="{width:'720px',marginLeft:'auto',marginRight:'auto',marginTop:'16px',background:'#ffffff'}">
					<a-sub-menu>
						<span slot="title">全部商品分类</span>
						<a-menu-item key="8">
							option1
						</a-menu-item>
						<a-menu-item key="9">
							option2
						</a-menu-item>
					</a-sub-menu>
					<a-menu-item key="1">
						首页
					</a-menu-item>
					<a-menu-item key="2">
						理学类
					</a-menu-item>
					<a-menu-item key="3">
						工学类
					</a-menu-item>
					<a-menu-item key="4">
						医学类
					</a-menu-item>
					<a-menu-item key="5">
						经济学类
					</a-menu-item>
					<a-menu-item key="6">
						管理学类
					</a-menu-item>
					<a-menu-item key="7">
						文学类
					</a-menu-item>
				</a-menu>
			</a-layout-header>
			<a-layout-header class="banner">
				<div class="inner-page-heading">
					<h2>Shop</h2>
					<p>Spanning fifteen years of work, Everywhere I look is a book full of unexpected moments.</p>
				</div>
			</a-layout-header>
			<a-layout-content><app-main></app-main></a-layout-content>
			<a-layout-footer style="text-align: center;">
				Copyright @ 2020 Shangzhi Pang. All Rights Reserved.<br/>
				Licensed under <b>MIT License</b>.
			</a-layout-footer>
		</a-layout>
	</div>
	</a-config-provider>
</template>

<script>
import {AppMain} from './components'
import zhCN from 'ant-design-vue/lib/locale-provider/zh_CN'
export default{
	name:'layout',
	components:{
		AppMain
	},
	data(){
		return{
			locale:zhCN,
		}
	}
}
</script>

<style scope>
	.app-wrapper{
		position:relative;
		height:100%;
		width:100%;
	}
	.app-wrapper .ant-layout{
		background: #ffffff;
	}
	.app-wrapper .header{
		background: rgb(30,41,61);
		height:64px;
		width:100%;
		margin-bottom:;
	}
	.app-wrapper .banner{
		height: 300px;
		width: 100%;
		background: rgb(70,60,47);
		margin-bottom:16px;
	}
	.app-wrapper .navbar{
		background: #ffffff;
		height: 80px;
		width:100%;
		margin-bottom: 0px;
	}
	.app-wrapper .navbar .logo{
		margin-left:392px;
		width:auto;
		height: 48px;
		float: left;
	}
	.app-wrapper .ant-layout-content{
		background: #ffffff;
		margin-left: auto;
		margin-right: auto;
		min-height: 640px;
		width:1136px;
	}
	.app-wrapper .ant-layout-footer{
		margin-top: 16px;
		margin-left: auto;
		margin-right: auto;
		width: 100%;
		min-height: 48px;
		background: #ffffff;
	}
	.inner-page-heading{
		text-align: center;
		margin-top:72px;
	}
	.inner-page-heading h2{
		color:#fff;
		margin:0;
		font-size:36px;
		font-weight: bold;
	}
	.inner-page-heading p{
		color:#fff;
		margin: 0;
	}
</style>