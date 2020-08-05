<template>
  <div>
    <div style="height: 60px; width: 100px">
      <span style="font-size:30px">{{time}}</span>
    </div>

    <div>
      <clock :time="time"></clock>
    </div>

    <div>
      <pomodoro
        :total-pomodoro = "totalPomodoro"
        :work-duration = "1"
        :rest-duration="0"
        :diameter = "diameter"
        :stroke-width="6"
        inner-text-color="#000000"
      >
      </pomodoro>
    </div>

    <div style="height: 200px; width: 600px">
      <el-calendar v-model="value"></el-calendar>
    </div>
  </div>
</template>

<script>
  import Clock from 'vue-clock2';
  import Pomodoro from 'vue-pomodoro';
  export default {
    components:{Clock, Pomodoro},
    data() {
        return {
          time: '',
          diameter: 180,
          totalPomodoro: 1,
          value: new Date(),
        }
    },
    created() {
      setInterval(()=>{
        this.time = this.getCurrentTime()
      })
    },
    methods:{
      getCurrentTime(){
        let date = new Date();
        let hour = this.formateTime(date.getHours())
        let minut = this.formateTime(date.getMinutes())
        let seconds = this.formateTime(date.getSeconds())
        return  hour + ":" + minut + ":" + seconds;
      },

      formateTime(value){
        return value < 10 ? '0' + value : value
      },
    },
  }
</script>
