<template>
  <div id="app">
    <img src="./assets/index_logo1.png">
    <router-view/>
  </div>
</template>

<script>

  import { mapState } from 'vuex'
  import * as types from './utils/types'

export default {
  name: 'App',
  computed: mapState({
    title: state => state.title,
    token: state => state.token,
  })
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
