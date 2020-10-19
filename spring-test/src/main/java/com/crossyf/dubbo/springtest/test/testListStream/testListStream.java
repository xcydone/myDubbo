package com.crossyf.dubbo.springtest.test.testListStream;

import java.util.*;
import java.util.stream.Collectors;


// strem不会修改原数组
public class  testListStream {
    public static void main(String[] args){

        testList();
    }

    public static void testList(){
        List<OrderDto> listOrder = new ArrayList<>();
        for(int i = 1; i < 4; i++){
            OrderDto order = new OrderDto();
            order.setOrderId(i);
            order.setOrderCode(UUID.randomUUID().toString().replaceAll("-",""));
            if(i%2 == 0){
                order.setWorkflowId(998);
            }else{
                order.setWorkflowId(996);
            }
            listOrder.add(order);
        }
        System.out.println("--------------------------原始数据-----------------------------");
        System.out.println(Arrays.asList(listOrder));

        // 将一个对象集合转化到另一个对象集合
        List<OrderDtoCopy> listCopy = listOrder.stream().map(e->new OrderDtoCopy(e.getOrderId(),e.getOrderCode())).collect(Collectors.toList());
        System.out.println();
        System.out.println("--------------------------OrderDto 到 OrderDtoCopy-----------------------------");
        System.out.println(Arrays.asList(listCopy));


        // 交集 差集
        List<Integer> list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = new ArrayList();
        list2.add(2);
        list2.add(3);
        list2.add(4);

        // 交集
        List<Integer> list3 = list1.stream().filter(list2::contains).collect(Collectors.toList());
        System.out.println();
        System.out.println("--------------------------交集-----------------------------");
        System.out.println(list3);

        // 差集 list1-list2
        List<Integer> list4 = list1.stream().filter(e -> !list2.contains(e)).collect(Collectors.toList());
        System.out.println();
        System.out.println("--------------------------差集 list1-list2-----------------------------");
        System.out.println(list4);

        // 差集 list2-list1
        List<Integer> list5 = list2.stream().filter(e -> e != 2).collect(Collectors.toList());
        System.out.println();
        System.out.println("--------------------------差集 list2-list1-----------------------------");
        System.out.println(list5);


        // 并集
        //使用并行流
        List<Integer> listAll = list1.parallelStream().collect(Collectors.toList());
        List<Integer> listAll2 = list2.parallelStream().collect(Collectors.toList());
        listAll.addAll(listAll2);
        System.out.println();
        System.out.println("--------------------------并集 list2+list1-----------------------------");
        System.out.println(listAll);
        System.out.println(list1);
        System.out.println(list2);

        // 去重并集
        List<Integer> listAllDistinct = listAll.stream().distinct().collect(Collectors.toList());
        System.out.println();
        System.out.println("--------------------------去重并集 list2+list1-----------------------------");
        System.out.println(listAllDistinct);

        /*// 直接并集-修改了原数组
        list1.addAll(list2);
        System.out.println(list1);
        System.out.println(list2);*/

        // 过滤一个元素
        OrderDto dto = listOrder.stream().filter(e-> e.getOrderId() == 1).findAny().get();
        System.out.println();
        System.out.println("--------------------------过滤元素-----------------------------");
        System.out.println(dto.toString());

        Map<Integer, String> mapOrderIdCode = new HashMap<>();
        mapOrderIdCode.put(33, UUID.randomUUID().toString());
        mapOrderIdCode.put(11, UUID.randomUUID().toString());
        mapOrderIdCode.put(22, UUID.randomUUID().toString());

        System.out.println();
        System.out.println("--------------------------原始map-----------------------------");
        System.out.println(mapOrderIdCode.toString());

        // Map集合转 List 不排序 排序 （key或者value排序）
        List<OrderDtoCopy> listMap0 = mapOrderIdCode.entrySet().stream()
                .map(e -> new OrderDtoCopy(e.getKey(), e.getValue())).collect(Collectors.toList());

        List<OrderDtoCopy> listMap1 = mapOrderIdCode.entrySet().stream().sorted(Comparator.comparing(e -> e.getKey()))
                .map(e -> new OrderDtoCopy(e.getKey(), e.getValue())).collect(Collectors.toList());

        List<OrderDtoCopy> listMap2 = mapOrderIdCode.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                .map(e -> new OrderDtoCopy(e.getKey(), e.getValue())).collect(Collectors.toList());

        List<OrderDtoCopy> listMap3 = mapOrderIdCode.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .map(e -> new OrderDtoCopy(e.getKey(), e.getValue())).collect(Collectors.toList());
        System.out.println();
        System.out.println("--------------------------Map集合转 List 不排序 排序 （key或者value排序）-----------------------------");
        System.out.println(listMap0);
        System.out.println(listMap1);
        System.out.println(listMap2);
        System.out.println(listMap3);

        // List集合转 Map
        // 其中Collectors.toMap方法的第三个参数为键值重复处理策略，如果不传入第三个参数，当有相同的键时，会抛出一个IlleageStateException。
        Map<Integer, String> mapOrderCode = listOrder.stream().collect(Collectors.toMap(orderDto -> orderDto.getOrderId(), orderDto -> orderDto.getOrderCode(), (orderDto1, orderDto2) -> orderDto1));
        Map<Integer, String> mapOrderCode2 = listOrder.stream().collect(Collectors.toMap(OrderDto::getOrderId, OrderDto::getOrderCode, (orderDto1, orderDto2) -> orderDto1));
        System.out.println();
        System.out.println("--------------------------List集合转 Map-----------------------------");
        System.out.println(mapOrderCode.toString());
        System.out.println(mapOrderCode2.toString());


        // groupingBy -- 按照某个字段进行分类
        Map<Integer,List<OrderDto>> workIdMap = listOrder.stream().collect(Collectors.groupingBy(OrderDto :: getWorkflowId));
        System.out.println();
        System.out.println("--------------------------groupingBy 按照某个字段进行分类-----------------------------");
        System.out.println(workIdMap.toString());


        // map -- 提取某个字段
        String strOrderCode = listOrder.stream().map(OrderDto::getOrderCode).collect(Collectors.joining(",","[","]"));
        System.out.println();
        System.out.println("--------------------------joining 提取某个字段-----------------------------");
        System.out.println(strOrderCode);

        List listOrderCode = listOrder.stream().map(OrderDto::getOrderCode).collect(Collectors.toList());
        System.out.println(listOrderCode);

//        Map<Integer, String> mapOrderCode = listOrder.stream().map(OrderDto::getOrderCode).collect(Collectors.toMap(OrderDto::hashCode,OrderDto::getOrderCode));



        Long countOrderCode = listOrder.stream().collect(Collectors.counting()); // 直接size就好了
        System.out.println();
        System.out.println("--------------------------counting 统计数量-----------------------------");
        System.out.println(countOrderCode);

        // filter -- 过滤
        List<OrderDto> listOrderFilter = listOrder.stream().filter(orderDto -> {return orderDto.getOrderId() > 2;}).collect(Collectors.toList());
        System.out.println(listOrderFilter);

        // reduce -- 累加器
        List hh = listOrder.stream().map(orderDto -> orderDto.getOrderId()).collect(Collectors.toList());

    }
}
