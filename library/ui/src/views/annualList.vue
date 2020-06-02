<template>
<div class="mainContainer">
    <div class="selectTitle">
        <div class="selectCard2 myflex">
            <div class="name2">年份:</div>
            <div class="list">:
                <a-select v-model="type1" style="width: 320px" @change="onChange">
                    <a-select-option :value="item" v-for="item in 10" :key="item">{{2020-item}}</a-select-option>
                </a-select>
            </div>
        </div>
        <div class="selectCard2 myflex">
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
        </div>
        <div class="selectCard2 myflex">
            <div class="name2">字母:</div>
            <div class="list2">
                <a-radio-group @change="onChange2" v-model="type5" buttonStyle="solid">
                    <a-radio-button :value="index" v-for="(item,index) in 27" :key="index" style="margin:0.5rem">{{index===0?'全部':String.fromCharCode(item+63)}}</a-radio-button>
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
        <div v-for="res in 5" :key="res" style="display:flex;">
            <div class="book" v-for="res in 4" :key="res">
            <a-card  style="width: 100%">
                <img :src="require('@/assets/img/b'+res+'.jpg')" slot="cover" v-if="type3==='a'"/>
                <img :src="require('@/assets/img/b'+(5-res)+'.jpg')" slot="cover" v-else/>
                <a-card-meta title="Europe Street beat">
                <template slot="description">www.instagram.com</template>
                </a-card-meta>
            </a-card>
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
  name: 'annualList',
  data () {
    return {
      type1: 1, // 分类
      type2: 1, // 地区
      type4: 1, // 进度
      type5: 0, // 字母
      type3: 'a', //
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
      console.log(`checked = ${e.target.value}`)
    },
    getRecommendList () {
      this.$api.getRecommendList().then(res => {
        console.log('eeeee', res)
      })
    }
  },
  mounted () {
    this.getRecommendList()
  }
}
</script>

<style lang="stylus" scoped>
.mainContainer{
    width: 82%;
    margin :auto;
}
.selectTitle{
    margin: 5rem 0 3rem;
    padding :2rem 3rem;
    background-color: #fbfbfb;
}
.selectCard2{
    border-bottom: 0.1rem solid #cccccc;
    padding: 0.5rem 0;
}
.name2{
    font-size :2.2rem;
    font-weight: bold;
    color: #000000;
    line-height: 1.8;
}
.name{
    font-size: 2.2rem;
    font-weight: bold;
    color: #000000;
    border-bottom: 0.1rem solid #cccccc;
    line-height :1.8;
    padding :0.5rem 0
}
.list{
    padding: 0 1rem ;
}
.content{
  width: 100%;
  padding: 2rem 7rem 1.5rem 7rem;
}
.content .book{
  height:  auto;
  width: 25%;
  padding: 0 3%;
  margin: 1rem 0;
}
.mainContainer .content .book:hover{
    box-shadow: 0 0 2rem 0 #cccccc inset;
}
</style>
