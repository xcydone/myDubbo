<template>
  <div id="paper">
    <el-form :model="loginForm" :rules="rules" class="login-container" label-position="left"
             label-width="0px" v-loading="loading">
      <h3 class="login_title">系统登录</h3>
      <el-form-item prop="userId">
        <el-input type="text" v-model="loginForm.userId" auto-complete="off" placeholder="账号" class="login_input" ></el-input>
      </el-form-item>
      <el-form-item prop="operPwd">
        <el-input type="password" v-model="loginForm.operPwd" auto-complete="off" placeholder="密码" class="login_input"></el-input>
      </el-form-item>
      <el-form-item prop="verificationCode">
        <el-input type="text" v-model="loginForm.verificationCode" placeholder="验证码" class="login_input"></el-input>
        <a href="javascript:" class="login_input" @click="refreshCode"><img :src="vsCode"  alt="点击刷新验证码"/></a>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 60%;height: 30px;" v-on:click="login" class="login_input">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  export default{
    data () {
      return {
        rules: {
          userId: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
          operPwd: [{required: true, message: '密码不能为空', trigger: 'blur'}],
          verificationCode: [{ required: true, message: "请输入验证码", trigger: "blur" }]
        },
        checked: true,
        loginForm: {
          userId: '',
          operPwd: '',
          verificationCode: ''
        },
        loading: false,
        randnum: '',
        vsCode: ''
      }
    },
    mounted () {
      // 初始化验证码
      this.refreshCode()
    },
    methods: {
      login () {
          this.api.login({
            verificationCode: this.loginForm.verificationCode,
            randnum: this.randnum
          }).then(resp => {
            // debugger
            if (resp.data.code === 200) {
              var path = this.$route.query.redirect
              this.$router.push({path: path === '/' || path === undefined ? '/HelloWorld' : path})
            }
          })
          .catch(failResponse => {
            this.loginForm.verificationCode = ""
            this.refreshCode()
          })
      },
      refreshCode () {
          this.api.onReady().then(resp => {
            if (resp.data.code === 200) {
              this.$store.commit('login', resp.data, this.loginForm.userId);
              this.randnum = resp.data.data;
              this.vsCode = this.baseUrl + '/login/getCaptcha?randnum=' + this.randnum;
            }
          })
          .catch(failResponse => {
            console.log("请求错误" + failResponse);
          })
        },
      }
    }
</script>
<style>
   #paper {
    /*background:url("../assets/img/bg/eva1.jpg") no-repeat;*/
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
   body{
     margin: -5px 0px;
   }
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 60px auto;
    width: 200px;
    padding: 60px 60px 60px 60px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }
  .login_remember {
    margin: 0px 0px 35px 0px;
    text-align: left;
  }
  .login_input {
    margin-top: 25px;
  }
  /*.login_button {*/
    /*background: #505458;*/
  /*}*/
  /*el_checkbox {*/
    /*background: #505458;*/
  /*}*/
</style>
