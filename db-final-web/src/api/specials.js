import request from '@/utils/request'

export function getDiscount(){
	return request({
		url:'/discount/item',
		method:'get',
	})
}