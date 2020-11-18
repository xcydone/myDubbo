package com.crossyf.dubbo.springtest.test2.testTreeNodeAgain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Node implements Serializable {
    private static final long serialVersionUID = 3084346286501026486L;

    private Integer value;
    private Node leftNode;
    private Node rightNode;

    public Node(Integer value) {
        this.value = value;
    }
}
