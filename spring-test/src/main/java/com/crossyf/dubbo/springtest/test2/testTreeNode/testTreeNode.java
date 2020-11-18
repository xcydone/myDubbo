package com.crossyf.dubbo.springtest.test2.testTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testTreeNode {
    public static int counter = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            Integer sum = scanner.nextInt();
            String strNode = scanner.nextLine();
            computePath(strNode, sum);
        }
    }

    /**
     * 计算路径上的值 10,5,12,4,7
     * @param strNode
     * @param sum
     */
    public static void computePath(String strNode, Integer sum){
        String[] arr = strNode.split(",");

        Integer len = arr.length;
        Node[] nodeArr = new Node[len + 1];

        for(int i = 0; i < len; i++){
            int val = Integer.valueOf(arr[i]);
            nodeArr[i + 1] = new Node(val);
        }

        //构建二叉树
        Node root = nodeArr[1];
        for(int i = 1; i < len + 1; i++){
            if(i * 2 < len + 1){
                nodeArr[i].left = nodeArr[2 * i];
            }

            if(i * 2 + 1 < len + 1){
                nodeArr[i].right = nodeArr[2 * i + 1];
            }
        }

        // 函数实现
        printPaths(root, len, sum);
    }

    // 10,5,12,4,7
    public static void printPaths(Node root, Integer len, Integer sum){
        int[] path = new int[len];
        printPaths(root, path,0, sum);
        if (counter == 0) {
            System.out.println("error");
        }
    }

    public static void printPaths(Node root, int[] path, int pathlen, int N) {
        if (root == null) return;
        path[pathlen++] = root.value;
        if (root.right == null && root.left == null ) {
            printArray(path,pathlen,N);
        } else {
            printPaths(root.left,path,pathlen,N);
            printPaths(root.right,path,pathlen,N);
        }
    }

    public static void printArray(int[] path,int len,int N) {
        int total = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(path[i] + ",");
            total += path[i];
        }
        if (total == N) {
            System.out.print(sb.toString().substring(0,sb.toString().length() -1)); // 去除逗号
            counter++;
        }
    }
}
