package com.crossyf.dubbo.springtest.utils;

import java.util.List;

public class ListUtils {

    private ListUtils() {
    }

    /**
     * 检查List是否为空
     *
     * @param list 待检查List
     * @return true/false
     */
    public static boolean isEmpty(List list) {
        boolean isNull = (list == null || list.isEmpty());

        if (isNull) {
            return true;
        }

        for (Object object : list) {
            isNull = isNull || object == null;
        }

        return isNull;
    }
}
