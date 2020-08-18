package com.crossyf.dubbo.springbatch.step.writer;

import com.crossyf.dubbo.springbatch.entity.ConfTableExecution;
import com.crossyf.dubbo.springbatch.service.IConfTableExecutionService;
import com.crossyf.dubbo.springbatch.provider.MapItemSqlParameterSourceProvider;
import lombok.NoArgsConstructor;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author wh_Leimc
 * @date 2020/8/10 16:38
 */
@NoArgsConstructor
public class CommonJdbcItemWriter extends JdbcBatchItemWriter<Map<String, Object>> {
    private DataSource dataSource;
    private Map<String, JobParameter> jobMap;
    private IConfTableExecutionService iConfTableExecutionService;

    public CommonJdbcItemWriter(DataSource dataSource, Map<String, JobParameter> jobMap, IConfTableExecutionService iConfTableExecutionService) {
        this.dataSource = dataSource;
        this.jobMap = jobMap;
        this.iConfTableExecutionService = iConfTableExecutionService;
        this.init();
    }

    public void init(){
        Integer tableId = Integer.parseInt(jobMap.get("tableId").toString());
        ConfTableExecution confTableExecution = iConfTableExecutionService.getConfTableExecutionMap(tableId);

        String writerTableName = confTableExecution.getWriterTableName();
        String writerColumnNames = confTableExecution.getWriterColumnNames();
        String readerColumnNames = confTableExecution.getReaderColumnNames();
        String[] valueNames = readerColumnNames.split(",");
        StringBuilder valuesSql = new StringBuilder();
        for (String valueName : valueNames) {
            valuesSql.append(":").append(valueName).append(",");
        }
        String values = valuesSql.toString().substring(0, valuesSql.length() - 1);

        StringBuilder insertSqlSb = new StringBuilder();
        insertSqlSb.append("insert into ").append(writerTableName).append(" (").append(writerColumnNames).append(")");
        insertSqlSb.append(" values(").append(values).append(")");

        this.setDataSource(dataSource);
        this.setSql(insertSqlSb.toString());
        this.setItemSqlParameterSourceProvider(new MapItemSqlParameterSourceProvider<>());
    }
}
