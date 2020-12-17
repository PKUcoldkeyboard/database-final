import {login,logout,getInfo} from '@/api/login'
import {getToken,setToken,removeToken} from '@/utils/auth'

export default{
	state:{
	  	token:getToken(),
	  	name:'',
	  	avatar:'',
	  	roles:[]
	},
	mutations:{
		SET_TOKEN:(state,token)=>{
			state.token=token
		},
		SET_NAME:(state,name)=>{
			state.name=name
		},
		SET_AVATAR:(state,avatar)=>{
			state.avatar=avatar
		},
		SET_ROLES:(state,roles)=>{
			state.roles=roles
		}
	},//promise,resolve,reject是js异步内容，resolve(操作成功),reject(操作失败)
	actions:{
		//登录
		Login({commit},userInfo){
			const username=userInfo.username.trim()
			return new Promise((resolve,reject)=>{
				login(username,userInfo.password).then(response=>{
					const data=response.data
					const tokenStr=data.tokenHead+data.token
					setToken(tokenStr)
					commit('SET_TOKEN',tokenStr)
					resolve()
				}).catch(error=>{
					reject(error)
				})
			})
		},
		//登出
		Logout({commit},state){
			return new Promise((resolve,reject)=>{
				logout().then(()=>{
					commit('SET_TOKEN','')
					commit('SET_ROLES',[])
					removeToken()
					resolve()
				}).catch(error=>{
					reject(error)
				})
			})
		},

		//前端登出
		FedLogout({commit}){
			return new Promise(resolve=>{
				commit('SET_TOKEN','')
				removeToken()
				resolve()
			})
		},

		GetInfo({commit},state){
			return new Promise(resolve=>{
				getInfo().then(response=>{
					const data=response.data
					if(data.roles&&data.roles.length>0){
						commit('SET_ROLES',data.roles)
					}else{
						reject('getInfo:roles must be a non-null array!')
					}
					commit('SET_NAME',data.username)
					commit('SET_AVATAR',data.icon)
					resolve()
				}).catch(error=>{
					reject(error)
				})
			})
		}
	}
}