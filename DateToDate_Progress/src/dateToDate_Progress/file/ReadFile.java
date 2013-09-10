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

		String user = System.getProperty("user.name");
		String s = user.format("C:\\Users\\%s\\Documents\\dateToDate_Progress", user);
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
