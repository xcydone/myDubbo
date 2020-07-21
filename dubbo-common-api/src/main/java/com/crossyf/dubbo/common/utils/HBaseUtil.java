package com.crossyf.dubbo.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
* @Description:   habse类
* @Author: caifang
* @Date: 2020/7/17
*/

@Slf4j
public class HBaseUtil {

    @Value("${hbase.connectKey}")
    private String connectKey;

    @Value("${hbase.connectValue}")
    private String connectValue;

    @Value("${hbase.nameSpace}")
    private String nameSpace;

    @Value("${hbase.defaultPath}")
    private String defaultPath;

    private Configuration conf;
    private Connection conn;
    private Admin admin;

    /**
    * @Description:  获取连接 获取管理员对象
    * @Param: []
    * @return: org.apache.hadoop.hbase.client.Connection
    * @Author: caifang
    * @Date: 2020/7/17
    */
    @PostConstruct
    public void getConnection() {
        // 创建一个可以用来管理hbase配置信息的conf对象
        Configuration conf = HBaseConfiguration.create();

        // 设置当前的程序去寻找的hbase在哪里
        /*conf.set(nameSpace, defaultPath);
        conf.set(connectKey, connectValue);*/
        conf.set("fs.defaultFS", "hdfs://myha01/hbase224");
        conf.set("hbase.zookeeper.quorum", "xcydtwo:2181,xcydthree:2181,xcydfour:2181,xcydfive:2181");
        /*conf.set("hbase.zookeeper.property.clientPort", "21810");*/
        try {
            conn = ConnectionFactory.createConnection(conf);
            admin = conn.getAdmin();
            log.info("HBase启动");
            log.info("HBase启动conf:"+conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * @Description: 创建表
    * @Param: [tableName-表名, family-列族的数组]
    * @return: void
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public void createTable(String tableName, String[] family) throws Exception {
        // 转换表名类型
        TableName name = TableName.valueOf(tableName);

        //判断表是否存在
        if(admin.tableExists(name)) {
            System.out.println("table已经存在！");
        }else{
            Collection<ColumnFamilyDescriptor> collections = createFamily(family);

            TableDescriptor td = TableDescriptorBuilder
                    .newBuilder(name)
                    .setColumnFamilies(collections)
                    .setCompactionEnabled(true)
                    .setMaxFileSize(1024 * 1024 * 1024)
                    .setValue("COMPRESSION","SNAPPY")
                    .setValue("hbase.hstore.engine.class","org.apache.hadoop.hbase.regionserver.DateTieredStoreEngine")
                    .setValue("hbase.hstore.blockingStoreFiles","60")
                    .setValue("hbase.hstore.compaction.min","2")
                    .setValue("hbase.hstore.compaction.max","60")
                    .setValue("BLOCKSIZE","65536").build();

            admin.createTable(td);

            //判断表是否创建成功
            if(admin.tableExists(name)) {
                System.out.println("table创建成功");
            }else {
                System.out.println("table创建失败");
            }
        }
    }

    /**
    * @Description:  创建列族
    * @Param: [family]
    * @return: java.util.Collection<org.apache.hadoop.hbase.client.ColumnFamilyDescriptor>
    * @Author: caifang
    * @Date: 2020/7/18
    */
    public Collection<ColumnFamilyDescriptor> createFamily(String[] family){
        Collection<ColumnFamilyDescriptor> collections = new ArrayList<>();
        for(String str : family){
            ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(str.getBytes())
                    .setCompressTags(true)
                    .setInMemoryCompaction(MemoryCompactionPolicy.ADAPTIVE)
                    .setMaxVersions(6)
                    .setMinVersions(2)
                    .setTimeToLive(60 * 60 * 24 * 7)
                    .setValue("COMPRESSION","SNAPPY")
                    .setValue("hbase.hstore.engine.class","org.apache.hadoop.hbase.regionserver.DateTieredStoreEngine")
                    .setValue("hbase.hstore.blockingStoreFiles","60")
                    .setValue("hbase.hstore.compaction.min","2")
                    .setValue("hbase.hstore.compaction.max","60")
                    .setCompactionCompressionType(Compression.Algorithm.SNAPPY)
                    .build();

            collections.add(columnFamilyDescriptor);
        }
        return collections;
    }

    /**
    * @Description:  查询所有表名
    * @Param: []
    * @return: void
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public TableName[] getAllTables() throws Exception {
        // 获取表名一
        TableName[] tableList = admin.listTableNames();
        /*System.out.println(Arrays.asList(tableList));

        //获取列簇的描述信息
        List<TableDescriptor>  tableDescriptors= admin.listTableDescriptors();
        for (TableDescriptor tableName : tableDescriptors) {
            // 获取表名二
            TableName name = tableName.getTableName();
            System.out.println("tableName: " + name);

            // 获取列族
            ColumnFamilyDescriptor[] columnFamilys = tableName.getColumnFamilies();
            for( ColumnFamilyDescriptor columnFamily: columnFamilys){
                String columnFamilyname= columnFamily.getNameAsString();
                System.out.println("columnFamilyname: " + columnFamilyname);
            }
            System.out.println();
        }*/

        return tableList;
    }

    /**
    * @Description:  查看表的列簇属性
    * @Param: [tableName]
    * @return: void
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public void descTable(String tableName) throws Exception {
        TableName name = TableName.valueOf(tableName);

        //判断表是否存在
        if(admin.tableExists(name)) {
            //获取表中列簇的描述信息
            TableDescriptor tableDescriptor = admin.getDescriptor(name);

            //获取列簇中列的信息
            ColumnFamilyDescriptor[] columnFamilies = tableDescriptor.getColumnFamilies();
            for(ColumnFamilyDescriptor columnFamily : columnFamilies) {
                System.out.println(columnFamily);
            }
        }else {
            System.out.println("table不存在");
        }
    }

    /**
    * @Description:  判断表是否存在
    * @Param: [tableName]
    * @return: boolean
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public boolean existTable(String tableName) throws Exception {
        TableName name = TableName.valueOf(tableName);
        return admin.tableExists(name);
    }

    /**
    * @Description:  disable表
    * @Param: [tableName]
    * @return: void
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public void disableTable(String tableName) throws Exception {
        TableName name = TableName.valueOf(tableName);
        if(admin.tableExists(name)) {
            if(admin.isTableEnabled(name)) {
                admin.disableTable(name);
            }else {
                System.out.println("table不是活动状态");
            }
        }else {
            System.out.println("table不存在");
        }
    }

    /**
    * @Description:  drop表
    * @Param: [tableName]
    * @return: void
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public void dropTable(String tableName) throws Exception {
        TableName name = TableName.valueOf(tableName);

        if(admin.tableExists(name)) {
            boolean tableEnabled = admin.isTableEnabled(name);
            if(tableEnabled) {
                admin.disableTable(name);
            }

            admin.deleteTable(name);
            if(admin.tableExists(name)) {
                System.out.println("删除失败");
            }else {
                System.out.println("删除成功");
            }
        }else {
            System.out.println("table不存在");
        }
    }

    /**
    * @Description:  添加数据
    * @Param: [tableName, rowKey, familyName, columnName, value]
    * @return: void
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public void putData(String tableName, String rowKey, String familyName, String columnName, String value) throws Exception {
        TableName name = TableName.valueOf(tableName);
        //添加数据之前先判断表是否存在，不存在的话先创建表
        if(!admin.tableExists(name)) {
            String[] familyNames = {familyName};
            createTable(tableName, familyNames);
        }

        Table table = conn.getTable(name);
        Put put = new Put(rowKey.getBytes());
        put.addColumn(familyName.getBytes(), columnName.getBytes(), value.getBytes());
        table.put(put);
    }

    /**
    * @Description:  添加数据-带时间戳
    * @Param: [tableName, rowKey, familyName, columnName, value, timestamp]
    * @return: void
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public void putData(String tableName, String rowKey, String familyName, String columnName, String value, long timestamp) throws Exception {
        TableName name = TableName.valueOf(tableName);
        if (!admin.tableExists(name)) {
            String[] familyNames = {familyName};
            createTable(tableName, familyNames);
        }

        Table table = conn.getTable(name);
        Put put = new Put(rowKey.getBytes());
        put.addColumn(familyName.getBytes(), columnName.getBytes(), timestamp, value.getBytes());
        table.put(put);
    }

    /**
    * @Description:  根据rowkey查询数据
    * @Param: [tableName, rowKey]
    * @return: org.apache.hadoop.hbase.client.Result
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public Result getResult(String tableName, String rowKey) throws Exception {
        Result result;

        TableName name = TableName.valueOf(tableName);
        if(admin.tableExists(name)) {
            Table table = conn.getTable(name);
            Get get = new Get(rowKey.getBytes());
            result = table.get(get);
        }else {
            result = null;
        }

        return result;
    }

    /**
    * @Description:  根据rowkey查询数据-familyName
    * @Param: [tableName, rowKey, familyName]
    * @return: org.apache.hadoop.hbase.client.Result
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public Result getResult(String tableName, String rowKey, String familyName) throws Exception {
        Result result;
        TableName name = TableName.valueOf(tableName);
        if(admin.tableExists(name)) {
            Table table = conn.getTable(name);
            Get get = new Get(rowKey.getBytes());
            get.addFamily(familyName.getBytes());
            result = table.get(get);
        }else {
            result = null;
        }

        return result;
    }

    /**
    * @Description:  根据rowkey查询数据-familyName columnName
    * @Param: [tableName, rowKey, familyName, columnName]
    * @return: org.apache.hadoop.hbase.client.Result
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public Result getResult(String tableName, String rowKey, String familyName, String columnName) throws Exception {
        Result result;
        TableName name = TableName.valueOf(tableName);
        if(admin.tableExists(name)) {
            Table table = conn.getTable(name);
            Get get = new Get(rowKey.getBytes());
            get.addColumn(familyName.getBytes(), columnName.getBytes());
            result = table.get(get);
        }else {
            result = null;
        }

        return result;
    }

    /**
    * @Description:  查询指定version的数据
    * @Param: [tableName, rowKey, familyName, columnName, versions]
    * @return: org.apache.hadoop.hbase.client.Result
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public Result getResultByVersion(String tableName, String rowKey, String familyName, String columnName, int versions) throws Exception {
        Result result;
        TableName name = TableName.valueOf(tableName);
        if(admin.tableExists(name)) {
            Table table = conn.getTable(name);
            Get get = new Get(rowKey.getBytes());
            get.addColumn(familyName.getBytes(), columnName.getBytes());
            get.setMaxVersions(versions);
            result = table.get(get);
        }else {
            result = null;
        }

        return result;
    }

    /**
    * @Description:  scan全表数据
    * @Param: [tableName]
    * @return: org.apache.hadoop.hbase.client.ResultScanner
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public ResultScanner getResultScann(String tableName) throws Exception {
        ResultScanner result;
        TableName name = TableName.valueOf(tableName);
        if(admin.tableExists(name)) {
            Table table = conn.getTable(name);
            Scan scan = new Scan();
            result = table.getScanner(scan);
        }else {
            result = null;
        }

        return result;
    }

    /**
    * @Description:  删除数据（指定的行）
    * @Param: [tableName, rowKey]
    * @return: void
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public void deleteColumn(String tableName, String rowKey) throws Exception {
        TableName name = TableName.valueOf(tableName);
        if(admin.tableExists(name)) {
            Table table = conn.getTable(name);
            Delete delete = new Delete(rowKey.getBytes());
            table.delete(delete);
        } else {
            System.out.println("table不存在");
        }
    }

    /**
    * @Description:  删除数据（指定的列）
    * @Param: [tableName, rowKey, falilyName]
    * @return: void
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public void deleteColumn(String tableName, String rowKey, String falilyName) throws Exception {
        TableName name = TableName.valueOf(tableName);
        if(admin.tableExists(name)) {
            Table table = conn.getTable(name);
            Delete delete = new Delete(rowKey.getBytes());
            delete.addFamily(falilyName.getBytes());
            table.delete(delete);
        }else {
            System.out.println("table不存在");
        }
    }

    /**
    * @Description:  删除数据（指定的列） columnName
    * @Param: [tableName, rowKey, falilyName, columnName]
    * @return: void
    * @Author: caifang
    * @Date: 2020/7/17
    */
    public void deleteColumn(String tableName, String rowKey, String falilyName, String columnName) throws Exception {
        TableName name = TableName.valueOf(tableName);
        if(admin.tableExists(name)) {
            Table table = conn.getTable(name);
            Delete delete = new Delete(rowKey.getBytes());
            delete.addColumn(falilyName.getBytes(), columnName.getBytes());
            table.delete(delete);
        }else {
            System.out.println("table不存在");
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            Thread.sleep(1000L);
            conn.close();
            log.info("HBase停止");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getConnectKey() {
        return connectKey;
    }

    public void setConnectKey(String connectKey) {
        this.connectKey = connectKey;
    }

    public String getConnectValue() {
        return connectValue;
    }

    public void setConnectValue(String connectValue) {
        this.connectValue = connectValue;
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
