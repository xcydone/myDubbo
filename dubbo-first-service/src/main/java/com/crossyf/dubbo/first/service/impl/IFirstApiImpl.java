package com.crossyf.dubbo.first.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.crossyf.dubbo.first.api.IFirstApi;
import com.crossyf.dubbo.first.dto.PersonDto;
import com.crossyf.dubbo.first.dto.PersonQryDto;
import com.crossyf.dubbo.first.entity.Person;
import com.crossyf.dubbo.first.mapper.PersonMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@DubboService
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

    @Override
    public PersonDto findPersonById(String id) {
        Person pn = personMapper.selectById(id);
        PersonDto dto = new PersonDto();
        BeanUtils.copyProperties(pn, dto);
        return dto;
    }

    @Override
    public List<PersonDto> findPersonByIds(List<String> listIds){
        List<Person> personList = personMapper.selectBatchIds(listIds);
        List<PersonDto> resultList = personList.stream().map(p -> {
            PersonDto pn = new PersonDto();
            BeanUtils.copyProperties(p,pn);
            return pn;
        }).collect(Collectors.toList());

        return resultList;
    }

    @Override
    public List<PersonDto> findPersonListBySex(String sex, String status, List<String> fathers){
        LambdaQueryWrapper<Person> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Person::getSex, sex);
        wrapper.eq(Person::getStatus, status);
        wrapper.in(Person::getFather, fathers);
        List<Person> personList = personMapper.selectList(wrapper);
        List<PersonDto> resultList = personList.stream().map(p -> {
            PersonDto pn = new PersonDto();
            BeanUtils.copyProperties(p,pn);
            return pn;
        }).collect(Collectors.toList());

        return resultList;
    }

    @Override
    public List<PersonDto> findPersonListByMap(PersonQryDto personQryDto) {
        return null;
    }

    @Override
    public void inserPerson(PersonDto dto) {
        Person pn = new Person();
        BeanUtils.copyProperties(dto, pn);
        personMapper.insert(pn);
    }

    @Override
    public void inserBatchPerson(List<PersonDto> personDtos) {
        Date date = new Date();
        List<Person> persons = personDtos.stream().map(p -> {
            Person pn = new Person();
            BeanUtils.copyProperties(p, pn);
            pn.setId(UUID.randomUUID().toString());
            pn.setStatus("1000");
            pn.setCreateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate());
            return pn;
        }).collect(Collectors.toList());
        personMapper.insertBatchPerson(persons);
    }

    @Override
    public void deletePerson(PersonQryDto personQryDto) {
        personMapper.deleteById(personQryDto.getId());
    }
}
