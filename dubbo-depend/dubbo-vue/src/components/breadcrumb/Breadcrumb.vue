<template>
  <div class='wlw-breadcrumb'>
    <div class="wlw-breadcrumb-left no-redirect">
      <!--<i :class='lastLevel? "icon-fanhui-" : "icon-weizhi-"' @click='back()'></i>-->
      {{ currentName }}
    </div>
    <div class="wlw-breadcrumb-right">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item v-for="(item, index) in levelList" :key="index.path">
          <span v-if="item.redirect==='noRedirect'||index==levelList.length-1||item.meta.hasPageChild" class="no-redirect">
            {{ item.meta.title }}</span>
          <a v-else @click.prevent="handleLink(item)">{{ item.meta.title }}</a>
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
  </div>
</template>

<script>
import pathToRegexp from 'path-to-regexp'
export default {
  name: 'WlwBreadcrumb',
  data () {
    return {
      levelList: null,
      currentName: ''
    }
  },
  props: {
    lastLevel: String
  },
  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },
  created() {
    this.getBreadcrumb()
  },
  methods: {
    getBreadcrumb() {
      this.currentName = this.$route.meta.title
      let matched = this.$route.matched.filter(item => item.meta && item.meta.title)
      const first = matched[0]
      if (first.meta && !first.meta.disabledDashboard) {
        if (!this.isDashboard(first)) {
          matched = [{ path: '/dashboard', meta: { title: 'dashboard' } }].concat(matched)
        }
      }
      this.levelList = matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
    },
    isDashboard(route) {
      const name = route && route.name
      if (!name) {
        return false
      }
      return name.trim().toLocaleLowerCase() === 'Dashboard'.toLocaleLowerCase()
    },
    pathCompile(path) {
      const { params } = this.$route
      var toPath = pathToRegexp.compile(path)
      return toPath(params)
    },
    handleLink(item) {
      const { redirect, path } = item
      if (redirect) {
        this.$router.push(redirect)
        return
      }
      this.$router.push(this.pathCompile(path))
    },
    back() {
      console.log(this.lastLevel)
      if(this.lastLevel != undefined) {
        this.$router.push(this.lastLevel)
      }
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
