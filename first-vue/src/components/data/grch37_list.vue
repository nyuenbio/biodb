<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div id="grch37" align="center" class="grch37" >
        <h3 class="title">Grch37 Data ElementUI</h3>

      <template>
        <div align="left">
            <div style="display: inline-block"> 搜索：</div>
            <div class="search-div">
              <label>rsId: </label>
              <el-input v-model="criteria.rsId" class="searchs" placeholder="请输入rsId编号" clearable prefix-icon="el-icon-search">
              </el-input>
            </div>
            <div class="search-div">
              <label>chr: </label>
              <el-input v-model="criteria.chr" class="searchs" placeholder="请输入chr" clearable prefix-icon="el-icon-search">
              </el-input>
            </div>
            <div class="search-div">
              <span style="width: 5px;">&nbsp;</span>
              <label>pos区间: </label>
              <el-input v-model="criteria.posStart" class="searchs" placeholder="请输入pos区间开始值" clearable prefix-icon="el-icon-search">
              </el-input> --
              <el-input v-model="criteria.posEnd" class="searchs" placeholder="请输入pos区间结束值" clearable prefix-icon="el-icon-search">
              </el-input>
              <span style="width: 5px;">&nbsp;</span>
            </div>
            <div class="search-div">
              <label>ref: </label>
              <el-input v-model="criteria.ref" class="searchs" placeholder="请输入ref" clearable prefix-icon="el-icon-search">
              </el-input>
            </div>
            <div class="search-div">
              <label>alt: </label>
              <el-input v-model="criteria.alt" class="searchs" placeholder="请输入alt" clearable prefix-icon="el-icon-search">
              </el-input>
            </div>
            <div class="search-div">
              <label>commonState: </label>
              <el-input v-model="criteria.commonState" class="searchs" placeholder="请输入commonState" clearable prefix-icon="el-icon-search">
              </el-input>
            </div>

        </div>
      </template>
      <div style="height: 10px;"></div>
      <p class="addClass">
        <el-button class="el-icon-plus" @click.prevent="addRow()" disabled>添加</el-button>
        <el-button style="margin-left: 25px;" icon="el-icon-search" type="primary" plain @click="searchDataByCondition()">查询</el-button>
        <el-button style="margin-left: 25px;" icon="el-icon-upload" plain type="primary" @click="" disabled>导入</el-button>
        <el-button style="margin-left: 25px;" icon="el-icon-download" plain type="primary" @click="exportExcel" >导出</el-button>
      </p>
      <div style="height: 20px;"></div>


      <el-table
        id="grch37Table"
        :data="tableData"
        style="width: 100%"
        stripe
        border
        :default-sort = "{prop: 'date', order: 'descending'}"
        @selection-change="handleSelectionChange"
        @row-click="handleClick"
        :row-class-name="tableRowClassName"
        :row-key="getRowKeys"
      >
        <el-table-column
          type="selection"
          :reserve-selection="true"
        >
        </el-table-column>
        <el-table-column
          prop="rsId"
          label="rsId 编号"
          sortable
          width="180">
          <template slot-scope="scope" >
            <el-input prefix-icon="el-icon-edit" v-model="scope.row.rsId" @change="handleEdit(scope.row)" ></el-input>
          </template>
        </el-table-column>
        <el-table-column
          prop="chr"
          label="chr 染色体"
          sortable
          width="180">
        </el-table-column>
        <el-table-column
          prop="pos"
          label="pos 位置"
          width="180">
        </el-table-column>
        <el-table-column
          prop="ref"
          label="ref 原始基因"
          width="180">
        </el-table-column>
        <el-table-column
          prop="alt"
          label="alt 突变基因"
          width="180">
        </el-table-column>
        <el-table-column
          prop="commonState"
          label="commonState 描述"
          width="180">
            <template slot-scope="scope">
                <el-input prefix-icon="el-icon-edit" v-model="scope.row.commonState" @change="handleEdit(scope.row)" ></el-input>
            </template>
        </el-table-column>

        <el-table-column label="操作" width="280">
          <template slot-scope="scope">
            <el-button
              plain
              size="small"
              type="primary"
              v-if="!scope.row.chr"
              @click="addData(scope.row)">确定</el-button>
            <el-button
              icon="el-icon-notebook-2"
              plain
              size="small"
              type="primary"
              v-if="scope.row.chr"
              @click="handleDetailLog(scope.row)">查看详情</el-button>
            <el-button
              plain
              icon="el-icon-delete"
              size="small"
              type="danger"
              v-if="scope.row.isDelete==false"
              @click="handleDelete(scope.$index, scope.row)">删&nbsp;&nbsp;&nbsp;&nbsp;除</el-button>
            <el-button
              plain
              size="small"
              icon="el-icon-delete-solid"
              v-if="scope.row.isDelete==true"
              disabled="disabled">已删除</el-button>
          </template>

        </el-table-column>

      </el-table>
      <div style="height: 20px;"></div>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="criteria.currentPage"
        :page-sizes="[10, 20, 100]"
        :page-size="criteria.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalCount">
      </el-pagination>

      <detailLog ref="detailLog" v-if="detailLogVisible" :visible.sync="detailLogVisible" :clickData.sync="clickData">
      </detailLog>

      <!-- 一个隐藏的table，放入勾选的数据，作导出时用 -->
      <el-table id="exportGrch37Table" :data="multipleSelection" style="display: none;" >
        <el-table-column
          prop="rsId"
          label="rsId 编号"
          sortable
          width="180">
        </el-table-column>
        <el-table-column
          prop="chr"
          label="chr 染色体"
          sortable
          width="180">
        </el-table-column>
        <el-table-column
          prop="pos"
          label="pos 位置"
          width="180">
        </el-table-column>
        <el-table-column
          prop="ref"
          label="ref 原始基因"
          width="180">
        </el-table-column>
        <el-table-column
          prop="alt"
          label="alt 突变基因"
          width="180">
        </el-table-column>
        <el-table-column
          prop="commonState"
          label="commonState 描述"
          width="180">
        </el-table-column>
      </el-table>
    </div>
</template>
<style scoped>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }

  .searchs{
    display: inline-block;
    width: 200px;
  }

  .addClass{
    float:left;
    margin-left: 5%;
  }
  .search-div{
    display: inline-block;
    margin-top:5px;
    margin-left: 10px;
  }


</style>

<script>
    // import __ from '__' // __是需要手动引入的文件
    import log from './grch37_log'
    import FileSaver from 'file-saver'
    import XLSX from 'xlsx'
    import {formatDate} from "../../utils/dateUtil.js"
    import * as types from '../../utils/types.js'
    import { mapState } from 'vuex'


    export default {
        name: 'grch37',
        components: {
            'detailLog': log
        },
        data(){
          return{
            //表格当前页数据
            tableData:[],
            //多选数组
            multipleSelection: [],// 当前页选中的数据
            multipleSelectionAll: [],//所有选中的数据包含跨页数据
            //默认高亮行数据id
            //highlightId: -1,
            totalCount:1,
            //搜索条件
            criteria: {
              currentPage: 1,
              pageSize:10
            },
            detailLogVisible:false,
            clickData:{},
            exportFlag: true
          }
        },methods:{
            searchData(){
              let token = localStorage.getItem("token");
              //console.log('localstorage get token => ')
              //console.log(token)
              //console.log("this.token =》 "+this.token)
              this.$loading('正在加载数据，请耐心等待......');
              setTimeout(function () {}, 2000)
              this.$axios({
                  url:'/grch37/getDataByCondition',
                  method:'post',
                  data:this.criteria
              }).then(res => {
                  //console.log('getGrch37DataByCondition res data =>')
                  //console.log(res.data)

                  if(res.data.code == 401){
                    this.$loading.close();
                    this.$router.replace({path: '/login'})
                  }else {
                    this.$loading.close();
                    let list = res.data.data.rows;
                    list.forEach((ele, idx) => {
                      ele.rsIdOldVal = ele.rsId;
                      ele.commonSateOldVal = ele.commonState;
                      ele.idKey = this.criteria.pageSize * (this.criteria.currentPage - 1) + 1 + idx;
                    });
                    this.tableData = res.data.data.rows;
                    this.totalCount = res.data.data.total;
                  }
              })
            },
            searchDataByCondition(){
                var that = this;
                that.criteria.currentPage = 1;
                this.searchData();
            },
            handleSizeChange(val) {
              //console.log(`每页 ${val} 条`);
              var that = this;
              that.criteria.currentPage = 1;
              that.criteria.pageSize = val;
              this.searchData();
            },
            handleCurrentChange(val) {
              //console.log(`当前页: ${val}`);
              var that = this;
              that.criteria.currentPage = val;
              this.searchData();
            },
            handleDetailLog(row){
                  this.detailLogVisible= true;
                  this.clickData = row;
                  this.clickData.currentPage=1;
                  this.clickData.pageSize=10;
            },
            handleEdit(row){
                //console.log(row.rsId+" "+row.rsIdOldVal + '  ####   ' + row.commonState+" "+row.commonSateOldVal);
                this.$axios({
                    url:'/grch37/editData?commonSateOldVal='+row.commonSateOldVal+"&rsIdOldVal="+row.rsIdOldVal,
                    method:'post',
                    data:row
                }).then(res => {
                  if(res.data.code == 401){
                    this.$router.replace({path: '/login'})
                  }
                  this.searchData();
                })
            },
            handleDelete(index, row){
              this.$confirm('确认删除？').then(_=>{
                this.$axios({
                    url:'/grch37/deleteData',
                    method:'post',
                    data:row
                }).then(res => {
                    if(res.data.code == 401){
                      this.$router.replace({path: '/login'})
                    }
                    this.searchData();
                })
              }).catch(_=>{

              })
            },
            addRow(){
                var list={
                    rsId: this.rsId,
                    chr: this.chr,
                    pos: this.pos,
                    ref: this.ref,
                    alt: this.alt,
                    commonState: this.commonState
                }
                this.tableData.unshift(list);

            },
            //多选响应
            handleSelectionChange: function(val) {
              this.multipleSelection = val;
              //console.log(this.multipleSelection)
            },
            getRowKeys(row) {
              //console.log(row.idKey)
              return row.idKey;
            },
            //点击行响应
            handleClick: function(row, event, column){
                console.log(row.idKey)
            },
            tableRowClassName({row, rowIndex}) {
              if (rowIndex % 2 == 0) {
                return 'success-row';
              } else{
                return 'warning-row';
              }
            },
            formatDate(time) {
              return formatDate(time,"yyyy-MM-dd hh:mm:ss");
            },
            exportExcel () {
              /* generate workbook object from table */
              let wb = XLSX.utils.table_to_book(document.querySelector('#exportGrch37Table'));
              /* get binary string as output */
              let wbout = XLSX.write(wb, { bookType: 'xlsx', bookSST: true, type: 'array' });
              try {
                FileSaver.saveAs(new Blob([wbout], { type: 'application/octet-stream' }), 'grch37Table_'+this.formatDate(new Date())+'.xlsx');
              } catch (e)
              {
                if (typeof console !== 'undefined')
                  console.log(e, wbout)
              }
              return wbout
            }


        },
        created(){
          this.searchData();
        },
        computed: mapState({
          title: state => state.title,
          token: state => state.token,
        })
    }

</script>

