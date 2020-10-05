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