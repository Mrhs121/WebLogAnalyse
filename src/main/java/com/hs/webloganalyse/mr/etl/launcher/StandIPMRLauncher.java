package com.hs.webloganalyse.mr.etl.launcher;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.hs.webloganalyse.common.GlobalConstants;
import com.hs.webloganalyse.mr.analyse.StandIPMapper;
import com.hs.webloganalyse.mr.analyse.StandIPReducer;

import com.hs.webloganalyse.utils.HdfsUtils;
import com.hs.webloganalyse.utils.TimeUtils;

public class StandIPMRLauncher {

	// private static Logger logger = Logger.getLogger(SessionMRLauncher.class);

	public static void main(String[] args)
			throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, GlobalConstants.STANDIP_TASK);

		job.setJarByClass(StandIPMRLauncher.class);

		// 指定本业务job要使用的mapper/Reducer业务类
		job.setMapperClass(StandIPMapper.class);
		job.setReducerClass(StandIPReducer.class);

		// 指定mapper输出数据的kv类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);

		// 指定最终输出的数据的kv类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		String currentDate = TimeUtils.getCurrentDate();

		String inputpath = GlobalConstants.CLEANED_WEBLOG_PATH_PREFIX + currentDate + "/";
		String outputpath = GlobalConstants.STANDIP_PATH_PREFIX + currentDate + "/";
		// 指定job的输入原始文件所在目录
		FileInputFormat.setInputPaths(job, new Path(inputpath));
		// 指定job的输出结果所在目录
		HdfsUtils.checkOutputPathIsExist(outputpath, conf);
		FileOutputFormat.setOutputPath(job, new Path(outputpath));

		// 将job中配置的相关参数，以及job所用的java类所在的jar包，提交给yarn去运行

		boolean res = job.waitForCompletion(true);
		System.exit(res ? 0 : 1);
	}

}
