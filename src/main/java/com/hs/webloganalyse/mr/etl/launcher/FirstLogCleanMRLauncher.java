package com.hs.webloganalyse.mr.etl.launcher;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;
import com.hs.webloganalyse.common.GlobalConstants;
import com.hs.webloganalyse.mr.etl.FirstLogCleanMapper;
import com.hs.webloganalyse.utils.HdfsUtils;
import com.hs.webloganalyse.utils.TimeUtils;

/**
 * 第一次清洗任务的启动器 - FirstLogCleanMapper
 * 
 * @author 黄晟
 *
 */
public class FirstLogCleanMRLauncher {

	private static Logger logger = Logger.getLogger(FirstLogCleanMRLauncher.class);

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		Job job = null;

		job = Job.getInstance(conf, GlobalConstants.FIRST_CLEAN_TASK);

		job.setJarByClass(FirstLogCleanMRLauncher.class);
		job.setMapperClass(FirstLogCleanMapper.class);

		// 指定mapper输出数据的kv类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);

		String currentDate = TimeUtils.getCurrentDate();
		logger.info("current date --->" + currentDate);
		String inputpath = GlobalConstants.ORIGIN_WEBLOG_PATH_PREFIX + currentDate + "/*/*";
		String outputpath = GlobalConstants.CLEANED_WEBLOG_PATH_PREFIX + currentDate + "/";

		// 指定输入输出路径
		FileInputFormat.setInputPaths(job, new Path(inputpath));
		HdfsUtils.checkOutputPathIsExist(outputpath, conf);
		FileOutputFormat.setOutputPath(job, new Path(outputpath));
		boolean res = job.waitForCompletion(true);
		System.exit(res ? 0 : 1);

	}
}
