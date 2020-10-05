<template>
	<div>
		<a-button type="primary" @click="logout">登出</a-button>
	</div>
</template>

<script>
export default{
	data(){
		return{

		}
	},
	methods:{
		logout(){
			this.$store.dispatch('FedLogout').then(()=>{
				alert('成功登出');
			})
		}
	}
}
</script>

<style>

</style>