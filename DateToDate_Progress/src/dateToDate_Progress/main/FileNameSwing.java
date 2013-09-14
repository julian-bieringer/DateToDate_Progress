package dateToDate_Progress.main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import dateToDate_Progress.file.ReadFile;

public class FileNameSwing extends JPanel implements ActionListener{
	
	static List<FileCheckbox> fileCheckBoxList = new ArrayList<FileCheckbox>();
	static JFrame frame;
	JButton selectAll;
	JButton deselectAll;
	JButton delete;
	JButton accept;
	JButton decline;
	
	public FileNameSwing() {
		super(new BorderLayout());
		
		if(fileCheckBoxList.size() <= 0)
		getFileNames();
		
		
		selectAll = new JButton("   select all   ");
		selectAll.setActionCommand("SELECTALL");
		
		deselectAll = new JButton("   deselect all   ");
		deselectAll.setActionCommand("DESELECTALL");
		
		delete = new JButton("   delete   ");
		delete.setActionCommand("DELETE");
		
		accept = new JButton("   accept   ");
		accept.setActionCommand("ACCEPT");
		
		decline = new JButton("   decline   ");
		decline.setActionCommand("DECLINE");
		
		selectAll.addActionListener(this);
		deselectAll.addActionListener(this);
		delete.addActionListener(this);
		accept.addActionListener(this);
		decline.addActionListener(this);
		
		JPanel Text = new JPanel(new GridLayout(fileCheckBoxList.size() + 3, 6));
		Text.add(new JLabel());
		Text.add(new JLabel("files"));
		Text.add(new JLabel());
		Text.add(new JLabel("start date"));
		Text.add(new JLabel());
		Text.add(new JLabel("end date"));
		
		for(int i = 0; i < 6; i++)
			Text.add(new JLabel());
		
		for(int i = 0; i < fileCheckBoxList.size();i++){
			Text.add(fileCheckBoxList.get(i).getCheckBox());
			Text.add(new JLabel(fileCheckBoxList.get(i).getFileName()));
			Text.add(new JLabel());
			FileDate fileDate;
			try {
				fileDate = new ReadFile().main(fileCheckBoxList.get(i).getFileName());
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
				Text.add(new JLabel(formatter.format(fileDate.getStart())));
				Text.add(new JLabel());
				Text.add(new JLabel(formatter.format(fileDate.getEnd())));
			} catch (IOException e){}
		}
			
		for(int i = 0; i < 6; i++)
			Text.add(new JLabel());
		
		JPanel Options = new JPanel(new GridLayout(2,2));
		Options.add(selectAll);
		Options.add(deselectAll);
		Options.add(new JLabel());
		Options.add(new JLabel());
		
		JPanel Delete = new JPanel(new GridLayout(2,1));
		Delete.add(delete);
		Delete.add(new JPanel());
		
		
		JPanel Buttons = new JPanel(new GridLayout(1, 3));
		Buttons.add(accept);
		Buttons.add(decline);
		
		final JPanel centre = new JPanel(new GridLayout(2,3));
		centre.add(Options);
		centre.add(Delete);

		add(Text, BorderLayout.NORTH);
		add(centre, BorderLayout.CENTER);
		add(Buttons, BorderLayout.SOUTH);

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
			List<String> fileNames = getFileName();
			List<FileDate> fileDates = new ArrayList<FileDate>();
			try {
				try {
					for(int i = 0; i < fileNames.size();i++){
						fileDates.add(new ReadFile().main(fileNames.get(i)));
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new CalcAndDraw().main(fileDates);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(Action.equals("SELECTALL")){
			for(int i = 0; i < fileCheckBoxList.size();i++)
				fileCheckBoxList.get(i).getCheckBox().setSelected(true);
		}	
		else if(Action.equals("DESELECTALL"))
			for(int i = 0; i < fileCheckBoxList.size();i++)
				fileCheckBoxList.get(i).getCheckBox().setSelected(false);
		else if(Action.equals("DELETE")){
			int dialogButton = JOptionPane.YES_NO_OPTION;
			JOptionPane.setDefaultLocale(Locale.US);  
			int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure to delete the selected files?", "Warning",dialogButton);
			
			if(dialogResult == 0){
				for(int i = 0; i < fileCheckBoxList.size();i++){
					if(fileCheckBoxList.get(i).getCheckBox().isSelected() == true){
						String user = System.getProperty("user.name");
						String s = user.format("C:\\Users\\%s\\Documents\\dateToDate_Progress", user);
						s = s.replace("\\", "/");
						File file = new File(String.format("%s/%s", s, fileCheckBoxList.get(i).getFileName()));
						file.delete();
					}
				}
			}
			if(dialogResult == 0){
				getFileNames();
				frame.dispose();
				new FileNameSwing().main();
			}
		}
		else if(Action.equals("DECLINE")){
			fileCheckBoxList = new ArrayList<FileCheckbox>();
			frame.dispose();
		}		
	}
	private  List<String> getFileName() {
		List<String> fileNames = new ArrayList<String>();
		
		for(int i = 0; i < fileCheckBoxList.size();i++){
			if(fileCheckBoxList.get(i).getCheckBox().isSelected() == true)
				fileNames.add(fileCheckBoxList.get(i).getFileName());
		}
		return fileNames;
	}
	private void getFileNames() {
		fileCheckBoxList = new ArrayList();
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
