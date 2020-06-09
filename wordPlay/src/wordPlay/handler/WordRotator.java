package wordPlay.handler;
import wordPlay.driver.Driver;
/*import wordPlay.util.FileProcessor*/
import wordPlay.util.Results;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WordRotator{
    ArrayList<String> outputArray = new ArrayList<String>();

    /***
     * This method takes the input string and rotates it based on its reqired position.
     * @param inputStr
     * @param position
     */
    public void WordRotator(String inputStr,int position){
        String outputStr = new String();
        if (inputStr.contains("."))  //checking for the period
        {
            Character lastchar = inputStr.charAt(inputStr.length() - 1);
            String newInputStr = inputStr.replace(".", ""); // removing the period from the string
            if (newInputStr == "") {
                outputStr = ".\n";
            } else {
                while ((newInputStr.length() - 1) < position) {
                    position = Math.abs(position - newInputStr.length());
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

            }
        }
        else {
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
            Pattern p = Pattern.compile("[.]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(s);
            boolean check = m.find();
            if(check){
            strbuff.append(s);
            }else{
                strbuff.append(s);
                strbuff.append(" ");
            }
        }
         String str = strbuff.toString();
        Driver.resultmap.put(1,str);
    }

}