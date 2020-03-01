<template>
  <div class="mylib">
    <div class="mycon">
        <div class="menu">
            <div class="info"><img src="@/assets/img/lib.png" title="个性图书" width="40px" height="40px"><div>图书管理后台</div></div>
            <a-menu
                :defaultSelectedKeys="['1']"
                :defaultOpenKeys="['sub1']"
                mode="inline"
                :inlineCollapsed="collapsed"
            >
                <a-sub-menu key="sub1">
                    <span slot="title"  title="图书"><a-icon type="home" theme="twoTone"/><span>图书管理</span></span>
                    <a-menu-item @click="goTo('info')">我的图书</a-menu-item>
                </a-sub-menu>
                <a-sub-menu key="sub2">
                    <span slot="title"  title="图书分类"><a-icon type="appstore" theme="twoTone"/><span>图书分类</span></span>
                    <a-menu-item  @click="goTo('category')">我的分类</a-menu-item>
                    <a-menu-item>Option 6</a-menu-item>
                    <a-menu-item >Option 7</a-menu-item>
                    <a-menu-item >Option 8</a-menu-item>
                </a-sub-menu>
                <a-sub-menu key="sub3">
                    <span slot="title" title="评论管理"><a-icon type="like" theme="twoTone"/><span>评论管理</span></span>
                    <a-menu-item @click="goTo('comment')">我的评论</a-menu-item>
                    <a-menu-item  >参与的评论</a-menu-item>
                </a-sub-menu>
                <a-sub-menu key="sub4">
                    <span slot="title" title="个人中心"><a-icon type="team" /><span>个人中心</span></span>
                    <a-menu-item @click="goTo('user')">用户</a-menu-item>
                </a-sub-menu>
            </a-menu>
        </div>
        <div class="content">
            <div class="container">
            <keep-alive>
                <router-view></router-view>
            </keep-alive>
            </div>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Main',
  data () {
    return {
      collapsed: false
    }
  },
  methods: {
    goTo (url) {
        console.log('sss', url)
        this.$router.push({ name: url })
    }
  }
}
</script>

<style scoped >
.mylib{
    height:100%;
    width: 100%;
    position: absolute;
    padding:1.5%;
    background-image: url('../assets/img/bg.jpeg');
    background-size: 100% 100%;
    background-position: 0 50%;
}
.mycon{
    display:flex;
    height:100%;
    width: 100%;
    background: rgba(255,255,255,0.7);
    border-radius: 20px;
}
.info{
    border-bottom: 1px solid #cccccc;
    padding: 10px 25px;
    font-size: 18px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-weight: bold;
    color: #1890ff;
    display: flex;
    justify-content: space-evenly;
    align-items: center;
    white-space: nowrap;
    text-overflow: clip;
}
.menu{
    width: 15%;
    height: 100%;
    background-color: #fff;
    border-top-left-radius: 20px;
    border-bottom-left-radius: 20px;
}
.content{
    border-left: 2px solid #cccccc;
    height: 100%;
    width: 100%;
}
.container{
    height: 100%;
    overflow: hidden;
}
</style>
