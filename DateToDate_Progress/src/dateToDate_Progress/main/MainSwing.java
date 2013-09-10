package dateToDate_Progress.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Semaphore;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dateToDate_Progress.file.WriteFile;

public class MainSwing extends JPanel implements ActionListener{
	
	static FileDate fileDate;
	static JFrame frame;
	JButton newFile;
	JButton show;
	JButton exit;
	
	public MainSwing() {
		super(new BorderLayout());
		
		newFile = new JButton("    new file    ");
		newFile.setActionCommand("NEW FILE");
		
		show = new JButton("   show   ");
		show.setActionCommand("SHOW");
		
		exit = new JButton("   exit   ");
		exit.setActionCommand("EXIT");
		
		newFile.addActionListener(this);
		show.addActionListener(this);
		exit.addActionListener(this);
		
		JPanel Buttons = new JPanel(new GridLayout(5, 1));
		Buttons.add(newFile);
		Buttons.add(new JLabel());
		Buttons.add(show);
		Buttons.add(new JLabel());
		Buttons.add(exit);

		add(Buttons, BorderLayout.CENTER);

		setBorder(BorderFactory.createMatteBorder(25, 150, 25, 150, getBackground()));
	}
	private static void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("***DateToDate_Progress   -   Main***");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new MainSwing();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String Action = e.getActionCommand();
		
		if(Action.equals("NEW FILE")){
			new FileWriteSwing().main();
		}
		else if(Action.equals("SHOW")){
			new FileNameSwing().main();
			//call class to calc and draw
		}
		else if(Action.equals("EXIT"))
			System.exit(0);
		
	}
	public static void main() {		
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
}

}
