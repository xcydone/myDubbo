package com.crossyf.dubbo.springtest.mapper;

import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.crossyf.dubbo.springtest.dto.PartnerDto;
import com.crossyf.dubbo.springtest.dto.PartnerQryDto;
import com.crossyf.dubbo.springtest.entity.Partner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PartnerMapper extends BaseMapper<Partner> {
    List<PartnerDto> findPartnerByParam(@Param("name") String name, @Param("levelName") String levelName);

    List<PartnerDto> findPartnerByEntity(PartnerQryDto qryDto);

    List<PartnerDto> findPartnerTwoOne(PartnerQryDto qryDto);

    void updatePartnerById(PartnerQryDto qryDto);

    List<PartnerDto> findPartnerInStatus(@Param("list") List status);

    @Lang(MybatisXMLLanguageDriver.class)
    @Select("SELECT * FROM partner where name_20 like concat('%',#{name},'%') and level_heHis like concat('%',#{levelName},'%')")
    List<PartnerDto> findPartnerByLang(@Param("name") String name, @Param("levelName") String levelName);


    // <script>可实现复杂的收sql语句
    @Lang(MybatisXMLLanguageDriver.class)
    @Select("<script>SELECT * FROM partner where name_20 like concat('%',#{name},'%') and level_heHis like concat('%',#{levelName},'%')</script>")
    List<PartnerDto> findPartnerByScript(@Param("name") String name, @Param("levelName") String levelName);


    Map<String, Object> findPartnerMap(@Param("id") int id);

    List<PartnerDto> findPartnerStatus(@Param("status") int status);

    Map selectDTableId(@Param("id") int id);

}
