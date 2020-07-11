package com.crossyf.dubbo.first.api;

import com.crossyf.dubbo.first.dto.PersonDto;

import java.util.List;

public interface IFirstApi {
    String sayFirst(String name);

    List<PersonDto> findOne(String id, String userName);
}
