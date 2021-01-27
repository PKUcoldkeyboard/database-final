<template>
  <div>
    <a-breadcrumb separator="">
      <a-breadcrumb-item>
        <router-link to="/store/index">当前位置：首页</router-link>
      </a-breadcrumb-item>
      <a-breadcrumb-separator>></a-breadcrumb-separator>
      <a-breadcrumb-item> 购物车 </a-breadcrumb-item>
    </a-breadcrumb>
    <a-divider></a-divider>
    <a-table :columns="columns" :data-source="data" :pagination="pagination" rowKey=id>
      <a slot="name" slot-scope="text">{{ text }}</a>
      <span slot="pic" slot-scope="text, record">
        <img referrer="no-referrer|origin|unsafe-url"  style="width: 100px; heigth: 100px" :src="record.picture" />
      </span>
      <span slot="customTitle"
        ><a-icon type="book" style="font-size: 18px" /> 图书</span
      >
      <template slot="quantity" slot-scope="text,record">
        <el-input-number v-model="record.quantity" :min='1' :max='10' label="describe" @change="changeQuantity(record.id,record.quantity)" >{{record.quantity}}</el-input-number>
      </template>
      <span slot="createTime" slot-scope="text,record">{{record.createTime | formatTime}}</span>
      <template slot="operation" slot-scope="text, record">
        <a-popconfirm
          v-if="data.length"
          title="您确定要删除吗?"
          @confirm="() => onDelete(record.id)"
        >
          <a href="javascript:;">删除</a>
        </a-popconfirm>
      </template>
    </a-table>
    <el-button style="margin-left: 600px;" type="danger" @click="jump" round>结算</el-button>
  </div>
</template>
<script>
import {formatDate} from "@/utils/date.js"
const columns = [
  {
    title: " ",
    dataIndex: "pictrue",
    scopedSlots: { customRender: "pic" },
  },
  {
    dataIndex: "name",
    key: "name",
    slots: { title: "customTitle" },
    scopedSlots: { customRender: "name" },
  },
  {
    title: "价格",
    dataIndex: "price",
    key: "price",
  },
  {
    title: "数量",
    dataIndex: "quantity",
    key: "quantity",
    scopedSlots: { customRender: "quantity" },
    align:'center'
  },
  {
    title: "加入时间",
    dataIndex: "createTime",
    key: "createTime",
     scopedSlots: { customRender: "createTime" },
  },
  {
    title: "operation",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" },
  },
];


export default {
  data() {
    return {
      data:[],
      columns,
      total:0,
      pagination:{
              defaultPageSize:5,
              showSizeChanger:true,
              pageSizeOptions: ['5', '10', '15', '20'],
              // onShowSizeChange:(current, pageSize)=>this.pageSize = pageSize
            }

    };
  },
  methods: {
    onDelete(id) {
      const dataSource = [...this.data];
      this.data = dataSource.filter((item) => item.id !== id);
      this.axios.delete("http://39.107.230.124:7777/cart/item/"+id).then((body) => {
      console.log(body);
    });
    },
    changeQuantity(id,quantity){
      console.log(quantity)
      this.axios.post("http://39.107.230.124:7777/cart/item/"+id,{quantity:quantity}).then((body) => {
      console.log(body);
    });
    },
    jump(){
      this.$router.push({path:'/store/steps'})
    }
  },
  mounted() {
    this.axios.get("http://39.107.230.124:7777/cart/item",{params:{pageNum:1,pageSize:this.total}}).then((body) => {
      console.log(body.data.data);
      this.data = body.data.data.list;
      console.log(this.data);
    });
  },
  filters:{
    formatTime(time){
      let date = new Date(time);
      return formatDate(date,'yyyy-MM-dd hh:mm:ss')
    }
  }
};
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