package com.hs.webloganalyse.mr.analyse;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BrowserReducer extends Reducer<Text, LongWritable, Text, LongWritable>{
	private LongWritable sumValue = new LongWritable();
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,
			Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		long browserSum = 0;
		for (LongWritable longWritable : values) {
			browserSum+=longWritable.get();
		}
		sumValue.set(browserSum);
		context.write(key, sumValue);
	}
}
