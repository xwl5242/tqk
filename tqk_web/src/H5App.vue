<template>
  <div>
    <h5BotTab/>
    <router-view/>
  </div>
</template>
<script>
  import * as util from './api/util'
  import h5BotTab from './components/m/h5_botTab'
  export default {
    name: "H5App",
    created() {
      util.modeRem(false)
    },
    components: {
      h5BotTab: h5BotTab
    }
  }
</script>
<style>
  @import "../static/css/h5_common.css";
  @import "../static/css/h5_main.css";
</style>
