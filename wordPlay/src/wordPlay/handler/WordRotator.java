package wordPlay.handler;
import wordPlay.driver.Driver;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

public class WordRotator{
    public void WordRotator(String inputStr,int position){
        String outputStr = new String();
        if (inputStr.contains(".")) {
            Character lastchar = inputStr.charAt(inputStr.length() - 1);
            String newInputStr = inputStr.replace(".", "");
            if((newInputStr.length()-1)< position) {
                position = Math.abs(position-newInputStr.length());
            }
            for (int i = newInputStr.length() - position; i < newInputStr.length(); i++) {
                outputStr += newInputStr.charAt(i);
            }
            for (int i = 0; i < newInputStr.length() - position; i++) {
                outputStr += newInputStr.charAt(i);
            }

            if (lastchar.equals('.'))
                outputStr += lastchar;

        } else {
            if((inputStr.length()-1)< position) {
                position = Math.abs(position-inputStr.length());
            }
            for (int i = inputStr.length() - position; i < inputStr.length(); i++) {
                outputStr += inputStr.charAt(i);
            }
            for (int i = 0; i < inputStr.length() - position; i++) {
                outputStr += inputStr.charAt(i);
            }

        }
        Results result = new Results();
        result.writeToFile(outputStr);
    }
}