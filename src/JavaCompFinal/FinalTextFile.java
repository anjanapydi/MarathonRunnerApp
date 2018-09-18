package JavaCompFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * FinalTextFile.java - This class reads runners data from .txt file and returns an arraylist to main method.
 * @author Anjana
 *
 */
public class FinalTextFile {
	/**
	 * This method reads .txt file from given path. If file not present, create it and proceed.
	 * @return arraylist of runners
	 */
	public List<RunnersThread> getFileData() {
		//Below code gives information of input file.
		String dirString = "Resources";
		String fileString = "FinalTextData.txt";
		Path filePath = Paths.get(dirString,fileString);
		List<RunnersThread> fileData = new ArrayList<RunnersThread>();
		try {
			//Below code creates file if it does not exist
			if (Files.notExists(filePath)) {
				Files.createFile(filePath);
				//Below code is to write to newly created file
				File runnersFile = filePath.toFile();
				FileWriter fd = new FileWriter(runnersFile, false);
				BufferedWriter out = new BufferedWriter(fd);
				//Below code is to write to text file
				out.write("Tortoise	10		0");
				out.write("\r\n");
				out.write("Hare		100		90");
				out.write("\r\n");
				out.write("Dog 		50		40");
				out.write("\r\n");
				out.write("Cat 		30		75");
				out.write("\r\n");
				out.close();
			} // end if
			//Below code reads data from .txt file
			//Below code is to connect character input stream to file
			File runnersFile = filePath.toFile();
			FileReader fd = new FileReader(runnersFile);
			BufferedReader in = new BufferedReader(fd);
			//Below code is to read records in text file to arraylist
			String inputRecord = in.readLine();
			while (inputRecord != null) {
				StringTokenizer st = new StringTokenizer(inputRecord,"\t");
				String name = st.nextToken();
				int speed = Integer.parseInt(st.nextToken());
				int rest = Integer.parseInt(st.nextToken());
				RunnersThread r = new RunnersThread(name, speed, rest);
				fileData.add(r);
				inputRecord = in.readLine();
			} // end while
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		} // end try-catch block
		return fileData;
	} //end getFileData method
} //end FinalTextFile class


