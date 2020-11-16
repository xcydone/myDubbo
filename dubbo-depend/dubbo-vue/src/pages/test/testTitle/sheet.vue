<template>
  <div style="height:100%;">
    <el-container class="pom-wrap" v-loading="dataLoading">
      <el-main>
        <el-row type="flex" justify="space-between">
          <el-form
            :inline="true"
            :model="formInline"
            label-width="60px"
            class="pom-form-inline"
          >
            <el-form-item
              v-for="(domain, index1) in showList"
              :label="domain.displayName"
              :key="domain.colId"
              v-show="index1 < showSearchNum"
            >
              <el-input
                v-model="domain.value"
                style="width: 160px;"
                placeholder="请输入"
                v-if="domain.uiType == 1"
                clearable
              ></el-input>
              <el-date-picker
                v-model="domain.value"
                :type="domain.dateType"
                :format="domain.dataFormate"
                :value-format="domain.dataFormate"
                placeholder="选择日期"
                style="width: 180px;"
                v-if="domain.uiType == 2"
              >
              </el-date-picker>
              <el-date-picker
                v-model="domain.value"
                type="daterange"
                :format="domain.dataFormate"
                :value-format="domain.dataFormate"
                range-separator="-"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                style="width: 220px"
                v-if="domain.uiType == 3"
                size="small"
              >
              </el-date-picker>
              <el-select
                v-model="domain.value"
                :multiple="domain.uiType == 5"
                placeholder="请选择"
                v-if="domain.uiType == 4 || domain.uiType == 5"
                @change="getRegionList(domain)"
                clearable
                size="small"
                filterable
              >
                <el-option
                  v-for="attr in domain.selectDataList"
                  :key="attr.paramValue"
                  :label="attr.paramDesc"
                  :value="attr.paramValue"
                />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button
                v-if="!showSearch"
                @click="showT(true)"
                type="text"
                v-show="this.showList.length > 3"
                >展开 <i class="el-icon-arrow-down"></i
              ></el-button>
              <el-button
                v-if="showSearch"
                @click="showT(false)"
                type="text"
                v-show="this.showList.length > 3"
                >收起 <i class="el-icon-arrow-up"></i
              ></el-button>
            </el-form-item>
          </el-form>
        </el-row>

        <div class="eel-alert">
          <i class="icon-audittishi"></i>
          <span>报表记录总数：共计 {{ pagination.total }} </span>
        </div>

        <sheet-Table
                v-loading="loading"
                element-loading-text="拼命加载中"
                :tableTitles="columns"
                :tableData="sheetShowData"
                :tableHeight="276"
                :flag="true"
                ref="tableListRef">
        </sheet-Table>

        <div class="pagination-block print_hide">
          <el-pagination
                  @size-change="handleSizeChange"
                  @current-change="handleCurrentChange"
                  :current-page="pagination.currentPage"
                  :page-sizes="pagination.pageSizes"
                  :page-size="pagination.pageSize"
                  :layout="pagination.layout"
                  :total="pagination.total">
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import sheetTable from "./sheetTable";
import { formatDate } from "@/utils/dateutils";

export default {
  components: {sheetTable},
  data() {
    return {
      loading: false,
      dataLoading: false,
      timer: null, //定义定时器名称

      showSearch: false,
      showSearchNum: 3,

      formInline: {
        domains: [],
      },

      querytList:[
        {
          "colId": 1150,
          "colSeq": 9,
          "uiType": 4,
          "colName": "reseller_id",
          "displayName": "转售商",
          "dataFormate": "",
          "selectColLogic": "select '' paramValue,'全部' paramDesc from dual union select company_id paramValue, company_name paramDesc from conf_company_define where class_type=2",
          "value": "",
          "selectDataList": [
            {
              "paramDesc": "全部",
              "paramValue": ""
            },
            {
              "paramDesc": "阿里巴巴云计算（北京）有限公司",
              "paramValue": "100000008"
            },
            {
              "paramDesc": "深圳市爱施德股份有限公司",
              "paramValue": "100000029"
            },
            {
              "paramDesc": "北京北纬通信科技股份有限公司",
              "paramValue": "100000038"
            }
          ],
          "condtionOperators": "=",
          "relationColId": null
        },
        {
          "colId": 1149,
          "colSeq": 20,
          "uiType": 2,
          "colName": "billing_cycle_id",
          "displayName": "财务账期",
          "dataFormate": "yyyyMM",
          "selectColLogic": "",
          "value": "",
          "selectDataList": [],
          "condtionOperators": "=",
          "relationColId": null
        }
      ],

      columns: [],  // 列信息
      columnsQry: [
        {
          "titleId": 1000,
          "sheetId": 9999,
          "parentTitleId": -1,
          "titleDesc": "序号",
          "isLeaf": 1,
          "isLevel": 1,
          "colId": null,
          "colWidth": 50,
          "colHeight": 400,
          "colAlign": "center",
          "fontSize": 11,
          "colName": null,
          "colType": null,
          "cellShowType": null,
          "isLinkColumn": null,
          "linkParams": null,
          "colSeq": 1,
          "sheetName": "挂账平衡报表二级表头",
          "childrenList": []
        },
        {
          "titleId": 1001,
          "sheetId": 9999,
          "parentTitleId": -1,
          "titleDesc": "转售商",
          "isLeaf": 1,
          "isLevel": 1,
          "colId": 1142,
          "colWidth": 200,
          "colHeight": null,
          "colAlign": "left",
          "fontSize": 11,
          "colName": "reseller_name",
          "colType": 1,
          "cellShowType": "",
          "isLinkColumn": 0,
          "linkParams": "",
          "colSeq": 2,
          "sheetName": "挂账平衡报表二级表头",
          "childrenList": []
        },
        {
          "titleId": 1002,
          "sheetId": 9999,
          "parentTitleId": -1,
          "titleDesc": "TTSS挂账数",
          "isLeaf": 0,
          "isLevel": 1,
          "colId": null,
          "colWidth": null,
          "colHeight": null,
          "colAlign": "left",
          "fontSize": 11,
          "colName": null,
          "colType": null,
          "cellShowType": null,
          "isLinkColumn": null,
          "linkParams": null,
          "colSeq": 3,
          "sheetName": "挂账平衡报表二级表头",
          "childrenList": [
            {
              "titleId": 1003,
              "sheetId": 9999,
              "parentTitleId": 1002,
              "titleDesc": "预交保底结算款/n1",
              "isLeaf": 1,
              "isLevel": 2,
              "colId": 1144,
              "colWidth": null,
              "colHeight": null,
              "colAlign": "center",
              "fontSize": 11,
              "colName": "ttss_amount_1",
              "colType": 2,
              "cellShowType": "",
              "isLinkColumn": 0,
              "linkParams": "",
              "colSeq": 4,
              "sheetName": "挂账平衡报表二级表头",
              "childrenList": []
            },
            {
              "titleId": 1004,
              "sheetId": 9999,
              "parentTitleId": 1002,
              "titleDesc": "月度应付结算款/n2",
              "isLeaf": 1,
              "isLevel": 2,
              "colId": 1145,
              "colWidth": null,
              "colHeight": null,
              "colAlign": "left",
              "fontSize": 11,
              "colName": "ttss_amount_2",
              "colType": 2,
              "cellShowType": "",
              "isLinkColumn": 0,
              "linkParams": "",
              "colSeq": 5,
              "sheetName": "挂账平衡报表二级表头",
              "childrenList": []
            },
            {
              "titleId": 1005,
              "sheetId": 9999,
              "parentTitleId": 1002,
              "titleDesc": "挂账合计/n3=1+2",
              "isLeaf": 1,
              "isLevel": 2,
              "colId": 1146,
              "colWidth": null,
              "colHeight": null,
              "colAlign": "left",
              "fontSize": 11,
              "colName": "ttss_amount_all",
              "colType": 2,
              "cellShowType": "",
              "isLinkColumn": 0,
              "linkParams": "",
              "colSeq": 6,
              "sheetName": "挂账平衡报表二级表头",
              "childrenList": []
            }
          ]
        },
        {
          "titleId": 1006,
          "sheetId": 9999,
          "parentTitleId": -1,
          "titleDesc": "SAP挂账数/n4",
          "isLeaf": 1,
          "isLevel": 1,
          "colId": 1147,
          "colWidth": null,
          "colHeight": null,
          "colAlign": "left",
          "fontSize": 11,
          "colName": "sap_amount",
          "colType": 2,
          "cellShowType": "",
          "isLinkColumn": 0,
          "linkParams": "",
          "colSeq": 7,
          "sheetName": "挂账平衡报表二级表头",
          "childrenList": []
        },
        {
          "titleId": 1007,
          "sheetId": 9999,
          "parentTitleId": -1,
          "titleDesc": "挂账差异/n5=4-3",
          "isLeaf": 1,
          "isLevel": 1,
          "colId": 1148,
          "colWidth": null,
          "colHeight": null,
          "colAlign": "left",
          "fontSize": 11,
          "colName": "diff_amount",
          "colType": 2,
          "cellShowType": "!=0:2",
          "isLinkColumn": 0,
          "linkParams": "",
          "colSeq": 8,
          "sheetName": "挂账平衡报表二级表头",
          "childrenList": []
        }
      ],

      sheetShowData: [
        {
          "billing_cycle_id": 202005,
          "ttss_amount_all": 90.0,
          "data_id": 1001,
          "reseller_name": "北京分享在线网络技术有限公司",
          "diff_amount": 110.0,
          "sap_amount": 200.0,
          "ttss_amount_1": 30.0,
          "reseller_id": "100000039",
          "ttss_amount_2": 60.0
        },
        {
          "billing_cycle_id": 202005,
          "ttss_amount_all": 70.0,
          "data_id": 1003,
          "reseller_name": "苏宁云商集团股份有限公司",
          "diff_amount": 100.0,
          "sap_amount": 170.0,
          "ttss_amount_1": 30.0,
          "reseller_id": "100000040",
          "ttss_amount_2": 40.0
        },
        {
          "billing_cycle_id": 202005,
          "ttss_amount_all": 90.0,
          "data_id": 1005,
          "reseller_name": "深圳市优友互联有限公司",
          "diff_amount": 0.0,
          "sap_amount": 90.0,
          "ttss_amount_1": 50.0,
          "reseller_id": "100000041",
          "ttss_amount_2": 40.0
        }
      ],

      pagination: {
        currentPage: 1,
        pageSize: 10,
        layout: "total, sizes, prev, pager, next, jumper",
        total: 0,
      },

      showList: [],
    };
  },
  mounted: function(){
    this.dataLoading = true;

    // 展示的数据
    this.qryData();
  },

  methods: {
    qryData() {

      // 列信息处理
      this.columns = this.formateColumns(this.columnsQry);

      // 查询条件处理
      this.formInline.domains = this.querytList;
      this.showList = this.formateSelect(this.formInline.domains);

      //设置分页总数
      this.pagination.total = this.sheetShowData.length

      setTimeout(() => {
        this.dataLoading = false;
        this.$refs.tableListRef.resize()
      }, 400);
    },

    formateColumns(arrs) {
      for (let i = 0; i < arrs.length; i++) {
        arrs[i]['renderheader'] = this.renderheader
        // 存在子列子列加属性
        if (!(arrs[i].childrenList == undefined || arrs[i].childrenList == null || arrs[i].childrenList.length == 0)) {
          this.formateColumns(arrs[i].childrenList);
        }
      }
      return arrs
    },

    renderheader(h, {column}) {
      return h("span", {}, [
        h("span", {}, column.label.split("/n")[0]),
        h("br"),
        h("span", {}, column.label.split("/n")[1]),
      ]);
    },

    formateSelect(arrs) {
      let selectsTmp = [];
      let dateNow = new Date();
      for (let i = 0; i < arrs.length; i++) {
        // 处理数据加属性
        let itemList = arrs[i];

        // 设置默认的账期时间
        if (arrs[i].uiType == 2) {
          // debugger
          if (arrs[i].dataFormate != "" && arrs[i].dataFormate != null) {
            itemList["value"] = formatDate(dateNow, arrs[i].dataFormate);
            if (arrs[i].dataFormate.indexOf("MM") == -1) {
              itemList["dateType"] = "year";
            } else if (arrs[i].dataFormate.indexOf("dd") == -1) {
              itemList["dateType"] = "month";
            } else {
              itemList["dateType"] = "date";
            }
          }
        }
        selectsTmp.push(itemList);
      }
      return selectsTmp;
    },

    // 查询数据
    getSheetDataList(val) {
      if (val) {
        this.pagination.currentPage = 1;
      }
      let qryList = [];
      this.formInline.domains.forEach((domains) => {
        if (domains.value != "" && domains.value != null) {
          let item = {
            name: domains.colName,
            oper: domains.condtionOperators,
            value: [].concat(domains.value),
          };
          qryList.push(item);
        }
      });
      this.loading = true;
    },

    handleSizeChange(val) {
      this.pagination.pageSize = val;
      this.pagination.currentPage = 1;
      /*this.getSheetDataList();*/
    },

    handleCurrentChange(val) {
      this.pagination.currentPage = val;
      /*this.getSheetDataList();*/
    },

    handleSelectionChange(selections) {
    },

    showT(falg) {
      if (falg) {
        this.showSearch = true;
        this.showSearchNum = this.showList.length;
      } else {
        this.showSearch = false;
        this.showSearchNum = 3;
      }
    }
  },

  beforeDestroy() {
    clearInterval(this.timer);
    this.timer = null;
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss"></style>
