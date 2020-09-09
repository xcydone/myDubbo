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

    /**
    * @Description:  查询全局map
    * @Param: [tableId]
    * @return: com.eshore.springbatch.entity.ConfTableExecution
    * @Author: caifang
    * @Date: 2020/8/11
    */
    ConfTableExecution getConfTableExecutionMap(Integer tableId);


    List<ConfTableExecutionDto> getConfTableExecutionTwo();

    List<ConfTableExecutionDto> getConfTableExecutionWe();

    void insertHH(ConfTableExecutionDto dto);

}
