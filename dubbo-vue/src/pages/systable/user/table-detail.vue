<template>
  <div>
    <el-container class="is-vertical">
      <el-header class="base-cont-header" style="height: 20px; min-width:1060px;">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ name: 'table' }">用户列表</el-breadcrumb-item>
          <el-breadcrumb-item>用户详情</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>
      <el-main>
        <el-card>
          <p>用户详情</p>
          <hr>
          <el-form :model="personDetail" ref="data" label-position="left">
            <el-row>
              <el-col :span="6">
                <el-form-item>
                  <span slot="label" class="font-style">用户名称</span>
                  <span class="font-value">{{personDetail.userName}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item>
                  <span slot="label" class="font-style">手机号码</span>
                  <span class="font-value">{{personDetail.phone}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item>
                  <span slot="label" class="font-style">性别</span>
                  <span class="font-value">{{personDetail.sex}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item>
                  <span slot="label" class="font-style">班级</span>
                  <span class="font-value">{{personDetail.classes}}</span>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="6">
                <el-form-item>
                  <span slot="label" class="font-style">电子邮箱</span>
                  <span class="font-value">{{personDetail.email}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item>
                  <span slot="label" class="font-style">创建时间</span>
                  <span class="font-value">{{personDetail.createTime | formatDate}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item >
                  <span slot="label" class="font-style">地区</span>
                  <span class="font-value">{{personDetail.zone}}</span>
                </el-form-item>
              </el-col>
              <el-col :span="6">
                <el-form-item >
                  <span slot="label" class="font-style">段位</span>
                  <span class="font-value">{{personDetail.level}}</span>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
        <el-card>
          <p>课程详情</p>
          <hr>
          <el-tabs v-model="activeName" type="border-card"  style="margin: 0px;" @tab-click="handleClick">
            <el-tab-pane label="课程列表" name="first">
              <el-table :data = courseList size = "medium" tooltip-effect = "dark">
                <el-table-column label="课程ID" prop="courseId" min-width=100px></el-table-column>
                <el-table-column label="课程名称" prop="courseName" min-width=100px></el-table-column>
                <el-table-column label="课程老师" prop="courseTeacher" min-width=100px></el-table-column>
                <el-table-column label="课程时间" prop="courseDate"></el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="教师列表" name="second">
              <el-table :data = teacherList size = "medium" tooltip-effect = "dark">
                <el-table-column label="教师ID" prop="teacherId" min-width=100px></el-table-column>
                <el-table-column label="教师名称" prop="teacherName" min-width=100px></el-table-column>
                <el-table-column label="教师职称" prop="level"></el-table-column>
                <el-table-column label="备注" prop="remark"></el-table-column>
              </el-table>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import * as date from '@/helper/date-format'

export default {
  name: 'persondetailview',
  data() {
    return {
      person_id:'',
      activeName: 'first',
      personDetail: {},
      courseList: [],
      teacherList: []
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    }
  },
  mounted: function() {
    console.log(this.$store.state.translate)

    this.getPersonDetailById()
    this.getPersonCourseById()
    this.getAllTecaher()
  },
  filters: {
    formatDate: function(val) {
      return date.format(val, 'YYYY-MM-DD')
    }
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    /**
       * 获取用户详情
       */
    getPersonDetailById() {
      this.person_id = this.$route.params.id
      this.api.getOnePersonById(this.person_id).then(r => {
        if (r.data) {
          this.personDetail = r.data
          }
        }).catch(r => {

      })
    },
    /**
     * 获取用户课程详情
     */
    getPersonCourseById() {
      this.api.getPersonCourseById(this.person_id).then(r => {
        let courseCode = []
        let courseLists = r.data
        if(courseLists != null && courseLists.length){
          courseLists.forEach(course => {
            let courseInfo = {
              'courseId': course.course_id,
              'courseName': course.course_name,
              'courseTeacher': course.course_teacher,
              'courseDate': course.course_date
            }
            courseCode.push(courseInfo)
          })
        }
        this.courseList = courseCode
      }).catch(r => {
        console.log("调用接口出错")
      })
    },
    /**
     * 获取用户课程详情
     */
    getAllTecaher() {
      this.api.getAllTecaher().then(r => {
        let teacherCode = []
        let teacherLists = r.data
        if(teacherLists != null && teacherLists.length){
          teacherLists.forEach(teacher => {
            let teacherInfo = {
              'teacherId': teacher.id,
              'teacherName': teacher.teacherName,
              'remark': teacher.remark,
              'level': teacher.level
            }
            teacherCode.push(teacherInfo)
          })
        }
        this.teacherList = teacherCode
      }).catch(r => {
        console.log("调用接口出错")
      })
    }
  }
}
</script>
<style lang="scss" scoped>
.el-header {
  padding: 0px 30px;
      // height: 20px;
  .el-breadcrumb {
    padding: 0px 0px 0px 24px;
    height: 20px;
  }
}
.el-main {
  padding: 20px 30px;
  min-width:1060px;
}
.el-pagination{
  margin-top: 40px;
  // vertical-align: bottom;
}
.el-card{
  padding: 0px 20px;
}
p{
  font-family: PingFangSC-Medium;
  font-size: 16px;
  color: #3A3A3A;
  margin-left: 0px;
}
hr{
  border-top-width: 0px;
  background: #E6E6E6;
  margin-bottom: 20px;
  margin-top: 10px;
}
.font-style{
  font-family: PingFangSC-Regular;
  font-size: 14px;
  color: #999999;
  line-height: 18px;
}
.font-value{
  font-family: PingFangSC-Regular;
  font-size: 14px;
  color: #3A3A3A;
  line-height: 18px;
  margin-left: 1px;
}
.detail-footer{
  margin-top: 20px;
  text-align: right;
  .font-sty1{
    font-family: PingFangSC-Regular;
    font-size: 14px;
    color: #3A3A3A;
  }
  .font-sty2{
    font-size: 14px;
    color: #2DC6F6;
  }
  .font-sty3{
    font-family: PingFangSC-Regular;
    font-size: 26px;
    color: #2DC6F6;
  }
}

</style>
