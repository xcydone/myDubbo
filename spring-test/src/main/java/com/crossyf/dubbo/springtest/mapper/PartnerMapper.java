package com.crossyf.dubbo.springtest.mapper;

import com.crossyf.dubbo.springtest.dto.PartnerDto;
import com.crossyf.dubbo.springtest.dto.PartnerQryDto;
import com.crossyf.dubbo.springtest.entity.Partner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zuos
 * @since 2020-09-08
 */
public interface PartnerMapper extends BaseMapper<Partner> {
    List<PartnerDto> findPartnerByParam(@Param("name") String name, @Param("levelName") String levelName);

    List<PartnerDto> findPartnerByEntity(PartnerQryDto qryDto);

    List<PartnerDto> findPartnerTwoOne(PartnerQryDto qryDto);

    void updatePartnerById(PartnerQryDto qryDto);

    List<PartnerDto> findPartnerInStatus(@Param("list") List status);
}
