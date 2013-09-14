package dateToDate_Progress.main;

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ValueAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DrawBarChart {
	
	static int count = 0;
	static List<FileDate> fileDates;
	static Stage stagestore;
	
    public static void drawChart(){
    	
    	String part = "% are already done";
    	String title;
		double twoDecimals = Double.valueOf(Math.round((fileDates.get(0).getPercent())*100)/100.0);
		
    	if(fileDates.size() == 1)
    		title = part.format("%s%s at file %s", twoDecimals*100, part, fileDates.get(0).getFileName());
    	else
    		title = "several files are selected";
    	
		final NumberAxis xAxis = new NumberAxis();
		xAxis.setAutoRanging(false);
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number,String> bc = 
            new BarChart<Number,String>(xAxis,yAxis);
        bc.setTitle(title);
        bc.setLegendVisible(false);
        
        
        final XYChart.Series series = new XYChart.Series(); 
        for(int i = 0; i < fileDates.size();i++){
	        series.getData().add(new XYChart.Data(100.0, "full amount of time"));
	        twoDecimals = Double.valueOf(Math.round((fileDates.get(i).getPercent())*100)/100.0);
	        if(fileDates.size() >= 2)
	        	series.getData().add(new XYChart.Data(fileDates.get(i).getPercent()*100, String.format("already done [%s], %s%s", fileDates.get(i).getFileName(), twoDecimals * 100, "%")));
	        else 
	        	series.getData().add(new XYChart.Data(fileDates.get(i).getPercent()*100, "already done"));
        }
        
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		int tmpWidth = (int) primaryScreenBounds.getWidth();
		final int width = tmpWidth - (tmpWidth/110);
		int tmpHeight = (int) primaryScreenBounds.getHeight();
		final int height = tmpHeight - (tmpHeight / 18);
        
		final Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				final Stage stage = new Stage();
		        stage.setTitle("***DateToDate_Progress   -   Chart***");
		        Scene scene  = new Scene(bc,width,height);
		        bc.getData().addAll(series);
		        stage.setScene(scene);
		        stage.show();
		        if (count <= 0)
					stagestore = stage;
				if (count > 0) {
					stagestore.close();
					stagestore = stage;
				}
				count++;
			}
		};
		
        Platform.runLater(new Runnable() {
			public void run() {
				runnable.run();
			}
		});
    }
    public static void main(List<FileDate> fileDates){
    	DrawBarChart.fileDates = fileDates;
    	sortList();
    	drawChart();
    }
	private static void sortList() {
		boolean exchange = false;
		int length = fileDates.size();
		do{
			exchange = false;
			length--;
			for(int i = 0; i < length;i++){
				if(fileDates.get(i).getPercent() < fileDates.get(i+1).getPercent()){
					FileDate tmp = fileDates.get(i);
					fileDates.set(i, fileDates.get(i+1));
					fileDates.set(i+1, tmp);
					exchange = true;
				}
			}
		}while(exchange == true);
		
	}
}

