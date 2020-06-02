package wordPlay.driver;
import java.io.*;
import wordPlay.util.FileProcessor;
import java.io.FileNotFoundException;
import wordPlay.handler.WordRotator;


/**
 * @author John Doe
 *
 */
public class Driver {
	public static void main(String[] args) {

		try {
			FileProcessor fileprocessor = new FileProcessor("/Users/jasmeetkaur/Desktop/input.txt");

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

			int count = 1;
			String returnWord;
			while ((returnWord = fileprocessor.poll()) != null) {
				wordrotate.WordRotator(returnWord, count);
				if (returnWord.contains("."))
					count = 1;
				else
					count = count + 1;
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("File not found");}
		catch (IOException e) {
			System.out.println("IO Exception");
		}

		/*try {
			File myObj = new File("wordPlay/src/output.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
		}*/

	}
}

