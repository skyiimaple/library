<template>
  <div class="allTop">
    <div>
      <div class="title">
        <div class="name">电子图书</div>
        <div class="more">更多电子图书</div>
      </div>
      <div class="content">
        <div class="book" v-for="res in 4" :key="res">
          <a-card hoverable style="width: 100%">
            <img
            alt="example"
            :src="require('@/assets/img/b'+res+'.jpg')"
            slot="cover"
            />
            <a-card-meta title="Europe Street beat">
              <template slot="description">www.instagram.com</template>
            </a-card-meta>
          </a-card>
        </div>
      </div>
    </div>
    <div>
      <div class="title">
        <div class="name">年度榜单</div>
        <div class="more">更多榜单</div>
      </div>
      <div class="content">
        <div class="book" v-for="res in 4" :key="res">
          <a-card hoverable style="width: 100%">
            <img
            alt="example"
            :src="require('@/assets/img/c'+res+'.jpg')"
            slot="cover"
            />
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
  name: 'info'
}
</script>

<style scoped>
.allTop{
  cursor: default;
  /* display: flex; */
}
.title{
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin:3rem 7rem 1.5rem 7rem;
  border-bottom: 0.1rem solid #ccc;
}
.title .name{
  font-size: 3rem;
  font-weight: bold;
}
.title .more{
  font-size: 1.6rem;
  color: skyblue;
}
.content{
  width: 100%;
  padding:1rem 7rem 1.5rem 7rem;
  display: flex;
  /* justify-content: space-around; */
}
.content .book{
  height: auto;
  width:25%;
  padding:0 3%;
  /* border: #ccc solid 0.1rem; */
}
</style>
