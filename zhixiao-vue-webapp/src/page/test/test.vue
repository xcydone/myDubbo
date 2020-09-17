<template>
  <div class="page-search">
    <div class="search-body">
      <div class="search-list" id="scrollViewList">
        <div class="show-more">{{ moreText }}</div>
      </div>
    </div>
  </div>
</template>
<script>
import API from '../../api/api.js';
import store from 'storejs';
export default {
  data() {
    return {
      moreText: ''
    }
  },
  mounted() {
    this.$store.state.isTabber = false;
    this.getTestMsg();
  },
  methods: {
    getTestMsg() {
      this.axios.get(API.test.getTestMsg())
        .then((res) => {
          this.moreText = res.data;
        })
        .catch((res) => {
          console.log(res);
        });
    }
  }
};
</script>
<style lang='scss' scoped>
.page-search{
  height: 100%;
  overflow: hidden;
  .search-body{
    height: 100%;
    padding-bottom: 2.347rem;
    .search-list{
      height: 100%;
      overflow-y: auto;
      background-color: #f5f2ed;
    }
  }
}
</style>