<template>
	<div>
		<a-card class="login-form-layout">
			<a-form-model :model="loginForm"
						  :rules="loginRules"
						  ref="loginForm"
						  label-position="left"
						  autoComplete="on">
				<h2 class="login-title">登录</h2>
				<a-form-model-item has-feedback prop="username">
					<a-input type="text"
							 placeholder="请输入帐号" 
							 v-model="loginForm.username"
							 autoComplete="on">
						<a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)"/>
					</a-input>
				</a-form-model-item>
				<a-form-model-item has-feedback prop="password">
					<a-input :type="pwdType"
							 placeholder="请输入密码"
							 v-model="loginForm.password"
							 @keyup.enter.native="handleLogin"
							 autoComplete="on">
						<a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)"/>
						<a-icon slot="suffix" type="eye" style="color:rgba(0,0,0,.25)" @click="showPwd"/>
					</a-input>
				</a-form-model-item>
				<a-form-model-item>
					<a-checkbox :checked="rememberMe" @change="setRemember">记住密码</a-checkbox>
					<router-link class="login-form-forget" to="/findPwd">找回密码</router-link>
				</a-form-model-item>
				<a-form-model-item style="margin-bottom: 60px;text-align: center;">
					<a-button type="primary" :loading="loading" @click.native.prevent="handleLogin"
							  style="width: 100%">
						登录
					</a-button>
					<a-button type="default" @click.native.prevent="handleRegister" style="width: 100%">
						立即注册
					</a-button>
				</a-form-model-item>
			</a-form-model>
		</a-card>
	</div>
</template>

<script>
import {validUsername,validPassword} from '@/utils/validate'
import {setCookie,getCookie} from '@/utils/support'
export default{
	name:'login',
	data(){
		const validateUsername=(rule,value,callback)=>{
			if(!validUsername(value)){
				callback(new Error('请输入正确的用户名'))
			}else{
				callback()
			}
		};
		const validatePassword=(rule,value,callback)=>{
			if(!validPassword(value)){
				callback(new Error('密码为同时含有大小写字母、数字的8-16位组合'))
			}else{
				callback()
			}
		}
		return{
			loginForm:{
				username:'',
				password:''
			},
			loginRules:{
				username:[{required:true,trigger:'blur',validator:validateUsername}],
				password:[{required:true,trigger:'blur',validator:validatePassword}]
			},
			loading:false,
			pwdType:'password',
			rememberMe:false
		}
	},
	created(){
		this.loginForm.username=getCookie("username");
		this.loginForm.password=getCookie("password");
		if(this.loginForm.username===undefined||this.loginForm.username==null||this.loginForm.username===''){
			this.loginForm.username='';
		}else{
			this.rememberMe=true;
		}
		if(this.loginForm.password===undefined||this.loginForm.password==null){
			this.loginForm.password='';
		}
	},
	methods:{
		showPwd(){
			if(this.pwdType==='password'){
				this.pwdType=''
			}else{
				this.pwdType='password'
			}
		},
		setRemember(){
			if(this.rememberMe===true){
				this.rememberMe=false;
			}else{
				this.rememberMe=true;
			}
		},
		handleLogin(){
			this.$refs.loginForm.validate(valid=>{
				if(valid){
					this.loading=true;
					this.$store.dispatch('Login',this.loginForm).then(()=>{
						this.loading=false;
						if(this.rememberMe){
							setCookie("username",this.loginForm.username,15);
							setCookie("password",this.loginForm.password,15);
						}
						this.$router.push({path:'/index/head'})
					}).catch(()=>{
						this.loading=false;
					})
				}else{
					console.log('参数验证不合法');
					return false;
				}
			})
		},
		handleRegister(){
			this.$router.push({path:'/register'})
		}
	}
}
</script>

<style scoped>
	.login-form-layout{
		position: absolute;
		left: 0;
		right: 0;
		width:360px;
		margin: 140px auto;
		box-shadow: 3px 3px 6px rgba(0,0,0,.08)
	}
	.login-title{
		text-align: center;
	}	
	.login-center-layout{
		background: #409EFF;
	    width: auto;
	    height: auto;
	    max-width: 100%;
	    max-height: 100%;
	    margin-top: 200px;
	}
	.login-form-forget{
		float: right;
	}
</style>