package com.hs.webloganalyse.mr.etl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import com.hs.webloganalyse.bean.WebLogSessionBean;
import com.hs.webloganalyse.utils.SessionParser;
import com.hs.webloganalyse.utils.WebLogParser;

public class SessionReducer extends Reducer<Text, Text, Text, NullWritable> {
	private Text IPAddr = new Text();
	private Text content = new Text();
	private NullWritable v = NullWritable.get();
	WebLogParser webLogParser = new WebLogParser();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SessionParser sessionParser = new SessionParser();

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

		Date sessionStartTime = null;
		String sessionID = UUID.randomUUID().toString();

		// 将IP地址所对应的用户的所有浏览记录按时间排序
		ArrayList<WebLogSessionBean> sessionBeanGroup = new ArrayList<WebLogSessionBean>();
		for (Text browseHistory : values) {
			WebLogSessionBean sessionBean = sessionParser.loadBean(browseHistory.toString());
			sessionBeanGroup.add(sessionBean);
		}
		// 根据访问的时间排个序
		Collections.sort(sessionBeanGroup, new Comparator<WebLogSessionBean>() {

			public int compare(WebLogSessionBean sessionBean1, WebLogSessionBean sessionBean2) {
				Date date1 = sessionBean1.getTimeWithDateFormat();
				Date date2 = sessionBean2.getTimeWithDateFormat();
				if (date1 == null && date2 == null)
					return 0;
				return date1.compareTo(date2);
			}
		});

		for (WebLogSessionBean sessionBean : sessionBeanGroup) {

			if (sessionStartTime == null) {
				// 当天日志中某用户第一次访问网站的时间
				sessionStartTime = timeTransform(sessionBean.getTime());
				content.set(sessionParser.parser(sessionBean, sessionID));
				try {
					context.write(content, v);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {

				Date sessionEndTime = timeTransform(sessionBean.getTime());
				long sessionStayTime = timeDiffer(sessionStartTime, sessionEndTime);
				// 默认session的超时时间为30分钟
				if (sessionStayTime > 30 * 60 * 1000) {
					// 将当前浏览记录的时间设为下一个session的开始时间
					sessionStartTime = timeTransform(sessionBean.getTime());
					// 重新分配sessionID
					sessionID = UUID.randomUUID().toString();
					continue;
				}
				content.set(sessionParser.parser(sessionBean, sessionID));
				try {
					context.write(content, v);
				} catch (IOException e) {

					e.printStackTrace();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
	}

	private Date timeTransform(String time) {

		Date standard_time = null;
		try {
			standard_time = sdf.parse(time);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return standard_time;
	}

	// 时间差
	private long timeDiffer(Date start_time, Date end_time) {

		long diffTime = 0;
		diffTime = end_time.getTime() - start_time.getTime();

		return diffTime;
	}
}
