import axios from 'axios'
import store from '../store'
import {getToken} from '@/utils/auth'
import {message,Modal} from 'ant-design-vue'

//创建axios实例
axios.defaults.withCredentials=true
const service=axios.create({
	baseURL:'http://39.107.230.124:7777',
	timeout:15000 //请求超时时间
})

//request拦截器
service.interceptors.request.use(config=>{
	//携带JWT请求头
	if(store.getters.token){
		config.headers['Authorization']=getToken()
	}
	return config 
},error=>{
	console.log(error)
	Promise.reject(error)
})

//response拦截器
service.interceptors.response.use(
	response=>{
		const res=response.data
		if(res.code!=20000){
			message.error({
				content:res.message,
				duration:3
			})
			//未登录
			if(res.code===20005){
				Modal.confirm({
					content:'你已被登出，可以取消继续留在该页面，或者重新登录',
					okText:'重新登录',
					cancelText:'取消'
				}).then(()=>{
					store.dispatch('FedLogout').then(()=>{
						location.reload()
					})
				})
			}
			return Promise.reject('error')
		}else{
			return response.data
		}
	},
	error=>{
		console.log('error'+error)
		message.error({
			content:error.message,
			duration:3
		})
		return Promise.reject(error)
	}
)

export default service