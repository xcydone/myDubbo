package com.crossyf.dubbo.springtest.service;

import com.crossyf.dubbo.springtest.dto.ConfTableExecutionDto;
import com.crossyf.dubbo.springtest.entity.ConfTableExecution;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zuos
 * @since 2020-08-10
 */
public interface IConfTableExecutionService {

    ConfTableExecution getConfTableExecutionMap(Integer tableId);

    List<ConfTableExecutionDto> getConfTableExecutionTwo();

    List<ConfTableExecutionDto> getConfTableExecutionWe();

    void insertHH(ConfTableExecutionDto dto);

}
