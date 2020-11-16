package com.crossyf.dubbo.springbatch.step.reader;

import com.crossyf.dubbo.springbatch.entity.ConfTableExecution;
import com.crossyf.dubbo.springbatch.service.IConfTableExecutionService;
import com.crossyf.dubbo.springbatch.constant.BatchGlobal;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author wh_Leimc
 * @date 2020/8/10 16:32
 */
@NoArgsConstructor
@Slf4j
public class CommonJdbcItemReader extends JdbcPagingItemReader<Map<String, Object>> {
    private DataSource dataSource;
    private Map<String, JobParameter> jobMap;
    private IConfTableExecutionService confTableExecutionService;

    public CommonJdbcItemReader(DataSource dataSource, Map<String, JobParameter> jobMap, IConfTableExecutionService confTableExecutionService) {
        this.dataSource = dataSource;
        this.jobMap = jobMap;
        this.confTableExecutionService = confTableExecutionService;
        this.init();
    }

    public void init() {
        JobParameter jobParameter = jobMap.get("tableId");
        Integer tableId = Integer.parseInt(jobParameter.toString());
        ConfTableExecution conf = confTableExecutionService.getConfTableExecutionMap(tableId);
        String selectSql = conf.getSelectSql();
        //替换条件中的通配符
        Set<String> keySet = jobMap.keySet();
        for (String key : keySet) {
            if (selectSql.contains(key)) {
                if (key.contains("$")) {
                    char[] chars = key.toCharArray();
                    StringBuilder keySb = new StringBuilder();
                    for (char c : chars) {
                        if (c == '$') {
                            keySb.append("\\$");
                        } else if (c == '{') {
                            keySb.append("\\{");
                        } else if (c == '}') {
                            keySb.append("\\}");
                        } else {
                            keySb.append(c);
                        }
                    }
                    selectSql = selectSql.replaceAll(keySb.toString(), jobMap.get(key).toString());
                }
            }
        }
        log.info("reader prepared execute sql -> " + selectSql);
        if (selectSql.contains("$") || selectSql.contains("{") || selectSql.contains("}")) {
            log.info("select_sql contains illegal character , return null!");
            return;
        }
        selectSql = selectSql.toLowerCase();
        if (!selectSql.contains(BatchGlobal.SELECT) || !selectSql.contains(BatchGlobal.FROM) || !selectSql.contains(BatchGlobal.ORDER)) {
            log.info("got the select_sql where from conf_table_execution missing keyword where table_id = " + tableId);
            return;
        }
        MySqlPagingQueryProvider queryProvider = new MySqlPagingQueryProvider();
        setProvider(selectSql, queryProvider);
        String valName = selectSql.substring(selectSql.indexOf(BatchGlobal.SELECT) + 6, selectSql.indexOf(BatchGlobal.FROM));

        this.setFetchSize(10);
        this.setDataSource(dataSource);
        this.setQueryProvider(queryProvider);
        this.setRowMapper(new RowMapper<Map<String, Object>>() {
            @Override
            public Map mapRow(ResultSet rs, int i) throws SQLException {
                Map dataMap = new LinkedHashMap<String, Object>();
                String[] valNames = valName.split(",");
                for (String name : valNames) {
                    String fieldName = name.trim();
                    dataMap.put(fieldName, rs.getObject(fieldName));
                }
                return dataMap;
            }
        });
    }

    private void setProvider(String selectSql, MySqlPagingQueryProvider queryProvider) {
        String selectClause = selectSql.substring(selectSql.indexOf(BatchGlobal.SELECT), selectSql.indexOf(BatchGlobal.FROM));
        queryProvider.setSelectClause(selectClause);
        String fromClause = null, whereSql = null, orderBySql = null, groupBySql = null;
        if (selectSql.contains(BatchGlobal.WHERE)) {
            fromClause = selectSql.substring(selectSql.indexOf(BatchGlobal.FROM), selectSql.indexOf(BatchGlobal.WHERE));
            String whereAfter = selectSql.substring(selectSql.indexOf(BatchGlobal.WHERE));
            //包含order by和 group by
            if (whereAfter.contains(BatchGlobal.ORDER) && whereAfter.contains(BatchGlobal.GROUP)) {
                if (whereAfter.indexOf(BatchGlobal.ORDER) < whereAfter.indexOf(BatchGlobal.GROUP)) {
                    whereSql = whereAfter.substring(0, whereAfter.indexOf(BatchGlobal.ORDER));
                    orderBySql = whereAfter.substring(whereAfter.indexOf(BatchGlobal.ORDER), whereAfter.indexOf(BatchGlobal.GROUP));
                    groupBySql = whereAfter.substring(whereAfter.indexOf(BatchGlobal.GROUP));
                } else {
                    whereSql = whereAfter.substring(0, whereAfter.indexOf(BatchGlobal.GROUP));
                    groupBySql = whereAfter.substring(whereAfter.indexOf(BatchGlobal.GROUP), whereAfter.indexOf(BatchGlobal.ORDER));
                    orderBySql = whereAfter.substring(whereAfter.indexOf(BatchGlobal.ORDER));
                }
            } else if (whereAfter.contains(BatchGlobal.ORDER)) {
                whereSql = whereAfter.substring(0, whereAfter.indexOf(BatchGlobal.ORDER));
                orderBySql = whereAfter.substring(whereAfter.indexOf(BatchGlobal.ORDER));
            } else if (whereAfter.contains(BatchGlobal.GROUP)) {
                whereSql = whereAfter.substring(0, whereAfter.indexOf(BatchGlobal.GROUP));
                orderBySql = whereAfter.substring(whereAfter.indexOf(BatchGlobal.GROUP));
            } else {
                whereSql = whereAfter;
            }
        } else if (selectSql.contains(BatchGlobal.ORDER) && selectSql.contains(BatchGlobal.GROUP)) {
            if (selectSql.indexOf(BatchGlobal.ORDER) < selectSql.indexOf(BatchGlobal.GROUP)) {
                fromClause = selectSql.substring(selectSql.indexOf(BatchGlobal.FROM), selectSql.indexOf(BatchGlobal.ORDER));
                orderBySql = selectSql.substring(selectSql.indexOf(BatchGlobal.ORDER), selectSql.indexOf(BatchGlobal.GROUP));
                groupBySql = selectSql.substring(selectSql.indexOf(BatchGlobal.GROUP));
            } else {
                fromClause = selectSql.substring(selectSql.indexOf(BatchGlobal.FROM), selectSql.indexOf(BatchGlobal.GROUP));
                groupBySql = selectSql.substring(selectSql.indexOf(BatchGlobal.GROUP), selectSql.indexOf(BatchGlobal.ORDER));
                orderBySql = selectSql.substring(selectSql.indexOf(BatchGlobal.ORDER));
            }
        } else if (selectSql.contains(BatchGlobal.ORDER)) {
            fromClause = selectSql.substring(selectSql.indexOf(BatchGlobal.FROM), selectSql.indexOf(BatchGlobal.ORDER));
            orderBySql = selectSql.substring(selectSql.indexOf(BatchGlobal.ORDER));
        } else if (selectSql.contains(BatchGlobal.GROUP)) {
            fromClause = selectSql.substring(selectSql.indexOf(BatchGlobal.FROM), selectSql.indexOf(BatchGlobal.GROUP));
            groupBySql = selectSql.substring(selectSql.indexOf(BatchGlobal.GROUP));
        }
        queryProvider.setFromClause(fromClause);

        if (StringUtils.isNotEmpty(whereSql)) {
            queryProvider.setWhereClause(whereSql);
        }
        queryProvider.setSortKeys(getSortKeys(orderBySql));

        if (StringUtils.isNotEmpty(groupBySql)) {
            queryProvider.setGroupClause(groupBySql);
        }

    }

    private Map<String, Order> getSortKeys(String orderSql) {
        if (StringUtils.isNotEmpty(orderSql)) {
            Map<String, Order> sortKeys = new HashMap<>();
            String[] orders = orderSql.substring(orderSql.indexOf(BatchGlobal.BY) + 2).split(",");
            //order by id,name desc
            for (String order : orders) {
                //name desc
                order = order.trim();
                if (order.contains(" ")) {
                    if (order.split(" ")[1].equals(BatchGlobal.ORDER_ASC)) {
                        sortKeys.put(order.split(" ")[0], Order.ASCENDING);
                    } else {
                        sortKeys.put(order.substring(0, order.indexOf(" ")), Order.DESCENDING);
                    }
                } else {
                    //id
                    sortKeys.put(order, Order.ASCENDING);
                }
            }
            return sortKeys;
        }
        return null;
    }

}
