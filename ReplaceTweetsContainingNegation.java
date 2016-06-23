import com.sun.xml.internal.stream.util.ThreadLocalBufferAllocator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Jean on 20.06.2016.
 */


public class ReplaceTweetsContainingNegation  {
    List<String> lines = new ArrayList<String>();
    String line = null;
    String[] negationalTerms = {"not","nothing", "None",
            "No one",
            "Nobody",
            "Nothing",
            "Neither",
            "Nowhere",
            "Never",
    "Doesn't",
            "doesn't",
                "n't"};

    public void  doIt() {
        try {
            //File f1 = new File("d:/new folder/t1.htm");
            //File f1 = new File("C:/Users/Jean/Desktop/Computational_Linguistics_Teamlab_NLP/corpus_without_negations/toprocess/training");
            File f1 = new File("C:/Users/Jean/Desktop/try/2016-03-31_01-at-2016-04-08-16-14-emotion.csv");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            /** while ((line = br.readLine()) != null) {
                if (line.contains("java"))
                    line = line.replace("java", " ");
                lines.add(line);
            } **/
            List<String> content = Files.readAllLines(Paths.get(f1.getPath()), StandardCharsets.UTF_8);
            for (int i = 0; i < content.size(); i++) {

                String[] x = content.get(i).split("\t");
                //if (x.length <= 8) continue;
                if (x.length <= 8) {
                    try {
                        System.out.println("--------------------------CONTINUE--------------------------");
                        System.out.println(x[x.length]);
                        System.out.println("--------------------------CONTINUE--------------------------");
                        //continue;
                    } catch (ArrayIndexOutOfBoundsException a) {
                        System.out.println("#########OUT OF BOUNDS m8 " + a.getMessage() + " # OF CLASS:" + a.getClass());
                        continue;
                    }
                }

                //if (x[8].contains("not")) {
                /**if (Arrays.asList(negationalTerms).contains("not")) {
                 System.out.println(x[8]);
                 Thread.sleep(500);
                 } **/
                for (int j = 0; j < negationalTerms.length; j++) {

                    // EPIC LOWERCASE OH YEAH
                    if (x[8].toLowerCase().contains(negationalTerms[j].toLowerCase())) {
                        //System.out.println("NEGATION TERM: " + negationalTerms[j]);
                        System.out.println(x[8]);
                        //Thread.sleep(500);
                    }
                    /**for (String string : negationalTerms){
                        System.out.println(",,,,,,,,,,,,,,,,,,,,,NEGATIONTERM STRING: " + string);
                        if (string.equalsIgnoreCase(x[8])){
                            System.out.println("++++++++++++++++MATCHES WOT");;
                        }
                    }**/
                }

                //System.out.println("0: " + x[8]);





            }
            fr.close();
            br.close();

           /** FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for(String s : lines)
                out.write(s);
            out.flush();
            out.close(); **/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ReplaceTweetsContainingNegation fr = new ReplaceTweetsContainingNegation();
        fr.doIt();
    }
}

