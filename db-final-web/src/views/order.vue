<template>
  <div>
    <a-breadcrumb separator="">
      <a-breadcrumb-item>
        <router-link to="/store/index">当前位置：首页</router-link>
      </a-breadcrumb-item>
      <a-breadcrumb-separator>></a-breadcrumb-separator>
      <a-breadcrumb-item> 我的订单 </a-breadcrumb-item>
    </a-breadcrumb>
    <a-divider></a-divider>
    <a-table
      :columns="columns"
      :data-source="data"
      :pagination="pagination"
      rowKey="id"
    >
      <div
        slot="filterDropdown"
        slot-scope="{ selectedKeys, confirm, clearFilters, column }"
        style="padding: 8px"
      >
        <span>根据订单ID搜索</span>
        <a-input
          v-model="order_id_select"
          style="width: 188px; margin-bottom: 8px; display: block"
          @pressEnter="() => handleSearch()"
        />
        <a-button
          type="primary"
          icon="search"
          size="small"
          style="width: 90px; margin-right: 8px"
          @click="() => handleSearch(selectedKeys, confirm, column.dataIndex)"
        >
          搜索
        </a-button>
        <a-button
          size="small"
          style="width: 90px"
          @click="() => handleReset(clearFilters)"
        >
          重置
        </a-button>
      </div>
      <a slot="name" slot-scope="text">{{ text }}</a>
      <span slot="status" slot-scope="text,record">{{statusList[record.status]}}</span>
      <span slot="confirmStatus" slot-scope="text,record">{{statusConList[record.confirmStatus]}}</span>
      <span slot="createTime" slot-scope="text, record">
        <template>
          <ul style="list-style: none">
            <li>创建时间：{{ record.createTime | formatTime }}</li>
            <li>支付时间：{{ record.payTime | formatTime }}</li>
            <li>签收时间：{{ record.receiveTime | formatTime }}</li>
          </ul>
        </template>
      </span>
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
  </div>
</template>
<script>
import { formatDate } from "@/utils/date.js";
const columns = [
  {
    title: "订单号",
    dataIndex: "orderId",
    key: "orderId",
    align: "center",
    slots: { title: "customTitle" },
    scopedSlots: {
      filterDropdown: "filterDropdown",
      filterIcon: "filterIcon",
      customRender: "customRender",
    },
    onFilter: (value, record) =>
      record.name.toString().toLowerCase().includes(value.toLowerCase()),
    onFilterDropdownVisibleChange: (visible) => {
      if (visible) {
        setTimeout(() => {
          this.searchInput.focus();
        }, 0);
      }
    },
  },
  {
    title: "总计金额",
    dataIndex: "totalAmount",
    key: "totalAmount",
    align: "center",
  },
  {
    title: "优惠券抵扣金额",
    dataIndex: "couponAmount",
    key: "couponAmount",
    align: "center",
  },
  {
    title: "满减金额",
    dataIndex: "promotionAmount",
    key: "promotionAmount",
    align: "center",
  },
  {
    title: "运费",
    dataIndex: "freightAmount",
    key: "freightAmount",
    align: "center",
  },
  {
    title: "实际收款",
    dataIndex: "payAmount",
    key: "payAmount",
    align: "center",
  },

  {
    title: "订单确认状态",
    dataIndex: "confirmStatus",
    key: "confirmStatus",
    align: "center",
    scopedSlots: { customRender: "confirmStatus" },
  },
  {
    title: "订单状态",
    dataIndex: "status",
    key: "status",
    align: "center",
    scopedSlots: { customRender: "status" },
  },
  {
    title: "订单时间",
    dataIndex: "createTime",
    key: "createTime",
    align: "center",
    scopedSlots: { customRender: "createTime" },
  },
  {
    title: "订单操作",
    dataIndex: "operation",
    align: "center",
    scopedSlots: { customRender: "operation" },
  },
];

export default {
  data() {
    return {
      data: [],
      statusConList:["未确认",'已确认'],
      statusList:["代付款","已付款","已发货","已完成"],
      columns,
      total: 0,
      order_id_select: "",
      pagination: {
        defaultPageSize: 5,
        showSizeChanger: true,
        pageSizeOptions: ["5", "10", "15", "20"],
        // onShowSizeChange:(current, pageSize)=>this.pageSize = pageSize
      },
    };
  },
  methods: {
    onDelete(id) {
      const dataSource = [...this.data];
      this.data = dataSource.filter((item) => item.id !== id);
      this.axios
        .delete("http://39.107.230.124:7777/order/item/" + id)
        .then((body) => {
          console.log(body);
        });
    },
    handleSearch() {
        console.log(this.order_id_select)
      this.axios
        .get("http://39.107.230.124:7777/order/item/sn/" + this.order_id_select)
        .then((body) => {
          console.log(body.data.data);
          this.data[0] = body.data.data;
          console.log(this.data);
        });
    },

    handleReset(clearFilters) {
      clearFilters();
      this.searchText = "";
    },
  },
  mounted() {
    this.axios.get("http://39.107.230.124:7777/order/item/").then((body) => {
    //   console.log(body.data.data);
      this.data = body.data.data.list;
    //   console.log(this.data);
    });
  },
  filters: {
    formatTime(time) {
      let date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    },
  },
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