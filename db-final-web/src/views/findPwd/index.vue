<template>
	<div>
		<a-card class="login-form-layout">
			<a-form-model :model="form"
						  :rules="rules"
						  ref="form"
						  label-position:leff
						  autoComplet="on">
				<h2 class="login-title">找回密码</h2>
				<a-form-model-item has-feedback prop="username">
					<a-input type="text"
							 placeholder="请输入帐号" 
							 v-model="form.username"
							 autoComplete="on">
						<a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)"/>
					</a-input>
				</a-form-model-item>
				<a-form-model-item has-feedback prop="newPassword">
					<a-input :type="newPwdType"
							 placeholder="请输入新密码"
							 v-model="form.newPassword"
							 autoComplete="on">
						<a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)"/>
						<a-icon slot="suffix" type="eye" style="color:rgba(0,0,0,.25)" @click="showNewPwd"/>
					</a-input>
				</a-form-model-item>
				<a-form-model-item has-feedback prop="code">
					<a-input type="text"
							 placeholder="请输入邮箱验证码"
							 v-model="form.code"
							 autoComplete="on">
						<a-icon slot="prefix" type="codepen" style="color:rgba(0,0,0,.25)"/>
						<!--实际场景要设置按钮禁用，防止多次点击，这里简单起见就不设置了-->
						<a-button slot="suffix" type="link" style="color:#564695 !important" @click.native.prevent="sendEmail">获取验证码</a-button>
					</a-input>
				</a-form-model-item>
				<a-form-model-item style="margin-bottom: 60px;text-align: center;">
					<a-button type="primary" :loading="loading" @click.native.prevent="handleFind"
							  style="width: 100%">
						确定
					</a-button>
					<a-button type="default" @click.native.prevent="handleLogin" style="width: 100%">
						返回登录
					</a-button>
				</a-form-model-item>
			</a-form-model>
		</a-card>
	</div>
</template>

<script>
import {validUsername,validPassword,validEmail} from '@/utils/validate'
import {verifyForFindPwd,findPwd} from '@/api/login'
import {setCookie} from '@/utils/support'
export default{
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
		const validateCode=(rule,value,callback)=>{
			if(value.length!=6){
				callback(new Error('请输入6位验证码'))
			}else{
				callback()
			}
		};
		return{
			form:{
				username:'',
				newPassword:'',
				code:'',
			},
			rules:{
				username:[{required:true,trigger:'blur',validator:validateUsername}],
				newPassword:[{required:true,trigger:'blur',validator:validatePassword}],				
				code:[{required:true,trigger:'blur',validator:validateCode}],
			},
			loading:false,
			newPwdType:'password',
		}
	},
	methods:{
		showNewPwd(){
			if(this.newPwdType==='password'){
				this.newPwdType=''
			}else{
				this.newPwdType='password'
			}
		},
		sendEmail(){
			if(validUsername(this.form.username)){
				new Promise((resolve,reject)=>{
					verifyForFindPwd(this.form.username)
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
					content:'请先输入正确的帐号',
					duration:3,
				})
			}
		},
		handleFind(){
			this.$refs.form.validate(valid=>{
				if(valid){
					this.loading=true;
					new Promise((resolve,reject)=>{
						findPwd(this.form.username,this.form.newPassword,this.form.code)
						.then(()=>{
							resolve()
						}).catch(error=>{
							reject(error)
						})
					}).then(()=>{
						this.loading=false;
						setCookie("username",this.form.username,15);
						setCookie("password",this.form.newPassword,15);
						this.$message.success({
							content:'修改密码成功',
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
		border-radius: 20px;
		box-shadow: 3px 3px 6px rgba(0,0,0,.08);
	}
	.login-title{
		text-align: center;
	}	
	.login-form-forget{
		float: right;
	}
</style>