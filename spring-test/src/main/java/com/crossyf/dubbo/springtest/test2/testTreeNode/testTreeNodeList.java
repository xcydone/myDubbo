package com.crossyf.dubbo.springtest.test2.testTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class testTreeNodeList {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer sum = Integer.parseInt(scanner.nextLine());
        String strNode = scanner.nextLine();

        computePath(strNode, sum);

        scanner.close();
    }

    /**
     * 存放路径上的值 10,5,12,4,7
     * @param strNode
     * @param sum
     */
    public static void computePath(String strNode, Integer sum){
        if(Objects.isNull(strNode) || "".equals(strNode.trim())){
            System.out.println("检查输入的字符串");
            return;
        }

        String[] arr = strNode.split(",");

        Integer len = arr.length;
        List<Node> nodeList = new ArrayList<>();

        for(int i = 0; i < len; i++){
            Node node = new Node(Integer.parseInt(arr[i]));
            nodeList.add(node);
        }

        //构建二叉树
        Node rootNode = nodeList.get(0);
        for(int i = 0; i < len; i++){ // 0-1 2  1-3 4
            if(i * 2 + 1 < len){
                nodeList.get(i).left = nodeList.get(i * 2 + 1);
            }
            if(i * 2 + 2 < len){
                nodeList.get(i).right = nodeList.get(i * 2 + 2);
            }
        }

        // 函数实现
        printPaths(rootNode, null, sum);
    }

    /**
     *
     * @param rootNode
     * @param listPath
     * @param sum
     */
    public static void printPaths(Node rootNode, List<Integer> listPath, Integer sum) {

        // 每次的路径需要记录传给下一个
        if (rootNode == null) return;

        List<Integer> pathNew = new ArrayList<>();;
        if(!Objects.isNull(listPath) && listPath.size() != 0){
            pathNew.addAll(listPath);
        }
        pathNew.add(rootNode.value);

        if(Objects.isNull(rootNode.left) && Objects.isNull(rootNode.right)){
            printArray(pathNew, sum);
        }else{
            printPaths(rootNode.left, pathNew, sum);
            printPaths(rootNode.right, pathNew, sum);
        }
    }

    public static void printArray(List<Integer> listPath, Integer sum) {
        Integer total = listPath.stream().reduce((o1,o2)->o1+o2).get();
        if (total == sum) {
            String pathStr = listPath.stream().map(o->String.valueOf(o)).collect(Collectors.joining(","));
            System.out.println(pathStr);
        }
    }
}
