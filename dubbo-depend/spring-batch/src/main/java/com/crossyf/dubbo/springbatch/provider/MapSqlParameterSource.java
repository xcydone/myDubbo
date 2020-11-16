package com.crossyf.dubbo.springbatch.provider;

import org.springframework.beans.NotReadablePropertyException;
import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MapSqlParameterSource extends AbstractSqlParameterSource {
    private HashMap hashMap;

    @Nullable
    private String[] propertyNames;

    public MapSqlParameterSource(Object object) {
        this.hashMap = (HashMap<String,Object>) object;
    }

    public boolean hasValue(String paramName) {
        return this.hashMap.get(paramName) == null ? false: true;
    }

    @Nullable
    public Object getValue(String paramName) throws IllegalArgumentException {
        try {
            return this.hashMap.get(paramName);
        } catch (NotReadablePropertyException var3) {
            throw new IllegalArgumentException(var3.getMessage());
        }
    }

    public int getSqlType(String paramName) {
        return 12;
    }

    @Nullable
    public String[] getParameterNames() {
        return this.getReadablePropertyNames();
    }

    public String[] getReadablePropertyNames() {
        List<String> names = new ArrayList();
        Set props = this.hashMap.keySet();

        return (String[])props.toArray();
    }
}
