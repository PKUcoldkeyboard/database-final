<template>
    <div>
        <a-breadcrumb separator="">
            <a-breadcrumb-item>
                当前位置：首页
            </a-breadcrumb-item>
        </a-breadcrumb>
        <a-divider></a-divider>
        <a-card class="filter-container" shadow="never" style="border-radius: 20px;">
            <div>
                <a-icon type="search" />
                <span>筛选搜索</span>
                <a-button style="float:right;" @click="handleSearchList()" type="primary" size="small">
                    查询结果
                </a-button>
                <a-button style="float: right;margin-right: 15px" @click="handleResetSearch()" size="small">
                    重置
                </a-button>
            </div>
            <div style="margin-top: 15px">
                <a-form layout="inline" :model="listQuery" size="small" label-width="140px">
                    <a-form-item label="输入搜索:">
                        <a-input style="width: 203px" v-model="listQuery.keyword" placeholder="搜索关键词"></a-input>
                    </a-form-item>
                    <a-form-item label="图书名称:">
                        <a-input style="width: 203px" v-model="listQuery.title" placeholder="图书名称"></a-input>
                    </a-form-item>
                    <a-form-item label="图书作者">
                        <a-input style="width: 203px" v-model="listQuery.author" placeholder="图书作者"></a-input>
                    </a-form-item>
                    <a-form-item label="图书分类">
                        <a-select style="width:203px" v-model="listQuery.catagoryId">
                            <a-select-option v-for="item in catagoryOptions" :value="item">
                                {{item}}
                            </a-select-option>
                        </a-select>
                    </a-form-item>
                    <a-form-item label="图书出版社">
                        <a-input style="width: 203px" v-model="listQuery.press" placeholder="图书出版社"></a-input>
                    </a-form-item>
                    <a-form-item label="ISBN">
                        <a-input style="width: 215px" v-model="listQuery.isbn" placeholder="ISBN"></a-input>
                    </a-form-item>
                </a-form>
            </div>
        </a-card>
        <a-list item-layout="vertical" size="large" :pagination="pagination" :data-source="listData">
            <a-list-item slot="renderItem" key="item.title" slot-scope="item, index">
                <img slot="extra" width="200" height="200" alt="logo" src="http://image12.bookschina.com/2020/20200805/1/s8336292.jpg" />
                <a-list-item-meta :description="item.description">
                    <a slot="title" :href="item.href">{{ item.title }}</a>
                </a-list-item-meta>
                <span style="font-size:20px;color: red">¥47.6</span><span style="font-size:16px;"> (7折)</span><span style="color:rgba(0, 0, 0, 0.45);"> 定价:¥68.0</span><br><br>
                {{ item.content }}
                <a-button slot="actions" type="dashed">
                    加入购物车
                </a-button>
                <a-button slot="actions">
                    收藏
                </a-button>
            </a-list-item>
        </a-list>
    </div>
</template>
<script>
const defaultListQuery = {
    keyword: null,
    title: null,
    author: null,
    press: null,
    isbn: null,
    catagoryId: '全部',
}
const listData = [];
for (let i = 0; i < 23; i++) {
    listData.push({
        href: 'https://www.antdv.com/',
        title: `中华人民共和国民法典`,
        avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
        description: '中国法制出版社 / 2020-06-01 / 中国法制出版社',
        content: '《中华人民共和国民法典》被称为“社会生活百科全书”，是民事权利的宣言书和保障书。本书收录的是《中华人民共和国民法典》标准文本，共7编，依次为总则编、物权编、合同编、人格权编、婚姻家庭编、继承编、侵权责任编以及附则，并按照权威版本进行了全文折校，保证文字的精准性',
    });
}
export default {
    data() {
        return {
            listQuery: Object.assign({}, defaultListQuery),
            catagoryOptions: ['全部', '医学与生物学', '历史与考古', '哲学与宗教', '商业与经济', '地球与环境科学', '工程与应用科学', '数学、物理与化学', '新闻与传播', '法律、政治与政府', '社会科学', '艺术与建筑', '语言与文学', '音乐、舞蹈、戏剧与电影'],
            listData,
            pagination: {
                onChange: page => {
                    console.log(page);
                },
                pageSize: 10,
            },
            actions: [
                { type: 'star-o', text: '156' },
                { type: 'like-o', text: '156' },
                { type: 'message', text: '2' },
            ],
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