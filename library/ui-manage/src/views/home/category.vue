<template>
 <div class="allTop">
   <navTop :list="mylist"></navTop>
    <div class="bar">
        <div class="barContent">
          <a-button type="primary" @click="show(0)">新增分类</a-button>
        </div>
    </div>
    <div class="table">
        <a-table :loading="tableLoading"  :pagination="pagination" :columns="columns"  :dataSource="datasource" bordered style="height:664px" :scroll="{ y: 460 }">
          <template slot="opertion" slot-scope="text,record">
            <a-button style="margin:5px 10px" @click="show(record)">修改</a-button>
            <a-button type='danger' @click="deleting(record.id)">删除</a-button>
          </template>
        <template slot="status" slot-scope="text,record">
            {{record.status===1?'可读':'不可读'}}
        </template>
        <template slot="created" slot-scope="text,record">
            {{record.created?$getTime(text,0,true):''}}
        </template>
        <template slot="updated" slot-scope="text,record">
            {{record.updated?$getTime(text,0,true):''}}
        </template>
        </a-table>
    </div>
    <a-modal v-model="visible" title="分类添加/修改" :okText="status==='add'?'增加':'修改'" @ok="commit"  :width="620" :maskClosable="false">
     <div style="height:45px">
       <span >分类名：</span>
       <a-input style="width:200px" v-model="category" type="text"></a-input>
     </div>
    </a-modal>
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
      category: '',
      currId: '',
      visible: false,
      status: '',
      datasource: [
      ],
      columns: [
        { title: '分类名', dataIndex: 'name', width: 150, align: 'center' },
        { title: '当前状态', dataIndex: 'status', width: 90, align: 'center', scopedSlots: { customRender: 'status' } },
        { title: '加入时间', dataIndex: 'created', width: 150, align: 'center', scopedSlots: { customRender: 'created' } },
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
          this.getCatgoryManage()
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
    show (val) {
      this.visible = true
      if (val !== 0) {
        this.category = val.name
        this.status = 'edit'
        this.currId = val.id
      } else {
        this.category = ''
        this.status = 'add'
        this.currId = ''
      }
    },
    commit () {
      let ways = this.status === 'add' ? 'addCategory' : 'editCatgory'
      this.$api[ways]({ name: this.category, id: this.currId }).then(res => {
        if (res.success) {
          this.$message.success('操作成功')
          this.getCatgoryManage()
        } else {
          this.$message.error(res.message)
        }
      })
    },
    deleting (id) {
      this.$api.deleteCategory(id).then(res => {
        if (res.success) {
          this.$message.success('删除成功')
          this.getCatgoryManage()
        } else {
          this.$message.error(res.message)
        }
      })
    }
  },
  activated () {
    this.getCatgoryManage()
  }
}
</script>

<style>
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
