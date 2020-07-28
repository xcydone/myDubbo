<template>
  <div>
    <wlw-breadcrumb></wlw-breadcrumb>
    <el-card class="wlw-card" shadow="never">
      <el-form :inline="true" :model="queryForm" class="wlw-form-inline">
        <el-form-item label="企业名称">
          <el-input placeholder="请输入企业名称" v-model="queryForm.companyName" style='width: 186px;'></el-input>
        </el-form-item>
        <el-form-item label="企业编码">
          <el-input placeholder="请输入用户编码" v-model="queryForm.companyCode" style='width: 186px;'></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getCompanyList('btn')">查询</el-button>
          <el-button type="primary" @click="setQueryForm()" class='wlw--btn-gray'>重置</el-button>
          <el-button type="primary" @click="addCompany()" class='wlw--btn-gray'>新增企业</el-button>
        </el-form-item>
      </el-form>
      <el-table class="wlw-table" ref="table"
                :data="tableData.slice((agentPage.pageNow-1)*agentPage.pageSize,agentPage.pageNow*agentPage.pageSize)"
                tooltip-effect="dark" style="width: 100%"
                :v-loading="tableLoading1">
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="companyName" label="企业名称" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-link type="primary" @click="getDetail(scope.row)">{{scope.row.companyName}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="companyCode" label="企业编码" show-overflow-tooltip></el-table-column>
        <el-table-column prop="companyDesc" label="描述" show-overflow-tooltip></el-table-column>
        <el-table-column prop="status" label="状态" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{translate(scope.row.status,'STATUS')}}</span>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button @click="editPersonDetail(scope.row)" type="text" size="small">编辑</el-button>
            <el-button @click="getCompanyEmployee(scope.row)" type="text" size="small">查看企业下的员工</el-button>
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
          <span class="name">企业名称</span>
          <span class="value">{{ detailData.companyName }}</span>
        </li>
        <li style="padding-top: 20px; margin-left: 20px">
          <span class="name">企业编码</span>
          <span class="value">{{ detailData.companyCode }}</span>
        </li>
        <li style="padding-top: 20px; margin-left: 20px">
          <span class="name">描述</span>
          <span class="value">{{ detailData.desc}}</span>
        </li>
      </ul>
    </el-drawer>

    <el-dialog title="新增企业" :visible.sync="addCompanyVisible" v-show="addCompanyVisible">
      <el-form ref="companyForm" :model="companyForm" label-width="102px" style='width: 518px;'>
        <el-form-item label="企业名称： ">
          <el-input v-model="companyForm.companyName"></el-input>
        </el-form-item>
        <el-form-item label="企业编码： ">
          <el-input v-model="companyForm.companyCode"></el-input>
        </el-form-item>
        <el-form-item label="企业描述： ">
          <el-input v-model="companyForm.companyDesc"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addCompanyIn()">保存</el-button>
          <el-button type="primary" @click="addCompanyVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="查看 企业 员工" :visible.sync="employeeVisible" v-if="employeeVisible" width="740px">
      <el-form :inline="true" :model="personForm" class="wlw-form-inline2 wlw-dialog-form">
        <el-form-item label="名称:" label-width="40px">
          <el-input v-model="personForm.personName" placeholder="请输入员工名称" style='width: 160px;'></el-input>
        </el-form-item>
        <el-form-item label="职位:" label-width="40px" style='margin-right: 10px;'>
          <el-select v-model="personForm.personPosition" style='width: 160px;' clearable>
            <el-option v-for="item in dictList.positionList"
                       :key="item.dictValue"
                       :label="item.dictName"
                       :value="item.dictValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item style='margin-right: 0;'>
          <el-button type="primary" @click="getCompanyEmployeeList('btn')">查询</el-button>
          <el-button type="primary" @click="addEmployee()">新增</el-button>
          <el-button type="primary" @click="addCompanyVisible = false">删除</el-button>
        </el-form-item>
      </el-form>
      <el-table class="wlw-table" ref="table"
                :data="tableDataPerson.slice((personPage.pageNow-1)*personPage.pageSize,personPage.pageNow*personPage.pageSize)"
                tooltip-effect="dark" style="width: 100%"
                :v-loading="tableLoading1">
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="userName" label="名称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="employeeCode" label="人员编号" show-overflow-tooltip></el-table-column>
        <el-table-column prop="positionId" label="职位" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{translate(scope.row.positionId,'POSITION')}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="dept" label="部门" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{translate(scope.row.dept,'DEPT')}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{translate(scope.row.status,'STATUS')}}</span>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination @size-change="onSizeChangePerson"
                     @current-change="onCurrentChangePerson"
                     :current-page="personPage.pageNow"
                     :page-size="personPage.pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="personPage.pageTotal">
      </el-pagination>
    </el-dialog>

    <el-dialog title="新增企业员工" :visible.sync="addEmployeeVisible" v-if="addEmployeeVisible">
      <el-form ref="employeeForm" :model="employeeForm" label-width="102px" style='width: 418px;'>
        <el-form-item label="员工名称： " placeholder="请选择员工" readonly>
          <el-select v-model="employeeForm.personId">
            <el-option v-for="item in dictList.employeeList"
                       :key="item.id"
                       :label="item.userName"
                       :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="员工编号： " placeholder="请输入编号" readonly>
          <el-input v-model="employeeForm.employeeCode"></el-input>
        </el-form-item>
        <el-form-item label="部门： ">
          <el-select v-model="employeeForm.personDept">
            <el-option v-for="item in dictList.deptList"
                       :key="item.dictValue"
                       :label="item.dictName"
                       :value="item.dictValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="职位： ">
          <el-select v-model="employeeForm.personPositon">
            <el-option v-for="item in dictList.positionList"
                       :key="item.dictValue"
                       :label="item.dictName"
                       :value="item.dictValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addEmployeeIn()">保存</el-button>
          <el-button type="primary" @click="addEmployeeVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
  import WlwBreadcrumb from '@/components/breadcrumb/Breadcrumb'

  export default {
    name: "table-main",
    components: {
      WlwBreadcrumb
    },
    data() {
      return {
        userId: '12',
        tableData: [],
        detailData: {},
        tableDataPerson: [],
        exporting: false,
        queryForm:{
          companyName: null,
          companyCode: null
        },
        tableLoading1: true,
        agentPage:{
          pageNow: 1,
          pageSize: 10,
          pageTotal: 0
        },
        personPage:{
          pageNow: 1,
          pageSize: 10,
          pageTotal: 0
        },
        drawerVisible: false,
        addCompanyVisible: false,
        employeeVisible: false,
        companyCode: null,
        addEmployeeVisible: false,
        companyForm:{
          companyName: null,
          companyCode: null,
          companyDesc: null
        },
        personForm:{
          personName: null,
          personPosition: null
        },
        employeeForm:{
          personId: null,
          personPositon: null,
          personDept: null,
          companyCode: null,
          employeeCode: null
        },
        dictList: {
          statusList: [],
          positionList: [],
          deptList: [],
          employeeList: []
        },
      }
    },

    mounted: function() {

      // 获取主数据
      this.findDictList('STATUS')
      this.findDictList('POSITION')
      this.findDictList('DEPT')

      this.getPersonList()

      // 获取信息
      this.getCompanyList()
    },

    methods: {

      findDictList(val){
        this.api.findDictListByTypeValue(val).then( res => {
          if(res){
            if(val == 'POSITION'){
              this.dictList.positionList = res.data
            }

            if(val == 'STATUS'){
              this.dictList.statusList = res.data
            }

            if(val == 'DEPT'){
              this.dictList.deptList = res.data
            }
          }
        })
      },

      translate(val, name){
        let attrName = ''
        if(name == 'STATUS' && this.dictList.statusList.length > 0){
          this.dictList.statusList.forEach(attr => {
            if (val == attr.dictValue) {
              attrName = attr.dictName
            }
          })
        }

        if(name == 'POSITION' && this.dictList.positionList.length > 0){
          this.dictList.positionList.forEach(attr => {
            if (val == attr.dictValue) {
              attrName = attr.dictName
            }
          })
        }

        if(name == 'DEPT' && this.dictList.deptList.length > 0){
          this.dictList.deptList.forEach(attr => {
            if (val == attr.dictValue) {
              attrName = attr.dictName
            }
          })
        }
        return attrName
      },

      getPersonList(){
        this.api.getQueryPerson({
          'username': null
        }).then(res => {
          if(res.data){
            this.dictList.employeeList = res.data
          }
        }).catch(r => {
          console.log("调用接口失败")
        })
      },

      onSizeChange(val){
        this.agentPage.pageSize = val
        this.agentPage.pageNow = 1
      },

      onCurrentChange (val) {
        this.agentPage.pageNow = val
        this.getCompanyList()
      },

      onSizeChangePerson(val){
        this.personPage.pageSize = val
        this.personPage.pageNow = 1
      },

      onCurrentChangePerson (val) {
        this.personPage.pageNow = val
        this.getCompanyEmployeeList()
      },

      setQueryForm(){
        this.queryForm.companyName = null
        this.queryForm.companyCode = null
        this.getCompanyList()
      },
      getCompanyList(val){
        if(val){
          this.agentPage.pageNow = 1
        }
        this.queryCompanyList()
      },
      /**
       * 获取单个人的角色信息
       */
      queryCompanyList() {
        this.tableData = []
        this.tableLoading1 = true

        let companyName = this.formatStatus(this.queryForm.companyName)
        let companyCode = this.formatStatus(this.queryForm.companyCode)
        this.api.queryCompanyList({
          'companyName':companyName,
          'companyCode':companyCode
        }).then(res => {
          this.tableLoading1 = false

          if(res.data){
            this.agentPage.pageTotal = res.data.length
            this.tableData = res.data
          }
        }).catch(r => {
          console.log("调用接口失败")
        })
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
          if(res.companyId == val.companyId){
            this.detailData = res
          }
        })
      },

      addCompany(){
        this.addCompanyVisible = true
      },

      addCompanyIn(){
        let companyName = this.companyForm.companyName
        let companyCode = this.companyForm.companyCode
        let companyDesc = this.companyForm.companyDesc
        this.api.addCompanyIn({
          'companyName':companyName,
          'companyCode':companyCode,
          'companyDesc': companyDesc
        }).then(res => {
          this.addCompanyVisible = false
          this.getCompanyList()
        })
      },

      getCompanyEmployee(scope){
        this.employeeVisible = true
        this.companyCode = scope.companyCode
        this.getCompanyEmployeeList()

      },

      getCompanyEmployeeList(val){
        if(val){
          this.personPage.pageNow = 1
        }
        this.api.queryEmployeeList({
          'userName': this.personForm.personName,
          'positionId': this.personForm.personPosition,
          'companyCode': this.companyCode
        }).then( res=>{
          if(res){
            this.tableDataPerson = res.data
            this.personPage.pageTotal = res.data.length
          }
        })
      },

      // 编辑
      editPersonDetail(row) {

      },

      addEmployee(){
        this.addEmployeeVisible = true
        this.employeeForm.companyCode = null
      },

      addEmployeeIn(){
        this.api.addEmployeeIn({
          'personId': this.employeeForm.personId,
          'companyCode': this.companyCode,
          'dept': this.employeeForm.personDept,
          'positionId': this.employeeForm.personPositon,
          'employeeCode': this.employeeForm.employeeCode,
        }).then(res => {
          this.addEmployeeVisible = false
          this.getCompanyEmployeeList()
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

  .wlw-form-inline2 {
    .el-form-item {
      width: 30%;
      margin-right: 0;
    }
  }

</style>
