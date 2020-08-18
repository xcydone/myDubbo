<template>
    <div>
      <el-table
        :data="tableData"
        border
        :summary-method="getSummaries"
        show-summary
        style="width: 100%; margin-top: 20px">
        <el-table-column
          prop="id"
          label="ID"
          width="180">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名">
        </el-table-column>
        <el-table-column
          prop="amount1"
          label="数值 1（元）">
        </el-table-column>
        <el-table-column
          prop="amount2"
          label="数值 2（元）">
        </el-table-column>
        <el-table-column
          prop="amount3"
          label="数值 3（元）">
        </el-table-column>
      </el-table>
    </div>
</template>

<script>
    export default {
      data() {
        return {
          tableData: [{
            id: '12987122',
            name: '王小虎',
            amount1: '234',
            amount2: '3.2',
            amount3: 10
          }, {
            id: '12987123',
            name: '王小虎',
            amount1: '165',
            amount2: '4.43',
            amount3: 12
          }],
          tableData2: [{
            id: '12987122',
            name: '王小虎',
            amount1: '1',
            amount2: '1',
            amount3: 1
          }, {
            id: '12987123',
            name: '王小虎',
            amount1: '2',
            amount2: '2',
            amount3: 2
          }]
        };
      },
      methods: {
        getSummaries(param) {
          console.log("param:" + param)
          let { columns, data } = param;
          data = this.tableData2
          console.log("columns:" + columns)
          console.log("data:" + data)
          const sums = [];
          columns.forEach((column, index) => {
            if (index === 0) {
              sums[index] = '总价';
              return;
            }
            const values = data.map(item => {
                console.log("item:" + item);
                return Number(item[column.property]);
            });
            if (!values.every(value => isNaN(value))) {
              sums[index] = values.reduce((prev, curr) => {
                const value = Number(curr);
                if (!isNaN(value)) {
                  return prev + curr;
                } else {
                  return prev;
                }
              }, 0);
              sums[index] += ' 元';
            } else {
              sums[index] = 'N/A';
            }
          });

          return sums;
        }
      }
    }
</script>
