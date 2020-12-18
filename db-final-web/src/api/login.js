import request from '@/utils/request'

export function login(username,password){
	return request({
		url:'/auth/login',
		method:'post',
		data:{
			username:username,
			password:password
		}
	})
}

export function logout(){
	return request({
		url:'/auth/logout',
		method:'post'
	})
}

export function getInfo(){
	return request({
		url:'/auth/info',
		method:'get',
	})
}

export function register(username,password){
	return request({
		url:'/auth/register',
		method:'post',
		data:{
			username:username,
			password:password,
			roles:[{roleId:1,roleName:'ROLE_USER'}],
		}
	})
}

export function changePwd(username,oldPass,newPass){
	return request({
		url:'/auth/changePwd',
		method:'post',
		data:{
			username:username,
			oldPassword:oldPass,
			newPassword:newPass,
		}
	})
}

export function verify(receiver){
	return request({
		url:'/sendEmail?receiver='+receiver,
		method:'get',
	})
}