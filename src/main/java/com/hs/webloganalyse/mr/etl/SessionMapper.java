package com.hs.webloganalyse.mr.etl;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import com.hs.webloganalyse.utils.WebLogParser;

public class SessionMapper extends Mapper<LongWritable, Text, Text, Text> {

	private Text IP = new Text();
	private Text content = new Text();
	WebLogParser webLogParser = new WebLogParser();

	public void map(LongWritable key, Text value, Context context) {

		// 将一行内容转成string
		String line = value.toString();

		String[] weblogArry = line.split(" ");

		IP.set(weblogArry[0]);
		content.set(line);
		try {
			// 将ip作为key
			context.write(IP, content);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
