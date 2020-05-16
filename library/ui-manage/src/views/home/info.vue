<template>
  <div class="allTop">
    <navTop :list="mylist"></navTop>
    <div class="bar">
        <div class="barContent">
            <a-row style="height:40px;text-align:right">
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
            </a-row>
        </div>
    </div>
    <div class="table">
        <a-table :columns="columns"  :dataSource="datasource" bordered style="height:664px" :scroll="{ x: 1500, y: 400 }">
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
      mylist: ['图书管理', '我的图书'],
      datasource: [
      ],
      columns: [
        { title: '书名', dataIndex: 'name', width: 150, align: 'center' },
        { title: '作者', dataIndex: 'author', width: 150, align: 'center' },
        { title: '翻译人', dataIndex: 'translator', width: 150, align: 'center' },
        { title: '出版社', dataIndex: 'publisher', width: 150, align: 'center' },
        { title: '封面照片', dataIndex: 'image', width: 150, align: 'center' },
        { title: '当前状态', dataIndex: 'status', width: 150, align: 'center' },
        { title: '加入时间', dataIndex: 'created', width: 150, align: 'center' },
        { title: '更新时间', dataIndex: 'updated', width: 150, align: 'center' },
        { title: '操作', dataIndex: 'opertion', align: 'center' }
      ],
      loading: true
    }
  },
  methods: {
    getBookManage () {
      let data = {
        page: 1,
        size: 5
      }
      this.$api.getBookManage(data).then(res => {
        if (res.success) {
          console.log('sss', res)
          this.datasource = res.queryResult.list
        }
      })
    }
  },
  mounted () {
    this.getBookManage()
  }
}
</script>

<style scoped>
.bar{
    padding: 10px 0;
}
.barContent{
    height: 100px;
    background: rgba(255,255,255,0.8);
    padding: 10px;
}
.table{
    background-color: #fff;
    border-bottom-right-radius: 20px;
}
</style>
