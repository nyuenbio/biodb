<template>
    <div>
        {{msg}}
    </div>
</template>
<script>
    // import __ from '__' // __是需要手动引入的文件
    export default {
        name: 'my_footer',
        components: {},
        data() {
            return {
              msg: '©2019 上海恩元生物科技'
            }
        },
        methods: {}
    }
</script>
<style lang="postcss" scoped>
    body {
        background-color: #ff0000;
    }
</style>
