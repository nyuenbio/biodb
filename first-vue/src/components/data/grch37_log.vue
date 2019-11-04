<template>
    <el-dialog :visible.sync="visible" :show-close="false" width="80%" :modal="true" :close-on-click-modal="false"
               :close-on-press-escape="false">
        <h2 slot="title">操作详情</h2>
        <button type="button" aria-label="Close"  @click.stop="cancelModal">
          关闭窗口<i class="el-dialog__close el-icon el-icon-close"></i>
        </button>
        <el-table
          :data="logData"
          style="width: 100%"
          stripe
          border>

          <el-table-column prop="chr" label="chr 染色体" width="180"></el-table-column>
          <el-table-column prop="pos" label="pos 位置" width="180"></el-table-column>
          <el-table-column prop="ref" label="ref 原始基因" width="180"></el-table-column>
          <el-table-column prop="alt" label="alt 突变基因" width="180"></el-table-column>
          <!-- <el-table-column prop="operateTableName" label="操作表名" width="180"></el-table-column> -->
          <el-table-column prop="operateColumnName" label="操作字段名" width="180"></el-table-column>
          <el-table-column prop="oldData" label="操作前旧值" width="180"></el-table-column>
          <el-table-column prop="newData" label="操作后新值" width="180"></el-table-column>
          <el-table-column prop="operateUser" label="操作人" width="180"></el-table-column>
          <el-table-column prop="operateTime" label="操作时间" width="180"></el-table-column>
        </el-table>
        <div style="height: 20px;"></div>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="clickData.currentPage"
          :page-sizes="[10, 20]"
          :page-size="clickData.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalCount">
        </el-pagination>
    </el-dialog>
</template>
<script>
    // import __ from '__' // __是需要手动引入的文件
    export default {
        name: 'grch37_log',
        components: {},
        props: {
            visible: {
                type: Boolean,
                default: false
            },
            clickData:{

            }
        },
        data() {
            return {
                logData:[],
                totalCount:1
            }
        },
        methods: {
          cancelModal(){
            // 关闭弹窗，触发父组件修改visible值
            this.$emit('update:visible', false);
          },
          handleSizeChange(val) {
            //console.log(`每页 ${val} 条`);
            this.clickData.pageSize = val;
            this.searchData();
          },
          handleCurrentChange(val) {
            //console.log(`当前页: ${val}`);
            this.clickData.currentPage = val;
            this.searchData();
          },
          searchData(){
            this.$axios({
              url:'/grch37/getLog',
              method:'post',
              data:this.clickData
            }).then(res => {
              //console.log('res data => ')
              //console.log(res.data.data)
              this.logData = res.data.data.rows;
              this.totalCount=res.data.data.total;

            })
          }
        },
        created(){
            //console.log(" clickData   =>")
            //console.log(this.clickData)
            this.searchData();
        }
    }
</script>
<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
