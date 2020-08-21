package com.crossyf.dubbo.springtest.test.testBigDecimal;

import java.math.BigDecimal;

public class testBigDecimalType {
    public static void main(String[] args){
        Double dd = 1055642.41;  // 1.0556424099999999E8 精度丢失
        System.out.println(dd * 100.0);

        Long dd2 = BigDecimal.valueOf(dd).multiply(BigDecimal.valueOf(100)).longValue();
        System.out.println(dd2);

        Long currencyAmount = 105564249L; // 四舍五入保留一位小数，保留2位小数
        BigDecimal ddd1 = BigDecimal.valueOf(currencyAmount).divide(BigDecimal.valueOf(100)).setScale(1, BigDecimal.ROUND_HALF_UP);
        BigDecimal ddd2 = BigDecimal.valueOf(currencyAmount).divide(BigDecimal.valueOf(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(ddd1.toString());
        System.out.println(ddd2.toString());

        String prefix = "JT_BSS_SURE_\\d{8}_\\d{3}.NOR.{yyyymm}";
        String month = "202008";
        String hh = prefix.replace("{yyyymm}", month);
        System.out.println(prefix);
        System.out.println(hh);
    }
}
