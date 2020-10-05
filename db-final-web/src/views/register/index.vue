<template>
	<div>
		<a-card class="login-form-layout">
			<a-form-model :model="registerForm"
						  :rules="registerRules"
						  ref="registerForm"
						  label-position="left"
						  autoComplete="on">
				<div style="text-align: center;">
					<img src='@/assets/logo.png' style="width: 56px;height: 56px;">
				</div>
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
import {validUsername,validPassword} from '@/utils/validate'
import {register} from '@/api/login'
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
			}else{
				callback()
			}
		};
		return{
			registerForm:{
				username:'',
				oldPassword:'',
				newPassword:'',
			},
			registerRules:{
				username:[{required:true,trigger:'change',validator:validateUsername}],
				oldPassword:[{required:true,trigger:'change',validator:validatePassword}],
				newPassword:[{required:true,trigger:'change',validator:samePassword}]
			},
			loading:false,
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
		handleRegister(){
			this.$refs.registerForm.validate(valid=>{
				if(valid){
					this.loading=true;
					new Promise((resolve,reject)=>{
						register(this.registerForm.username,this.registerForm.oldPassword)
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
					console.log('参数验证不合法');
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
		margin: 140px auto;
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