package com.eshore.springbatch.service;

import com.eshore.springbatch.entity.ConfTableExecution;

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

}
