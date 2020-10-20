package com.crossyf.dubbo.springtest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.crossyf.dubbo.springtest.constant.BatchGlobal;
import com.crossyf.dubbo.springtest.dto.ConfTableExecutionDto;
import com.crossyf.dubbo.springtest.entity.ConfTableExecution;
import com.crossyf.dubbo.springtest.mapper.ConfTableExecutionMapper;
import com.crossyf.dubbo.springtest.service.IConfTableExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ConfTableExecutionDto> getConfTableExecutionTwo() {
        return confTableExecutionMapper.qryTwo();
    }

    @Override
    public List<ConfTableExecutionDto> getConfTableExecutionWe() {
        return confTableExecutionMapper.qryWe();
    }

    @Override
    public void insertHH(ConfTableExecutionDto dto) {

        ConfTableExecution cd = new ConfTableExecution();
        BeanUtils.copyProperties(dto, cd);
        confTableExecutionMapper.insert(cd);
    }

}
