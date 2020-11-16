<!-- 
        tableList组件使用说明
        主要将后台数据记录，分页参数，关于分页回调事件，传入组件中;
        支持分页和多选;

        组件导入:
         import TableList from './../common/TableList' //组件相对路径自己调整
         components:{TableList},  //注册
         

        在页面中使用: 
        isOperate:是否用操作
        tableData: 表格数据
        columns: 显示列配置
        pagination： 分页配置
        options： 其他参数配置
        currentChange： 当前页改变触发事件
        pageSizeChange：页大小改变触发事件
        selectionChange： 多选改变触发事件

        <table-list 
        :isOperate="true"
        :tableData="tableData" 
        :columns="columns" 
        :pagination="pagination" 
        :options="options"
        @currentChange="handleCurrentChange"
        @pageSizeChange="handleSizeChange"
        @selectionChange="handleSelectionChange"
        >
            <template slot-scope="data">
                <el-button  size="mini" type="text" @click="handleEdit(data.index, data.row)">编辑</el-button>
                <el-button  size="mini" type="text" @click="handleRset(data.index, data.row)">密码重置</el-button>
                <el-button size="mini" type="text" @click="handleDelete(data.index, data.row)">删除</el-button>
            </template>
        </table-list>
      
        配置:
        //表格数据
        tableData: [],

        //是否有操作(eg: update,delete...)
        isOperate:true,

        //列显示 (prop:对象属性   label:列名   width:列宽   formatter:数据格式化函数(row,col,cellValue))
        columns:[ 
          {prop:"userId",label:"用户",width:80,formatter:null},
          {prop:"moduleType",label:"所属模块",width:80,formatter:this.formatModuleType},
          {prop:"deptName",label:"机构",width:90,formatter:null},
          {prop:"operName",label:"姓名",width:80,formatter:null},
          {prop:"role",label:"用户角色",width:90,formatter:null},
          {prop:"createDate",label:"创建时间",width:100,formatter:this.formatDate},
        ],

        //分页参数 前提保证options.pagination=true
        pagination:{
          currentPage: 1,
          pageSize: 10,
          layout: "total, sizes, prev, pager, next, jumper",
          total: 0
        },

        //其他配置
        options:{
          mutiSelect: false, //boolean 表格是否多选
          isindex: true, //boolean 表格是否展示序列号
          pagination: true, //boolean 是否显示分页导航栏
        }
        //分页查询函数
        getList(){
        this.api.getOperList(this.query).then((res) =>{
          //后台返回Page<Type>
          this.tableData = res.data.records //数据记录
          this.pagination.total = res.data.total //总条数
          this.pagination.currentPage = res.data.current //当前页
          this.pagination.pageSize = res.data.size //页大小
        })
       },
       //数据格式化函数
        formatDate(row,col,cellValue){
         //return ...
        },
        formatModuleType(row,col,cellValue){
         //return ....
      },
      //页大小改变的回调函数
      handleSizeChange(val) {
        this.query.pageSize = val
        this.query.current = 1
        this.getList()
      },
      //当前页改变的回调函数
      handleCurrentChange(val) {
        this.query.current = val
        this.getList()
      },
      //多选改变回调函数
      handleSelectionChange(selections){
         //...
      },

      具体参考: user.vue
      更多参数配置见下面属性参数
-->

<template>
    <div id="tableList">
        <el-table :data="tableData"
                  @selection-change="handleSelectionChange"
                  v-loading="options.loading"
                  style="width:100%;"
                  stripe>
            <!-- 是否多选 -->
            <el-table-column type="selection"
                             v-if="options.mutiSelect"></el-table-column>
            <!-- 是否展示序列号 -->
            <el-table-column type="index"
                             v-if="options.isindex"
                             :index="indexMethod"></el-table-column>
            <template v-for="(item,index) in columns">
                <!-- 常规列数据 -->
                <el-table-column :label="item.label"
                                 :width="item.width"
                                 :key="index"
                                 :formatter="item.formatter"
                                 :prop="item.prop"
                                 show-overflow-tooltip>
                </el-table-column>
            </template>
            <el-table-column
                label="操作"
                align="center"
                v-if="isOperate">
                <template slot-scope="scope">
                    <slot :row='scope.row' :index='scope.$index'></slot>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination-block"
             v-if="options.pagination">
            <el-pagination @size-change="handleSizeChange"
                           @current-change="handleCurrentChange"
                           :current-page="pagination.currentPage"
                           :page-sizes="pagination.pageSizes"
                           :page-size="pagination.pageSize"
                           :layout="pagination.layout"
                           :total="pagination.total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
export default {
    name: "tableList",
    props: {
        tableData: {
            type: Array,
            default: () => []
        },
        options: {
            type: Object,
            default: data => {
                return (data = {
                    mutiSelect: false, //boolean 是否多选
                    isindex: false, //boolean 是否展示序列号
                    stripe: true, //boolean 斑马纹
                    border: true, //boolean 纵向边框
                    size: "medium", //String  medium / small / mini  table尺寸
                    fit: true, //自动撑开
                    loading: false, // 加载动画
                    pagination: true //是否有分页
                });
            }
        },
        pagination: {
            type: Object,
            default: data => {
                return (data = {
                    currentPage: 1,
                    pageSizes: [10,20,30],
                    pageSize: 10,
                    layout: "total, prev, pager, next, jumper",
                    total: 0
                });
            }
        },
        columns: {
            type: Array,
            default: () => []
        },
        isOperate:{
            type: Boolean,
            default: false
        }
    },
    data() {
        return {};
    },
    methods: {
        // 序列号相关
        indexMethod(index) {
            return index + 1;
        },
        // 页大小回调函数
        handleSizeChange(val) {
            this.$emit("pageSizeChange", val);
        },
        // 当前页改变回调函数
        handleCurrentChange(val) {
            this.$emit("currentChange", val);
        },
        //多选相关
        handleSelectionChange(selections){
            this.$emit('selectionChange',selections)
        },
    }
};
</script>
<style lang="scss" scoped>
.pagination-block{
    float: right;
}
</style>