package com.hs.webloganalyse.mr.etl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Reducer;

import com.hs.webloganalyse.utils.PageViewsParser;
import com.hs.webloganalyse.utils.VisitsInfoParser;

public class VisitReducer extends Reducer<Text, Text, Text, NullWritable> {

	private Text content = new Text();
	private NullWritable v = NullWritable.get();
	VisitsInfoParser visitsParser = new VisitsInfoParser();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	PageViewsParser pageViewsParser = new PageViewsParser();
	HashMap<String, Integer> viewedPagesMap = new HashMap<String, Integer>();

	String entry_URL = "";
	String leave_URL = "";
	int total_visit_pages = 0;

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		// 将session所对应的所有浏览记录按时间排序
		ArrayList<String> browseInfoGroup = new ArrayList<String>();
		for (Text browseInfo : values) {
			browseInfoGroup.add(browseInfo.toString());
		}
		Collections.sort(browseInfoGroup, new Comparator<String>() {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			public int compare(String browseInfo1, String browseInfo2) {
				String dateStr1 = browseInfo1.split(" ")[0] + " " + browseInfo1.split(" ")[1];
				String dateStr2 = browseInfo2.split(" ")[0] + " " + browseInfo2.split(" ")[1];
				Date date1;
				Date date2;
				try {
					date1 = sdf.parse(dateStr1);
					date2 = sdf.parse(dateStr2);
					if (date1 == null && date2 == null)
						return 0;
					return date1.compareTo(date2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			}
		});

		// 统计该session访问的总页面数,第一次进入的页面，跳出的页面
		for (String browseInfo : browseInfoGroup) {

			String[] browseInfoStrArr = browseInfo.split(" ");
			String curVisitURL = browseInfoStrArr[3];
			Integer curVisitURLInteger = viewedPagesMap.get(curVisitURL);
			if (curVisitURLInteger == null) {
				viewedPagesMap.put(curVisitURL, 1);
			}
		}
		total_visit_pages = viewedPagesMap.size();
		String visitsInfo = visitsParser.parser(browseInfoGroup, total_visit_pages + "");
		content.set(visitsInfo);
		try {
			context.write(content, v);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
