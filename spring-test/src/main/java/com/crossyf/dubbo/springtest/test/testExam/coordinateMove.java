package com.crossyf.dubbo.springtest.test.testExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class coordinateMove {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            String strSource = scanner.nextLine();
            String[] sourceList = strSource.split(";");
            List<String> list = new ArrayList<>();
            for(String str: sourceList){
                /*String regex = "^(A|S|D|W)[0-9]{1,2}$";*/
                String regex = "^[ASDW][0-9]{1,2}$";
                if(str.matches(regex)){
                    list.add(str);
                }
            }

            // System.out.println(list);

            int x = 0;
            int y = 0;
            for (String str: list){
                switch (str.charAt(0)){
                    case 'A': x -= Integer.parseInt(str.substring(1)); break;
                    case 'D': x += Integer.parseInt(str.substring(1)); break;
                    case 'S': y -= Integer.parseInt(str.substring(1)); break;
                    case 'W': y += Integer.parseInt(str.substring(1)); break;
                }
            }

            System.out.println(x + "," + y);
        }
    }

}
