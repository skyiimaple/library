<template>
 <div class="allTop">
   <navTop :list="mylist"></navTop>
    <div class="bar">
        <div class="barContent">
          <a-button type="primary" @click="adding">新增评论</a-button>
        </div>
    </div>
    <div class="table">
        <a-table :loading="tableLoading"  :pagination="pagination" :columns="columns"  :dataSource="datasource" bordered style="height:664px" :scroll="{ y: 460 }">
          <template slot="opertion" slot-scope="text,record">
            <a-button type='danger' @click="deleting(record.id)">删除</a-button>
          </template>
        <template slot="updated" slot-scope="text,record">
            {{record.updated?$getTime(text,0,true):''}}
        </template>
        </a-table>
    </div>
     <a-modal v-model="visible" title="评论添加" @ok="commit"  :width="620" :maskClosable="false">
     <div style="height:45px">
       <span >图书：</span>
       <a-input style="width:200px" v-model="rendData.bid" type="number"></a-input>
     </div>
     <div style="height:45px">
       <span>用户：</span>
       <a-input style="width:200px" v-model="rendData.uid" type="number"></a-input>
     </div>
     <!-- <div style="height:45px">
       <span>评分：</span>
       <a-input style="width:200px"></a-input>
     </div> -->
     <div style="height:45px">
       <span>内容：</span>
       <a-input style="width:400px" v-model="rendData.text"></a-input>
     </div>
    </a-modal>
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
      rendData: {
        uid: '',
        bid: '',
        text: ''
      },
      visible: false,
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
          this.getCommon()
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
    commit () {
      this.$api.addCommon(this.rendData).then(res => {
        if (res.success) {
          this.$message.success('添加成功')
          this.getCommon()
        } else {
          this.$message.error(res.message)
        }
      })
    },
    adding () {
      this.visible = true
    },
    deleting (id) {
      this.$api.deleteComment(id).then(res => {
        if (res.success) {
          this.$message.sueecee('删除成功')
        } else {
          this.$message.error(res.message)
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
