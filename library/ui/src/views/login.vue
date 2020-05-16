<template>
    <a-modal v-model="visible" :title="Title"  :footer="null" :width="620" :maskClosable="false">
      <div class="content">
        <div class="myRow">
          <span class="name">用户名：</span>
          <a-input class="input" v-model="userinfo.username" size="large" placeholder="请输入用户名" style="width:30rem"/>
        </div>
        <div class="myRow">
          <span class="name">密码：</span>
          <a-input class="input" v-model="userinfo.password" size="large" placeholder="请输入密码" type="password" style="width:30rem"  />
        </div>
        <div class="myRow">
          <span class="name">验证码：</span>
          <a-input class="input" v-model="userinfo.safeword" size="large" placeholder="请输入验证码"  style="width:30rem" @keyup.enter="loginFn()" />
        </div>
        <div style="text-align:center">这里放验证码</div>
        <div class="myRow">
          <a-button class="loginbtn" type="primary" v-if="Title==='登录'" @click="login">登录</a-button>
          <a-button class="loginbtn" type="danger" @click="regist">注册</a-button>
        </div>
      </div>
    </a-modal>
</template>

<script>
import mapMutations from 'vuex'
export default {
  name: 'login',
  data () {
    return {
      userinfo: {
        username: '',
        password: ''
      },
      visible: false,
      Title: '登录'
    }
  },
  methods: {
    ...mapMutations(['setUserInfo']),
    openLogin () {
      this.visible = true
    },
    regist () {
      this.Title = '注册'
    },
    login () {
      console.log('sss')
      this.$api.login(this.userinfo).then(res => {
        if (res.success) {
          this.$message.success('登录成功')
          this.visible = false
          this.setUserInfo()
        }
      })
    }
  }
}
</script>

<style scoped>
.myRow{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 8rem;
  padding-right: 8rem;
}
.name{
  width: 10rem;
  text-align: right;
}
.loginbtn{
  margin: 0 5px;
}
.content{
  background-image: url('../assets/img/login.jpg');
}
</style>
