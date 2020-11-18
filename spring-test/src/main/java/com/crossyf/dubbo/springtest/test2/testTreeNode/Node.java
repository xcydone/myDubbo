package com.crossyf.dubbo.springtest.test2.testTreeNode;

import lombok.Data;

@Data
public class Node {
    public Integer value;
    public Node left;
    public Node right;

    public Node(Integer value) {
        this.value = value;
    }
}
