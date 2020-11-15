<template>
  <div>
    <wlw-breadcrumb></wlw-breadcrumb>
    <el-card class="wlw-card" shadow="never">
      <el-form :inline="true" :model="queryForm" class="wlw-form-inline">
        <el-form-item label="字典名称">
          <el-input placeholder="请输入字典名称" v-model="queryForm.typeName" style='width: 186px;'></el-input>
        </el-form-item>
        <el-form-item label="字典编码">
          <el-input placeholder="请输入字典编码" v-model="queryForm.typeValue" style='width: 186px;'></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getDictTypeList('btn')">查询</el-button>
          <el-button type="primary" @click="setQueryForm()" class='wlw--btn-gray'>重置</el-button>
          <el-button type="primary" @click="addDictType()" class='wlw--btn-gray'>新增字典类别</el-button>
        </el-form-item>
      </el-form>
      <el-table class="wlw-table" ref="table"
                :data="tableData.slice((agentPage.pageNow-1)*agentPage.pageSize,agentPage.pageNow*agentPage.pageSize)"
                tooltip-effect="dark" style="width: 100%"
                :v-loading="tableLoading1">
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="typeName" label="类别名称" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-link type="primary" @click="getDetail(scope.row)">{{scope.row.typeName}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="typeValue" label="类别编码" show-overflow-tooltip></el-table-column>
        <el-table-column prop="createTime" label="创建时间" show-overflow-tooltip></el-table-column>
        <el-table-column prop="typeDesc" label="描述" show-overflow-tooltip></el-table-column>
        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button @click="editDictType(scope.row)" type="text" size="small">编辑</el-button>
            <el-button @click="getDictList(scope.row)" type="text" size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination @size-change="onSizeChange"
                     @current-change="onCurrentChange"
                     :current-page="agentPage.pageNow"
                     :page-size="agentPage.pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="agentPage.pageTotal">
      </el-pagination>
    </el-card>
    <el-drawer :visible.sync="drawerVisible" v-if="drawerVisible" size='350px'>
      <div slot='title'>
        <p>详情</p>
      </div>
      <ul :data="detailData">
        <li style="padding-top: 20px; margin-left: 20px">
          <span class="name">类别名称</span>
          <span class="value">{{ detailData.typeName }}</span>
        </li>
        <li style="padding-top: 20px; margin-left: 20px">
          <span class="name">类别编码</span>
          <span class="value">{{ detailData.typeValue }}</span>
        </li>
        <li style="padding-top: 20px; margin-left: 20px">
          <span class="name">描述</span>
          <span class="value">{{ detailData.typeDesc}}</span>
        </li>
      </ul>
    </el-drawer>

    <el-dialog :title="addDictTypeTitle" :visible.sync="addDictTypeVisible" v-show="addDictTypeVisible">
      <el-form ref="dictTypeForm" :model="dictTypeForm" label-width="102px" style='width: 518px;'>
        <el-form-item label="类别名称： ">
          <el-input v-model="dictTypeForm.typeName"></el-input>
        </el-form-item>
        <el-form-item label="类别编码： ">
          <el-input v-model="dictTypeForm.typeValue"></el-input>
        </el-form-item>
        <el-form-item label="类别描述： ">
          <el-input v-model="dictTypeForm.typeDesc"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addOrEditDictTypeIn()">保存</el-button>
          <el-button type="primary" @click="addDictTypeVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
  import WlwBreadcrumb from '@/components/breadcrumb/Breadcrumb'

  export default {
    name: "dictList",
    components: {
      WlwBreadcrumb
    },
    data() {
      return {
        userId: '12',
        tableData: [],
        detailData: {},
        exporting: false,
        queryForm:{
          typeName: null,
          typeValue: null
        },
        tableLoading1: true,
        agentPage:{
          pageNow: 1,
          pageSize: 10,
          pageTotal: 0
        },
        drawerVisible: false,
        addDictTypeVisible: false,
        addDictTypeTitle: '',
        addOrEdit: '',
        dictTypeForm:{
          typeId: null,
          typeName: null,
          typeValue: null,
          typeDesc: null
        },
      }
    },

    mounted: function() {
      // 获取信息
      this.getDictTypeList()
    },

    methods: {
      onSizeChange(val){
        this.agentPage.pageSize = val
        this.agentPage.pageNow = 1
      },

      onCurrentChange (val) {
        this.agentPage.pageNow = val
        this.getDictTypeList()
      },

      setQueryForm(){
        this.queryForm.typeValue = null
        this.queryForm.typeName = null
        this.getDictTypeList()
      },
      getDictTypeList(val){
        if(val){
          this.agentPage.pageNow = 1
        }
        this.queryDictTypeList()
      },

      queryDictTypeList() {
        this.tableData = []
        this.tableLoading1 = true

        let typeName = this.formatStatus(this.queryForm.typeName)
        let typeValue = this.formatStatus(this.queryForm.typeValue)
        /*httpSys.findDictTypeList({
          'typeName':typeName,
          'typeValue':typeValue
        }).then(res => {
          this.tableLoading1 = false

          if(res.data){
            this.agentPage.pageTotal = res.data.length
            this.tableData = res.data
          }
        }).catch(r => {
          console.log("调用接口失败")
        })*/
      },
      formatStatus(val){
        if(val == ""){
          return null
        }else {
          return val
        }
      },

      getDetail(val){
        this.detailData = null
        this.drawerVisible = true
        this.tableData.forEach( res=>{
          if(res.typeId == val.typeId){
            this.detailData = res
          }
        })
      },

      addDictType(){
        this.addDictTypeVisible = true
        this.addDictTypeTitle = '新增字典类别'
        this.addOrEdit = 'add'
      },

      addDictTypeIn(){
        /*httpSys.addDictTypeIn(this.dictTypeForm).then(res => {
          this.addDictTypeVisible = false
          this.getDictTypeList()
        })*/
      },


      // 编辑
      editDictType(row) {
        this.addDictTypeVisible = true
        this.addDictTypeTitle = "编辑字典类别"
        this.addOrEdit = 'edit'
        this.dictTypeForm = row
        console.log(this.dictTypeForm)
      },

      editDictTypeIn() {
        /*httpSys.editDictTypeIn(this.dictTypeForm).then( res =>{
          this.addDictTypeVisible = false
          this.getDictTypeList()
        })*/
      },

      addOrEditDictTypeIn(){
        if(this.addOrEdit == 'add'){
          this.addDictTypeIn()
        }else{
          this.editDictTypeIn()
        }
      },

      getDictList(row){
        this.$router.push('/systable/dictDetail?typeId='+ row.typeId)
        /*this.$router.push({name: 'dictDetail', params: {typeId: row.typeId}})*/
      },
    }
  }
</script>

<style lang="scss" scoped>
  .wlw-table {
    margin-top: 17px;
    margin-bottom: 15px;
  }

  .wlw-form-inline {
    .el-form-item {
      width: 24%;
      margin-right: 0;
    }
  }
</style>
