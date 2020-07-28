<template>
  <el-dialog title="选择用户" :visible="show" @close="onClose" width="740px">
    <el-form :inline="true" :model="queryForm" class="wlw-form-inline">
      <el-form-item label="用户id">
        <el-input placeholder="请输入用户id" v-model="queryForm.id" style='width: 186px;'></el-input>
      </el-form-item>
      <el-form-item label="用户名称">
        <el-input placeholder="请输入用户名称" v-model="queryForm.username" style='width: 186px;'></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getParentList('btn')">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table class="wlw-table" ref="table"
              :data="tableData.slice((agentPage.pageNow-1)*agentPage.pageSize,agentPage.pageNow*agentPage.pageSize)"
              tooltip-effect="dark" style="width: 100%"
              @current-change="handleShopChange"
              :v-loading="tableLoading1">
      <el-table-column prop="userName" label="姓名" show-overflow-tooltip>
        <template slot-scope='scope'>
          <el-radio v-model="selShopId" :label="scope.row.id">{{scope.row.userName}}</el-radio>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="手机号码" show-overflow-tooltip></el-table-column>
      <el-table-column prop="sex" label="性别" show-overflow-tooltip></el-table-column>
    </el-table>
    <el-pagination @size-change="onSizeChange"
                   @current-change="onCurrentChange"
                   :current-page="agentPage.pageNow"
                   :page-size="agentPage.pageSize"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="agentPage.pageTotal">
    </el-pagination>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="selectShop">确 定</el-button>
      <el-button @click="onClose">取 消</el-button>
    </span>
  </el-dialog>
</template>

<script>

  export default {
    name: "mother",
    props: {
      userId: '',
      show: Boolean,
    },
    data() {
      return {
        queryForm: {
          id: '',
          username: ''
        },
        agentPage:{
          pageTotal: 0,
          pageNow: 1,
          pageSize: 10,
        },
        tableData: [],
        tableLoading1: true,

        selShopId: null,
        shopData: [],
        selected: null
      }
    },

    mounted() {
      console.log(this.userId)
      this.getParentList()
    },

    methods: {
      getParentList(val){
        this.tableData = []
        if(val){
          this.agentPage.pageNow = 1
        }

        this.tableLoading1 = true

        this.queryForm.id = this.formatStatus(this.queryForm.id)
        this.queryForm.username = this.formatStatus(this.queryForm.username)
        this.api.getAllParent(this.queryForm).then(res => {
          this.tableLoading1 = false

          if(res.data){
            this.agentPage.pageTotal = res.data.length
            this.tableData = res.data
          }
        }).catch(r => {
          console.log("调用接口失败2")
        })
      },

      formatStatus(val){
        if(val == ""){
          return null
        }else {
          return val
        }
      },

      onSizeChange(val) {
        this.agentPage.pageSize = val
        this.agentPage.pageNow = 1
        this.getParentList()
      },
      onCurrentChange(val) {
        this.agentPage.pageNow = val
        this.getParentList()
      },

      handleShopChange(val) {
        this.selected = val;
        console.log(this.selected)
      },
      selectShop() {
        if (!this.selected) {
          this.$message({
            type: 'warn',
            message: '请选择用户！'
          })
          return
        }
        this.$emit('on-select', this.selected);
      },
      onClose(type = 'dismiss') {
        this.$emit('on-select', null);
      }
    }
  }
</script>
