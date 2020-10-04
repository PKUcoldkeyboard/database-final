<template>
	<div>
		<a-input v-model="username" label="username"></a-input>
		<a-input v-model="password" label="password"></a-input>
		<a-button type="primary" @click="onSubmit">测试</a-button>
	</div>
</template>

<script>
export default{
	data(){
		return{
			username:'',
			password:''
		}
	},
	methods:{
		onSubmit(){
			axios.post('localhost:7777/auth/login?username='+username+'&password='+password)
			.then(res=>{
				console.log(res);
			})
		}
	}
}
</script>

<style>

</style>