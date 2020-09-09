package com.crossyf.dubbo.springtest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crossyf.dubbo.springtest.dto.ConfTableExecutionDto;
import com.crossyf.dubbo.springtest.entity.ConfTableExecution;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zuos
 * @since 2020-08-10
 */
public interface ConfTableExecutionMapper extends BaseMapper<ConfTableExecution> {
    List<ConfTableExecutionDto> qryTwo();

    List<ConfTableExecutionDto> qryWe();
}
