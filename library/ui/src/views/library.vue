<template>
<div class="mainContainer">
    <div class="selectTitle">
        <!-- <div class="selectCard2 myflex">
            <div class="name2">分类:</div>
            <div class="list">
                <a-select v-model="type1" style="width: 320px" @change="onChange">
                    <a-select-option :value="item.id" v-for="item in cateData" :key="item.id">{{item.name}}</a-select-option>
                </a-select>
            </div>
        </div> -->
        <!-- <div class="selectCard2 myflex">
            <div class="name2">地区:</div>
            <div class="list2">
                <a-radio-group @change="onChange2" v-model="type2" buttonStyle="solid">
                    <a-radio-button :value="item.id" v-for="item in tagsData" :key="item.id" style="margin:0.5rem">{{item.name}}</a-radio-button>
                </a-radio-group>
            </div>
        </div>
        <div class="selectCard2 myflex">
            <div class="name2">进度:</div>
            <div class="list2">
                <a-radio-group @change="onChange2" v-model="type4" buttonStyle="solid">
                    <a-radio-button :value="item.id" v-for="item in data3" :key="item.id" style="margin:0.5rem">{{item.name}}</a-radio-button>
                </a-radio-group>
            </div>
        </div> -->
        <div class="selectCard2 myflex">
            <div class="name2">字母:</div>
            <div class="list2">
                <a-radio-group  v-model="type5" buttonStyle="solid">
                    <a-radio-button :value="index" v-for="(item,index) in 27" :key="index" style="margin:0.5rem">{{index===0?'全部':String.fromCharCode(item+63)}}</a-radio-button>
                </a-radio-group>
            </div>
        </div>
        <div class="selectCard2 myflex">
            <div class="name2">标签:</div>
            <div class="list2">
                <a-radio-group @change="onChange2" v-model="type1" buttonStyle="solid">
                    <a-radio-button :value="item.id" v-for="(item,index) in tagsList" :key="index" style="margin:0.5rem">{{item.name}}</a-radio-button>
                </a-radio-group>
            </div>
        </div>
    </div>
    <div>
      <a-radio-group  v-model="type3" size="large" buttonStyle="solid">
        <a-radio-button value="a">默认排序</a-radio-button>
        <a-radio-button value="b">人气排序</a-radio-button>
      </a-radio-group>
    </div>
    <div class="content">
        <div class="card-item">
            <div class="book" v-for="(res,index) in datasource" :key="index" @click="goTo(res)">
              <div class="image">
                <img :src="res.image" alt="" :title="res.name">
              </div>
              <div style="text-align:center;font-size:20px;padding:5px 10px">{{res.name}}</div>
              <div  style="text-align:center;font-size:20px;padding:5px 10px">{{res.publisher}}</div>
            </div>
        </div>
    </div>
    <div><div style="text-align:center;font-size:20px;padding:5px10px;margin:auto;width:150px;border:1px solid #ccc" @click="more">加载更多+</div></div>
</div>
</template>

<script>
// import mapState from 'vuex'
export default {
  name: 'library',
  data () {
    return {
      datasource: [],
      tagsList: [],
      currentsize: '',
      type1: 1, // 分类
      type2: 1, // 地区
      type4: 1, // 进度
      type5: 0, // 字母
      type3: 'a', //
      cateData: [
        { id: 0, name: '全部' },
        { id: 1, name: '小说' },
        { id: 2, name: '文学' },
        { id: 3, name: '人文科技' },
        { id: 4, name: '经济管理' },
        { id: 5, name: '科技科普' },
        { id: 6, name: '计算机与互联网' },
        { id: 7, name: '成功励志' },
        { id: 8, name: '生活' },
        { id: 9, name: '少儿' },
        { id: 10, name: '艺术设计' },
        { id: 11, name: '漫画绘本' },
        { id: 12, name: '考试教育' },
        { id: 13, name: '杂志' }
      ],
      tagsData: [
        { id: 1, name: '全部' },
        { id: 2, name: '国内' },
        { id: 3, name: '日本' },
        { id: 4, name: '欧美' },
        { id: 5, name: '其他' }
      ],
      data3: [
        { id: 1, name: '全部' },
        { id: 2, name: '连载' },
        { id: 3, name: '完结' }
      ]
    }
  },

  methods: {
    onChange (e) {
      console.log(`checked = ${e.target.value}`)
    },
    onChange2 (e) {
      console.log(`checked `, e)
      this.getBookManage()
    },
    getBookManage () {
      let data = {
        page: 1,
        size: 10
      }
      if (this.type1 !== 1) {
        data.bid = this.type1
      }
      this.$api.getBookManage(data).then(res => {
        if (res.success) {
          console.log('sss', res)
          this.datasource = res.queryResult.list
          this.currentsize = res.queryResult.list.length
        }
      })
    },
    more () {
      let data = {
        page: 1,
        size: this.currentsize
      }
      if (this.type1 !== 1) {
        data.bid = this.type1
      }
      this.$api.getBookManage(data).then(res => {
        if (res.success) {
          console.log('sss', res)
          this.datasource = this.datasource.concat(res.queryResult.list)
          this.currentsize = this.datasource.length + 10
        }
      })
    },
    getTags () {
      let data = {
        page: 1,
        size: 10
      }
      this.$api.getTags(data).then(res => {
        if (res.success) {
          console.log('sss', res)
          this.tagsList = res.queryResult.list
        }
      })
    },
    goTo (data) {
      this.$router.push({ name: 'bookInfo', params: { data: data }, query: { id: data.id, name: data.name } })
    }
  },
  mounted () {
    this.getBookManage()
    this.getTags()
  }
}
</script>

<style scoped>
.mainContainer{
    width:90%;
    margin: auto
}
.selectTitle{
    margin :5rem 0 3rem;
    padding :2rem 3rem;
    background-color: #fbfbfb;
}
.selectCard2{
    border-bottom: 0.1rem solid #cccccc;
    padding: 0.5rem 0;
}
.name2{
    font-size: 2.2rem;
    font-weight: bold;
    color: #000000;
    line-height :1.8;
    width:60px;
}
.name{
    font-size: 2.2rem;
    font-weight :bold;
    color: #000000;
    border-bottom :0.1rem solid #cccccc;
    line-height: 1.8;
    padding: 0.5rem 0
}
.list{
    padding: 0 1rem ;
}
.content{
  width :100%;
  padding :2rem 1rem 1.5rem 8rem;
}
.content .book{
  height:  580px;
  width :312px;
  margin :1rem;
  display: inline-block;
  overflow: hidden;
}
.content .book  .image{
  width: 312px;
  height: 500px;
  overflow: hidden;
  position: relative;
}
.content .book  .image img{
  position: absolute;
  width: 100%;
  height: 100%;

}
.mainContainer .content .book:hover{
box-shadow: 0 5px 20px  #666666;
}
</style>
