package dateToDate_Progress.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import dateToDate_Progress.main.FileDate;

public class WriteFile {

	public static void main(FileDate fileDate) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();

		String s = "C:\\Users\\ThinkpadT420\\Documents";
		s = s.replace("\\", "/");

		File dir = new File(String.format(("%s/dateToDate_Progress"), s));
		dir.mkdir();

		String path = String.format("%s/dateToDate_Progress/%s", s, fileDate.getFileName());

		// save
		mapper.writeValue(new FileWriter(path), fileDate);
	}

}
