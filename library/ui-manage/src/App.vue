<template>
  <a-locale-provider :locale="zhCN">
    <div id="app">
      <keep-alive>
        <router-view />
      </keep-alive>
    </div>
  </a-locale-provider>
</template>

<script>
import zhCN from 'ant-design-vue/lib/locale-provider/zh_CN'
export default {
  name: 'App',
  data () {
    return {
      zhCN
    }
  }
}
</script>

<style>
html {
  font-size: 60%;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  font-size: 16px!important;
}
.ant-breadcrumb {
    font-size: 20px !important;
}
.ant-table-pagination.ant-pagination {
    float: left !important;
    margin: 16px !important;
}
</style>
