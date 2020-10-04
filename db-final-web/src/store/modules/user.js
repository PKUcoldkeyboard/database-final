export default{
	state:{
	  	token:'',
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
		SET_AVATAR(state,avatar)=>{
			state.avatar=avatar
		},
		SET_ROLES(state,roles)=>{
			state.roles=roles
		}
	},
	actions:{
		//登录
		
	}
}