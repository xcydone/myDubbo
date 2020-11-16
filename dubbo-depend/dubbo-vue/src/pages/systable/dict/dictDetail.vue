<template>
  <div>
    <wlw-breadcrumb></wlw-breadcrumb>
    <el-card class="wlw-card" shadow="never">
      <el-form :inline="true" :model="queryForm" class="wlw-form-inline">
        <el-form-item label="字典项名称">
          <el-input placeholder="请输入字典项名称" v-model="queryForm.dictName" style='width: 186px;'></el-input>
        </el-form-item>
        <el-form-item label="字典项编码">
          <el-input placeholder="请输入字典项编码" v-model="queryForm.dictValue" style='width: 186px;'></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="queryForm.statusCd" placeholder="请选择" style='width: 186px;'>
            <el-option v-for="item in dictList.statusCdList"
                       :key="item.dictValue"
                       :label="item.dictName"
                       :value="item.dictValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item style='width: 340px;'>
          <el-button type="primary" @click="getDictList('btn')">查询</el-button>
          <el-button type="primary" @click="setQueryForm()" class='wlw--btn-gray'>重置</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addDict()">新增</el-button>
          <el-button type="primary" @click="deleteDict()" class='wlw--btn-gray'>删除</el-button>
          <el-button type="primary" @click="validDict('on')">启用</el-button>
          <el-button type="primary" @click="validDict('off')" class='wlw--btn-gray'>停用</el-button>
        </el-form-item>
      </el-form>
      <el-table class="wlw-table" ref="table"
                :data="tableData.slice((agentPage.pageNow-1)*agentPage.pageSize,agentPage.pageNow*agentPage.pageSize)"
                tooltip-effect="dark" style="width: 100%" stripe
                :v-loading="tableLoading1">
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="dictName" label="字典项名称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="dictValue" label="字典项编码" show-overflow-tooltip></el-table-column>
        <el-table-column prop="createTime" label="创建时间" show-overflow-tooltip></el-table-column>
        <el-table-column prop="statusCd" label="启用状态" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{translate(scope.row.statusCd,'STATUS_CD')}}</span>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button @click="editDict(scope.row)" type="text" size="small">编辑</el-button>
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

    <el-dialog :title="addDictTitle" :visible.sync="addDictVisible" v-show="addDictVisible">
      <el-form ref="dictForm" :model="dictForm" label-width="102px" style='width: 518px;'>
        <el-form-item label="字典名称： ">
          <el-input v-model="dictForm.dictName"></el-input>
        </el-form-item>
        <el-form-item label="字典属性： ">
          <el-input v-model="dictForm.dictValue"></el-input>
        </el-form-item>
        <el-form-item label="是否启用： ">
          <el-radio v-model="dictForm.statusCd" label="2000">是</el-radio>
          <el-radio v-model="dictForm.statusCd" label="2200">否</el-radio>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addOrEditDictIn()">保存</el-button>
          <el-button type="primary" @click="addDictVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
  import WlwBreadcrumb from '@/components/breadcrumb/Breadcrumb'

  export default {
    name: "dictDetail",
    components: {
      WlwBreadcrumb
    },
    data() {
      return {
        typeId: null,
        tableData: [],
        detailData: {},
        exporting: false,
        queryForm:{
          dictName: null,
          dictValue: null,
          statusCd: null
        },
        tableLoading1: true,
        agentPage:{
          pageNow: 1,
          pageSize: 10,
          pageTotal: 0
        },
        addDictVisible: false,
        addDictTitle: '',
        addOrEdit: '',
        dictForm:{
          dictId: null,
          dictName: null,
          dictValue: null,
          statusCd: null
        },
        dictList: {
          statusCdList: []
        },
      }
    },

    mounted: function() {
      /*this.typeId = this.$route.params.typeId*/
      this.typeId = this.$route.query.typeId
      this.dictForm.typeId = this.typeId

      // 获取主数据
      this.findDictList('STATUS_CD')

      // 获取信息
      this.getDictList()
    },

    methods: {
      findDictList(val){
        /*httpSys.findDictListByTypeValue(val).then( res => {
          if(res){
            this.dictList.statusCdList = res.data
          }
        })*/
      },

      translate(val, name){
        let attrName = ''
        if(name == 'STATUS_CD' && this.dictList.statusCdList.length > 0){
          this.dictList.statusCdList.forEach(attr => {
            if (val == attr.dictValue) {
              attrName = attr.dictName
            }
          })
        }
        return attrName
      },

      onSizeChange(val){
        this.agentPage.pageSize = val
        this.agentPage.pageNow = 1
      },

      onCurrentChange (val) {
        this.agentPage.pageNow = val
        this.getDictTypeList()
      },

      setQueryForm(){
        this.queryForm.dictValue = null
        this.queryForm.dictName = null
        this.queryForm.statusCd = null
        this.getDictList()
      },
      getDictList(val){
        if(val){
          this.agentPage.pageNow = 1
        }
        this.queryDictList()
      },

      queryDictList() {
        this.tableData = []
        this.tableLoading1 = true

        let dictName = this.formatStatus(this.queryForm.dictName)
        let dictValue = this.formatStatus(this.queryForm.dictValue)
        let statusCd = this.formatStatus(this.queryForm.statusCd)

        /*httpSys.findDictList({
          'typeId': this.typeId,
          'dictName': dictName,
          'dictValue':dictValue,
          'statusCd': statusCd
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

      addDict(){
        this.addDictVisible = true
        this.addDictTitle = '新增字典类别'
        this.addOrEdit = 'add'
      },

      addDictIn(){
        httpSys.addDictIn(this.dictForm).then(res => {
          this.addDictVisible = false
          this.getDictList()
        })
      },


      // 编辑
      editDict(row) {
        this.addDictVisible = true
        this.addDictTitle = "编辑字典类别"
        this.addOrEdit = 'edit'
        this.dictForm = row
      },

      editDictIn() {
        httpSys.editDictIn(this.dictForm).then( res =>{
          this.addDictVisible = false
          this.getDictList()
        })
      },

      addOrEditDictIn(){
        if(this.addOrEdit == 'add'){
          this.addDictIn()
        }else{
          this.editDictIn()
        }
      },

      validDict(val){
        let statusCd = '2000'
        if(val == 'off'){
          statusCd = '2200'
        }

        let dictIds = []
        let dictList = this.$refs.table.selection
        if (dictList.length === 0) {
          this.$message({
            type: 'warning',
            message: '请选择您需要删除的项'
          })
          return
        }else{
          dictList.forEach( dict =>{
            dictIds.push(dict.dictId)
          })
        }

        httpSys.validDict({
          'dictIds': dictIds,
          'statusCd': statusCd
        }).then( res => {
          this.$message({
            type: 'success',
            message: '修改状态成功'
          })
          this.getDictList()
        })
      },
      deleteDict(){
        let dictIds = []
        let dictList = this.$refs.table.selection
        if (dictList.length === 0) {
          this.$message({
            type: 'warning',
            message: '请选择您需要删除的项'
          })
          return
        }else{
          dictList.forEach( dict =>{
            dictIds.push(dict.dictId)
          })
        }

        httpSys.validDict({
          'dictIds': dictIds,
          'status': '1100'
        }).then( res => {
          this.$message({
            type: 'success',
            message: '删除字典成功'
          })
          this.getDictList()
        })
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
