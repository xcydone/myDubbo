<template>
    <div style="width: 1000px; height: 1000px">
      <p align="center">{{msg}}</p>
      <cmp :html="html"></cmp>
    </div>
</template>

<script>
  import Vue from "vue";

  export default {
    components:{
      cmp:{
        props:{html: String},
        render(h) {
          const com = Vue.extend({
            template: this.html,
            props: {}
          })
          return h(com, {
            props: {}
          })
        }
      }
    },

    data() {
      return {
        msg: '初始代模板',
        html: null,
      }
    },
    created() {
      this.html = '<p> 我是cmp </p>'
    },
    methods:{

    },
  }
</script>

<style>
</style>
