package com.eshore.springbatch.constant;

import com.eshore.springbatch.entity.ConfTableExecution;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BatchGlobal {
    public static Map<Integer, ConfTableExecution> BATCH_CONF_TABLE_EXECUTION_MAP = new HashMap();
    public static String SELECT = "select";
    public static String FROM = "from";
    public static String WHERE = "where";
    public static String ORDER = "order";
    public static String GROUP = "group";
    public static String BY = "by";
    public static String ORDER_ASC = "asc";

    public static int VALID_STATUS = 1;
    public static int INVALID_STATUS = 0;

    public static String DEFAULT_TXT = "txt";

}
