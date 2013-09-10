package dateToDate_Progress.main;

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
	static double percent;
	static Stage stagestore;
	
    public static void drawChart(){
    	
    	String part = "% are already done";
    	String title = part.format("%s%s at file %s", percent*100, part, CalcAndDraw.fileDate.getFileName());
    	
		final NumberAxis xAxis = new NumberAxis();
		xAxis.setAutoRanging(false);
        final CategoryAxis yAxis = new CategoryAxis();
        final BarChart<Number,String> bc = 
            new BarChart<Number,String>(xAxis,yAxis);
        bc.setTitle(title);
        bc.setLegendVisible(false);
        
        
        final XYChart.Series series = new XYChart.Series(); 
        series.getData().add(new XYChart.Data(100.0, "full amount of time"));
        series.getData().add(new XYChart.Data(percent*100, "already done"));
        
        
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
    public static void main(double percent){
    	DrawBarChart.percent = percent;
    	drawChart();
    }
}

