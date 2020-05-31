<template>
  <div class="mainContainer">
    <div class="box">
        <a-tabs v-model="tableValue" >
            <a-tab-pane tab="用户信息" key="1"></a-tab-pane>
            <a-tab-pane tab="我的密码" key="2" ></a-tab-pane>
            <a-tab-pane tab="收藏" key="3"></a-tab-pane>
            <a-tab-pane tab="浏览历史" key="4"></a-tab-pane>
        </a-tabs>
    </div>
    <div v-if="tableValue==='1'">用户信息</div>
    <div v-if="tableValue==='2'">我的密码</div>
    <div v-if="tableValue==='3'">收藏</div>
    <div v-if="tableValue==='4'">浏览历史</div>
  </div>
</template>

<script>
export default {
  name: 'person',
  data () {
    return {
      tableValue: '1'
    }
  }
}
</script>

<style lang="stylus" scoped>
.mainContainer{
    width:82%;
    margin:auto;
}
.box{
}
</style>
