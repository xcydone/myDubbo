<template>
  <div>
    <wlw-breadcrumb></wlw-breadcrumb>
    <el-card class="wlw-card" shadow="never">
      <el-form :inline="true" :model="queryForm" class="wlw-form-inline">
        <el-form-item label="用户电话">
          <el-input placeholder="请输入用户电话" v-model="queryForm.phone" style='width: 186px;'></el-input>
        </el-form-item>
        <el-form-item label="用户名称">
          <el-input placeholder="请输入用户名称" v-model="queryForm.username" style='width: 186px;'></el-input>
        </el-form-item>
        <el-form-item label="妈妈名称">
          <el-input placeholder="请选择用户" v-model="queryForm.motherName" style='width: 186px;' readonly>
            <el-button slot="append" icon="el-icon-attract" @click='getParentList'></el-button>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getPerson('btn')">查询</el-button>
          <el-button type="primary" @click="setQueryForm()" class='wlw--btn-gray'>重置</el-button>
        </el-form-item>
      </el-form>
      <el-row>
        <el-button :loading="exporting" type="primary" @click="exportOrder()" style="margin-left: 15px">导出</el-button>
      </el-row>
      <el-table border class="wlw-table" ref="table"
                :data="tableData.slice((agentPage.pageNow-1)*agentPage.pageSize,agentPage.pageNow*agentPage.pageSize)"
                tooltip-effect="dark" style="width: 100%"
                :v-loading="tableLoading1">
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="userName" label="姓名" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-link type="primary" @click="getDetail(scope.row)">{{scope.row.userName}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号码" show-overflow-tooltip></el-table-column>
        <el-table-column prop="sex" label="性别" show-overflow-tooltip>
          <template slot-scope="scope">
            <span>{{translate(scope.row.sex,'SEX')}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="companydto.companyName" label="企业" show-overflow-tooltip>
        </el-table-column>
        <el-table-column prop="motherName" label="母亲" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-link type="primary" @click="getMotherDetail(scope.row)">{{scope.row.motherName}}</el-link>
          </template>
        </el-table-column>
        <el-table-column prop="email" label="电子邮箱" show-overflow-tooltip></el-table-column>
        <el-table-column prop="createTime" label="创建时间" show-overflow-tooltip></el-table-column>
        <el-table-column prop="zone" label="地区" show-overflow-tooltip></el-table-column>
        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button @click="editPersonDetail(scope.row)" type="text" size="small">编辑</el-button>
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
          <span class="name">姓名: </span>
          <span class="value">{{ detailData.userName }}</span>
        </li>
        <li style="padding-top: 20px; margin-left: 20px">
          <span class="name">手机号码: </span>
          <span class="value">{{ detailData.phone }}</span>
        </li>
        <li style="padding-top: 20px; margin-left: 20px">
          <span class="name">性别 : </span>
          <span class="value">{{ detailData.sex}}</span>
        </li>
      </ul>
    </el-drawer>

    <mother-dialog v-if="parentListVisible" :show="parentListVisible" :userId="userId" @on-select="selectParentList"></mother-dialog>

    <el-dialog title="母亲资料" :visible.sync="motherDialogShow" v-if="motherDialogShow">
      <el-form :model="motherForm" label-width="102px" style='width: 518px;'>
        <el-form-item label="母亲name" show-overflow-tooltip>
          <el-select v-model="motherForm.name"  placeholder="请选择" style="width: 286px;">
            <el-option v-for="item in dictList.motherList" :key="item.id" :label="item.userName" :value="item.id">
            </el-option>
          </el-select>
          <!--<el-input v-model="motherForm.name"></el-input>-->
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="editParent()" >修改</el-button>
          <el-button type="primary" @click="motherDialogShow = false" class='wlw--btn-gray'>取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import WlwBreadcrumb from '@/components/breadcrumb/Breadcrumb'
  import motherDialog from '@/components/dialog/mother'

  export default {
    name: "table-main2",
    components: {
      WlwBreadcrumb,motherDialog
    },
    data() {
      return {
        userId: '12',
        tableData: [],
        detailData: {},
        exporting: false,
        queryForm:{
          userId: null,
          username: null,
          motherName: null,
          motherId: null,
          phone: null
        },
        motherForm:{
          id: '',  // 记录子代的id
          name: '' // 记录父代的id
        },
        tableLoading1: true,
        agentPage:{
          pageNow: 1,
          pageSize: 10,
          pageTotal: 0
        },
        drawerVisible: false,
        parentListVisible: false,
        dictList: {
          sexList: [],
          motherList: []
        },
        motherDialogShow: false,
      }
    },

    mounted: function() {
      // 获取主数据
      this.findDictList('SEX')

      this.findMotherList();

      // 获取信息
      this.getPerson()
    },

    methods: {
      findDictList(val){
        this.api.findDictListByTypeValue(val).then( res => {
          if(res){
            this.dictList.sexList = res.data
          }
        })
      },

      findMotherList(){
        this.apithis.api.getAllParent({
          'id': null
        }).then( res => {
          if(res){
            this.dictList.motherList = res.data
          }
        })


      },

      translate(val, name){
        let attrName = ''
        if(name == 'SEX' && this.dictList.sexList.length > 0){
          this.dictList.sexList.forEach(attr => {
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
        this.getPerson()
      },

      setQueryForm(){
        this.queryForm.id = null
        this.queryForm.phone = null
        this.queryForm.username = null
        this.queryForm.motherId = null
        this.queryForm.motherName = null
        this.getPerson()
      },
      getPerson(val){
        if(val){
          this.agentPage.pageNow = 1
        }
        this.getPersonList()
      },
      /**
       * 获取单个人的角色信息
       */
      getPersonList() {
        this.tableData = []
        this.tableLoading1 = true

        let id = this.formatStatus(this.queryForm.userId)
        let phone = this.formatStatus(this.queryForm.phone)
        let username = this.formatStatus(this.queryForm.username)
        let motherId = this.formatStatus(this.queryForm.motherId)
        this.api.getQueryPerson({
          'id': id,
          'username': username,
          'phone': phone,
          'mother': motherId
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
      exportOrder(){
        this.exporting = true
      },

      getDetail(val){
        this.detailData = null
        this.drawerVisible = true
        this.tableData.forEach( res=>{
          if(res.id == val.id){
            this.detailData = res
          }
        })
      },

      // 编辑
      editPersonDetail(row) {
        this.$store.state.translate = "123"
        this.$router.push({ name: 'detail-edit', params: {id: row.id} })
      },

      exportOrder(){
        this.queryForm.id = this.formatStatus(this.queryForm.id)
        this.queryForm.username = this.formatStatus(this.queryForm.username)
        this.exporting = true
        let url = httpPerson.exportPerson(this.queryForm)
        window.location.href = url
        this.exporting = false
      },

      getParentList(){
        this.parentListVisible = true
      },

      selectParentList(user){
        this.parentListVisible = false
        if (user) {
          this.queryForm.motherName = user.userName
          this.queryForm.motherId = user.id
        }
      },

      getMotherDetail(scope){
        this.motherForm.name = scope.mother + ''
        this.motherForm.id = scope.id
        this.motherDialogShow = true
      },
      editParent(){
        console.log(this.motherForm.name)
        console.log(this.motherForm.id)
        this.api.editParent(this.motherForm.name,this.motherForm.id).then( res => {

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
