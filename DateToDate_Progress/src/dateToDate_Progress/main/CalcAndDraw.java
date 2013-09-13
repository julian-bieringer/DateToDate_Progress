package dateToDate_Progress.main;

import java.util.Date;
import java.util.List;

public class CalcAndDraw {
	
	public static void main(List<FileDate> fileDates) throws InterruptedException {
	int seconds = fileDates.get(0).getSeconds();
	DrawBarChart.count = 0;
		while(true){
			
			for(int i = 0; i < fileDates.size();i++){
				int dayDifference = (int) ((fileDates.get(i).getEnd().getTime() - fileDates.get(i).getStart().getTime())/86400000);
				Date now = new Date();
				double spanToNow = (now.getTime() - fileDates.get(i).getStart().getTime())/86400000.0;
				fileDates.get(i).setPercent(spanToNow/dayDifference);
			}
			
			new DrawBarChart().main(fileDates);
			
			
			for(int i = 0; i < seconds;i++){
				if(DrawBarChart.stagestore != null &&  DrawBarChart.stagestore.isShowing() == false)
					break;
				Thread.sleep(1000);
			}
			
			if(DrawBarChart.stagestore.isShowing() == false)
				break;
		}
	}
}
