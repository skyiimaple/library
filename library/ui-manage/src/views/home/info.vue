<template>
  <div class="allTop">
    <navTop :list="mylist"></navTop>
    <div class="bar">
        <div class="barContent">
          <a-button type="primary">新增图书</a-button>
            <!-- <a-row style="height:40px;text-align:right">
                <a-col :span="6">
                    <a-col :span="6" style="padding-top:5px;"><span>过滤时间：</span></a-col>
                    <a-col :span="18">
                    <a-range-picker style="width:100%"/>
                    </a-col>
                </a-col>
                <a-col :span="6">
                    <a-col :span="6" style="padding-top:5px;"><span>过滤类型：</span></a-col>
                    <a-col :span="12">
                    <a-select
                        showSearch
                        placeholder="Select a person"
                        optionFilterProp="children"
                        style="width:100%"
                    >
                        <a-select-option value="jack">Jack</a-select-option>
                        <a-select-option value="lucy">Lucy</a-select-option>
                        <a-select-option value="tom">Tom</a-select-option>
                    </a-select>
                    </a-col>
                </a-col>
            </a-row> -->
        </div>
    </div>
    <div class="table">
        <a-table :loading="tableLoading" :columns="columns" :pagination="pagination"  :dataSource="datasource" bordered style="height:664px" :scroll="{ y: 460 }">
        <template slot="image" slot-scope="text,record">
            <div class="my-image"><img :src="record.image" alt=""></div>
        </template>
        <template slot="author" slot-scope="text,record">
            {{record.author!=='null'?record.author:'未知'}}
        </template>
        <template slot="status" slot-scope="text,record">
            {{record.status===1?'可读':'不可读'}}
        </template>
        <template slot="created" slot-scope="text,record">
            {{record.created?$getTime(record.status,0,true):''}}
        </template>
        <template slot="updated" slot-scope="text,record">
            {{record.updated?$getTime(record.status,0,true):''}}
        </template>
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
  name: 'info',
  components: {
    navTop: () => import('@/components/navtop')
  },
  data () {
    return {
      tableLoading: true,
      mylist: ['图书管理', '我的图书'],
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
      loading: true,
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
          this.getBookManage()
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
    getBookManage () {
      let data = {
        page: this.pagination.current,
        size: this.pagination.pageSize
      }
      this.$api.getBookManage(data).then(res => {
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
      this.$api.deleteBook(id).then(res => {
        if (res.success) {
          this.$message.sueecee('删除成功')
        }
      })
    }
  },
  mounted () {
    this.getBookManage()
  }
}
</script>

<style >
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
