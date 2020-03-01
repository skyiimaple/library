<template>
  <div class="navTop">
      <div class="leftOper">
        <a-button type="primary" icon="left" size="large" v-if="list">返回</a-button>
        <div style="padding:0 20px">
            <a-breadcrumb>
                <a-breadcrumb-item> <a-icon type="home" style="font-size:20px"/></a-breadcrumb-item>
                <a-breadcrumb-item v-for="(item,index) in list" :key="index">{{item}}</a-breadcrumb-item>
            </a-breadcrumb>
        </div>
    </div>
    <div >
        <div v-if="login"  class="loginInfo">
            <div><img src="@/assets/img/tou.png" title="我的头像" width="40px" height="40px"></div>
            <div style="width:50px;">user</div>
            <div><img src="@/assets/img/down.png"  width="20px" height="20px"></div>
        </div>
        <div v-else>登录</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'navtop',
  props: {
    list: Array
  },
  data () {
    return {
      login: true
    }
  }
}
</script>

<style scoped>
.navTop{
    padding: 10px 15px;
    width: 100%;
    background: rgba(255,255,255,0.8);
    border-top-right-radius: 20px;
    display: flex;
    justify-content: space-between;
    padding-right: 100px;
}
.leftOper{
    display: flex;
    justify-content: left;
    align-items: center;
}
.loginInfo{
    display: flex;
    align-items: center;
}
</style>
