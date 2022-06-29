package com.hs.webloganalyse.mr.analyse;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import com.hs.webloganalyse.utils.WebLogParser;

/**
 * 浏览器分析
 * @author 黄晟
 *
 */
public class BrowserMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
	 
	private Text mkey = new Text();
	private LongWritable mvalue = new LongWritable(1);
	
// 	Mapper<LongWritable, Text, Text, LongWritable>：文件行号，行内容，浏览器名称，浏览次数
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		String oneline = value.toString();
		String browser = WebLogParser.parserBrowser(oneline);
		if (!browser.equals("")) {
			mkey.set(browser);
			context.write(mkey, mvalue);
		}
	}
}
