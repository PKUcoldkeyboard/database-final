import axios from 'axios'
import store from '../store'
import {getToken} from '@/utils/auth'

//创建axios实例
const service=axios.create({
	baseURL:process.env.BASE_API,
	timeout:15000
})

//request拦截器
service.interceptors.request.use(config=>{
	//携带JWT请求头
	if(store.getters.token){
		config.headers['Authorization']=getToken()
	}
	return config 
},error=>{
	Promise.reject(error)
})

//response拦截器
service.interceptors.response.use(
	response=>{
		const res=response.data
		if(res.code!=20000){
			//未登录
			if(res.code===20005){

			}
			return Promise.reject('error')
		}else{
			return response.data
		}
	},
	error=>{
		console.log('error'+error)
		return Promise.reject(error)
	}
)

export default service