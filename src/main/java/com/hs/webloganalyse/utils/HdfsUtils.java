package com.hs.webloganalyse.utils;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class HdfsUtils {
	
	/**
	 * 检查MR的输出路劲是否存在，若存在则立即删除，避免报错
	 * @param path
	 * @param conf
	 * @throws IOException
	 */
	public static void checkOutputPathIsExist(String path,Configuration conf) throws IOException {
		Path outputPath = new Path(path);
		outputPath.getFileSystem(conf).delete(outputPath, true);
	}
}
