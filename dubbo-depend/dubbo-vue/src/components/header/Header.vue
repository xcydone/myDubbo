<template>
  <div class='wlw-header'>
    <div class="wlw-icon"></div>
    <el-menu :default-active="activeIndex" router class="wlw-el-menu" mode="horizontal" @select="handleSelect" background-color="#0e1424" text-color="#bbc0d1" active-text-color="#fff" >
      <el-submenu index="/product">
        <template slot="title">产品管理</template>
        <el-menu-item index="/table/list">统一产品</el-menu-item>
      </el-submenu>
      <el-submenu index="/system">
        <template slot="title">系统管理</template>
        <el-menu-item index="/systable/userList">用户管理</el-menu-item>
        <el-menu-item index="/systable/entityList">企业管理</el-menu-item>
        <el-menu-item index="/systable/dictList">字典管理</el-menu-item>
        <el-menu-item index="/systable/todolist">动态页面</el-menu-item>
        <el-menu-item index="/systable/test">动态页面2</el-menu-item>
      </el-submenu>
    </el-menu>
    <div class="wlw-header-rightmenu">
      <el-button type="text" icon="el-icon-bell"></el-button>
      <el-dropdown class="avatar-container hover-effect" trigger="hover">
        <div class="avatar-wrapper">
          <div class="user-avatar"></div>
          <span class="user-name">唐宋元</span>
        </div>
        <el-dropdown-menu slot="dropdown">
          <div class="wlw--header-dropdown">
            <div class="user-avatar"></div>
            <p class="user-name">Kaven1388808</p>
            <p class="user-company">上海丰田汽车有限公司</p>
            <div class="user-label">总代</div>
            <div class="logout-btn">退出登录</div>
          </div>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: 'WlwHeader',
  data () {
    return {
      activeIndex: this.$router.path
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    }
  },
  mounted () {
    console.log(this.$router.path)
  }
}
</script>

<style lang="scss" scoped>

</style>
