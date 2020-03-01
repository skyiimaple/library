<template>
  <div class="top">
    <div class="mytop ">
      <div class="myleft">
        我的图书中心
      </div>
      <div class="myright" v-if="myname==='首页'">
        <ul >
          <li v-for="(item,index) in titleList" :key="index" @click="clickSelect(item)" :class="{'active':myname===item.name}">
            <div v-if="item.name!=='首页'">{{item.name}}</div>
          </li>
        </ul>
      </div>
      <div class="myright" v-else>
        <ul>
          <li v-for="(item,index) in titleList" :key="index" @click="clickSelect(item)" :class="{'active':myname===item.name}">
            <div >{{item.name}}</div>
          </li>
        </ul>
      </div>
    </div>
    <div class="searchContainer">
      <div class="search" v-if="myname==='首页'">
        <span class="title">一本书可以看到一个世界</span>
        <div class="searchType" >
          <div v-for="(item,i) in ['按作者','按书名','按分类']" :key="i"  @click="current=i">{{item}}</div>
          <div class="moveline"  :style="{left:linewidth+'rem'}"></div>
        </div>
        <div>
          <a-input-search placeholder="你想看的世界" @search="onSearch" size="large" enterButton style="width:40%"/>
        </div>
      </div>
      <div class="search2" v-else>
        <span class="title">{{myname}}</span>
        <div class="searchType" >
          <div v-for="(item,i) in ['按作者','按书名','按分类']" :key="i"  @click="current=i">{{item}}</div>
          <div class="moveline"  :style="{left:linewidth+'rem'}"></div>
        </div>
        <div>
          <a-input-search placeholder="你想看的世界" @search="onSearch" size="large" enterButton style="width:40%"/>
        </div>
      </div>
    </div>
    <login ref="login"></login>
  </div>
</template>

<script>
import { mapState, mapMutations } from 'vuex'
export default {
  name: 'navTop',
  components: {
    login: () => import('@/views/login')
  },
  data () {
    return {
      current: 0,
      myname: '首页',
      titleList: [
        { name: '首页', path: 'home' },
        { name: '电子图书', path: 'library' },
        { name: '年度榜单', path: 'annualList' },
        { name: '书店', path: 'bookShop' },
        { name: '个人中心', path: 'person' },
        { name: '登录/注册' }
      ]
    }
  },
  computed: {
    ...mapState(['currMenu']),
    linewidth () {
      let i = 1
      if (this.current === 1) i = 9
      if (this.current === 2) i = 17
      return i
    }
  },
  methods: {
    ...mapMutations(['setCurrMenu']),
    onSearch () {

    },
    clickSelect (val) {
      this.myname = val.name
      if (val.name === '登录/注册') {
        this.$refs.login.openLogin()
      } else {
        this.$router.push('/' + val.path)
      }
      this.setCurrMenu(val)
    }
  },
  mounted () {
    this.myname = this.currMenu.name
  }
}
</script>

<style scoped>
.top{
  background-image: url('../assets/img/bg.jpg');
  background-size: 100% 100%;
  background-position: 0 50%;
}
.mytop{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: rgba(252,252,252,0.7);
  padding: 0 6rem;
  cursor: default;
  font-size: 2.2rem;
  font-weight: 500;
}
.myleft {
  padding: 0.9rem 1.6rem;
}
.myright ul{
  display: flex;
}
.myright ul{
  margin: 0;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  height: 100%;
}
.myright ul li{
  list-style: none;
  padding: 0.9rem 1.6rem;
  height: 100%;
}
.myright ul li:hover{
  color: skyblue;
}
.search{
  padding: 20rem 6rem 20rem 13%;
  /* background-color: rgba(252,252,252,0.7); */
}
.search2{
  padding: 8rem 6rem 8rem 13%;
}
.title{
  font-size: 4.8rem;
  font-weight: bold;
  color: #fff;
}
.searchType{
  display: flex;
  justify-content: left;
  margin-bottom: 2rem;
  position: relative;
  font-size: 2rem;
  color: rgb(31, 32, 88);
}
.searchContainer .moveline{
  position: absolute;
  bottom: -2.2rem;
  left: 1rem;
  width: 0;
  height: 0;
  padding: 0;
  border-left:1.8rem solid transparent;
  border-bottom: 2.5rem solid #ffffff;
  border-right: 1.8rem solid transparent;
  z-index: 2;
  transition: 0.2s all linear;
}
.searchType div{
  padding: 0.5rem 2rem 0.5rem 0;
  cursor: default;
}
.searchType div:hover{
  font-weight: 600;
}
.searchType div:nth-child(1):hover ~.moveline{
  left:1rem !important;
}
.searchType div:nth-child(2):hover ~.moveline{
  left:9rem!important;
}
.searchType div:nth-child(3):hover ~.moveline{
  left:17rem!important;
}
.active{
  color: skyblue;
  font-weight: 600;
}
</style>
