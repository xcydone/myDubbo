package com.crossyf.dubbo.springtest.test.testEnum;

import com.crossyf.dubbo.springtest.constant.Day;

import java.util.Arrays;

public class testEnum {
    public static void main(String[] args){
        singleEnumFun();

    }

    public static void singleEnumFun(){
        //枚举顺序值
        Day day = Day.TUESDAY;
        System.out.println(day.ordinal()); // 1

        Day[] days = new Day[]{Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY,
                Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};

        for(int i = 0; i < days.length; i++){
            System.out.println("day[" + i + "]=" + days[i].name() + " ordinal():"+days[i].ordinal());
        }

        System.out.println("-------------------------------------");

        System.out.println(day.equals(Day.TUESDAY)); // true
        System.out.println("TUESDAY".equals(day.name())); // true
        System.out.println("TUESDAY".equals(day.toString())); // true name和toString相似返回字符串

        System.out.println("-------------------------------------");

        // 比较的是ordinal值 self.ordinal - other.ordinal
        System.out.println(day.compareTo(Day.MONDAY)); // 1 返回当前索引减去待比较的枚举对象的顺序
        System.out.println(day.compareTo(Day.TUESDAY)); // 0
        System.out.println(day.compareTo(Day.WEDNESDAY));  // -1
        System.out.println(day.compareTo(Day.THURSDAY));  // -2

        System.out.println("-------------------------------------");

        System.out.println(day.getDeclaringClass()); // class com.crossyf.dubbo.springtest.constant.Day

        System.out.println("-------------------------------------");
        // 返回带指定名称的指定枚举类型的枚举常量。
        Day d=Enum.valueOf(Day.class,days[0].name());
        Day d2=Day.valueOf(Day.class,days[0].name());
        System.out.println("d:"+d);
        System.out.println("d2:"+d2);

        System.out.println("-------------------------------------");

        Day[] days2 = Day.values();
        System.out.println("day2:"+ Arrays.toString(days2));
        Day day1 = Day.valueOf("MONDAY"); // 获取枚举对象
        System.out.println("day:"+day1);
    }
}

