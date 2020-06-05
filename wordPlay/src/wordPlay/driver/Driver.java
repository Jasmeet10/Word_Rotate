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


/**
 * @author John Doe
 *
 */
public class Driver {
	public static HashMap<Integer,String> resultmap = new HashMap<>();
	public static void main(String[] args) {
		int count = 0,lineNum = 0;
		int totalNumofWords = 0 ,totalNumofChar = 0;
		String returnWord;
		MetricsCalculator metricsCalculator = new MetricsCalculator();
		try {
			FileProcessor fileprocessor = new FileProcessor("/Users/jasmeetkaur/Desktop/csx42-summer-2020-assign1-Jasmeet10 _V3/wordPlay/input.txt");

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
					System.out.println("There is a special character in the input file.");
					break;
				}
			}
			if (Flag == 0 && fileprocessor.poll() == null) {
				System.out.println("File is empty");
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Missing Input File");}
		catch (IOException e) {
			System.out.println("IO Exception");
		}
		metricsCalculator.MetricsCalculator(totalNumofWords,totalNumofChar, lineNum);
		Results results = new Results();
		results.writeToFile();
		results.writeToStdout();
	}

}

