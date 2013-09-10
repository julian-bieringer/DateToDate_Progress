package dateToDate_Progress.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application{

	@Override public void start(Stage arg0) throws Exception {
		Platform.setImplicitExit(false);
	}
	
	public static void main(String[] args) {
		new MainSwing().main();
		launch(new String[] {});
	}

}
