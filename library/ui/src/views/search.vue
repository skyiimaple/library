<template>
  <div class="mainContainer">
    <div>
      <a-radio-group  v-model="type3" size="large" buttonStyle="solid">
        <a-radio-button value="a">默认排序</a-radio-button>
        <a-radio-button value="b">人气排序</a-radio-button>
      </a-radio-group>
    </div>
    <div class="content">
        <div class="card-item">
            <div class="book" v-for="(res,index) in viewsData" :key="index" @click="goTo(res)">
              <div class="image">
                <img :src="res.image" alt="">
              </div>
              <div style="text-align:center;font-size:20px;padding:5px 10px" v-html="res.name"></div>
              <div  style="text-align:center;font-size:20px;padding:5px 10px">{{res.publisher}}</div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'search',
  data () {
    return {
      type3: 'a' //
    }
  },
  computed: {
    ...mapState(['viewsData'])
  },
  methods: {
    goTo (data) {
      console.log('sss', data)
      this.$router.push({ name: 'bookInfo', query: { id: data.id } })
    }
  },
  activated () {
  }
}
</script>

<style scoped>
.mainContainer{
    padding-top: 20px;
    padding-left: 100px;
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
