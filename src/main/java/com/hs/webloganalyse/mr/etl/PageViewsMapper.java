package com.hs.webloganalyse.mr.etl;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

/**
 * pageview 任务，输入依赖于session任务的输出
 * @author 黄晟
 *
 */
public class PageViewsMapper extends Mapper<Object, Text, Text, Text> {

	private Text word = new Text();

	public void map(Object key, Text value, Context context) {
		
		// 2013-09-18 13:47:35 1.162.203.134 8cc3fec7-a7f8-46cc-8c55-d235267aeae3 /images/my.jpg http://www.angularjs.cn/A0d9
		String line = value.toString();
		String[] webLogContents = line.split(" ");

		// 根据session来分组
		word.set(webLogContents[3]);
		try {
			context.write(word, value);
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
}
