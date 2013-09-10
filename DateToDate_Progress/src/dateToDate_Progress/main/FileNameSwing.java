package dateToDate_Progress.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import dateToDate_Progress.file.ReadFile;

public class FileNameSwing extends JPanel implements ActionListener{
	
	static JFrame frame;
	JLabel fileName;
	JTextField fileNameText;
	JButton accept;
	JButton decline;
	
	public FileNameSwing() {
		super(new BorderLayout());
		
		accept = new JButton("   accept   ");
		accept.setActionCommand("ACCEPT");
		
		decline = new JButton("   decline   ");
		decline.setActionCommand("DECLINE");
		
		fileName = new JLabel("file name   ");
		
		fileNameText = new JTextField(20);
		
		accept.addActionListener(this);
		decline.addActionListener(this);
		
		JPanel Text = new JPanel(new GridLayout(2,2));
		Text.add(fileName);
		Text.add(fileNameText);
		Text.add(new JLabel());
		Text.add(new JLabel());
		
		JPanel Buttons = new JPanel(new GridLayout(1, 2));
		Buttons.add(accept);
		Buttons.add(decline);

		add(Text, BorderLayout.NORTH);
		add(Buttons, BorderLayout.CENTER);

		setBorder(BorderFactory.createMatteBorder(25, 25, 25, 25, getBackground()));
	}
	private static void createAndShowGUI() {
		// Create and set up the window.
		frame = new JFrame("***DateToDate_Progress   -   Chart-Properties***");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new FileNameSwing();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String Action = e.getActionCommand();
		
		if(Action.equals("ACCEPT")){
			try {
				FileDate fileDate = new ReadFile().main(fileNameText.getText());
				MainSwing.fileDate = fileDate;
				new CalcAndDraw().main(fileDate);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(Action.equals("DECLINE")){
			frame.dispose();
		}		
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
