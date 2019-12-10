<template>
  <div>
    <router-view/>
  </div>
</template>
<script>
  import * as util from './api/util'

  export default {
    name: "H5App",
    created() {
      util.modeRem(false)
    }
  }
</script>
<style>
  @import "../static/css/h5_common.css";
  @import "../static/css/h5_main.css";
</style>
