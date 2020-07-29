<script>
    import sheetColumn from "./sheetColumn";
    export default {
        components: {
            sheetColumn
        },
        render: function(h) {
            return <div className="table-control">
                        <el-table ref="tableRef"
                                  size="small"
                                  height={this.tableHeight}
                                  {...{attrs: {data:this.tableData}}}
                                  border
                        >
                            {
                                this.tableTitles.map(title => {
                                    return <sheetColumn
                                                {...{attrs: {column:title}}}
                                            >
                                            </sheetColumn>
                                })
                            }
                        </el-table>
                    </div>
        },
        props: {
            tableTitles: {
                type: Array,
                default: () => []
            },
            tableData: {
                type: Array,
                default: () => []
            },
            flag: {
                type: Boolean,
                default: () => true
            }
        },
        data() {
           return {
               tableHeight: null,
           }
        },
        created() {
            this.tableHeight = this.flag ? window.innerHeight - 260 : null;
        },
        methods: {
            resize() {
                this.$refs.tableRef.doLayout();
            },
        },
    }
</script>
<style>
    .el-table th, .el-table td {
        text-align: center;
        display: table-cell !important;
    }
</style>