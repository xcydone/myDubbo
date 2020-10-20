package com.crossyf.dubbo.springtest.service;

import com.crossyf.dubbo.springtest.dto.ConfTableExecutionDto;
import com.crossyf.dubbo.springtest.entity.ConfTableExecution;

import java.util.List;


public interface IConfTableExecutionService {

    ConfTableExecution getConfTableExecutionMap(Integer tableId);

    List<ConfTableExecutionDto> getConfTableExecutionTwo();

    List<ConfTableExecutionDto> getConfTableExecutionWe();

    void insertHH(ConfTableExecutionDto dto);

}
