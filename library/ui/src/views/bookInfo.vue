<template>
<div class="bookInfo">
    <div style="font-size:34px;padding:10px">{{infos1.name}}</div>
    <div class="myflex">
        <div><img :src="infos1.image" alt="" width="270px" height="398px"></div>
        <div style="padding-left:30px">
            <div style="line-hight:2;padding:5px">作者：{{infos1.author!=='null'?infos1.author:'未知'}}</div>
            <div style="line-hight:2;padding:5px">出版社：{{infos1.publisher!=='null'?infos1.publisher:'未知'}}</div>
            <div style="line-hight:2;padding:5px">翻译人：{{infos1.translator!=='null'?infos1.translator:'未知'}}</div>
            <div style="line-hight:2;padding:5px">上架时间：{{infos1.created!=='null'?getTime(infos1.created,0,true):'未知'}}</div>
            <div style="line-hight:2;padding:5px">更新时间：{{infos1.updated!=='null'?getTime(infos1.updated,0,true):'未知'}}</div>
        </div>
    </div>
    <div style="color:skyblue;font-size:22px;padding:10px">内容简介·····</div>
    <div>
       <p> {{infos2.contentDesc}}</p>
    </div>
    <div style="color:skyblue;font-size:22px;padding:10px">作者简介·····</div>
    <div>
       <p> {{infos2.authorProfile}}</p>
    </div>
    <div style="color:skyblue;font-size:22px;padding:10px">目录·····</div>
    <div>
       <p v-html="infos2.catalogue"></p>
    </div>
</div>
</template>

<script>
export default {
  name: 'bookInfo',
  data () {
    return {
      infos1: {},
      infos2: {}
    }
  },
  methods: {
    getInfoById () {
      console.log('rrrrrrrr')
      this.$api.getInfoById(this.$route.query.id).then(res => {
        this.infos2 = res
      })
      this.$api.getInfoById2(this.$route.query.id).then(res => {
        this.infos1 = res
        console.log('sss', this.infos1)
      })
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
