package com.eshore.springbatch.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.eshore.springbatch.constant.BatchGlobal;
import com.eshore.springbatch.entity.ConfTableExecution;
import com.eshore.springbatch.mapper.ConfTableExecutionMapper;
import com.eshore.springbatch.service.IConfTableExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zuos
 * @since 2020-08-10
 */
@Service
@Slf4j
public class ConfTableExecutionServiceImpl implements IConfTableExecutionService {
    @Autowired
    private ConfTableExecutionMapper confTableExecutionMapper;

    @Override
    public ConfTableExecution getConfTableExecutionMap(Integer tableId) {
        ConfTableExecution confTable = BatchGlobal.BATCH_CONF_TABLE_EXECUTION_MAP.get(tableId);
        if(confTable != null){
            return confTable;
        }else{
            LambdaQueryWrapper<ConfTableExecution> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(ConfTableExecution::getTableId, tableId);
            queryWrapper.eq(ConfTableExecution::getConfStatus, BatchGlobal.VALID_STATUS);
            confTable = confTableExecutionMapper.selectOne(queryWrapper);

            if(confTable == null) {
                log.error("tableId:" + tableId + "对应配置数据为空,请检查配置");
                return null;
            }

            BatchGlobal.BATCH_CONF_TABLE_EXECUTION_MAP.put(tableId, confTable);
            return confTable;
        }
    }

}
