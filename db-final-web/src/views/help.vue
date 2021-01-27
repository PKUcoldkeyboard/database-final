<template>
    <div>
        <a-breadcrumb separator="">
            <a-breadcrumb-item>
                <router-link to="/store/index">当前位置：首页</router-link>
            </a-breadcrumb-item>
            <a-breadcrumb-separator>></a-breadcrumb-separator>
            <a-breadcrumb-item>
                帮助中心
            </a-breadcrumb-item>
        </a-breadcrumb>
        <a-divider></a-divider>
        <div style="text-indent: 2em;">
            <p>1、下订单前要注册成为会员，主要是为了在以后的购买过程中可以节省时间，不必每次输入地址、电话等个人信息，并可以累积积分，享受到我们对于不同星级会员的优惠价格；注册会员是免费的，不需要承担会费等任何负担。</p>
            <p>2、注册方式：点击右上方"注册"，按照提示填入相应信息即可，如果您输入的用户名称与已注册会员相同，会有提示信息：此Email地址已注册，请更换其它地址，说明该邮箱已被注册，需更换其他邮箱进行注册。</p>
            <p>3、如何查找需要的书籍:首页有搜索框，在里面输入您要查找的图书名或关键词或作者名，然后点击搜索按钮即可查到相关商品，同时还可限定书的类别和排序方式。</p>
            <p>4、为什么搜索不到商品？这说明我们的库存中暂时没有您搜索的商品, 您可以尝试用其他方法搜索，使用尽可能少的关键字，可获得更多的搜索结果。</p>
            <p>5、订单状态中的“已确认”是什么意思？您的订单在提交后30分钟内可以在“我的账户”中自行取消，超过30分钟的不能自行取消，订单状态会变成“已确认”。</p>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {

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
</style>