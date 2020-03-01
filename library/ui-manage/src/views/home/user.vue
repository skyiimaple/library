<template>
  <div class="allTop">
    <navTop :list="mylist"></navTop>
    <div class="bar">
        个人中心->用户
    </div>
  </div>
</template>

<script>
export default {
  name: 'user',
  components: {
    navTop: () => import('@/components/navtop')
  },
  data () {
    return {
      mylist: ['个人中心', '个人中心']
    }
  },
  methods: {
  }
}
</script>

<style scoped>
</style>
