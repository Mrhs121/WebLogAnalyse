package com.hs.webloganalyse.mr.analyse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StandIPReducer extends Reducer<Text, Text, Text, Text>{
	private Text result = new Text();
    private Set<String> count = new HashSet<String>();
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
        /**
         * 以资源路径为键
         * 以相同资源路径的ip值的个数，添加入set集合中作为值
         * 写入到结果文件中
         */
		for (Text ip : values) {
			 count.add(ip.toString());
		}
    	//结果集中存放的是独立ip的个数
        result.set(String.valueOf(count.size()));
        context.write(key, result);
	}
}
