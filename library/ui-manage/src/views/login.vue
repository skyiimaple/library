<template>
  <div class="center">
    <div class="box">
      <div class="box_center">
        <div style="width:100%;margin: 30px 0 18px 0;text-align: center;">
            <span style="font-family: 微软雅黑;font-weight: 400;font-style: normal;font-size: 21px;color: rgb(85, 85, 85);">图书管理后台登录</span>
        </div>
        <div style="box-shadow: 0px 0px 8px rgba(202, 201, 201, 0.3);margin-top: 10px;padding: 35px 15px 60px 15px; ">
          <div style="margin-top: 15px;">
            <a-input placeholder="账号" v-model="userinfo.username" class="noBorder_input">
              <a-icon style="font-size: 20px;" slot="prefix" type="user" />
            </a-input>
          </div>
          <div style="margin-top: 25px;">
            <a-input placeholder="密码" v-model="userinfo.password"  type="password" class="noBorder_input">
              <a-icon style="font-size: 20px;" slot="prefix" type="unlock" />
            </a-input>
          </div>
          <div style="text-align: center;margin-top: 15px;margin-right: 7px"><span style="font-size: 12px;color: #7F7F7F;">找回密码</span></div>
        </div>
        <div style="margin-top: 28px;">
          <a-button type="primary" size="large"  style="width: 100%" @click="login">登录</a-button>
        </div>
      </div>
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
    login () {
      this.$api.login(this.userinfo).then(res => {
        if (res.success) {
          this.$message.success('登录成功')
          this.$router.push({ name: 'info' })
        } else {
          this.$message.error(res.message)
          this.$router.push({ name: 'info' })
        }
      })
    }
  },
  mounted () {
  },
  created () {
    console.log('login created')
  }
}
</script>

<style scoped>
.center{
    height:100%;
    width: 100%;
    position: absolute;
    background-image: url('../assets/img/bg.jpeg');
    background-size: 100% 100%;
    background-position: 0 50%;
}
.box{
  text-align: center;
  height:400px;
  width:600px;
  background-color: #fff;
  position:absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
  border-radius: 5px;
}
.box_center{
  width: 100%;
  padding: 0 20%
}
</style>
