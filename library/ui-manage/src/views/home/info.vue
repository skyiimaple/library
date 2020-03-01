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
        <a-table :columns="columns"  :dataSource="data" bordered style="height:664px" >
            <template v-for="col in ['name', 'age', 'address']" :slot="col" slot-scope="text, record">
            <div :key="col">
                <a-input
                v-if="record.editable"
                style="margin: 0"
                :value="text"
                @change="e => handleChange(e.target.value, record.key, col)"
                />
                <template v-else>{{text}}</template>
            </div>
            </template>
            <template slot="operation" slot-scope="text, record">
            <div class='editable-row-operations'>
                <span v-if="record.editable">
                <a @click="() => save(record.key)">Save</a>
                <a-popconfirm title='Sure to cancel?' @confirm="() => cancel(record.key)">
                    <a>Cancel</a>
                </a-popconfirm>
                </span>
                <span v-else>
                <a @click="() => edit(record.key)">Edit</a>
                </span>
            </div>
            </template>
        </a-table>
    </div>
  </div>
</template>

<script>
const columns = [{
  title: 'name',
  dataIndex: 'name',
  width: '25%',
  scopedSlots: { customRender: 'name' }
}, {
  title: 'age',
  dataIndex: 'age',
  width: '15%',
  scopedSlots: { customRender: 'age' }
}, {
  title: 'address',
  dataIndex: 'address',
  width: '40%',
  scopedSlots: { customRender: 'address' }
}, {
  title: 'operation',
  dataIndex: 'operation',
  scopedSlots: { customRender: 'operation' }
}]

const data = []
for (let i = 0; i < 100; i++) {
  data.push({
    key: i.toString(),
    name: `Edrward ${i}`,
    age: 32,
    address: `London Park no. ${i}`
  })
}
export default {
  name: 'info',
  components: {
    navTop: () => import('@/components/navtop')
  },
  data () {
    this.cacheData = data.map(item => ({ ...item }))
    return {
      mylist: ['图书管理', '我的图书'],
      data,
      columns,
      loading: true
    }
  },
  methods: {
    handleChange (value, key, column) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target[column] = value
        this.data = newData
      }
    },
    edit (key) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target.editable = true
        this.data = newData
      }
    },
    save (key) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        delete target.editable
        this.data = newData
        this.cacheData = newData.map(item => ({ ...item }))
      }
    },
    cancel (key) {
      const newData = [...this.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        Object.assign(target, this.cacheData.filter(item => key === item.key)[0])
        delete target.editable
        this.data = newData
      }
    }
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
