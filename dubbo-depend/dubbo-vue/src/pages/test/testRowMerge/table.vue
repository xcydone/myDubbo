<template>
  <div style="height:100%;">
    <el-container class="pom-wrap">
      <el-main>
        <el-row :gutter="25">
          <el-col :span="20">
            <!-- 多行小计表格需要添加border -->
            <el-table
              :data="tableData"
              style="width: 100%"
              :span-method="objectSpanMethod"
              :row-class-name="tableRowClassName"
              @sort-change="tableSortChange"
              v-loading="tableLoading1"
              border
            >
              <el-table-column
                prop="billId"
                width="120"
                label="账单ID"
                sortable="custom"
              >
              </el-table-column>
              <el-table-column prop="field1" width="120" label="帐期月">
              </el-table-column>
              <el-table-column prop="field2" label="业务大类">
              </el-table-column>
              <el-table-column prop="field3" label="业务小类">
              </el-table-column>
              <el-table-column prop="field4" label="运营商"> </el-table-column>
              <el-table-column prop="type" label="收支类型"> </el-table-column>
              <el-table-column prop="count" label="金额"> </el-table-column>
              <el-table-column prop="unit" label="币种"> </el-table-column>
            </el-table>
            <div class="el-table-total">
              <span>金额合计</span>
              <span class="fr">5740098</span>
            </div>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import demoData from "./demoData.vue";
export default {
  data() {
    return {
      currentPage: 1,
      initTableData: [],
      tableData: [],
      position: 0,
      spanArr: {},
      mergId: {},
      tableLoading1: false,
      columnsList:["billId","field1","field2","field3","field4","type","count","unit"],
    };
  },
  methods: {
    handleEdit() {},
    handleEdit1() {},
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },



    /* 合并首行方法 */
    rowspan(propsName) {
      this.spanArr[propsName] = [];
      this.position = 0;
      this.tableData.forEach((item, index) => {
        if (index === 0) {
          this.spanArr[propsName].push(1);
          this.position = 0;
        } else if (index > 0) {
          if (this.tableData[index][propsName] ===  this.tableData[index - 1][propsName]) {
            this.spanArr[propsName][this.position] += 1;
            this.spanArr[propsName].push(0);
          } else {
            this.spanArr[propsName].push(1);
            this.position = index;
          }
        }
      });
      console.log("this.spanArr[propsName]:",this.spanArr[propsName]);
    },

    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      /*console.log("row:",row)*/
      if (row.isSum) {
        // 对小计行的合并处理
        if (columnIndex === this.mergId["count"]-1) {
          // 显示小计标题的列进行合并（colspan根据实际列数而定）
          return {
            rowspan: 1,
            colspan: this.mergId["count"] - this.mergId[row.mergeField] - 1,
          };
        }

        if (columnIndex > this.mergId["count"]-1) {
          // 小计后面的单元格正常显示
          return {
            rowspan: 1,
            colspan: 1,
          };
        } else {
          // 隐藏小计前面的单元格
          return {
            rowspan: 0,
            colspan: 0,
          };
        }
      }

      if (columnIndex === this.columnsList.indexOf("billId")) {
        const _row = this.spanArr["billId"][rowIndex];
        const _col = _row > 0 ? 1 : 0;
        return {
          rowspan: _row,
          colspan: _col,
        };
      }

      if (columnIndex === this.columnsList.indexOf("field1")) {
        const _row2 = this.spanArr["field1"][rowIndex];
        const _col2 = _row2 > 0 ? 1 : 0;
        return {
          rowspan: _row2,
          colspan: _col2,
        };
      }else{
        return {
          rowspan: 1,
          colspan: 1,
        };
      }
    },

    tableRowClassName({ row, rowIndex }) {
      if (row.isSum) {
        return "sum-row";
      }
      return "";
    },

    // 升序/降序
    tableSortChange(column) {
      /*console.log("column:",column)*/
      switch (column.prop) {
        case "billId": // 根据账单Id排序
          this.sortByBillId(column.order);
          break;

        default:
          break;
      }
    },

    // 升序/降序
    sortByBillId(order) {
      let data = JSON.parse(JSON.stringify(this.initTableData));
      switch (order) {
        case "ascending": // 正序
          data = data
            .sort(function(obj1, obj2) {
              return obj1.billId - obj2.billId;
            })
            .map((item, index) => {
              item.dataIndex = index;
              return item;
            });
          break;

        case "descending": // 逆序
          data = data
            .sort(function(obj1, obj2) {
              return obj2.billId - obj1.billId;
            })
            .map((item, index) => {
              item.dataIndex = index;
              return item;
            });
          break;

        default:
          break;
      }

      console.log(data);
      this.initSumTable(data);
    },

    // 初始化合计列
    async initSumTable(initTableData) {
      this.tableLoading1 = true;
      let data = initTableData.map((item, index) => {
        item.dataIndex = index;
        return item;
      });
      // 合计时：
      let ttmp = await this.groupTableFn(
        data,
        true,
        "field1",
        "count",
        "unit",
        "美元"
      );
      this.tableData = JSON.parse(JSON.stringify(ttmp));
      console.log("ttmp:",JSON.parse(JSON.stringify(ttmp)));

      let ttmp2 = await this.groupTableFn(
        ttmp,
        true,
        "billId",
        "count",
        "unit",
        "美元"
      );
      this.tableData = JSON.parse(JSON.stringify(ttmp2));

      /*let ttmp2 = await this.groupTableFn(
        data,
        true,
        "billId",
        "count",
        "unit",
        "美元"
      );
      this.tableData = JSON.parse(JSON.stringify(ttmp2));*/

      // 不合计时
      /*this.tableData = JSON.parse(JSON.stringify(initTableData));*/

      setTimeout(() => {
        this.tableLoading1 = false;
      }, 500);
    },

    /**
     * @description: 需要合并行并小计的表格，对数据分组并计算总数
     * @param {String} mergeField [用于合并行的数据属性名称]
     * @param {String} countField [用于计算的数据属性名称]
     * @param {String} unitField [数据单位属性名称]
     * @return: afterTableData [处理后的对象数组]
     */
    async groupTableFn(tableData, isFirst, mergeField, countField, unitField, unitName) {
      console.log("初入参：",tableData);
      let sumTableData = [];
      tableData.forEach((item, index) => {
        let flag = sumTableData.find((sumData) => {
          return sumData[mergeField] === item[mergeField];
        });
        if (!flag) {
          let obj = {
            count: item[countField],
            lastDataIndex: item.dataIndex,
            data: [item],
          };

          // 补前面的属性
          for(let i = 0; i < this.columnsList.length; i++){
            if(this.columnsList[i] == mergeField){
              break;
            }else{
              obj[this.columnsList[i]] = item[this.columnsList[i]]
            }
          }

          obj[mergeField] = item[mergeField];
          obj["mergeField"] = mergeField;
          obj[unitField] = unitName;
          obj.type = "小计";
          obj.isSum = true; // 是否小计行
          sumTableData.push(obj);
        } else {
          flag.lastDataIndex = item.dataIndex;
          if(flag.lastDataIndex != undefined){
            flag.count += item[countField];
          }
          flag.data.push(item);
        }
      });

      // 在每个数据分组的最后一条数据后面插入小计行
      sumTableData.forEach((sumData) => {
        let index = 0;
        // 第一次合并
        if(sumData.lastDataIndex != undefined){
          index = tableData.findIndex((item) => {
            return item.dataIndex === sumData.lastDataIndex;
          })
        }else {
          // 多次合并
          let tableDataTmp = JSON.parse(JSON.stringify(tableData));
          let tableDataReverse = tableDataTmp.reverse();
          index = tableDataReverse.length - 1 - tableDataReverse.findIndex((item) => {
            // 合计列找mergeField
            if(item.isSum){
              return item[sumData.mergeField] == sumData[sumData.mergeField]
            }
          });
        }
        console.log("index: ",index)
        tableData.splice(index + 1, 0, sumData);
      });

      console.log("sumTableData: ",sumTableData)
      return tableData;
    },

    findMergeId(arrs){
      this.mergId["billId"] = arrs.indexOf("billId")
      this.mergId["field1"] = arrs.indexOf("field1")
      this.mergId["count"] = arrs.indexOf("count")
    },
  },
  watch: {
    tableData: {
      handler(newVal, oldVal) {
        this.rowspan("field1");
        this.rowspan("billId");
      },
    },
  },
  created() {
    this.initTableData = JSON.parse(JSON.stringify(demoData.tableData1));
    this.findMergeId(this.columnsList);
    /*this.initSumTable(JSON.parse(JSON.stringify(this.initTableData)));*/
    this.sortByBillId("ascending");


  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
  .el-table {
    text-align: center;
  }

</style>
