package com.crossyf.dubbo.first.item;

import com.crossyf.dubbo.common.utils.SpringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemContent {
    private Map<Integer, Class<IOrderItemHandle>> handleMap;

    public IOrderItemHandle getInstance(Integer operType) throws Exception{
        Class<IOrderItemHandle> cal = handleMap.get(operType);
        if(cal == null){
            throw new Exception("参数错误");
        }
        return SpringUtil.getBean(cal);
    }
}
