package com.crossyf.dubbo.springtest.test2.testTreeNodeAgain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class testNode {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer sum = Integer.parseInt(scanner.nextLine());
        String treeStr = scanner.nextLine();

        scanPath(treeStr, sum);
        scanner.close();
    }

    /**
     * 比较路径和路径上的值之和
     * @param treeStr
     * @param sum
     */
    public  static void scanPath(String treeStr, Integer sum){
        // 判空
        if(Objects.isNull(treeStr) || "".equals(treeStr.trim())){
            return;
        }

        // 存放节点
        String[] treeArr = treeStr.split(",");
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < treeArr.length; i++) {
            Node node = new Node(Integer.parseInt(treeArr[i]));
            nodeList.add(node);
        }

        // 关联节点
        Integer size = nodeList.size();
        for (int i = 0; i < size; i++) {
            // 0-1,2  1-3,4 2-5,6
            if(i*2+1 < size){
                nodeList.get(i).setLeftNode(nodeList.get(i*2+1));
            }

            if(i*2+2 < size){
                nodeList.get(i).setRightNode(nodeList.get(i*2+2));
            }
        }

        getPath(nodeList.get(0), null, sum);

    }


    /**
     * 获取所有的路径
     * @param firstNode
     * @param path
     * @param sum
     */
    public static void getPath(Node firstNode, List<Integer> path, Integer sum){
        // 判空
        if(Objects.isNull(firstNode)){
            return;
        }

        // 路径记录
        List<Integer> pathNew = new ArrayList<>();
        if(!Objects.isNull(path) && path.size() != 0){
            pathNew.addAll(path);
        }
        pathNew.add(firstNode.getValue());

        if(Objects.isNull(firstNode.getLeftNode()) && Objects.isNull(firstNode.getRightNode())){
            // 子节点-- 循环结束--打印
            printPath(pathNew, sum);
        }else{
            // 非子节点
            getPath(firstNode.getLeftNode(), pathNew, sum);
            getPath(firstNode.getRightNode(), pathNew, sum);
        }
    }

    /**
     * 打印路径
     * @param pathNew
     * @param sum
     */
    public static void printPath(List<Integer> pathNew, Integer sum){
        // 判空
        if(Objects.isNull(pathNew) || pathNew.size() == 0) {
            System.out.println("没有路径");
            return;
        }

        Integer sumPath = pathNew.stream().reduce((o1, o2) -> o1 + o2).get();
        if(sum == sumPath){
            String str = pathNew.stream().map(o->String.valueOf(o)).collect(Collectors.joining("->"));
            System.out.println(str);
        }
    }

}
