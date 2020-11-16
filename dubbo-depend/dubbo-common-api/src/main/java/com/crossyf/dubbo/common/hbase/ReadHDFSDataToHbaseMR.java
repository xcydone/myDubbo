package com.crossyf.dubbo.common.hbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;

@Slf4j
public class ReadHDFSDataToHbaseMR extends Configured implements Tool{
    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = this.getConf();
        log.info("HBase使用conf:"+conf);
        FileSystem fs = FileSystem.get(conf);

        // 创建job任务
        Job job = Job.getInstance(conf);
        job.setJarByClass(ReadHDFSDataToHbaseMR.class);

        //设置Mapper
        job.setMapperClass(HDFSToHbaseMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        //设置Reducer
        TableMapReduceUtil.initTableReducerJob("myStudent", HDFSToHbaseReducer.class, job,null,null,null,null,false);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Put.class);

        Path inputPath = new Path("file:///student/input/");
        Path outputPath = new Path("file:///student/output/");

        if(fs.exists(outputPath)) {
            fs.delete(outputPath,true);
        }

        FileInputFormat.addInputPath(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);

        boolean isDone = job.waitForCompletion(true);

        return isDone ? 0 : 1;
    }
}
