package wordPlay.handler;
import wordPlay.driver.Driver;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;

public class WordRotator{
    public void WordRotator(String inputStr,int position){
        String outputStr = new String();
        for(int i = inputStr.length()-position; i < inputStr.length(); i++){
            outputStr += inputStr.charAt(i);
        }
        for(int i = 0; i < inputStr.length()-position; i++){
            outputStr += inputStr.charAt(i);
        }
        Results result = new Results();
        result.writeToFile(outputStr);
    }
}