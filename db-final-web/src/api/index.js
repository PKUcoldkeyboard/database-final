import request from '@/utils/request'
import getCookie from '@/utils/support'

export function searchBooks(keyword,categoryName,pageNum,pageSize,sort){
	return request({
		url:'/index/list',
		method:'post',
		data:{
			keyword:keyword,
			categoryName:categoryName,
			pageNum:pageNum,
			pageSize:pageSize,
			sort:sort
		}
	})
}
export function addCart(username,bookId,price){
	return request({
		url:'/index/cart',
		method:'post',
		data:{
			username:username,
			bookId:bookId,
			price:price,
		}
	})
}