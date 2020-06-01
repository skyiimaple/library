<template>
 <div class="allTop">
   <navTop :list="mylist"></navTop>
    <div class="bar">
        <div class="barContent">
          <a-button type="primary">新增评论</a-button>
        </div>
    </div>
    <div class="table">
        <a-table :loading="tableLoading"  :pagination="pagination" :columns="columns"  :dataSource="datasource" bordered style="height:664px" :scroll="{ y: 460 }">
          <template slot="opertion" slot-scope="text,record">
            <a-button style="margin:5px 10px" @click="edit(record.id)">修改</a-button>
            <a-button type='danger' @click="delete(record.id)">删除</a-button>
          </template>
        <template slot="updated" slot-scope="text,record">
            {{record.updated?$getTime(record.status,0,true):''}}
        </template>
        </a-table>
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
      mylist: ['评论管理', '我的评论'],
      datasource: [
      ],
      columns: [
        { title: '书id', dataIndex: 'bid', width: 150, align: 'center' },
        { title: '内容', dataIndex: 'content', width: 120, align: 'center', scopedSlots: { customRender: 'content' } },
        { title: '得分', dataIndex: 'score', width: 100, align: 'center' },
        { title: '更新时间', dataIndex: 'updated', width: 150, align: 'center', scopedSlots: { customRender: 'updated' } },
        { title: '操作', dataIndex: 'opertion', width: 220, align: 'center', scopedSlots: { customRender: 'opertion' } }, {}
      ],
      tableLoading: false,
      pagination: {
        current: 1,
        pageSize: 10,
        pageSizeOptions: ['10', '50', '100'],
        total: 0,
        showSizeChanger: true,
        showTotal: function (total) {
          return '共' + total + '条记录'
        },
        onChange: (page, pageSize) => {
          this.pagination.current = page
          this.getCatgoryManage()
        },
        onShowSizeChange: (current, size) => {
          this.pagination.current = 1
          this.pagination.pageSize = size
          console.log('222')
        }
      }
    }
  },
  methods: {
    getCommon () {
      let data = {
        page: this.pagination.current,
        size: this.pagination.pageSize
      }
      this.$api.getCommon(data).then(res => {
        if (res.success) {
          console.log('sss', res)
          this.datasource = res.queryResult.list
          this.tableLoading = false
          this.pagination.total = res.queryResult.total
        }
      })
    },
    edit (id) {

    },
    delete (id) {
      this.$api.deleteComment(id).then(res => {
        if (res.success) {
          this.$message.sueecee('删除成功')
        }
      })
    }
  },
  activated () {
    this.getCommon()
  }
}
</script>

<style scoped>
.bar{
    padding: 10px 0;
}
.barContent{
    background: rgba(255,255,255,0.8);
    padding: 10px;
}
.table{
    background-color: #fff;
    border-bottom-right-radius: 20px;
}
.my-image{
  width: 100px;
  height: 100px;
  position: relative;
  overflow: hidden;
  display: flex;
  justify-content: center;
}
.my-image img{
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
}
</style>
