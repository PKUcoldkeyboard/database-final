<template>
	<div>
		<a-card class="login-form-layout">
			<a-form-model :model="registerForm"
						  :rules="registerRules"
						  ref="registerForm"
						  label-position="left"
						  autoComplete="on">
				<h2 class="login-title">注册</h2>
				<a-form-model-item has-feedback prop="username">
					<a-input type="text"
							 placeholder="请输入帐号" 
							 v-model="registerForm.username"
							 autoComplete="on">
						<a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)"/>
					</a-input>
				</a-form-model-item>
				<a-form-model-item has-feedback prop="oldPassword">
					<a-input :type="oldPwdType"
							 placeholder="请输入密码"
							 v-model="registerForm.oldPassword"
							 autoComplete="on">
						<a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)"/>
						<a-icon slot="suffix" type="eye" style="color:rgba(0,0,0,.25)" @click="showOldPwd"/>
					</a-input>
				</a-form-model-item>
				<a-form-model-item has-feedback prop="newPassword">
					<a-input :type="newPwdType"
							 placeholder="请再次输入密码"
							 v-model="registerForm.newPassword"
							 @keyup.enter.native="handleRegister"
							 autoComplete="on">
						<a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)"/>
						<a-icon slot="suffix" type="eye" style="color:rgba(0,0,0,.25)" @click="showNewPwd"/>
					</a-input>
				</a-form-model-item>
				<a-form-model-item has-feedback prop="email">
					<a-input type="text"
							 placeholder="请输入邮箱地址"
							 v-model="registerForm.email"
							 autoComplete="on">
						<a-icon slot="prefix" type="mail" style="color:rgba(0,0,0,.25)"/>
					</a-input>
				</a-form-model-item>
				<a-form-model-item has-feedback prop="code">
					<a-input type="text"
							 placeholder="请输入邮箱验证码"
							 v-model="registerForm.code"
							 autoComplete="on">
						<a-icon slot="prefix" type="codepen" style="color:rgba(0,0,0,.25)"/>
						<a-button slot="suffix" :disable="disable"type="link" @click.native.prevent="sendEmail">{{btnValue}}</a-button>
					</a-input>
				</a-form-model-item>
				<a-form-model-item style="margin-bottom: 60px;text-align: center;">
					<a-button type="primary" :loading="loading" @click.native.prevent="handleRegister"
							  style="width: 100%">
						注册
					</a-button>
					<a-button type="default" @click.native.prevent="handleLogin" style="width: 100%">
						已有账号
					</a-button>
				</a-form-model-item>
			</a-form-model>
		</a-card>
	</div>
</template>

<script>
import {validUsername,validPassword,validEmail} from '@/utils/validate'
import {register,verify} from '@/api/login'
import {setCookie} from '@/utils/support'
export default{
	name:'register',
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
		};
		const samePassword=(rule,value,callback)=>{
			if(value!=this.registerForm.oldPassword){
				callback(new Error('两次输入的密码不一致'))
			}else if(value===''){
				callback(new Error('密码为同时含有大小写字母、数字的8-16位组合'))
			}else{
				callback()
			}
		};
		const validateEmail=(rule,value,callback)=>{
			if(!validEmail(value)){
				callback(new Error('请输入正确的邮箱地址'))
			}else{
				callback()
			}
		};
		const validateCode=(rule,value,callback)=>{
			if(value.length!=6){
				callback(new Error('请输入6位验证码'))
			}else{
				callback()
			}
		};
		return{
			receiver:'',
			registerForm:{
				username:'',
				oldPassword:'',
				newPassword:'',
				email:'',
				code:'',
			},
			registerRules:{
				username:[{required:true,trigger:'blur',validator:validateUsername}],
				oldPassword:[{required:true,trigger:'blur',validator:validatePassword}],
				newPassword:[{required:true,trigger:'blur',validator:samePassword}],
				email:[{required:true,trigger:'blur',validator:validateEmail}],
				code:[{required:true,trigger:'blur',validator:validateCode}],
			},
			loading:false,
			disable:false,
			btnValue:'获取验证码',
			oldPwdType:'password',
			newPwdType:'password',
		}
	},
	methods:{
		showOldPwd(){
			if(this.oldPwdType==='password'){
				this.oldPwdType=''
			}else{
				this.oldPwdType='password'
			}
		},
		showNewPwd(){
			if(this.newPwdType==='password'){
				this.newPwdType=''
			}else{
				this.newPwdType='password'
			}
		},
		sendEmail(){
			if(validEmail(this.registerForm.email)){
				this.disable=true;
				new Promise((resolve,reject)=>{
					verify(this.registerForm.email)
					.then(()=>{
						resolve()
					}).catch(error=>{
						reject(error)
					})
				}).then(()=>{
					this.$message.success({
						content:'发送邮件成功',
						duration:3,
					})
				})
			}else{
				this.$message.error({
					content:'请输入正确的邮箱地址',
					duration:5,
				})
			}
		},
		handleRegister(){
			this.$refs.registerForm.validate(valid=>{
				if(valid){
					this.loading=true;
					new Promise((resolve,reject)=>{
						register(this.registerForm.username,this.registerForm.oldPassword,this.registerForm.code)
						.then(()=>{
							resolve()
						}).catch(error=>{
							reject(error)
						})
					}).then(()=>{
						this.loading=false;
						setCookie("username",this.registerForm.username,15);
						setCookie("password",this.registerForm.oldPassword,15);
						this.$message.success({
							content:'注册成功',
							duration:3,
						})
					}).catch(()=>{
						this.loading=false;
					})
				}else{
					return false;
				}
			})
		},
		handleLogin(){
			this.$router.push({path:'/login'})
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
		margin: 80px auto;
		box-shadow: 3px 3px 6px rgba(0,0,0,.08);
	}
	.login-title{
		text-align: center;
	}	
	.login-form-forget{
		float: right;
	}
</style>