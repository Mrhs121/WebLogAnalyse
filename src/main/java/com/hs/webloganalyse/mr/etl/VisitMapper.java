package com.hs.webloganalyse.mr.etl;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * visits表生成任务
 * 
 * @author 黄晟
 *
 */
public class VisitMapper extends Mapper<Object, Text, Text, Text> {

	private Text word = new Text();

	public void map(Object key, Text value, Context context) {

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
