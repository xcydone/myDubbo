<template>
  <div style="height:100%;">
    <div>
      <!--<img src="@/assets/logo.png">-->
      <h1>{{ msgInfo }}</h1>
      <!--<el-button type="text" @click="dialogVisible = true">新增</el-button>-->
      <!--<el-button type="text" @click="dialogVisible = true">点击打开 Dialog</el-button>-->
      <el-upload
        ref="upload"
        :action="actionUrl"
        :data="uploadData"
        :file-list="fileList"
        :before-upload="beforeAvatarUpload"
        :on-success="handleSuccess"
        :on-error="handleErrorFile"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        :limit="3"
        :auto-upload="false">
      </el-upload>
      <el-button @click="submitData">提交</el-button>
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

        // 上传文件相关
        actionUrl: '',
        uploadData: {operId: 'caifang', operDate: 20200708},
        fileList: [],


      }
    },
    mounted(){
      this.saySome();
      this.actionUrl = "http://localhost:8092/order/uploadFile/";
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

      // 检查上传的单个文件的大小
      beforeAvatarUpload(file){
        let isLt2M = file.size / 1024 / 1024 <= 30
        if (!isLt2M) {
          this.$message.error("上传的文件大小不能超过 30MB!");
          /*this.loading = false;*/
        }
        return isLt2M;
      },

      // 上传成功
      handleSuccess(response, file, fileList) {
        console.log("response: ",response)
        if (response.msg == "SUCCESS") {
          /*this.loading = false;*/
          this.$message({type: "success", message: "上传成功！"});
        }
      },

      // 上传失败
      handleErrorFile(err, file, fileList){
        console.log(err)
        this.$message({type: "error", message: "上传失败！"});
      },

      // 移除文件
      handleRemove(file, fileList){
        this.$message({type: "error", message: `${file.name}被移除`});
      },

      // 移除文件之前询问
      beforeRemove(file, fileList){
        return this.$confirm(`确定移除 ${ file.name }？`);
      },

      submitData(){
        this.$refs.upload.submit();
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
