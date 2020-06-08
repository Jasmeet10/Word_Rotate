package wordPlay.metrics;
import wordPlay.driver.Driver;
import wordPlay.util.FileProcessor;
import wordPlay.util.Results;


public class MetricsCalculator{
    public String avgwordperline;
    public String avgwordlen;

    /***
     * This method calculates both the metrices.
     * @param totalNumofWords
     * @param totalNumofChar
     * @param lineNum
     */
    public void MetricsCalculator(int totalNumofWords ,int totalNumofChar, int lineNum){
        if(lineNum !=0) {
               float AvgNumWordperline = ((float)totalNumofWords / (float)lineNum);
                 avgwordperline = String.format("%.2f",(AvgNumWordperline));
            }
            if(totalNumofWords != 0) {
               float AvgWordLength =(float)totalNumofChar / (float)totalNumofWords;
                avgwordlen = String.format("%.2f",(AvgWordLength));
            }
        Driver.resultmap.put(2,avgwordperline);
        Driver.resultmap.put(3,avgwordlen);

    }


}
