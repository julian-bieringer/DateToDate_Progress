package dateToDate_Progress.main;

import java.util.Date;

public class CalcAndDraw {

	static FileDate fileDate;
	
	public static void main(FileDate fileDate) throws InterruptedException {
		while(true){
			CalcAndDraw.fileDate = fileDate;
		
			int dayDifference = (int) ((fileDate.getEnd().getTime() - fileDate.getStart().getTime())/86400000);
			Date now = new Date();
			double spanToNow = (now.getTime() - fileDate.getStart().getTime())/86400000.0;
			double resultPercent = spanToNow/dayDifference;
			
			new DrawBarChart().main(resultPercent);
			
			int seconds = fileDate.getSeconds();
			
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
