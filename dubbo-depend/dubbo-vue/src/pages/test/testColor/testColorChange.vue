<template>
  <div style="height:100%;">
    <el-container class="pom-wrap">
      <el-main>
        <el-row :gutter="25">
          <el-col :span="20">
            <div>
              <we-div :color="color"></we-div>
            </div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  import weDiv from "@/pages/test/testColor/colorWE";

  export default {
    components: {weDiv},
    data() {
      return {
        color: 'red',
        flag: false,
        colorList: ['red','black','blue']
      };
    },
    created() {
      this.timer()
    },
    methods: {
      timer() {
        let i = 0
        setInterval(()=>{
          if(i == 2){
            i = 0
          }else{
            i++;
          }
          console.log(i)
          this.color = this.colorList[i];
        },1000);
      }
    },
    destroyed() {
      clearInterval(this.timer)
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss"></style>
