package com.crossyf.dubbo.springtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crossyf.dubbo.springtest.dto.ConfTableExecutionDto;
import com.crossyf.dubbo.springtest.entity.ConfTableExecution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfTableExecutionMapper extends BaseMapper<ConfTableExecution> {
    List<ConfTableExecutionDto> qryTwo();

    List<ConfTableExecutionDto> qryWe();
}
