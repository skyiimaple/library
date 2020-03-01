<template>
  <div class="allTop">
    <navTop :list="mylist"></navTop>
    <div class="bar">
        图书分类->我的分类
    </div>
  </div>
</template>

<script>
export default {
  name: 'category',
  components: {
    navTop: () => import('@/components/navtop')
  },
  data () {
    return {
      mylist: ['图书分类', '我的分类']
    }
  },
  methods: {
  }
}
</script>

<style scoped>
</style>
