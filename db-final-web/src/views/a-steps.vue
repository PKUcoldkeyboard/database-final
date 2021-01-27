<template>
  <div>
    <a-breadcrumb separator="">
      <a-breadcrumb-item>
        <router-link to="/store/index">当前位置：首页</router-link>
      </a-breadcrumb-item>
      <a-breadcrumb-separator>></a-breadcrumb-separator>
      <a-breadcrumb-item> 商品下单 </a-breadcrumb-item>
    </a-breadcrumb>
    <a-divider></a-divider>

    <a-steps :current="current">
      <a-step v-for="item in steps" :key="item.title" :title="item.title" />
    </a-steps>
    <div class="steps-content">
      <div v-if="current == 0">
      <a-form :form="form" @submit="handleSubmit">
        <a-form-item v-bind="formItemLayout" label="收件人姓名" >
          <a-input
            v-decorator="[
              'receiverName',
              {
                rules: [
                  {
                    required: true,
                    message: '请输入收件人姓名!',
                  },
                ],
              },
            ]"
          />
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="收件人电话号码">
          <a-input
            v-decorator="[
              'phoneNumber',
              {
                rules: [{ required: true, message: '请输入收件人联系电话!' }],
              },
            ]"
            style="width: 100%"
          >
            <a-select
              slot="addonBefore"
              v-decorator="['prefix', { initialValue: '86' }]"
              style="width: 70px"
            >
              <a-select-option value="86"> +86 </a-select-option>
              <a-select-option value="87"> +87 </a-select-option>
            </a-select>
          </a-input>
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="所在省份">
          <a-input
            v-decorator="[
              'province',
              {
                rules: [
                  {
                    required: true,
                    message: '请输入省份!',
                  },
                ],
              },
            ]"
          />
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="所在市">
          <a-input
            v-decorator="[
              'city',
              {
                rules: [
                  {
                    required: true,
                    message: '请输入所在市!',
                  },
                ],
              },
            ]"
          />
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="所在区/县">
          <a-input
            v-decorator="[
              'region',
              {
                rules: [
                  {
                    required: true,
                    message: '请输入所在区/县!',
                  },
                ],
              },
            ]"
          />
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="具体地址">
          <a-input
            v-decorator="[
              'detailAddress',
              {
                rules: [
                  {
                    required: true,
                    message: '请输入具体地址!',
                  },
                ],
              },
            ]"
          />
        </a-form-item>
        <a-form-item v-bind="tailFormItemLayout">
          <div class="steps-action">
            <a-button
              v-if="current < steps.length - 1"
              type="primary"
              @click="next"
              html-type="submit"
            >
              下一步
            </a-button>
            <a-button
              v-if="current == steps.length - 1"
              type="primary"
             
            >
              完成
            </a-button>
            <a-button v-if="current > 0" style="margin-left: 8px" @click="prev">
              上一步
            </a-button>
          </div>
        </a-form-item>
      </a-form>
      </div>
      <div v-if="current == 1">
        <a-descriptions title="订单信息" bordered >
          <a-descriptions-item label="商品">
            书籍
          </a-descriptions-item>
          <a-descriptions-item label="订单状态">
            待支付
          </a-descriptions-item>
          <a-descriptions-item label="订单自动更新">
            是
          </a-descriptions-item>
          <a-descriptions-item label="创建时间">
            {{this.data.createTime | formatTime}}
          </a-descriptions-item>
          <a-descriptions-item label="修改时间" :span="2">
            {{this.data.modifyTime | formatTime}}
          </a-descriptions-item>
          <a-descriptions-item label="订单状态" :span="3">
            <a-badge status="processing" text="待确认" />
          </a-descriptions-item>
          <a-descriptions-item label="总价">
            $80.00
          </a-descriptions-item>
          <a-descriptions-item label="优惠券抵扣"> $20.00 </a-descriptions-item>
          <a-descriptions-item label="需支付">
            $60.00
          </a-descriptions-item>
          <a-descriptions-item label="收件信息">
            收件人姓名: {{this.data.receiverName}}
            <br />
            联系电话: {{this.data.phoneNumber}}
            <br />
            收件地址:{{this.data.province+" "+this.data.city+" "+this.data.region+" "+this.data.detailAddress}}
            
          </a-descriptions-item>
        </a-descriptions>
        <div class="steps-action">
          <a-button
            v-if="current < steps.length - 1"
            type="primary"
            @click="next"
            html-type="submit"
          >
            确认并支付
          </a-button>
          <a-button
            v-if="current == steps.length - 1"
            type="primary"
            @click="$message.success('Processing complete!')"
          >
            完成
          </a-button>
          <a-button v-if="current > 0" style="margin-left: 8px" @click="prev">
            修改订单信息
          </a-button>
        </div>
      </div>
      <a-result status="success" title="支付成功!" v-if="current == 2">
        <template #extra>
          <a-button key="console" type="primary" @click="toOrder"> 查看订单 </a-button>
          <a-button key="buy" @click="toIndex"> 浏览其他商品 </a-button>
        </template>
      </a-result>
    </div>
  </div>
</template>
<script>
import {getCookie} from '@/utils/support'
// getCookie('username')
let username=getCookie('username')
console.log(username)
import {formatDate} from "@/utils/date.js"
export default {
  data() {
    return {
      current: 0,
      data:{},
      steps: [
        {
          title: "订单信息填写",
          content: "First-content",
        },
        {
          title: "订单信息确认",
          content: "Second-content",
        },
        {
          title: "支付完成",
          content: "Last-content",
        },
      ],
      confirmDirty: false,
      receiverList: {
        id: "",
        user_id: "",
        receiver_name: "caf",
        phone_number: "1660041898",
        post_code: "",
        province: "",
        city: "",
        region: "",
        detail_address: "",
      },
      receiverList2:{},
      autoCompleteResult: [],
      formItemLayout: {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 8 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
      },
      tailFormItemLayout: {
        wrapperCol: {
          xs: {
            span: 24,
            offset: 0,
          },
          sm: {
            span: 16,
            offset: 8,
          },
        },
      },
    };
  },
  beforeCreate() {
    this.form = this.$form.createForm(this);
  },
  mounted(){
    // console.log(getCookie('username'))
    this.axios.get("http://39.107.230.124:7777/receiver/info",{params:{username:username}}).then((body) => {
      // console.log(body.data.data);
      this.data = body.data.data;
      this.form.setFieldsValue(this.data)
    });
  },
   filters:{
    formatTime(time){
      let date = new Date(time);
      return formatDate(date,'yyyy-MM-dd hh:mm:ss')
    }
  },
  methods: {
    toOrder(){
      this.$router.push('/store/order')
    },
    toIndex(){
      this.$router.push('/store/index')
    },
    next() {
      this.current++;
    },
    prev() {
      this.current--;
    },
    handleSubmit(e) {
      
      e.preventDefault();
      // let data=this.form.getFieldsValue()
      // console.log(data)
      this.form.validateFieldsAndScroll((err, values) => {
        // console.log(values);
        this.receiverList2=values
        if (!err) {
          console.log("Received values of form: ", values);
        }
      });
    },
    handleConfirmBlur(e) {
      const value = e.target.value;
      this.confirmDirty = this.confirmDirty || !!value;
    },
  },
};
// this.form.setFieldsValue({
//   name: this.data.receiver_name,
//   age: this.data.age,
// });
</script>
<style scope>
.ant-breadcrumb {
  font-size: 16px;
}

.ant-divider-horizontal {
  margin-top: 16px !important;
  margin-bottom: 16px !important;
}
.steps-content {
  margin-top: 16px;
  border: 1px dashed #e9e9e9;
  border-radius: 6px;
  background-color: #fafafa;
  /* min-height: 200px; */
  text-align: center;
  padding-top: 80px;
}

.steps-action {
  margin-top: 0px;
}
.ant-form {
  width: 100%;
  height: 500px;
  margin: 0 auto;
  padding-right: 25%;
}
</style>