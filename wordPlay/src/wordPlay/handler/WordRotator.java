package wordPlay.handler;
import wordPlay.driver.Driver;
/*import wordPlay.util.FileProcessor*/;
import wordPlay.util.Results;
import java.util.ArrayList;
import java.util.List;

public class WordRotator{
    ArrayList<String> outputArray = new ArrayList<String>();
    public void WordRotator(String inputStr,int position){
        String outputStr = new String();
        if (inputStr.contains("."))  //checking for the period
        {
            Character lastchar = inputStr.charAt(inputStr.length() - 1);
            String newInputStr = inputStr.replace(".",""); // removing the period from the string
            while((newInputStr.length()-1)< position) {
                position = Math.abs(position-newInputStr.length());
            }
            for (int i = newInputStr.length() - position; i < newInputStr.length(); i++) {
                outputStr += newInputStr.charAt(i);
            }
            for (int i = 0; i < newInputStr.length() - position; i++) {
                outputStr += newInputStr.charAt(i);
            }

            if (lastchar.equals('.')) //adding the period on it position.
            {
                outputStr += lastchar + "\n";
                //outputStr += "\n";
            }


        } else {
            while ((inputStr.length()-1)< position) {
                position = Math.abs(position-inputStr.length());
            }
            for (int i = inputStr.length() - position; i < inputStr.length(); i++) {
                outputStr += inputStr.charAt(i);
            }
            for (int i = 0; i < inputStr.length() - position; i++) {
                outputStr += inputStr.charAt(i);

            }
        }

        outputArray.add(outputStr);
        StringBuffer strbuff = new StringBuffer();

        for (String s : outputArray) {
            strbuff.append(s);
            strbuff.append(" ");
        }
         String str = strbuff.toString();
        Driver.resultmap.put(1,str);
    }

}