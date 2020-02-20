<template>
<div class="mainContainer">
    <div class="selectTitle">
        <div class="selectCard">
            <div class="name">分类:  {{  type1}}</div>
            <div class="list">
                <a-radio-group @change="onChange" v-model="type1" buttonStyle="solid">
                    <a-radio-button :value="item.name" v-for="item in cateData" :key="item.id" style="margin:0.5rem">{{item.name}}</a-radio-button>
                </a-radio-group>
            </div>
        </div>
        <div class="selectCard">
            <div class="name">标签:  {{  type2}}</div>
            <div class="list">
                <a-radio-group @change="onChange2" v-model="type2" buttonStyle="solid">
                    <a-radio-button :value="item.name" v-for="item in tagsData" :key="item.cid" style="margin:0.5rem">{{item.name}}</a-radio-button>
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
  name: 'library',
  data () {
    return {
      type1: null, // 分类
      type2: null, // 标签
      type3: 'a', //
      cateData: [
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
      // 表签应该是随着分类改变的
      tagsData: [
        { cid: 1, name: '漫画' },
        { cid: 2, name: '武侠' },
        { cid: 3, name: '科幻' },
        { cid: 4, name: '推理' },
        { cid: 5, name: '韩寒' },
        { cid: 6, name: '网络' },
        { cid: 7, name: '穿越' },
        { cid: 8, name: '生活' },
        { cid: 9, name: '青春文学' },
        { cid: 10, name: '鲁迅' },
        { cid: 11, name: '当代文学' },
        { cid: 12, name: '诗词' },
        { cid: 13, name: '历史' }
      ]
    }
  },
  methods: {
    onChange (e) {
      console.log(`checked = ${e.target.value}`)
    },
    onChange2 (e) {
      console.log(`checked = ${e.target.value}`)
    }
  }
}
</script>

<style lang="stylus" scoped>
.mainContainer{
    width 82%;
    margin auto;
}
.selectTitle{
    margin 5rem 0 3rem;
    padding 2rem 3rem;
    background-color #fbfbfb;
}
.name{
    font-size 2.2rem;
    font-weight bold;
    color #000000;
    border-bottom 0.1rem solid #cccccc;
    line-height 1.8;
    padding 0.5rem 0
}
.list{
    padding 1rem 0 1rem 5rem;
    border-bottom 0.1rem solid #cccccc;
}
.content{
  width 100%;
  padding 2rem 7rem 1.5rem 7rem;
}
.content .book{
  height  auto;
  width 25%;
  padding 0 3%;
  margin 1rem 0;
}
.mainContainer .content .book:hover{
    box-shadow 0 0 2rem 0 #cccccc inset;
}
</style>
