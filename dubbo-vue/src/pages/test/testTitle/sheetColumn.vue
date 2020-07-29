<script>
    export default {
        data() {
            return {}
        },
        props: {
            column: {
                type: Object
            }
        },
        name: "sheetColumn",
        render: function (h) {
            // 未定义子列时
            if (this.column.childrenList == undefined
                || this.column.childrenList == null
                || this.column.childrenList.length == 0){
                // 序号列
                if (this.column.titleDesc == '序号') {
                    return <el-table-column
                                {...{scopedSlots: {
                                        default: (scope) => {
                                            return this.formateIndex(this.column, scope.$index + 1);
                                        }
                                }}}
                                prop={this.column.colName}
                                label={this.column.titleDesc}
                                width={this.column.colWidth}
                                render-header={this.column.renderheader}
                            >
                            </el-table-column>
                }else{
                    return <el-table-column
                                {...{scopedSlots: {
                                        default: (scope) => {
                                            return this.formateValue(this.column, scope.row[scope.column.property])
                                        }
                                }}}
                                prop={this.column.colName}
                                label={this.column.titleDesc}
                                width={this.column.colWidth}
                                render-header={this.column.renderheader}
                            >
                            </el-table-column>
                }
            }

            let buildTitles = (childrenList) => {
                let children = [];
                childrenList.map(child => {
                    // 存在子节点时，打一个父标签后递归处理
                    if (child.childrenList != undefined && child.childrenList != null && child.childrenList.length > 0) {
                        children.push(
                            <el-table-column
                                label={child.titleDesc}
                            >
                                {buildTitles(child.childrenList)}
                            </el-table-column>
                        )
                    } else {
                        // 不存在子节点，返回数据
                        children.push(
                            <el-table-column
                                {...{scopedSlots: {
                                        default: (scope) => {
                                            return this.formateValue(child, scope.row[scope.column.property])
                                        }
                                }}}
                                label={child.titleDesc}
                                prop={child.colName}
                                width={child.colWidth}
                                render-header={child.renderheader}
                            >
                            </el-table-column>
                        )
                    }
                });
                return children;
            };

            return <el-table-column
                        label={this.column.titleDesc}
                        prop={this.column.colName}
                        width={this.column.colWidth}
                        render-header={this.column.renderheader}
                    >
                            {buildTitles(this.column.childrenList)}
                    </el-table-column>
        },
        methods: {
            // 处理样式问题
            formateStyle(attr){
                let style = {}
                let items = ['colAlign','fontSize']
                items.forEach( item => {
                    if(attr[item] != null && attr[item] != undefined){
                        switch (item) {
                            case 'colAlign': style['text-align'] = attr[item]; break;
                            case 'fontSize': style['font-size'] = attr[item] + 'px'; break;
                        }
                    }
                })
                return style
            },

            // 处理index列
            formateIndex(attr, value){
                return <p style={this.formateStyle(attr)}> {value} </p>
            },

            // 处理普通列的数据
            formateValue(attr, value){
                // 先处理超链接
                if (attr.isLinkColumn == 1) {
                    return this.formatLink(attr,value);
                }

                if (attr.colType == 1) {
                    return this.formatText(attr, value);
                }
                if (attr.colType == 2) {
                    return this.formatNumber(attr,value);
                }
                if (attr.colType == 3) {
                    return this.formatDate(attr,value);
                }
            },

            // 处理超链接
            formatLink(attr,value) {
                if (JSON.stringify(attr.linkParams) === "{}") {
                    return;
                }
                let url = null;
                url = "http://" + attr.linkParams;
                return (<p style={this.formateStyle(attr)}>{" "}<a style="text-decoration:underline;" href={url} target="_blank"> {" "}{value}</a>{" "} </p>);
            },

            // 处理两位小数 + 字体背景色
            formatNumber(attr, value) {
                //toFixed(x),x为几则取几位小数,金额为分，页面展示为元
                let columnTWO = Number(value).toFixed(2);
                if (attr.cellShowType != null && attr.cellShowType != "") {
                    let operList = (attr.cellShowType + "").split("#");
                    let operSingle = operList.filter((item) => {
                        return (item + "").split(":")[1] == 2;
                    });
                    let oper = (operSingle + "").split(":");
                    let operR = value + "" + oper[0];
                    if (oper[1] == 2 && eval(operR)) {
                        let style = this.formateStyle(attr);
                        style['color'] = "#F83436"
                        return ( <div style={style}>{" "}{columnTWO}{" "}</div>)
                    }
                }
                return <p style={this.formateStyle(attr)}> {columnTWO} </p>;
            },

            // 处理文本 + 字体背景色
            formatText(attr, value) {
                if (attr.cellShowType != null && attr.cellShowType != "") {
                    let operList = (attr.cellShowType + "").split("#");
                    let operSingle = operList.filter((item) => {
                        return (item + "").split(":")[1] == 2;
                    });
                    let oper = (operSingle + "").split(":");
                    let operR = '"' + value + '"' + "" + oper[0];
                    if (oper[1] == 2 && eval(operR)) {
                        let style = this.formateStyle(attr);
                        style['color'] = "#F83436"
                        return ( <div style={style}>{" "}{value}{" "}</div>)
                    }
                }
                return <p style={this.formateStyle(attr)}> {value} </p>;
            },

            // 处理日期
            formatDate(attr, value) {
                let columnDate = formatDate(value, "yyyy-MM-dd");
                return <p> {columnDate} </p>;
            },
        }
    }
</script>

<style scoped>
</style>