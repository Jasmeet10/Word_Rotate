package wordPlay.driver;
import java.io.IOException;
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
 * @author Jasmeet Kaur
 *
 */
public class Driver {
	public static HashMap<Integer,String> resultmap = new HashMap<>();
	public static void main(String[] args) {
		int count = 0,lineNum = 0;
		int totalNumofWords = 0 ,totalNumofChar = 0;
		String returnWord;
		String input = args[0];
		String output = args[1];
		String metrics = args[2];
		MetricsCalculator metricsCalculator = new MetricsCalculator();
		/**
		 * This function creates a new object of file processor and trows an error if not done.
		 */
		try {
			/*
			 * As the build.xml specifies the arguments as input,output or metrics, in case the
			 * argument value is not given java takes the default value specified in
			 * build.xml. To avoid that, below condition is used
			 */
			if(output.isEmpty()){
				System.out.println("output file name is empty");
				System.exit(0);
			}
			if(input.isEmpty()){
				System.out.println("input file name is empty");
				System.exit(0);
			}
			if(metrics.isEmpty()){
				System.out.println("metrics file name is empty");
				System.exit(0);
			}
			if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}")) || (args[2].equals("${metrics}"))) {
				System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
				System.exit(0);
			}
			FileProcessor fileprocessor = new FileProcessor(input);
			//System.out.println("Hello World! Lets get started with the assignment");

			WordRotator wordrotate = new WordRotator();

			int Flag = 0;
			while ((returnWord = fileprocessor.poll()) != null) {
				//System.out.println(returnWord);
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
				/***
				 * This pattern matcher checkes if there is a special character in input file.
				 */
				Pattern p = Pattern.compile("[^a-zA-Z0-9.]", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(returnWord);
				boolean check = m.find();
				if (check) {
					System.out.println("There is a special character in the input file.");
					break;
				}
			}
			/***
			 * This condition checkes if the file is empty or not.
			 */
			if (Flag == 0 && fileprocessor.poll() == null) {
				System.out.println("File is empty");
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Missing Input File");}
		catch (IOException e) {
			System.out.println("IO Exception");
		}
		catch (SecurityException e){
			System.out.println("You do not have the read permissions to the input file");
		}
		catch (InvalidPathException e){

			System.out.println("Invalid path");
		}
		metricsCalculator.MetricsCalculator(totalNumofWords,totalNumofChar, lineNum);
		/***
		 * creates instance of results and calling write to file and std out method using interface instance.
		 */
		Results results = new Results(output, metrics);
		results.writeToFile();
		results.writeToStdout();
	}

}

