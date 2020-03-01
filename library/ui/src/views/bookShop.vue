<template>
 <div style="padding:0 10%;">
    <div style="margin-top:30px">
      <a-radio-group  v-model="type" size="large" buttonStyle="solid">
        <a-radio-button value="a">浏览最多</a-radio-button>
        <a-radio-button value="b">点赞最多</a-radio-button>
      </a-radio-group>
    </div>
    <div style="margin-top:30px" v-for="(res,index) in 3" :key="index">
        <div style="padding:0 4%;border:1px #ccc solid">
            <a-list itemLayout="vertical" size="large" :dataSource="listData">
                <a-list-item slot="renderItem" slot-scope="item" key="item.title">
                <template slot="actions" v-for="{type, text} in actions">
                    <span :key="type"><a-icon :type="type" style="margin-right: 8px" />{{text}}</span>
                </template>
                <img slot="extra" :src="require('@/assets/img/shop'+res+'.jpg')" width="160px" height="160px"/>
                <a-list-item-meta :description="item.description">
                    <a slot="title" >{{item.title}}</a>
                </a-list-item-meta>
                {{item.content}}
                </a-list-item>
            </a-list>
        </div>
    </div>
 </div>
</template>

<script>
export default {
  name: 'bookShop',
  data () {
    return {
      listData: [
        {
          title: '快乐书店',
          description: 'Ant Design, a design language for background applications, is refined by Ant UED Team.',
          content: 'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.'
        }
      ],
      actions: [
        { type: 'star-o', text: '156' },
        { type: 'like-o', text: '156' },
        { type: 'message', text: '2' }
      ],
      type: 'a'
    }
  }
}
</script>

<style lang="stylus" scoped>

</style>
