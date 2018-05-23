package com.hs.webloganalyse.mr.etl.launcher;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.hs.webloganalyse.common.GlobalConstants;
import com.hs.webloganalyse.mr.etl.PageViewsMapper;
import com.hs.webloganalyse.mr.etl.PageViewsReducer;
import com.hs.webloganalyse.utils.HdfsUtils;
import com.hs.webloganalyse.utils.TimeUtils;

/**
 * pageview 任务启动器
 * @author 黄晟
 *
 */
public class PageViewMRLauncher {
	public static void main(String[] args)
			throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();

		Job job = Job.getInstance(conf,GlobalConstants.PAGEVIEW_TASK);

		job.setJarByClass(VisitsMRLaucher.class);

		// 指定本业务job要使用的mapper/Reducer业务类
		job.setMapperClass(PageViewsMapper.class);
		job.setReducerClass(PageViewsReducer.class);

		// 指定mapper输出数据的kv类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		// 指定最终输出的数据的kv类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		String currentDate = TimeUtils.getCurrentDate();
		String inputpath = GlobalConstants.SESSIONDATA_PATH_PREFIX + currentDate + "/*";
		String outputpath = GlobalConstants.PAGEVIEW_PATH_PREFIX + currentDate + "/";
		// 指定job的输入原始文件所在目录
		FileInputFormat.setInputPaths(job, new Path(inputpath));
		// 指定job的输出结果所在目录
		HdfsUtils.checkOutputPathIsExist(outputpath, conf);
		FileOutputFormat.setOutputPath(job, new Path(outputpath));

		boolean res = job.waitForCompletion(true);
		System.exit(res ? 0 : 1);
	}
}
