package dateToDate_Progress.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import dateToDate_Progress.file.ReadFile;

public class FileNameSwing extends JPanel implements ActionListener{
	
	static List<FileCheckbox> fileCheckBoxList = new ArrayList<FileCheckbox>();
	static JFrame frame;
	JButton accept;
	JButton decline;
	
	public FileNameSwing() {
		super(new BorderLayout());
		
		getFileNames();
		
		accept = new JButton("   accept   ");
		accept.setActionCommand("ACCEPT");
		
		decline = new JButton("   decline   ");
		decline.setActionCommand("DECLINE");
		
		accept.addActionListener(this);
		decline.addActionListener(this);
		
		JPanel Text = new JPanel(new GridLayout(fileCheckBoxList.size() + 2, 2));
		for(int i = 0; i < fileCheckBoxList.size();i++){
			Text.add(new JLabel(fileCheckBoxList.get(i).getFileName()));
			Text.add(fileCheckBoxList.get(i).getCheckBox());
		}
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
				FileDate fileDate = new FileDate();
				try {
					fileDate = new ReadFile().main(getFileName());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainSwing.fileDate = fileDate;
				new CalcAndDraw().main(fileDate);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(Action.equals("DECLINE")){
			frame.dispose();
		}		
	}
	private String getFileName() {
		for(int i = 0; i < fileCheckBoxList.size();i++){
			if(fileCheckBoxList.get(i).getCheckBox().isSelected() == true)
				return fileCheckBoxList.get(i).getFileName();
		}
		return null;
	}
	private void getFileNames() {
		String user = System.getProperty("user.name");
		String s = user.format("C:\\Users\\%s\\Documents\\dateToDate_Progress", user);
		s = s.replace("\\", "/");
		File folder = new File(s);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		      FileCheckbox fileCheckbox = new FileCheckbox();
		      fileCheckbox.setFileName(file.getName());
		      fileCheckbox.setCheckBox(new JCheckBox());
		      fileCheckBoxList.add(fileCheckbox);
		    }
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
