<template>
  <div>
    <navTop></navTop>
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
    <div class="bot">
      <div class="ying">隐私政策</div>
      <div><span>Tel: 123778988   </span> <span>   粤ICP备123456789号-0</span></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Main',
  components: {
    navTop: () => import('@/components/navTop')
  }
}
</script>

<style scoped >
.bot{
  padding: 4rem 6rem;
  text-align: center;
}
.ying{
  cursor: pointer;
  padding:1rem 0 2rem 0;
}
</style>
