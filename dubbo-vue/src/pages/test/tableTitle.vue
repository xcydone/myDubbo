<template>
  <div style="width: 100%">
    <template>
      <div>
        <cmp :html="htmlStr" :tableData="data"></cmp>
      </div>
    </template>
    <el-button @click="testClick2">测试点击2</el-button>
    <el-button @click="testConst()">测试点击3</el-button>
  </div>
</template>

<script>
  import Vue from 'vue';
   export default {
    name:"tableTitle",
    data() {
      return {
        data: [
        {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        },
        {
          date: '2016-05-02',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        },
        {
          date: '2016-05-04',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        },
        {
          date: '2016-05-01',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        },
        {
          date: '2016-05-08',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        },
        {
          date: '2016-05-06',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        },
        {
          date: '2016-05-07',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }
        ],
        titleDate:[
          {
            titleDesc:"一级表头A1",
            titleId:1,
            prop:"title1",
            parentTitleId:0,
          },
          {
            titleDesc:"二级级表头A2A",
            titleId:4,
            prop:"title4",
            parentTitleId:1,
          },
          {
            titleDesc:"二级表头A2B",
            titleId:5,
            prop:"title5",
            parentTitleId:1
          },
          {
            titleDesc:"三级表头A3A",
            titleId:6,
            parentTitleId:5,
            prop:"title6",
          },
          {
            titleDesc:"四级表头A4A",
            titleId:8,
            parentTitleId:6,
            prop:"title8",
          },
          {
            titleDesc:"四级表头A4A",
            titleId:10,
            parentTitleId:8,
            prop:"title10",
          },
          {
            titleDesc:"五级表头A5A",
            titleId:11,
            parentTitleId:10,
            prop:"title11",
          },
          {
            titleDesc:"四级表头A4B",
            titleId:9,
            parentTitleId:6,
            prop:"title9",
          },
          {
            titleDesc:"一级表头B1",
            titleId:2,
            parentTitleId:0,
            prop:"title2",
          },
          {
            titleDesc:"二级级表头B2A",
            titleId:7,
            parentTitleId:2,
            prop:"title7",
          },
          {
            titleDesc:"一级表头C1",
            titleId:3,
            parentTitleId:0,
            prop:"title3",
          }
        ],
        titleDateTree: [
          {
            titleDesc:"一级表头A1",
            titleId:1,
            prop:"title1",
            parentTitleId:-1,
            level:1,
            childTileList:[
              {
                titleDesc:"二级级表头A2A",
                titleId:4,
                parentTitleId:1,
                prop:"title4",
                level:2,
                childTileList:[]
              },
              {
                titleDesc:"二级表头A2B",
                titleId:5,
                prop:"title5",
                parentTitleId:1,
                level:2,
                childTileList:[
                  {
                    titleDesc:"三级表头A3A",
                    titleId:6,
                    prop:"title6",
                    parentTitleId:5,
                    level:3,
                    childTileList:[]
                  }
                ]
              }
            ]
          },
          {
            titleDesc:"一级表头B1",
            titleId:2,
            parentTitleId:-1,
            prop:"title2",
            level:1,
            childTileList:[
              {
                titleDesc:"二级级表头B2A",
                titleId:7,
                prop:"title7",
                level:2,
                parentTitleId:2,
                childTileList:[]
              }
            ]
          },
          {
            titleDesc:"一级表头C1",
            titleId:3,
            level:1,
            parentTitleId:-1,
            prop:"title3",
            childTileList:[]
          }
        ],
        titleDateTree1: [
          {
            perfix:' <el-table-column' + ' prop="title1"' + ' label="一级表头A1"' + ' width="150">',
            childTileList:[
              {
                perfix:' <el-table-column' + '  prop="title4"' + '  label="二级级表头A2A"' + '   width="150">',
                childTileList:[],
                surfix:'</el-table-column>'
              },
              {
                perfix:' <el-table-column' + '  prop="title5"' + '  label="二级表头A2B"' + '   width="150">',
                childTileList:[
                  {
                    perfix:' <el-table-column' + '  prop="title6"' + '  label="三级表头A3A"' + '   width="150">',
                    childTileList:[],
                    surfix:'</el-table-column>'
                  }
                ],
                surfix:'</el-table-column>'

              }
            ],
            surfix:'</el-table-column>'
          },
          {
            perfix:' <el-table-column' + ' prop="title2"' + ' label="一级表头B1"' + ' width="150">',
            childTileList:[
              {
                perfix:' <el-table-column' + ' prop="title7"' + ' label="二级级表头B2A"' + ' width="150">',
                childTileList:[],
                surfix:'</el-table-column>'
              }
            ],
            surfix:'</el-table-column>'
          },
          {
            perfix:' <el-table-column' + ' prop="title3"' + ' label="一级表头C1"' + ' width="150">',
            childTileList:[],
            surfix:'</el-table-column>'
          }
        ],
        htmlStr:
          '<el-table\n' +
          '      :data="tableData"\n' +
          '      style="width: 100%">\n' +
          '      <el-table-column\n' +
          '        prop="date"\n' +
          '        label="日期"\n' +
          '        width="150">\n' +
          '      </el-table-column>\n' +
          '      <el-table-column label="配送信息">\n' +
          '        <el-table-column\n' +
          '          prop="name"\n' +
          '          label="姓名"\n' +
          '          width="120">\n' +
          '        </el-table-column>\n' +
          '        <el-table-column label="地址">\n' +
          '          <el-table-column\n' +
          '            prop="province"\n' +
          '            label="省份"\n' +
          '            width="120">\n' +
          '          </el-table-column>\n' +
          '          <el-table-column\n' +
          '            prop="city"\n' +
          '            label="市区"\n' +
          '            width="120">\n' +
          '          </el-table-column>\n' +
          '          <el-table-column\n' +
          '            prop="address"\n' +
          '            label="地址"\n' +
          '            width="300">\n' +
          '          </el-table-column>\n' +
          '          <el-table-column\n' +
          '            prop="zip"\n' +
          '            label="邮编"\n' +
          '            width="120">\n' +
          '          </el-table-column>\n' +
          '        </el-table-column>\n' +
          '      </el-table-column>\n' +
          '    </el-table>',

      }
    },

    components: {
      cmp: {
        props: {
          html: String,
          tableData:Array,
        },
        render(h) {
          const com = Vue.extend({
            template: this.html,
            props: {
              tableData: Array,
            }
          })
          return h(com, {
            props: {
              tableData: this.tableData
            }
          })
        }
      }
    },

    methods: {
      testClick2() {
        var filterArray1 = this.filterArray(this.titleDate, 0);
        let s = JSON.stringify(filterArray1);

        //TODO: 2020/7/24===================================================
        let finalHtml = JSON.stringify(filterArray1).replace(/\\/g, "").replace(/{"perfix":"/g, "").replace(/,"surfix":"/g, "")
          .replace(/","childrenList":/g, "").replace(/"},/g, "").replace(/"}]/g, "").replace(/[\\[\\]]/g, "")
          .replace(/\[ /g, "")
        this.htmlStr = '<el-table' + ' :data="tableData"' + ' style="width: 100%">' + finalHtml + '</el-table>'
      },

      // TODO: 2020/7/24===================================================
      filterArray(data, parent) {
        var tree = [];
        var temp;
        for (var i = 0; i < data.length; i++) {
          if (data[i].parentTitleId == parent) {
            //var obj = data[i];
            let obj = {}
            obj['perfix'] = ' <el-table-column' + ' prop=' + data[i].prop + ' label=' + data[i].titleDesc + ' width="190">' + '<template slot-scope="scope" slot="header"><el-tooltip effect="dark" content=' + data[i].titleDesc + ' placement="top" ><span>' + data[i].titleDesc + '</span></el-tooltip> </template>'
            /*console.log("obj:",JSON.parse(JSON.stringify(obj)))*/
            obj['childrenList'] = []
            obj['surfix'] = '</el-table-column>'

            temp = this.filterArray(data, data[i].titleId);
            if (temp.length > 0) {
              obj.childrenList = temp;
            }
            tree.push(obj);
          }
        }
        return tree;
      },

      testConst() {
        const haStr = 'nihao';
        /*haStr = 'help';*/

        const haArr = ['xiaozhu', 'xiaogou'];
        haArr[0] = 'zhuzhu';
        haArr.push('xiaoniu')
        console.log(haArr)

        const haMap = {1: 'xiaozhu', 2: 109};
        haMap[1] = 'xiaogou';
        haMap[3] = 'male'
        console.log(haMap[1])
        console.log(haMap[3])

        /*haMap = {'ni': 1};*/

        // 打印的是key--string
        /*for (let i in haMap){
          console.log(i)
          console.log(typeof i);
        }

        let haMap2 = {1: 'xiaozhu', 2: 109};
        for (let i of haMap2){
          console.log(i)
          console.log(typeof i);
        }*/

        const arr0 = ['a', 'b']
        arr0.name = 'c'

        for (let i in arr0) {
          console.log(i) // 0 1 name
        }


        const arr = ['a', 'b', 'c']
        for (let i in arr) {
          console.log(i)  // 0 1 2
        }

        /*for (let i of arr) {
          console.log(i) // a b c
        }*/

        let haMap3 = null;
        haMap3.name

      }
    }
  }
</script>
