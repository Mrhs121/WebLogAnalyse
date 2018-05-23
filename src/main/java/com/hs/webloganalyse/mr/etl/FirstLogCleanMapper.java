package com.hs.webloganalyse.mr.etl;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.hs.webloganalyse.bean.WebLogBean;
import com.hs.webloganalyse.common.GlobalConstants;
import com.hs.webloganalyse.utils.WebLogParser;

/**
 * 日志的第一次清理 ，过滤掉一些没有用的数据，如监控产生的一些日志 然后再写会hdfs
 * 
 * @author 黄晟
 *
 */
public class FirstLogCleanMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

	private Text mkey;
	private NullWritable mvalue;

	@Override
	protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		mkey = new Text();
		mvalue = NullWritable.get();
	}

	@Override
	protected void map(LongWritable key, Text value, 
			Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String oneline = value.toString();
		// 过滤掉不符合规则的数据
		// 解析日志
		WebLogBean webLogBean = WebLogParser.parser(oneline);
		if (webLogBean!=null) {
			mkey.set(webLogBean.toString());
			context.write(mkey, mvalue);
		}

	}
}
