package dateToDate_Progress.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dateToDate_Progress.file.WriteFile;

public class FileWriteSwing extends JPanel implements ActionListener {

		static FileDate fileDate = new FileDate();
		
		JLabel startDate;
		JLabel endDate;
		JLabel fileName;
		JLabel refreshTime;
		JTextField refreshTimeText;
		JTextField fileNameText;
		JTextField startDateText;
		JTextField endDateText;
		
		JButton accept;
		JButton preview;
		JButton decline;
		
		static JFrame frame;

		public FileWriteSwing() {
			super(new BorderLayout());

			JLabel startDate = new JLabel("start date: ");
			JLabel endDate = new JLabel("end date: ");
			JLabel fileName = new JLabel("file name: ");
			
			startDateText = new JTextField(20);
			startDateText.setText("yyyy-mm-dd");
			
			endDateText = new JTextField(20);
			endDateText.setText("yyyy-mm-dd");
			
			fileNameText = new JTextField(20);
			fileNameText.setText("e.g school");

			accept = new JButton("accept");
			accept.setActionCommand("ACCEPT");
			
			decline = new JButton("decline");
			decline.setActionCommand("DECLINE");
			
			preview = new JButton("preview");
			preview.setActionCommand("PREVIEW");
			
			refreshTime = new JLabel("refresh time in seconds   ");
			
			refreshTimeText = new JTextField(20);
			
			accept.addActionListener(this);
			preview.addActionListener(this);
			decline.addActionListener(this);
			
			JPanel Data = new JPanel(new GridLayout(6, 2));
			Data.add(startDate);
			Data.add(startDateText);
			Data.add(endDate);
			Data.add(endDateText);
			Data.add(refreshTime);
			Data.add(refreshTimeText);
			Data.add(new JLabel("            "));
			Data.add(new JLabel("            "));
			Data.add(fileName);
			Data.add(fileNameText);
			Data.add(new JLabel("            "));
			Data.add(new JLabel("            "));
			
			
			JPanel Buttons = new JPanel(new GridLayout(1, 3));
			Buttons.add(accept);
			Buttons.add(preview);
			Buttons.add(decline);

			add(Data, BorderLayout.NORTH);
			add(Buttons, BorderLayout.SOUTH);

			setBorder(BorderFactory.createMatteBorder(25, 25, 25, 25, getBackground()));
		}
		private static void createAndShowGUI() {
			// Create and set up the window.
			frame = new JFrame("***DateToDate_Progress   -   Create new file***");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// Create and set up the content pane.
			JComponent newContentPane = new FileWriteSwing();
			newContentPane.setOpaque(true); // content panes must be opaque
			frame.setContentPane(newContentPane);

			// Display the window.
			frame.pack();
			frame.setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			String Action = e.getActionCommand();

			if (Action.equals("ACCEPT")) {
				saveInFileDate();
				
				try {
					new WriteFile().main(fileDate);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(false);
			}
			else if(Action.equals("PREVIEW")){
				saveInFileDate();
				try {
					List<FileDate> fileDates = new ArrayList<FileDate>();
					fileDates.add(fileDate);
					new CalcAndDraw().main(fileDates);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
			else if(Action.equals("DECLINE")){
				frame.dispose();
		}
		}
		private void saveInFileDate() {
			fileDate.setFileName(fileNameText.getText());
			
			fileDate.setSeconds(Integer.parseInt(refreshTimeText.getText()));
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				fileDate.setStart(formatter.parse(startDateText.getText()));
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				fileDate.setEnd(formatter.parse(endDateText.getText()));
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		}
		public static void main() {
			assignFileDate();
			
			// Schedule a job for the event-dispatching thread:
			// creating and showing this application's GUI.
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
				}
			});
	}
		private static void assignFileDate() {
			fileDate.setStart(new Date());
			fileDate.setEnd(new Date());
			
		}
}
