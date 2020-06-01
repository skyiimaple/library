<template>
 <div class="allTop">
   <navTop :list="mylist"></navTop>
    <div class="bar">
        <div class="barContent">
          <a-button type="primary">新增分类</a-button>
        </div>
    </div>
    <div class="table">
        <a-table :loading="tableLoading"  :pagination="pagination" :columns="columns"  :dataSource="datasource" bordered style="height:664px" :scroll="{ y: 460 }">
          <template slot="opertion" slot-scope="text,record">
            <a-button style="margin:5px 10px" @click="edit(record.id)">修改</a-button>
            <a-button type='danger' @click="delete(record.id)">删除</a-button>
          </template>
        </a-table>
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
      mylist: ['图书分类', '我的分类'],
      datasource: [
      ],
      columns: [
        { title: '书名', dataIndex: 'name', width: 150, align: 'center' },
        { title: '作者', dataIndex: 'author', width: 80, align: 'center', scopedSlots: { customRender: 'author' } },
        { title: '翻译人', dataIndex: 'translator', width: 80, align: 'center' },
        { title: '出版社', dataIndex: 'publisher', width: 150, align: 'center' },
        { title: '封面照片', dataIndex: 'image', width: 150, align: 'center', scopedSlots: { customRender: 'image' } },
        { title: '当前状态', dataIndex: 'status', width: 90, align: 'center', scopedSlots: { customRender: 'status' } },
        { title: '加入时间', dataIndex: 'created', width: 150, align: 'center', scopedSlots: { customRender: 'created' } },
        { title: '更新时间', dataIndex: 'updated', width: 150, align: 'center', scopedSlots: { customRender: 'updated' } },
        { title: '操作', dataIndex: 'opertion', width: 150, align: 'center', scopedSlots: { customRender: 'opertion' } }, {}
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
    getCatgoryManage () {
      let data = {
        page: this.pagination.current,
        size: this.pagination.pageSize
      }
      this.$api.getCatgoryManage(data).then(res => {
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
      this.$api.deleteCategory(id).then(res => {
        if (res.success) {
          this.$message.sueecee('删除成功')
        }
      })
    }
  }
}
</script>

<style scoped>
</style>
