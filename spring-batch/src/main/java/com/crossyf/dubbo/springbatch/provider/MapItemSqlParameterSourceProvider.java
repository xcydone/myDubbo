package com.crossyf.dubbo.springbatch.provider;

import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class MapItemSqlParameterSourceProvider<T> implements ItemSqlParameterSourceProvider<T> {

    public MapItemSqlParameterSourceProvider() {
    }

    @Override
    public SqlParameterSource createSqlParameterSource(T item) {
        return new MapSqlParameterSource(item);
    }

}
