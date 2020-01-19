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
  font-size: 1.6rem;
}
.ant-card-hoverable:hover{
  box-shadow: 0px 0px 10px 1px rgba(0,0,0,0.3)!important;
}
.ant-modal-body {
    padding: 0 !important;
    font-size: 14px;
    line-height: 1.5;
    word-wrap: break-word;
}
</style>
