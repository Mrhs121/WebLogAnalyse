package com.hs.webloganalyse.mr.analyse;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 独立ip分析
 * @author 黄晟
 *
 */
public class StandIPMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	 private Text url = new Text();
     private Text ip = new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String[] aline = value.toString().split(" ");
		url.set(aline[3]);
		ip.set(aline[0]);
		context.write(url, ip);
	}
}
