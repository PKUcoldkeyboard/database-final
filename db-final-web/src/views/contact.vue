<template>
    <div>
        <a-breadcrumb separator="">
            <a-breadcrumb-item>
                <router-link to="/index/head">当前位置：首页</router-link>
            </a-breadcrumb-item>
            <a-breadcrumb-separator>></a-breadcrumb-separator>
            <a-breadcrumb-item>
                联系我们
            </a-breadcrumb-item>
        </a-breadcrumb>
        <a-divider></a-divider>
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