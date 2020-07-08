<template>
  <div style="height:100%;">
    <div>
      <!--<img src="@/assets/logo.png">-->
      <h1>{{ msgInfo }}</h1>
      <!--<el-button type="text" @click="dialogVisible = true">新增</el-button>-->
      <el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>
      <el-button type="text" @click="open">点击打开 Message Box</el-button>
    </div>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <span>这是一段信息</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
    <!--新增-->
    <!--<el-dialog title="这是标题" width="950px"
               :visible.sync="dialogVisibleNew"
               :close-on-click-modal="false">
      <div>
        <el-form ref="formline" v-model="formline" :rules="formlineRules">
          <el-table :data="dataList"
                    :height="250"
                    :header-cell-style="{ 'text-align': 'center' }"
                    :cell-style="{ 'text-align': 'center' }">
            <el-table-column type="index" label="序号"></el-table-column>
            <el-table-column prop="weekDays" label="星期">
              <template slot-scope="scope">
                <el-form-item :prop="'dataList.' + scope.$index + '.weekDaysTmp'" :rules="formlineRules.weekDaysTmp">
                  <el-select v-model="scope.row.weekDaysTmp" placeholder="请选择">
                    <el-option v-for="(item, index) in scope.row.weekDaysList"
                               :key="index"
                               :value="item.value"
                               :label="item.label">
                    </el-option>
                  </el-select>
                </el-form-item>
                <span> {{scope.row.weekDays}}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleNew = false" type="primary">确定</el-button>
      </span>
    </el-dialog>-->
  </div>
</template>

<script>
  import Vue from 'vue'

  export default {
    data () {
      return {
        msgInfo: '',
        dialogVisibleNew: false,
        dialogVisible: false,
        dataList: [],
        formline: {},
        formlineRules: {
          weekDaysTmp: {required: true, message: "请选择星期"}
        },

      }
    },
    mounted(){
      this.saySome();
    },
    methods: {
      saySome(){
        this.api.sayFirst().then( res => {
          this.msgInfo = res.data.data
          console.log("接口调用成功")
        })
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
      open() {
        let it = this
        /*this.$alert('这是一段内容', '标题名称', {
          confirmButtonText: '确定',
          callback: action => {
            this.$message({
              type: 'info',
              message: `action: ${action}`
            });
          }
        });*/
        it.$message('这是一条消息提示');

      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
