package wordPlay.util;
import wordPlay.driver.Driver;
import java.util.ArrayList;
import  java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.HashMap;


public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    /***
     * This method is the implementation of the interface of FileDisplayInterface.
     */
    public void writeToFile() {
       try {
            File file = new File("/Users/tejas/OneDrive/Desktop/csx42-summer-2020-assign1-Jasmeet10 _V3/wordPlay/output.txt");
            if(file.createNewFile());
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(Driver.resultmap.get(1));
                fileWriter.flush();
                fileWriter.close();


        }catch(IOException e){
            System.out.println("An error occurred.");
        }
       try{
       File file = new File("/Users/tejas/OneDrive/Desktop/csx42-summer-2020-assign1-Jasmeet10 _V3/wordPlay/metrics.txt");
            if(file.createNewFile());
            FileWriter fileWriter = new FileWriter(file);
                fileWriter.write("AVG_NUM_WORDS_PER_SENTENCE -");
                fileWriter.write(Driver.resultmap.get(2));
                fileWriter.write("\nAVG_WORD_LENGTH - ");
                fileWriter.write(Driver.resultmap.get(3));
                fileWriter.flush();
                fileWriter.close();
       }
       catch(IOException e){System.out.println("An error occurred.");}
    }
    /***
     * This method is the implementation of the interface of StdoutDisplayInterface.
     */
    public void writeToStdout(){
        System.out.println(Driver.resultmap.get(1));
        System.out.printf("AVG_NUM_WORDS_PER_SENTENCE -"+ Driver.resultmap.get(2));
        System.out.printf("\nAVG_WORD_LENGTH - "+Driver.resultmap.get(3));

    }

}
