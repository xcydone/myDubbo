package com.crossyf.dubbo.common.hbase;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.hbase.mapreduce.TableReducer;

import java.io.IOException;

public class HDFSToHbaseReducer extends TableReducer<Text, NullWritable, NullWritable>{

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        //\t切分每行数据
        String[] splits=key.toString().split(",");

        //初始化put对象
        Put put=new Put(splits[0].getBytes());
        put.addColumn("info".getBytes(),"name".getBytes(),splits[1].getBytes());
        put.addColumn("info".getBytes(),"country".getBytes(),splits[2].getBytes());
        put.addColumn("info".getBytes(), "age".getBytes(), splits[3].getBytes());
        put.addColumn("info".getBytes(), "department".getBytes(), splits[4].getBytes());

        context.write(NullWritable.get(), put);
    }
}
