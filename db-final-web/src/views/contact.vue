<template>
  <div>
    <a-breadcrumb separator="">
      <a-breadcrumb-item>
        <router-link to="/index/head">当前位置：首页</router-link>
      </a-breadcrumb-item>
      <a-breadcrumb-separator>></a-breadcrumb-separator>
      <a-breadcrumb-item> 联系我们 </a-breadcrumb-item>
    </a-breadcrumb>
    <a-divider></a-divider>
    <div class="contact-box">
      <div class="form-box">
        <div class="contact-text-box" style="margin-bottom:20px">
          <span class="contact-title">联系我们</span>
          <p class="contact-text">
            如果您有任何建议或者需求，请填写以下信息，我们会尽快给您反馈！
          </p>
        </div>
        <div class="form-content">
          <a-form :form='form' @submit="handleSubmit">
            <a-form-item  v-bind="formItemLayout" label="姓氏">
              <a-input
                v-decorator="[
                  'xs',
                  {
                    rules: [
                      { required: true, message: '请输入姓氏!' },
                    ],
                  },
                ]"
                
              />
            </a-form-item>
            <a-form-item  v-bind="formItemLayout"  label="名字">
              <a-input
               v-decorator="[
                  'name',
                  {
                    rules: [
                      { required: true, message: '请输入名字!' },
                    ],
                  },
                ]"
                
              />
            </a-form-item>
            <a-form-item v-bind="formItemLayout" label="邮箱">
              <a-input
                v-decorator="[
                  'email',
                  {
                    rules: [
                      { required: true, message: '请输入邮箱!' },
                    ],
                  },
                ]"
                
              />
            </a-form-item>
            
           
            <a-form-item
              v-bind="formItemLayout"
              label="主题"
            >
              <a-auto-complete>
                <a-input 
                v-decorator="[
                  'title',
                  {
                    rules: [
                      { required: true, message: '请输入主题!' },
                    ],
                  },
                ]"
                
                />
              </a-auto-complete>
            </a-form-item>
            <a-form-item style="width: 80%; height: 200px; margin-left: 0px">
            <a-input
              type="textarea"
              placeholder="请输入具体描述"
              style="width: 80%; height: 200px; margin-left: 104px"
              v-decorator="[
                  'content',
                  {
                    rules: [
                      { required: true, message: '请输入具体内容!' },
                    ],
                  },
                ]"
            />
            </a-form-item>
            <a-form-item
              style="width: 40%; margin-left: 120px; margin-top: 10px"
            >
              <a-button type="primary" html-type="submit" style="width:100px"> 发送 </a-button>
            </a-form-item>
          </a-form>
        </div>
      </div>
      <div class="side-box">
        <div class="side-title">关于我们</div>
        <div class="side-detail">
          北京大学数据库小组<br/>组长庞上智，组员：常奥飞，谭宇霄，王宏光
        </div>
        <div class="side-info">
          <div class="side-info1">
            <div class="info-icon">
              <a-icon type="compass" />
            </div>
            <div class="info-right">
              <p class="info-right-title">地址：</p>
              <p style="text-align: center">北京市海淀区颐和园路五号</p>
            </div>
          </div>
          <div class="side-info1">
            <div class="info-icon">
              <a-icon type="phone" />
            </div>
            <div class="info-right">
              <p class="info-right-title">联系电话：</p>
              <p>18818981898</p>
            </div>
          </div>
          <div class="side-info1">
            <div class="info-icon">
              <a-icon type="mail" />
            </div>
            <div class="info-right">
              <p class="info-right-title">邮箱：</p>
              <p>1898@pku.edu.cn</p>
            </div>
          </div>
          <div class="side-info1">
            <div class="info-icon">
              <a-icon type="global" />
            </div>
            <div class="info-right">
              <p class="info-right-title">网址：</p>
              <p>www.shangzhi.org</p>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
const residences = [];
// console.log(this.contactList)
export default {
  data() {
    return {
      data:{},
      contactList:{
      },
      autoCompleteResult:{website:'www.shangzhi.org'},
      m:1,
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
    this.form = this.$form.createForm(this, { name: "register" });
  },
  mounted(){
    console.log(this.contactList)
  },
  methods: {
    
    handleSubmit(e) {
      let data=this.form.getFieldsValue()
      console.log(data)

this.axios.post("http://39.107.230.124:7777/contact/feedback",{params:{xs:data.xs,name:data.name,email:data.email,title:data.title,content:data.content}}).then((body) => {
      console.log(body);
      alert("发送成功！")
    });

      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          this.contactList=values
          console.log("Received values of form: ", values);
        }
      });
    },
   
  },
};
</script>
<style scoped>
* {
    margin: 0;
    padding: 0;
  }
  .ant-breadcrumb {
    font-size: 16px;
  }
  
  .ant-divider-horizontal {
    margin-top: 16px !important;
    margin-bottom: 16px !important;
  }
  .contact-box {
    width: 100%;
    margin: 0 auto;
    display: flex;
  }
  .contact-title-box {
    width: 100%;
    height: 200px;
  }
  .contact-title {
    width: 100%;
    font-weight: bold;
      font-size: 28px;
  }
  .contact-text {
    font-size: 16px;
  }
  .form-box {
    width: 65%;
    height: 600px;
    margin-right: 20px;
  }
  .form-content {
    width: 100%;
    display: flex;
  }
  .side-box {
    width: 35%;
    height: 600px;
    display: flex;
    flex-direction: column;
    /* background-color: aquamarine; */
  }
  .side-title{
    width: 100%;
      font-weight: bold;
      font-size: 28px;

      height: 60px;
  }
  .side-detail{
      height: 100px;
  }
  .side-info{
      height: 300px;
      display: flex;
      /* flex-wrap: wrap; */
      flex-direction: column;
      justify-content: space-between;
  }
  .side-info1{
    width: 60%;
    display: flex;
  }
  .info-icon{
    /* margin: 20px 20px; */
      width: 20%;
      height: 34px;
      font-size: 28px;
      margin: 10px 10px 0;
  }
  .info-right-title{
    font-size: 18px;
    font-weight: bold;
  }
  .info-right{
      width: 70%;
      height: 34px;
      line-height: 17px;
     
  }
  .ant-form {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content:start;
  }
  .ant-input {
    margin-bottom: 20px;
    width: 260px;
    padding-left:10px ;
  }
  .ant-form-item-label {
    width: 100px;
  }
</style>