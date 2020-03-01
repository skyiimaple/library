<template>
  <div class="allTop">
    <navTop :list="mylist"></navTop>
    <div class="bar">
        评论管理->我的评论
    </div>
  </div>
</template>

<script>
export default {
  name: 'comment',
  components: {
    navTop: () => import('@/components/navtop')
  },
  data () {
    return {
      mylist: ['评论管理', '我的评论']
    }
  },
  methods: {
  }
}
</script>

<style scoped>
</style>
