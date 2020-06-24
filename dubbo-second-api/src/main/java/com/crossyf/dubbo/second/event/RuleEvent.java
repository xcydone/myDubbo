package com.crossyf.dubbo.second.event;

import com.crossyf.dubbo.common.zookeeper.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RuleEvent extends Event {
    private Integer adtId;
}
