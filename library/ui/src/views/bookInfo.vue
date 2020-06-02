<template>
<div class="bookInfo">
    <div style="font-size:34px;padding:10px">{{info.name}}</div>
    <div class="myflex">
        <div><img :src="info.image" alt="" width="270px" height="398px" :title="info.name"></div>
        <div style="padding-left:30px">
            <div style="line-hight:2;padding:5px">作者：{{info.author!=='null'?info.author:'未知'}}</div>
            <div style="line-hight:2;padding:5px">出版社：{{info.publisher!=='null'?info.publisher:'未知'}}</div>
            <div style="line-hight:2;padding:5px">翻译人：{{info.translator!=='null'?info.translator:'未知'}}</div>
        </div>
    </div>
    <div style="color:skyblue;font-size:22px;padding:10px">内容简介·····</div>
    <div>
       <p> {{info.contentDesc}}</p>
    </div>
    <div style="color:skyblue;font-size:22px;padding:10px">作者简介·····</div>
    <div>
       <p> {{info.authorProfile}}</p>
    </div>
    <div style="color:skyblue;font-size:22px;padding:10px">目录·····</div>
    <div>
       <p v-html="info.catalogue"></p>
    </div>
    <div style="color:skyblue;font-size:22px;padding:10px">{{info.name}}的书评·····(共{{common.total}}条)</div>
    <div v-if="common.total">
       <div v-for="(item,index) in common.list" :key="index" style="border-bottom:1px solid #ccc;margin:5px 0">
         <div style="font-size:20px"> {{index+1}}楼  {{item.name}}: </div>
         <div style="font-size:18px;padding-left:30px;color:#666666">{{item.content}}</div>
       </div>
    </div>
    <div v-else style="font-size:22px;color:#ccc"> 暂无评论,
      <span style="color:#ea4d4f" @click="show=!show"> 写评论</span>
      <span v-if="show">
        <a-input placeholder="写评论" style="width:300px;margin-left:10px"></a-input>
        <a-button style="margin-left:10px" type="primary">发表评论</a-button>
      </span>
    </div>
</div>
</template>

<script>
export default {
  name: 'bookInfo',
  data () {
    return {
      info: {},
      common: [],
      show: false
    }
  },
  methods: {
    getInfoById () {
      // if (this.$route.query.id) {
      //   this.$api.getInfoById(this.$route.query.id).then(res => {
      //     this.infos2 = res
      //   })
      //   this.$api.getInfoById2(this.$route.query.id).then(res => {
      //     this.infos1 = res
      //     console.log('sss', this.infos1)
      //   })
      this.$api.getCommon(this.$route.query.id).then(res => {
        this.common = res.queryResult
        console.log('common', this.common)
      })
      // } else {
      this.$api.getInfoByName(this.$route.query.name).then(res => {
        this.info = res.bookInfo
        console.log('lllll', res)
      })
      // }
    },
    getTime (date, offset = 0, type = false) {
      var tdate = new Date(date)
      tdate.setDate(tdate.getDate() + offset)
      var y = tdate.getFullYear()
      var m = tdate.getMonth() + 1
      m = m < 10 ? '0' + m : m
      var d = tdate.getDate()
      d = d < 10 ? '0' + d : d
      if (type) {
        var H = tdate.getHours()
        H = H < 10 ? '0' + H : H
        var M = tdate.getMinutes()
        M = M < 10 ? '0' + M : M
        var S = tdate.getSeconds()
        S = S < 10 ? '0' + S : S
        if (H === '00' && M === '00' && S === '00') {
          return y + '-' + m + '-' + d + ' ' + '12:00:00'
        } else {
          return y + '-' + m + '-' + d + ' ' + H + ':' + M + ':' + S
        }
      } else {
        return y + '-' + m + '-' + d
      }
    }
  },
  activated () {
    this.getInfoById()
  }
}
</script>

<style scoped>
.bookInfo{
    width: 85%;
    margin: auto;
}

</style>
