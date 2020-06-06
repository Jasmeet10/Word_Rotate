package wordPlay.driver;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import wordPlay.util.FileProcessor;
import java.io.FileNotFoundException;
import wordPlay.handler.WordRotator;
import wordPlay.metrics.MetricsCalculator;
import wordPlay.util.Results;
import java.util.HashMap;
import java.nio.file.InvalidPathException;

/**
 * @author John Doe
 *
 */
public class Driver {
	public static HashMap<Integer,String> resultmap = new HashMap<>();
	public static void main(String[] args) {
		Results results = new Results();
		int count = 0,lineNum = 0;
		int totalNumofWords = 0 ,totalNumofChar = 0;
		String returnWord;
		MetricsCalculator metricsCalculator = new MetricsCalculator();
		try {
			FileProcessor fileprocessor = new FileProcessor("/Users/jasmeetkaur/Desktop/csx42-summer-2020-assign1-Jasmeet10 _V4/wordPlay/input.txt");
			/*
			 * As the build.xml specifies the arguments as input,output or metrics, in case the
			 * argument value is not given java takes the default value specified in
			 * build.xml. To avoid that, below condition is used
			 */
			if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
				System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
				System.exit(0);
			}
			//System.out.println("Hello World! Lets get started with the assignment");

			WordRotator wordrotate = new WordRotator();

			int Flag = 0;
			while ((returnWord = fileprocessor.poll()) != null) {
				System.out.println(returnWord);
				count = count + 1;
				totalNumofWords = totalNumofWords +1;
				wordrotate.WordRotator(returnWord, count);
				totalNumofChar = totalNumofChar + returnWord.length() ;
				Flag = 1;
				if (returnWord.contains(".")) {
					count = 0;
					lineNum = lineNum +1;
					totalNumofChar = totalNumofChar-1;

				}

				Pattern p = Pattern.compile("[^a-zA-Z0-9.]", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(returnWord);
				boolean check = m.find();
				if (check) {
					String s = "There is a special character in the input file.";
					//System.out.println("There is a special character in the input file.");
					resultmap.put(4,s);
					results.writeToStdout();
					break;
				}
			}
			if (Flag == 0 && returnWord == null) {
				String s = "File is empty";
				resultmap.put(5,s);
				results.writeToStdout();
				//System.out.println("File is empty");
			}
		}
		catch (FileNotFoundException e) {
			String s = "Missing Input File";
			resultmap.put(6,s);
			results.writeToStdout();
			//System.out.println("Missing Input File");
			}
		catch (IOException e) {
			String s = "IO Exception";
			resultmap.put(7,s);
			results.writeToStdout();
			//System.out.println("IO Exception");
		}
		catch (SecurityException e){
			String s = "You do not have the read permissions to the input file";
			resultmap.put(8,s);
			results.writeToStdout();
			//System.out.println("You do not have the read permissions to the input file");
		}
		catch (InvalidPathException e){
			String s = "Invalid path";
			resultmap.put(9,s);
			results.writeToStdout();
		}

		metricsCalculator.MetricsCalculator(totalNumofWords,totalNumofChar, lineNum);
		results.writeToFile();
	}

}

