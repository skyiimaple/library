<template>
    <a-modal v-model="visible" :title="Title"  :footer="null" :width="520" :maskClosable="false">
      <div class="content">
        <div class="myRow">
          <span class="name">用户名：</span>
          <a-input class="input" v-model="userinfo.username" placeholder="请输入用户名" style="width:25rem"/>
        </div>
        <div class="myRow">
          <span class="name">密码：</span>
          <a-input class="input" v-model="userinfo.password" placeholder="请输入密码" type="password" style="width:25rem"  />
        </div>
        <div class="myRow">
          <span class="name">验证码：</span>
          <a-input class="input" v-model="userinfo.safeword" placeholder="请输入验证码"  style="width:25rem" @keyup.enter="loginFn()" />
        </div>
        <div style="text-align:center">这里放验证码</div>
        <div class="myRow">
          <a-button class="loginbtn" type="primary" v-if="Title==='登录'" @click="loginFn()">登录</a-button>
          <a-button class="loginbtn" type="danger" @click="regist">注册</a-button>
        </div>
      </div>
    </a-modal>
</template>
<script>
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
    openLogin () {
      this.visible = true
    },
    regist () {
      this.Title = '注册'
    }
  },
  created () {
    console.log('login created')
    // this.currentUser()
  }
}
</script>

<style scoped>
.myRow{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 6rem;
}
.name{
  width: 8rem;
  text-align: right;
}
.loginbtn{
  margin: 0 5px;
}
.content{
  background-image: url('../assets/img/login.jpg');
}
</style>
