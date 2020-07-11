package com.crossyf.dubbo.first.service.impl;

import com.crossyf.dubbo.first.api.IFirstApi;
import com.crossyf.dubbo.first.dto.PersonDto;
import com.crossyf.dubbo.first.entity.Person;
import com.crossyf.dubbo.first.mapper.PersonMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class IFirstApiImpl implements IFirstApi {
    @Autowired
    PersonMapper personMapper;

    @Override
    public String sayFirst(String name) {
        return "First," + name;
    }

    @Override
    public List<PersonDto> findOne(String id, String userName) {
        List<PersonDto> resultList = new ArrayList<>();
        List<Person> personList = personMapper.findOne(id, userName);
        for( int i = 0; i < personList.size(); i++){
            PersonDto dto = new PersonDto();
            BeanUtils.copyProperties(personList.get(i), dto);
            resultList.add(dto);
        }
        return resultList;
    }
}
