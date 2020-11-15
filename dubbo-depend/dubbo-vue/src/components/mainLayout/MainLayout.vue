<template>
  <div class="wlw-container">
    <wlw-header></wlw-header>
    <div class="wlw-main">
      <router-view />
    </div>
  </div>
</template>

<script>
import WlwHeader from '../header/Header'
export default {
  name: 'MainLayout',
  components: {
    WlwHeader
  }
}
</script>
