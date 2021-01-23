<template>
    <div>
        <a-breadcrumb separator="">
            <a-breadcrumb-item>
                当前位置：首页
            </a-breadcrumb-item>
        </a-breadcrumb>
        <a-divider></a-divider>
        <div>
            全文检索：
            <a-input-search v-model="keyword" style="margin-right:30px;width:300px;" placeholder="关键词/书名/作者" @search="handleSearchList" />
            分类：
            <a-select v-model="categoryName" default-value="全部" style="margin-right:30px;width: 250px" @change="handleCategoryChange">
                <a-select-option v-for="item in catagoryOptions" :value="item">
                    {{item}}
                </a-select-option>
            </a-select>
            排序:
            <a-select v-model="sort" default-value="按出版时间排序" style="width: 250px" @change="handleSortChange">
                <a-select-option v-for="item in sortOptions" :value="item">
                    {{item}}
                </a-select-option>
            </a-select>
        </div>
        <div class="list">
            <div class="bookList">
                <ul>
                    <li v-for="book in list">
                        <div class="cover">
                            <img src="http://image12.bookschina.com/2013/20131223/s5835607.jpg" />
                        </div>
                        <div class="info">
                            <h2 class="name">{{book.bookName}}</h2>
                            <div class="otherInfo">
                                <a-breadcrumb>
                                    <a-breadcrumb-item>{{book.author}}</a-breadcrumb-item>
                                    <a-breadcrumb-item>
                                        {{book.publishDate | formatTime}}
                                    </a-breadcrumb-item>
                                    <a-breadcrumb-item>{{book.press}}</a-breadcrumb-item>
                                </a-breadcrumb>
                            </div>
                            <div class="priceWrap">
                                <span class="sellPrice">¥{{book.price}}</span>
                            </div>
                            <p class="desc">{{book.description}}</p>
                            <div class="action">
                                <el-button type="danger" round @click="handleAddCart($event)" :id="book.id">加入购物车</el-button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <a-pagination style="margin-top:15px;float:right;" v-model="pageNum" :page-size="10" :total="total" show-less-items @change="handleCurrentChange" />
        </div>
    </div>
</template>
<script>
import { formatDate } from '@/utils/date.js'
import { searchBooks, addCart } from '@/api/index.js'
import { getCookie } from '@/utils/support'
export default {
    data() {
        return {
            keyword: '',
            categoryName: '全部',
            sort: '按出版时间排序',
            list: [],
            pageNum: 1,
            total: 1,
            sortOptions: ["按出版时间排序", "按销量排序", "按价格降序", "按价格升序", "按相关度排序"],
            catagoryOptions: ['全部', '医学与生物学', '历史与考古', '哲学与宗教', '商业与经济', '地球与环境科学', '工程与应用科学', '数学、物理与化学', '新闻与传播', '法律、政治与政府', '社会科学', '艺术与建筑', '语言与文学', '音乐、舞蹈、戏剧与电影'],
        }
    },
    filters: {
        formatTime(time) {
            let date = new Date(time);
            return formatDate(date, 'yyyy-MM-dd')
        }
    },
    created() {
        this.getList();
    },
    methods: {
        getList() {
            let keyword = this.keyword;
            let categoryName = this.categoryName;
            let pageNum = this.pageNum - 1;
            let pageSize = 10;
            let sortValue = 1;
            if (this.sort == '按销量排序') {
                sortValue = 2
            } else if (this.sort == '按价格升序') {
                sortValue = 3;
            } else if (this.sort == '按价格降序') {
                sortValue = 4
            } else if (this.sort == '按相关度排序') {
                sortValue = 5;
            } else if (this.sort == '按出版时间排序') {
                sortValue = 1;
            }
            searchBooks(keyword, categoryName, pageNum, pageSize, sortValue).then(response => {
                this.list = response.data.content;
                this.total = response.data.total;
            });
        },
        handleSearchList() {
            this.pageNum = 1;
            this.getList();
        },
        handleCategoryChange(categoryName) {
            this.categoryName = categoryName;
            this.getList();
        },
        handleSortChange(sort) {
            this.sort = sort;
            this.getList();
        },
        handleCurrentChange(pageNum) {
            this.pageNum = pageNum;
            this.getList();
        },
        handleAddCart(e) {
            let bookId = e.currentTarget.id;
            let price = e.currentTarget.parentElement.previousElementSibling.previousElementSibling.firstElementChild.innerHTML.substr(1);
            console.log("entrance1")
            let username=getCookie('username');
            addCart(username,bookId, price).then(response => {
                this.$message.success({
                    content: '加入购物车成功',
                    duration: 3,
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

.list {
    margin-top: 15px;
    float: left;
}

.bookList {
    float: left;
}

.bookList ul {
    list-style: none;
    margin: 0px;
}

.bookList li {
    float: left;
    width: 100%;
    border: 1px solid #e9e9e9;
    padding: 20px 30px 20px 20px;
    border-bottom: 1px solid #e9e9e9;
}

.bookList li .info h2 {
    height: 18px;
    overflow: hidden;
    font-weight: normal;
    font-size: 14px;
    line-height: 16px;
}

.bookList li .info .otherInfo {
    margin-top: 6px;
    height: 26px;
    overflow: hidden;
    line-height: 26px;
}

.bookList li .info .priceWrap {
    height: 30px;
    line-height: 30px;
    vertical-align: bottom;
}

.bookList li .info .priceWrap span {
    font-size: 18px;
    color: #e60000;
    margin-right: 10px;
    font-family: 'Microsoft YaHei';
}

.bookList li .info .desc {
    line-height: 22px;
    height: 44px;
    overflow: hidden;
    color: #666666;
    margin-top: 10px;
}

.bookList li .info .action {
    overflow: hidden;
    margin-top: 10px;
}

.bookList .cover {
    height: 200px;
    width: 200px;
    float: left;
    display: inline;
    margin-right: 20px;
    text-align: center;
}
</style>