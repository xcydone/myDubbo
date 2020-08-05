package com.crossyf.dubbo.first.api;

import com.crossyf.dubbo.first.dto.PersonDto;
import com.crossyf.dubbo.first.dto.PersonQryDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IFirstApi {
    String sayFirst(String name);

    List<PersonDto> findOne(String id, String userName);

    PersonDto findPersonById(String id);

    List<PersonDto> findPersonByIds(List<String> listIds);

    List<PersonDto> findPersonListBySex(String sex, String status, List<String> fathers);

    List<PersonDto> findPersonListByMap(PersonQryDto personQryDto);

    void inserPerson(PersonDto dto);

    void inserBatchPerson(List<PersonDto> personDtos);

    void deletePerson(PersonQryDto personQryDto);

}
