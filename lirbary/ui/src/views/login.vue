<template>
  <div class="aa">
    <div class="main">
      <div>
        <span>用户名：</span>
        <a-input class="input" v-model="userinfo.username" placeholder="请输入用户名" />
      </div>
      <div>
        <span>密 &nbsp;&nbsp;&nbsp;码：</span>
        <a-input class="input" v-model="userinfo.password" placeholder="请输入密码" type="password"
          @keyup.enter="loginFn()" />
      </div>
      <a-button class="loginbtn">登录</a-button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'login',
  data () {
    return {
      userinfo: {
        username: '',
        password: ''
      }
    }
  },
  methods: {

  },
  created () {
    console.log('login created')
    // this.currentUser()
  }
}
</script>

<style lang="stylus"></style>
