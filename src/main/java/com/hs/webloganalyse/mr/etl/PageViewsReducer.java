package com.hs.webloganalyse.mr.etl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import com.hs.webloganalyse.bean.PageViewsBean;
import com.hs.webloganalyse.utils.PageViewsParser;

/**
 * pageview任务
 * @author 黄晟
 *
 */
public class PageViewsReducer extends Reducer<Text, Text, Text, NullWritable> {

	private Text session = new Text();
	private Text content = new Text();
	private NullWritable v = NullWritable.get();
	PageViewsParser pageViewsParser = new PageViewsParser();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 上一条记录的访问信息
	PageViewsBean lastStayPageBean = null;
	Date lastVisitTime = null;

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		//System.out.println("\n--------------------------------------------------");
		// 将session所对应的所有浏览记录按时间排序
		ArrayList<PageViewsBean> pageViewsBeanGroup = new ArrayList<PageViewsBean>();
		for (Text pageView : values) {
			PageViewsBean pageViewsBean = pageViewsParser.loadBean(pageView.toString());
			pageViewsBeanGroup.add(pageViewsBean);
		}
		Collections.sort(pageViewsBeanGroup, new Comparator<PageViewsBean>() {

			public int compare(PageViewsBean pageViewsBean1, PageViewsBean pageViewsBean2) {
				Date date1 = pageViewsBean1.getTimeWithDateFormat();
				Date date2 = pageViewsBean2.getTimeWithDateFormat();
				if (date1 == null && date2 == null)
					return 0;
				return date1.compareTo(date2);
			}
		});

		// 计算每个页面的停留时间
		int step = 0;
		for (PageViewsBean pageViewsBean : pageViewsBeanGroup) {

			Date curVisitTime = pageViewsBean.getTimeWithDateFormat();

			if (lastStayPageBean != null) {
				// 计算前后两次访问记录相差的时间，单位是秒
				Integer timeDiff = (int) ((curVisitTime.getTime() - lastVisitTime.getTime()) / 1000);
				//System.out.println("time ----> "+curVisitTime.getTime()+"  "+lastVisitTime.getTime()+" "+timeDiff);
				// 根据当前记录的访问信息更新上一条访问记录中访问的页面的停留时间
				//lastStayPageBean.setStayTime(timeDiff.toString());
				pageViewsBean.setStayTime(timeDiff.toString());
			}

			// 更新访问记录的步数
			step++;
			pageViewsBean.setStep(step + "");
			// 更新上一条访问记录的停留时间后，将当前访问记录设定为上一条访问信息记录
			lastStayPageBean = pageViewsBean;
			lastVisitTime = curVisitTime;

			// 输出pageViews信息
			content.set(pageViewsParser.parser(pageViewsBean));
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
