import Cookies from 'js-cookie'

const TokenKey="Authorization"

export function getToken(){
	return Cookies.get(TokenKey)
}

export function setToken(token){
	return Cookies.set(TokenKey,token,{expires:3})
}

export function removeToken(){
	return Cookies.remove(TokenKey)
}