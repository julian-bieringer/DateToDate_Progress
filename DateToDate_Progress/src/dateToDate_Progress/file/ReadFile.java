package dateToDate_Progress.file;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import dateToDate_Progress.main.FileDate;

public class ReadFile {

	public static FileDate main(String fileName) throws JsonParseException, JsonMappingException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
		FileDate fileDate = new FileDate();

		String s = "C:\\Users\\ThinkpadT420\\Documents\\dateToDate_Progress";
		s = s.replace("\\", "/");
		String path = String.format("%s/%s", s, fileName);
		File f = new File(path);

		if (f.exists())
			fileDate = mapper.readValue(new File(path), FileDate.class);
		else
			System.out.println("File does not exist at " + f.toString());

		return fileDate;
	}

}
