<template>
    <div>
        <a-breadcrumb separator="">
            <a-breadcrumb-item>
                <router-link to="/store/index">当前位置：首页</router-link>
            </a-breadcrumb-item>
            <a-breadcrumb-separator>></a-breadcrumb-separator>
            <a-breadcrumb-item>
                每日特价
            </a-breadcrumb-item>
        </a-breadcrumb>
        <a-divider></a-divider>
        <div>
            <h3>活动规则</h3>
            <p>新上架图书限时特惠，每天13点更新，一律7折，数量有限，特惠时间最长一周。</p>
        </div>
        <div>
            <el-row :gutter="12">
                <el-col :span="6"  v-for="item in list" style="margin-bottom: 15px;">
                    <el-card shadow="hover">
                        <div class="cover">
                            <img :src="item.picture" />
                            <h2 style="margin-top:5px;font-size: 14px;color:#0080cc">{{item.name}}</h2>
                             <div class="otherInfo">
                                <a-breadcrumb style="font-size: 14px;">
                                    <a-breadcrumb-item>{{item.author}}</a-breadcrumb-item>
                                    <a-breadcrumb-item>{{item.press}}</a-breadcrumb-item>
                                </a-breadcrumb>
                            </div>
                            <a-divider/>
                            <div class="priceWrap">
                                <a-breadcrumb style="font-size: 14px;">
                                    <a-breadcrumb-item>定价：¥{{item.price}}</a-breadcrumb-item>
                                    <a-breadcrumb-item style="color: #e60000;">现价：¥{{item.newPrice}}</a-breadcrumb-item>
                                </a-breadcrumb>
                            </div>
                            <div class="action" style="margin-top: 15px;">
                                <el-button type="danger" round @click="handleAddCart($event)" :id="item.bookId">加入购物车</el-button>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
    </div>
</template>
<script>
import {getCookie} from '@/utils/support'
import {addCart} from '@/api/index'
import {getDiscount} from '@/api/specials'
export default {
    data() {
        return {
            list:[],
        }
    },
    created(){
        this.getList();
    },
    methods:{
        getList(){
            getDiscount().then(response=>{
                this.list=response.data.list;
            });
        },
        handleAddCart(e) {
            let bookId = e.currentTarget.id;
            let price = e.currentTarget.parentElement.previousElementSibling.firstElementChild.firstElementChild.nextElementSibling.firstElementChild.innerHTML.substr(4);
            let username=getCookie('username');
            addCart(username,bookId, price).then(() => {
                this.$success({
                    title:'加入购物车成功'
                })
            })
        }
    }
}
</script>
<style scope>
.ant-breadcrumb {
    font-size: 16px;
}

.ant-divider-horizontal {
    margin-top: 16px !important;
    margin-bottom: 16px !important;
}
.el-card__body{
    display: flex;
    align-items: center;
    justify-content: center;
}
.cover {
    height: 480px;
    width: 200px;
    display: inline;
    justify-content: center;
    align-items: center;
    text-align: center;
}
.cover img{
    height: 200px;
}
</style>